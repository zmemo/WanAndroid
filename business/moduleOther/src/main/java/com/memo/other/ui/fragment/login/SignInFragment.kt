package com.memo.other.ui.fragment.login

import com.memo.base.ui.fragment.BaseFragment
import com.memo.other.R
import com.memo.other.ui.activity.login.LoginActivity
import com.memo.tool.ext.gone
import com.memo.tool.ext.onClick
import com.memo.tool.ext.value
import com.memo.tool.ext.visible
import kotlinx.android.synthetic.main.fragment_sign_in.*


/**
 * title:登陆界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 15:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SignInFragment : BaseFragment() {

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_sign_in

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        mTvSignIn.onClick {
            mProgress.visible()
            mTvSignIn.isEnabled = false
            (mActivity as LoginActivity).login(mEtAccount.value, mEtPwd.value)
        }
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {}

    fun finishLogin() {
        mTvSignIn.isEnabled = true
        mProgress.gone()
    }
}
