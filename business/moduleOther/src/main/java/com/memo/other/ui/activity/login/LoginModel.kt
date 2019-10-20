package com.memo.other.ui.activity.login

import com.memo.base.manager.retrofit.convert
import com.memo.base.manager.retrofit.convertAny
import com.memo.base.ui.mvp.IModel
import com.memo.other.api.mApiService

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-10-15 01:12
 *
 * Talk is cheap, Show me the code.
 */
class LoginModel : IModel {

    fun login(account: String, pwd: String) = mApiService.login(account, pwd).convert()

    fun register(account: String, pwd: String) = mApiService.register(account, pwd, pwd).convertAny()
}