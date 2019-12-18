package com.memo.other.ui.activity.login

import com.memo.base.entity.remote.ArgCache
import com.memo.base.entity.remote.User
import com.memo.base.manager.data.DataManager
import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter
import com.memo.tool.helper.EncryptHelper

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-10-15 01:12
 *
 * Talk is cheap, Show me the code.
 */
class LoginPresenter : BasePresenter<LoginModel, LoginView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): LoginModel = LoginModel()

    fun login(account: String, pwd: String) {
        mModel.login(account, pwd)
            .doOnNext {
                val builder: StringBuilder = StringBuilder()
                it.collectIds.forEachIndexed { index, id ->
                    if (index == 0) {
                        builder.append(id)
                    } else {
                        builder.append(",").append(id)
                    }
                }
                val user = User(
                    builder.toString(), it.email, it.icon,
                    it.id, it.nickname, it.username
                )
                DataManager.get().putUser(user)
                DataManager.get().putArg(ArgCache(EncryptHelper.encryptRsa(account), EncryptHelper.encryptRsa(pwd)))
            }
            .execute(mView, {
                mView.loginSuccess()
            }, {
                mView.onError()
            })
    }

    fun register(account: String, pwd: String) {
        mModel.register(account, pwd)
            .execute(mView, {
                login(account, pwd)
            }, {
                mView.onError()
            })
    }


}