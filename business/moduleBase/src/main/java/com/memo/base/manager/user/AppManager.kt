package com.memo.base.manager.user

import com.memo.base.manager.data.DataManager
import com.memo.base.manager.router.RouterManager


/**
 * title:App管理
 * describe:
 *
 * @author zhou
 * @date 2019-10-12 11:11
 *
 * Talk is cheap, Show me the code.
 */
class AppManager {

    private object Holder {
        val instance = AppManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    fun exit() {
        DataManager.get().removeCookie()
        DataManager.get().removeUser()
        DataManager.get().removeSearchHistory()
        RouterManager.get().launcherLoginActivity()
    }

}