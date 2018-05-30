package com.zy.zytools.utils.http;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * desc:
 * author：zy
 * date:2018/5/8
 * time:15:08
 */

public abstract class HttpCallBack<T> {
    public final Class cls;

    /**
     * 访问网络回调
     * @param <T> ： 目标实例对象
     */
    public <T> HttpCallBack(){
        //为了得到T的Class，采用如下方法
        //1得到该泛型类的子类对象的Class对象
        Class clz = this.getClass();
        //2得到子类对象的泛型父类类型（也就是BaseDaoImpl<T>）
        ParameterizedType type = (ParameterizedType) clz.getGenericSuperclass();
        Type[] types = type.getActualTypeArguments();
        this.cls = (Class<T>) types[0];
    }

    public abstract void onSuccess(T t);

    public abstract void onError(int code, String msg);
}
