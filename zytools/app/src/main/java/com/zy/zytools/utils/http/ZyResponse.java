package com.zy.zytools.utils.http;

import java.io.Serializable;

/**
 * desc:
 * author：zy
 * date:2018/5/8
 * time:15:36
 */

public class ZyResponse<T> implements Serializable {

    private static final long serialVersionUID = 5213230387175987834L;

    public int code;
    public String msg;
    public T data;

    @Override
    public String toString() {
        return "zyResponse{\n" +//
                "\tcode=" + code + "\n" +//
                "\tmsg='" + msg + "\'\n" +//
                "\tdata=" + data + "\n" +//
                '}';
    }
}
