package com.memo.base.manager.retrofit

import com.franmontiel.persistentcookiejar.PersistentCookieJar
import com.franmontiel.persistentcookiejar.cache.SetCookieCache
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor
import com.memo.base.config.config.Config
import com.memo.base.config.constant.Constant
import com.memo.tool.app.BaseApp
import com.memo.tool.helper.GsonHelper
import com.memo.tool.http.interceptor.HttpLogInterceptor
import me.jessyan.retrofiturlmanager.RetrofitUrlManager
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * title:网络请求管理
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 18:09
 */
class RetrofitClient private constructor() {

    private var mRetrofit: Retrofit

    companion object {
        private val instance: RetrofitClient by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            RetrofitClient()
        }

        fun <T> create(service: Class<T>): T = instance.mRetrofit.create(service)
    }

    init {
        val mOkHttpClient = RetrofitUrlManager.getInstance()
            .with(OkHttpClient.Builder())
            .addInterceptor(HttpLogInterceptor(Config.isOpenLog, "HTTP"))
            .readTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .cookieJar(PersistentCookieJar(SetCookieCache(), SharedPrefsCookiePersistor(BaseApp.app)))
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(Constant.Api.BASE_URL)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.getGson()))
            .client(mOkHttpClient)
            .build()

    }
}