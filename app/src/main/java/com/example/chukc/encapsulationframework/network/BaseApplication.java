package com.example.chukc.encapsulationframework.network;

import android.app.Application;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.util.LruCache;
import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class BaseApplication extends Application {


    /**
     * 获取默认的Volley错误处理程序
     */
    @SuppressWarnings("unchecked")
    public <T> Response.ErrorListener<T> getDefaultErrorListenerT() {
        return (Response.ErrorListener<T>) defaultErrorListener;
    }



    /**
     * Volley的请求队列
     */
    protected RequestQueue mRequestQueue;
    /**
     * Volley的图片加载器
     */
    protected ImageLoader defaultImgLoader;
    /**
     * Volley默认的错误处理程序
     */
    protected Response.ErrorListener<?> defaultErrorListener = new Response.ErrorListener<Object>() {
        @Override
        public void onErrorResponse(VolleyError error, Object historyCache) {
            if (error instanceof NetworkError) {
//                new ToastShow(getApplicationContext(), "您的网络连接不稳定").show();
            }
            if (error != null) {
                error.printStackTrace();
                error.toString();
            }

        }
    };

    /**
     * Volley用于全局的图片内存缓存
     */
    protected final LruCache<String, Bitmap> imageCache = new LruCache<String, Bitmap>((int) (Runtime.getRuntime()
            .maxMemory() / 1024 / 8)) {
        @Override
        protected int sizeOf(String key, Bitmap bitmap) {
            // 重写此方法来衡量每张图片的大小，默认返回图片数量。
            int size = 0;
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
                size = bitmap.getRowBytes() * bitmap.getHeight() / 1024;
            } else {
                size = bitmap.getByteCount() / 1024;
            }
            Log.d("LruCache", size + "/" + imageCache.maxSize() + "/" + imageCache.size() + " maxsize:"
                    + Runtime.getRuntime().maxMemory() / 1024 / 8);
            return size;
        }

        ;
    };

    /**
     * Volley获取用于网络请求的队列
     */
    public RequestQueue getRequestQueue() {
        return mRequestQueue;
    }

    /**
     * Volley获取用于加载图片的加载器
     */
    public ImageLoader getDefaultImgLoader() {
        return defaultImgLoader;
    }

    /**
     * 初始化Volley
     */
    public void initVolley() {
        // 初始化Volley通讯
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        // 加载默认的ImageLoader
        defaultImgLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                Log.d("LruCache", "put:" + url);
                imageCache.put(url, bitmap);
            }

            @Override
            public Bitmap getBitmap(String url) {
                Bitmap img = imageCache.get(url);
                Log.d("LruCache", "get:" + url + "  \r\ncache:" + (img != null));
                return img;
            }
        });
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initVolley();
    }
}