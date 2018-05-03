package com.planet.emily.elite.util;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.text.TextUtils;

import com.planet.emily.elite.app.MyApplication;
import com.planet.emily.elite.view.RotateBitmap;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public final class PhotoHelper {

    public static final int REQUEST_TAKE_PHOTO = 6701;
    public static final int REQUEST_CHOOSE_PHOTO = 6702;
    public static final int IMPORT_FROM_FACEBOOK = 6703;
    public static final int TAKE_REAL_SHOT = 6704;
    public static final String TAKE_PHOTO_PATH = "TAKE_PHOTO_PATH";

    private static final String TAG = "PhotoHelper ";
    private static final String SCHEME_FILE = "file";
    private static final String SCHEME_CONTENT = "content";
    private String photoPath;//拍摄照片的文件名 万一photoHelper变量重新创建了 ，但是拍照流程没有完全走成功，这个变量可能为NULL，就会有问题，所以改成静态变量
    private Context mContext;

    public PhotoHelper() {
        this.mContext = MyApplication.getInstances();
    }

    //compress the jpg
    public static byte[] compressFile(String filePath, int targetWidth, int targetHeight) {
        Bitmap bmp = compressPhoto(filePath, targetWidth, targetHeight);
        if (bmp == null) {
            return null;
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 90, bos);
        byte[] compressByte = bos.toByteArray();
        return compressByte;
    }

    public static byte[] bitmap2Bytes(Bitmap bitmap, int quality) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, quality, stream);
        byte[] byteArray = stream.toByteArray();
        return byteArray;
    }

    /**
     * 压缩照片，压缩的同时还要注意 三星手机会旋转照片90°的问题，所以最后要检查是否旋转了
     *
     * @param filePath
     * @param targetWidth
     * @param targetHeight
     * @return
     */
    public static Bitmap compressPhoto(String filePath, int targetWidth, int targetHeight) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = calculateInSampleSize(options, targetWidth, targetHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        Bitmap compressBitmap = BitmapFactory.decodeFile(filePath, options);
        if (compressBitmap == null) {
            return null;
        }
        int exifRotation = getExifRotation(new File(filePath));
        Bitmap rotateBitmap = new RotateBitmap(compressBitmap, exifRotation).getRotateMatrixBitmap();
        return rotateBitmap;
    }

    private static int calculateInSampleSize(BitmapFactory.Options options,
                                             int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            // Calculate ratios of height and width to requested height and
            // width
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);

            // Choose the smallest ratio as inSampleSize value, this will
            // guarantee
            // a final image with both dimensions larger than or equal to the
            // requested height and width.
            inSampleSize = heightRatio < widthRatio ? widthRatio : heightRatio;
        }

        return inSampleSize;
    }

    /**
     * 三星手机拍照的话 默认会旋转90°的 ,所以这个方法是检查是否图片被旋转了
     *
     * @param imageFile
     * @return
     */
    public static int getExifRotation(File imageFile) {
        if (imageFile == null) return 0;
        try {
            ExifInterface exif = new ExifInterface(imageFile.getAbsolutePath());
            // We only recognize a subset of orientation tag values
            switch (exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED)) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    return 90;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    return 180;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    return 270;
                default:
                    return ExifInterface.ORIENTATION_UNDEFINED;
            }
        } catch (IOException e) {

            return 0;
        }
    }

    public String getPathFromMediaUri(Uri uri, Context context) {
        String photoPath = null;
        if (uri == null) {
            return photoPath;
        }
        if (SCHEME_FILE.equals(uri.getScheme())) {
            photoPath = uri.getPath();
        } else if (SCHEME_CONTENT.equals(uri.getScheme())) {
            photoPath = getPath(context, uri);
        }
        return photoPath;
    }

    /**
     * Get a file path from a Uri. This will get the the path for Storage Access
     * Framework Documents, as well as the _data field for the MediaStore and
     * other file-based ContentProviders.
     *
     * @param context The context.
     * @param uri     The Uri to query.
     * @author paulburke
     */
    @SuppressLint("NewApi")
    public static String getPath(final Context context, final Uri uri) {
        if (uri == null) {
            return null;
        }

        final boolean isKitKat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;

        // DocumentProvider
        if (isKitKat && DocumentsContract.isDocumentUri(context, uri)) {
            // ExternalStorageProvider
            if (isExternalStorageDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                if ("primary".equalsIgnoreCase(type)) {
                    return Environment.getExternalStorageDirectory() + "/" + split[1];
                }

                // TODO handle non-primary volumes
            }
            // DownloadsProvider
            else if (isDownloadsDocument(uri)) {

                final String id = DocumentsContract.getDocumentId(uri);
                final Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));

                return getDataColumn(context, contentUri, null, null);
            }
            // MediaProvider
            else if (isMediaDocument(uri)) {
                final String docId = DocumentsContract.getDocumentId(uri);
                final String[] split = docId.split(":");
                final String type = split[0];

                Uri contentUri = null;
                if ("image".equals(type)) {
                    contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                } else if ("video".equals(type)) {
                    contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                } else if ("audio".equals(type)) {
                    contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                }

                final String selection = "_id=?";
                final String[] selectionArgs = new String[]{
                        split[1]
                };

                return getDataColumn(context, contentUri, selection, selectionArgs);
            }
        }
        // MediaStore (and general)
        else if ("content".equalsIgnoreCase(uri.getScheme())) {
            String photoPath = getDataColumn(context, uri, null, null);
            if (TextUtils.isEmpty(photoPath)) {
                photoPath = copyPhotoFromContent(uri, context);// photo may be come from dropbox , google drive
            }
            if (TextUtils.isEmpty(photoPath)) { // still can't get photo path , like can't import image from OneDriver

            }
            return photoPath;
        }
        // File
        else if ("file".equalsIgnoreCase(uri.getScheme())) {
            return uri.getPath();
        }

        return null;
    }

    private static String copyPhotoFromContent(Uri uri, Context context) {
        try {
            ParcelFileDescriptor parcelFileDescriptor = context
                    .getContentResolver().openFileDescriptor(uri,
                            "r");
            FileDescriptor fileDescriptor = parcelFileDescriptor
                    .getFileDescriptor();
            if (fileDescriptor == null) {
                return null;
            }
            File photoFile = createImageFile(context);
            String photoPath = photoFile.getAbsolutePath();
            BufferedInputStream inputStream = new BufferedInputStream(new FileInputStream(fileDescriptor));
            IOUtils.copy(inputStream, new BufferedOutputStream(new FileOutputStream(photoPath)));
            return photoPath;
        } catch (Exception e) {
            e.printStackTrace();
            if (e != null && !TextUtils.isEmpty(e.getMessage())) {
            }
        }
        return null;
    }

    /**
     * Get the value of the data column for this Uri. This is useful for
     * MediaStore Uris, and other file-based ContentProviders.
     *
     * @param context       The context.
     * @param uri           The Uri to query.
     * @param selection     (Optional) Filter used in the query.
     * @param selectionArgs (Optional) Selection arguments used in the query.
     * @return The value of the _data column, which is typically a file path.
     */
    public static String getDataColumn(Context context, Uri uri, String selection,
                                       String[] selectionArgs) {

        Cursor cursor = null;
        final String column = MediaStore.MediaColumns.DATA;
        final String[] projection = {
                column
        };

        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs,
                    null);
            if (cursor != null && cursor.moveToFirst()) {
                final int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e != null && !TextUtils.isEmpty(e.getMessage())) {
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }


    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is ExternalStorageProvider.
     */
    public static boolean isExternalStorageDocument(Uri uri) {
        return "com.android.externalstorage.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is DownloadsProvider.
     */
    public static boolean isDownloadsDocument(Uri uri) {
        return "com.android.providers.downloads.documents".equals(uri.getAuthority());
    }

    /**
     * @param uri The Uri to check.
     * @return Whether the Uri authority is MediaProvider.
     */
    public static boolean isMediaDocument(Uri uri) {
        return "com.android.providers.media.documents".equals(uri.getAuthority());
    }

    public Intent getChoosePhotoIntent() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT).setType("image/*");
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true);
        try {
            return intent;
        } catch (ActivityNotFoundException e) {
            return null;
        }
    }

    public Intent getTakePhotoIntent() {
        if (!isIntentAvailable(mContext, MediaStore.ACTION_IMAGE_CAPTURE)) {
            return null;
        }
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(mContext.getPackageManager()) == null) {//检查有没有能处理这个intent的Activity,非常有必要，不检查有时候会Crash
            return null;
        }
        File photoFile = null;
        try {
            photoFile = createImageFile(mContext);
            // Save a file: path for use with ACTION_VIEW intents
            photoPath = photoFile.getAbsolutePath();
        } catch (IOException e) {
            photoPath = null;
        }
        // Continue only if the File was successfully created
        if (photoFile != null) {
            Uri photoURI;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                photoURI = FileProvider.getUriForFile(mContext,
                        "com.example.asd.fileprovider",
                        photoFile);
                takePictureIntent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            } else {
                photoURI = Uri.fromFile(photoFile);
            }
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
        }
        return takePictureIntent;
    }

    public static File createImageFile(Context context) throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        return image;
    }

    /**
     * Indicates whether the specified action can be used as an intent. This
     * method queries the package manager for installed packages that can
     * respond to an intent with the specified action. If no suitable package is
     * found, this method returns false.
     * http://android-developers.blogspot.com/2009/01/can-i-use-this-intent.html
     *
     * @param context The application's environment.
     * @param action  The Intent action to check for availability.
     * @return True if an Intent with the specified action can be sent and
     * responded to, false otherwise.
     */
    private static boolean isIntentAvailable(Context context, String action) {
        final PackageManager packageManager = context.getPackageManager();
        final Intent intent = new Intent(action);
        List<ResolveInfo> list =
                packageManager.queryIntentActivities(intent,
                        PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }

    public String getPhotoPath() {
        return photoPath;
    }
}
