package com.zy.zytools;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * desc:
 * author：zy
 * date:2018/5/8
 * time:14:05
 */

public class DemoAdapter extends BaseQuickAdapter<String,DemoAdapter.MyHolder> {
    public DemoAdapter(@Nullable List<String> data) {
        super(R.layout.adapter_demo, data);
    }

    @Override
    protected void convert(MyHolder helper, String item) {
        helper.mText.setText(item);
    }

    public static class MyHolder extends BaseViewHolder{

        private final TextView mText;

        public MyHolder(View view) {
            super(view);
            mText = (TextView) view.findViewById(R.id.text);
        }
    }
}
