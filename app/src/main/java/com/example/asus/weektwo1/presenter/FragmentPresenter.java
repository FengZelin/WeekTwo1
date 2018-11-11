package com.example.asus.weektwo1.presenter;

import com.example.asus.weektwo1.bean.MyDataBean;
import com.example.asus.weektwo1.inter.ICallBack;
import com.example.asus.weektwo1.model.Model;
import com.example.asus.weektwo1.view.IView;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

public class FragmentPresenter {
    private IView iv;
    private Model mModel;


    public void attch(IView iv){
        this.iv=iv;
        mModel=new Model();
    }

    public void getMyDataPresenter(String url){
        //2.2 定义一个Type的泛型
        Type type = new TypeToken<MyDataBean>(){}.getType();

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
    public void datach(){
        if(iv!=null){
            iv=null;
        }
    }
}
