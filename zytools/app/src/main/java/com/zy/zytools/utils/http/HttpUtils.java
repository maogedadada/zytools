package com.zy.zytools.utils.http;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okrx2.adapter.ObservableResponse;
import com.zy.zytools.utils.Utils;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * desc:
 * author：zy
 * date:2018/5/8
 * time:14:47
 */

public class HttpUtils {
    private CompositeDisposable compositeDisposable;

    public <T> void requestPost(String url, Map<String, String> map2, final HttpCallBack<T> callBack) {
        OkGo.<String>post(url)
                .params(map2)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        if (response.code() != 200) {
                            callBack.onError(response.code(), response.message());
                            return;
                        }
                        try {
                            T t = (T) new Gson().fromJson(response.body(), callBack.cls);
                            callBack.onSuccess(t);
                        } catch (Exception e) {
                            callBack.onError(0, e.toString());
                            Utils.showLog(e.toString());
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onError(0, e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    public <T> void requestGet(String url, Map<String, String> map2, final HttpCallBack<T> callBack) {
        OkGo.<String>get(url)
                .params(map2)
                .converter(new StringConvert())
                .adapt(new ObservableResponse<String>())
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<String>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        addDisposable(d);
                    }

                    @Override
                    public void onNext(@NonNull Response<String> response) {
                        if (response.code() != 200) {
                            callBack.onError(response.code(), response.message());
                            return;
                        }
                        try {
                            T t = (T) new Gson().fromJson(response.body(), callBack.cls);
                            callBack.onSuccess(t);
                        } catch (Exception e) {
                            callBack.onError(0, e.toString());
                            Utils.showLog(e.toString());
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        callBack.onError(0, e.toString());
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }
    public void addDisposable(Disposable disposable) {
        if (compositeDisposable == null) {
            compositeDisposable = new CompositeDisposable();
        }
        compositeDisposable.add(disposable);
    }

    //取消网络请求
    public void dispose() {
        if (compositeDisposable != null) compositeDisposable.dispose();
    }
}
