package com.planet.emily.elite.com.emily.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.planet.emily.elite.R;
import com.planet.emily.elite.util.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.planet.emily.elite.util.DataCleanManager.getFolderSize;
import static com.planet.emily.elite.util.DataCleanManager.getFormatSize;

public class SetActivity extends AppCompatActivity {

    @BindView(R.id.tv_cache_size)
    TextView tv_cache_size;

    @BindView(R.id.tv_phone)
    TextView tv_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        ButterKnife.bind(this);
        initUI();

    }

    private void initUI() {
        tv_cache_size.setText(getTotalCacheSize(this));
        getUserData();


    }

    private void getUserData() {
        SharedPreferences preferences = getSharedPreferences("UserInformation", Context.MODE_PRIVATE);
        String number = preferences.getString("number", "");
        tv_phone.setText(number);


    }

    @OnClick(R.id.ly_setting)
    public void clickSetting() {

    }


    @OnClick(R.id.ly_set_clean)
    public void clickClean() {
        DataCleanManager.clearAllCache(this);
        tv_cache_size.setText(getTotalCacheSize(this));

    }

    @OnClick(R.id.ly_set_version)
    public void clickVersion() {

    }

    @OnClick(R.id.ly_set_origin)
    public void clickOrigin() {
        Intent intent = new Intent(SetActivity.this, OriginActivity.class);
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.btn_logout)
    public void clickLogout(){

    }

    @OnClick(R.id.tv_back_set)
    public void clickBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    /**
     * 获取所有缓存的大小
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context)  {

        try {
            //获取内部缓存大小
            long cacheSize = getFolderSize(context.getCacheDir());
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                //如果有外置sd卡,就加上外部缓存大小
                cacheSize += getFolderSize(context.getExternalCacheDir());
            }
            return getFormatSize(cacheSize);
        }catch (Exception e){

        }

        return "";
    }

}
