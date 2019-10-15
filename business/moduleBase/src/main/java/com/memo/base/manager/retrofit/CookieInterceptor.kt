package com.memo.base.manager.retrofit

import com.memo.base.manager.data.DataManager
import okhttp3.Interceptor
import okhttp3.Response

/**
 * title:Cookie存储与添加
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
    private val COOKIE = "COOKIE"

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(request)
        val builder = request.newBuilder()
        val url = request.url().toString()
        if (url.contains(COOKIE_TAG_LOGIN_URL) && response.headers(SET_COOKIE).isNotEmpty()) {
            // 存储
            val cookies = response.headers(SET_COOKIE)
            saveCookies(cookies)
        } else {
            // 添加
            val cookie = DataManager.get().getCookie()
            if (cookie.isNotEmpty()) {
                builder.addHeader(COOKIE, cookie)
            }
        }
        return response
    }

    private fun saveCookies(cookies: MutableList<String>) {
        val builder = StringBuilder()
        val set = HashSet<String>()
        cookies.map { cookie ->
            cookie.split(";".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        }.forEach { cookie ->
            cookie.filterNot { set.contains(it) }.forEach { set.add(it) }
        }
        set.forEach {
            builder.append(it).append(";")
        }
        val cookie = builder.toString()
        DataManager.get().putCookie(cookie)
    }

}