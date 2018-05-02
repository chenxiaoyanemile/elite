package com.planet.emily.elite.com.emily.planet;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.planet.emily.elite.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateNextStepActivity extends AppCompatActivity {

    @BindView(R.id.cv_planet_avatar)
    CircleImageView planetAvatar;
    @BindView(R.id.et_planet_name)
    EditText planetName;
    @BindView(R.id.et_description)
    EditText planetDescription;

    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_next_step);
        ButterKnife.bind(this);
    }

    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.rb_study:
                if (checked)
                    type = "科研";
                break;
            case R.id.rb_product:
                if (checked)
                    type = "产品";
                break;
            case R.id.rb_development:
                if (checked)
                    type = "研发";
                break;
            case R.id.rb_algorithm:
                if (checked)
                    type = "算法";
                break;
            case R.id.rb_network:
                if (checked)
                    type = "网络";
                break;
        }
    }

    @OnClick(R.id.tv_create_finish)
    public void createPlanet() {
        String name = planetName.getText().toString().trim();
        String description = planetDescription.getText().toString().trim();

        if (!name.isEmpty() && type != null && !description.isEmpty()) {

            Intent intent = new Intent(CreateNextStepActivity.this, PlanetActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "请填入完整信息！", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.tv_back_home)
    public void onBack() {
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
