Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.maogedadada:zytools:1.0.1'
	}

#okhttp+rxjava
- 初始化

``` 

	private void initOkGo() {
        HttpHeaders headers = new HttpHeaders();
        HttpParams params = new HttpParams();
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间
        // 其他统一的配置
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(0)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }


``` 

- 使用

```
   
     mHttpUtils = new HttpUtils();
        mHttpUtils.requestGet("url", null, new HttpCallBack<Object>() {
            @Override
            public void onSuccess(Object o) {

            }

            @Override
            public void onError(int code, String msg) {

            }
        });

	 @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消网络请求
        mHttpUtils.dispose();
    }

```

#EasyRefreshLayout

```
		
  	<com.ajguan.library.EasyRefreshLayout
        android:id="@+id/easylayout"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

	  </com.ajguan.library.EasyRefreshLayout>

    EasyRefreshLayout.addEasyEvent(new EasyRefreshLayout.EasyEvent() {
            @Override
            public void onLoadMore() {

            }

            @Override
            public void onRefreshing() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        List<String> list = new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            list.add("this is refresh data >>>" + new Date().toLocaleString());
                        }
                        mAdapter.setNewData(list);
                        easyRefreshLayout.refreshComplete();
                        Toast.makeText(getApplicationContext(), "refresh success", Toast.LENGTH_SHORT).show();
                    }
                }, 1000);

            }
        });

```

#MagicIndicator

``` 

	    <net.lucode.hackware.magicindicator.MagicIndicator
        android:id="@+id/magic_indicator1"
        android:layout_width="match_parent"
        android:layout_height="45dp"
        />

   	    <android.support.v4.view.ViewPager
        android:id="@+id/view_pager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"/>

		mViewPager = (ViewPager) findViewById(R.id.view_pager);
        mViewPager.setAdapter(mExamplePagerAdapter);

        MagicIndicator magicIndicator = (MagicIndicator) findViewById(R.id.magic_indicator1);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {
            @Override
            public int getCount() {
                return mDataList == null ? 0 : mDataList.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorTransitionPagerTitleView(context);
                simplePagerTitleView.setText(mDataList.get(index));
                simplePagerTitleView.setNormalColor(Color.parseColor("#88000000"));
                simplePagerTitleView.setSelectedColor(Color.BLUE);
                simplePagerTitleView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mViewPager.setCurrentItem(index);
                    }
                });
                return simplePagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setColors(Color.parseColor("#000000"));
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        LinearLayout titleContainer = commonNavigator.getTitleContainer(); // must after setNavigator
        titleContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
        titleContainer.setDividerPadding(UIUtil.dip2px(this, 15));
        titleContainer.setDividerDrawable(getResources().getDrawable(R.drawable.simple_splitter));
        ViewPagerHelper.bind(magicIndicator, mViewPager);
    }
    public class ExamplePagerAdapter extends PagerAdapter {
        private List<String> mDataList;

        public ExamplePagerAdapter(List<String> dataList) {
            mDataList = dataList;
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setText(mDataList.get(position));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(24);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            TextView textView = (TextView) object;
            String text = textView.getText().toString();
            int index = mDataList.indexOf(text);
            if (index >= 0) {
                return index;
            }
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mDataList.get(position);
        }
    }

```

#二维码扫描

```

	 Intent intent = new Intent(MainActivity.this, QrActivity.class);
     intent.putExtra("title","二维码扫描");
     intent.putExtra("text","扫描中。。。");
     startActivityForResult(intent,1000);

	    @Override
	    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        if (requestCode==1000){
	            String result = data.getStringExtra("result");
	            if (result!=null) {
	                Utils.showLog(result);
	            }
	        }
	    }

```

#圆角图片

```

 	   <com.makeramen.roundedimageview.RoundedImageView
        android:layout_width="60dp"
        android:layout_height="60dp"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/imageView1"
        android:src="@mipmap/ic_launcher"
        android:scaleType="fitCenter"
        app:riv_corner_radius="30dip"
        app:riv_border_width="2dip"
        app:riv_border_color="#333333"
        app:riv_mutate_background="true"
        app:riv_tile_mode="repeat"
        app:riv_oval="true" />

```
