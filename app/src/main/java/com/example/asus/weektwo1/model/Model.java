package com.example.asus.weektwo1.model;

import com.example.asus.weektwo1.inter.ICallBack;
import com.example.asus.weektwo1.utils.HttpUtils;

import java.lang.reflect.Type;

public class Model {
    public void login(String url, ICallBack callBack, Type type){
        HttpUtils.getInstance().get(url,callBack,type);
    }
}
