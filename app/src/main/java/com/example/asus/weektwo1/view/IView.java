package com.example.asus.weektwo1.view;


public interface IView<T> {
    void success(T t);
    void failed(Object e);

}
