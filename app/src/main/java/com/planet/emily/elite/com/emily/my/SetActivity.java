package com.planet.emily.elite.com.emily.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.planet.emily.elite.R;
import com.planet.emily.elite.util.DataCleanManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.listener.BmobUpdateListener;
import cn.bmob.v3.update.BmobUpdateAgent;
import cn.bmob.v3.update.UpdateResponse;
import cn.bmob.v3.update.UpdateStatus;

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
        BmobUpdateAgent.setUpdateListener(new BmobUpdateListener() {

            @Override
            public void onUpdateReturned(int updateStatus, UpdateResponse updateInfo) {
                // TODO Auto-generated method stub
                if (updateStatus == UpdateStatus.Yes) {
                    //版本有更新
                    //BmobUpdateAgent.update(this);

                }else if(updateStatus == UpdateStatus.No){
                    Toast.makeText(SetActivity.this, "版本无更新", Toast.LENGTH_SHORT).show();
                }else if(updateStatus==UpdateStatus.EmptyField){//此提示只是提醒开发者关注那些必填项，测试成功后，无需对用户提示
                    Toast.makeText(SetActivity.this, "请检查你AppVersion表的必填项，1、target_size（文件大小）是否填写；2、path或者android_url两者必填其中一项。", Toast.LENGTH_SHORT).show();
                }else if(updateStatus==UpdateStatus.IGNORED){
                    Toast.makeText(SetActivity.this, "该版本已被忽略更新", Toast.LENGTH_SHORT).show();
                }else if(updateStatus==UpdateStatus.ErrorSizeFormat){
                    Toast.makeText(SetActivity.this, "请检查target_size填写的格式，请使用file.length()方法获取apk大小。", Toast.LENGTH_SHORT).show();
                }else if(updateStatus==UpdateStatus.TimeOut){
                    Toast.makeText(SetActivity.this, "查询出错或查询超时", Toast.LENGTH_SHORT).show();
                }
            }
        });
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
