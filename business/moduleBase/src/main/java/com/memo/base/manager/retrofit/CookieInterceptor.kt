package com.memo.base.manager.retrofit

import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.data.DataManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * title:Cookie存储
 * describe:
 *
 * @author zhou
 * @date 2019-10-12 17:51
 *
 * Talk is cheap, Show me the code.
 */
class CookieInterceptor : Interceptor {

    private val COOKIE_TAG_LOGIN_URL = "user/login"
    private val SET_COOKIE = "set-cookie"
    private val COOKIE = "cookie"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val builder = request.newBuilder()
        val url = request.url().toString()
        return if (url.contains(COOKIE_TAG_LOGIN_URL) && response.headers(SET_COOKIE).isNotEmpty()) {
            // 存储
            val cookies = response.headers(SET_COOKIE)
            LogUtils.iTag("Cookie - save", url, cookies)
            saveCookies(cookies)
            response
        } else {
            // 添加
            val cookie = DataManager.get().getCookie()
            if (cookie.isNotEmpty()) {
                builder.addHeader(COOKIE, cookie)
                LogUtils.iTag("Cookie - add", url, cookie)
            }
            chain.proceed(builder.build())
        }
    }

    private fun saveCookies(cookies: MutableList<String>) {
        val builder: StringBuilder = StringBuilder()
        for (cookie in cookies) {
            if (!cookie.endsWith(";")) {
                cookie.plus(";")
            }
            builder.append(cookie)
        }
        val cookie = builder.toString()
        DataManager.get().putCookie(cookie)
    }

}