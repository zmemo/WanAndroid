package com.memo.other.ui.activity.login

import com.memo.base.manager.retrofit.execute
import com.memo.base.manager.user.UserManager
import com.memo.base.ui.mvp.BasePresenter
import com.memo.tool.helper.toast

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
        if (account.isEmpty()) {
            toast("请输入账号")
            return
        }
        if (pwd.isEmpty()) {
            toast("请输入密码")
            return
        }
        mModel.login(account, pwd)
            .doOnNext { UserManager.get().setUser(it) }
            .execute(mView, {
                mView.loginSuccess()
            }, {
                mView.loginError()
            })
    }

    fun register(account: String, pwd: String, rePwd: String) {
        if (account.isEmpty()) {
            toast("请输入账号")
            return
        }
        if (pwd.isEmpty()) {
            toast("请输入密码")
            return
        }
        if (rePwd.isEmpty()) {
            toast("请重新输入密码")
            return
        }
        if (pwd != rePwd) {
            toast("两次输入的密码不一致")
        }
        mModel.register(account, pwd)
            .execute(mView, {
                login(account, pwd)
            })
    }


}