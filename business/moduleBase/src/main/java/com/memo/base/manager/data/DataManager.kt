package com.memo.base.manager.data

import com.tencent.mmkv.MMKV

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-29 10:51
 */
class DataManager {

    private val mSaver by lazy { MMKV.defaultMMKV() }

    private val COOKIE = "COOKIE"

    private object Holder {
        val instance = DataManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 存储用户cookie
     * @param cookie String
     */
    fun putCookie(cookie: String) = mSaver.encode(COOKIE, cookie)

    /**
     * 获取用户cookie
     */
    fun getCookie() = mSaver.decodeString(COOKIE, "")

    /**
     * 清除用户Cookie
     */
    fun removeCookie() = mSaver.removeValueForKey(COOKIE)

}