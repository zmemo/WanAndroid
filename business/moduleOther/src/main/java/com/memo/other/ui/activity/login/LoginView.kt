package com.memo.other.ui.activity.login

import com.memo.base.ui.mvp.IView

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-10-15 01:12
 *
 * Talk is cheap, Show me the code.
 */
interface LoginView : IView {
    /**
     * 登陆成功
     */
    fun loginSuccess()

    /**
     * 失败
     */
    fun onError()
}