package com.example.asus.weektwo1;



import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.asus.weektwo1.bean.Bean;
import com.example.asus.weektwo1.presenter.Presenter;
import com.example.asus.weektwo1.view.IView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IView<Bean> {
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;
    private Button btnZhuce;
    private ViewFlipper mFilpper;
    private Presenter mLoginPresenter;
    private String mUsername;
    private String mPassword;
    private CheckBox mCheckM;
    private CheckBox mCheckD;
    String url="http://www.zhaoapi.cn/user/login";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//       初始化控件
        getinit();
//        点击事件
        setOnClickListener();
        setHorseRaceLamp();
        mLoginPresenter=new Presenter();
        mLoginPresenter.attch(this);
    }

    private void setHorseRaceLamp() {
        for (int i = 1; i <= 3; i++) {
            if(i==1){
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_custom,null);
                mFilpper.addView(view);
            }else if(i==2){
                View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.layout_custom2,null);
                mFilpper.addView(view);

            }
        }
    }

    /**
     * //3 设置点击事件监听
     * */
    private void setOnClickListener() {
        btnLogin.setOnClickListener(this);
        btnZhuce.setOnClickListener(this);
    }




    private void getinit() {
        mFilpper=findViewById(R.id.filpper);
        etUsername = findViewById(R.id.et_username);
        etPassword = findViewById(R.id.et_password);
        //登录注册按钮
        btnLogin = findViewById(R.id.btn_login);
        btnZhuce = findViewById(R.id.btn_zhuce);
    }
    //防止内存泄漏
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mLoginPresenter!=null){
            mLoginPresenter.datach();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                //点击登录  进行数据访问
                mUsername = etUsername.getText().toString().trim();
                mPassword = etPassword.getText().toString();
                if(TextUtils.isEmpty(mUsername) || TextUtils.isEmpty(mPassword)){
                    Toast.makeText(MainActivity.this,"对不起！用户名或者密码不能为空！",Toast.LENGTH_SHORT).show();
                }else {
                    mLoginPresenter.getPresenterData(url, mUsername, mPassword);//调用请求方法
                }
                break;

            case R.id.btn_zhuce:

                break;
        }
    }

    @Override
    public void success(Bean bean) {
        if(bean!=null){
            //得到code值
            String code = bean.getCode();
            if(code.equals("0")){//判断是否登录成功
                //0 代表登录成功  提示
                //进行跳转
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                startActivity(intent);
                Toast.makeText(MainActivity.this,""+bean.getMsg(),Toast.LENGTH_SHORT).show();
            }else{
                //1 代表登录失敗  提示
                Toast.makeText(MainActivity.this,""+bean.getMsg(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void failed(Object e) {
        Toast.makeText(MainActivity.this,""+e,Toast.LENGTH_SHORT).show();
    }
}
