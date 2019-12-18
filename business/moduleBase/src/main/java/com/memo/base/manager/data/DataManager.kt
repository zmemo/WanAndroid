package com.memo.base.manager.data

import com.memo.base.entity.remote.ArgCache
import com.memo.base.entity.remote.User
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

    private object Holder {
        val instance = DataManager()
    }

    companion object {
        private const val COOKIE = "COOKIE"
        private const val USER = "USER"
        private const val HISTORY = "HISTORY"
        private const val ARG = "ARG"

        fun get() = Holder.instance
    }

    /**
     * 存储缓存
     */
    fun putArg(arg: ArgCache) = mSaver.encode(ARG, arg)

    /**
     * 获取缓存
     */
    fun getArg(): ArgCache? = mSaver.decodeParcelable(ARG, ArgCache::class.java)

    /**
     * 移除缓存
     */
    fun removeArg() = mSaver.removeValueForKey(ARG)

    /**
     * 添加用户
     * @param user 用户信息
     */
    fun putUser(user: User) = mSaver.encode(USER, user)

    /**
     * 获取用户
     */
    fun getUser(): User? = mSaver.decodeParcelable(USER, User::class.java)

    /**
     * 移除用户
     */
    fun removeUser() = mSaver.removeValueForKey(USER)

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

    /**
     * 存储搜索记录
     */
    fun putSearchHistory(histories: Set<String>) = mSaver.encode(HISTORY, histories)

    /**
     * 获取搜索记录
     */
    fun getSearchHistory() = mSaver.decodeStringSet(HISTORY) ?: setOf()

    /**
     * 删除搜索记录
     */
    fun removeSearchHistory() = mSaver.remove(HISTORY)

}