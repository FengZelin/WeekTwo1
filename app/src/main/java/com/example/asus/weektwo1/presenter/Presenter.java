package com.example.asus.weektwo1.presenter;

import com.example.asus.weektwo1.bean.Bean;
import com.example.asus.weektwo1.bean.MyDataBean;
import com.example.asus.weektwo1.inter.ICallBack;
import com.example.asus.weektwo1.model.Model;
import com.example.asus.weektwo1.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class Presenter {
        private IView iv;
        private Model mModel;


        public void attch(IView iv){
                this.iv=iv;
                mModel=new Model();
        }
    public void getPresenterData(String url,String username,String password){
        //1 首先得到用户输入的用户名和密码  通过IView层的抽象方法  调用
       /* String passwrod = mIView.getPasswrod();
        String userName = mIView.getUserName();*/

        //1.2 将得到的用户名和密码 进行拼接url
        url = url.concat("?mobile=").concat(username).concat("&password=").concat(password);
        //2.2 定义一个Type的泛型
        Type type = new TypeToken<Bean>(){}.getType();

        //2 通过M层对象,调用请求数据方法
        mModel.login(url, new ICallBack() {
            @Override
            public void onSuccess(Object obj) {
                iv.success(obj);
            }

            @Override
            public void onFailed(Exception e) {
                iv.failed(e);
            }

        }, type);
        }
    //4 定义一个方法  用来判断用户输入的密码和用户名  和  网路请求的用户名和密码是否相同

    //3 解除v 和 m层的关联
    public void datach(){
        if(iv!=null){
            iv=null;
        }
    }
}
