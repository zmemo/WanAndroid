package com.memo.base.manager.user

import com.memo.base.entity.remote.UserInfo


/**
 * title:用户信息管理
 * describe:
 *
 * @author zhou
 * @date 2019-10-12 11:11
 *
 * Talk is cheap, Show me the code.
 */
class UserManager {

    private var userInfo: UserInfo? = null

    private object Holder {
        val instance = UserManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    fun setUser(userInfo: UserInfo) {
        this.userInfo = userInfo
    }

    fun getUser() = this.userInfo

}