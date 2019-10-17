package com.memo.other.ui.fragment.login


import com.memo.base.ui.fragment.BaseFragment
import com.memo.other.R
import com.memo.other.ui.activity.login.LoginActivity
import com.memo.tool.ext.gone
import com.memo.tool.ext.onClick
import com.memo.tool.ext.value
import com.memo.tool.ext.visible
import com.memo.tool.helper.toast
import kotlinx.android.synthetic.main.fragment_sign_up.*

/**
 * title:注册界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 15:15
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SignUpFragment : BaseFragment() {
    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_sign_up

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        mTvSignUp.onClick {
            if (mEtAccount.value.isEmpty()) {
                toast("请输入账号")
                return@onClick
            }
            if (mEtPwd.value.isEmpty()) {
                toast("请输入密码")
                return@onClick
            }
            if (mEtRePwd.value.isEmpty()) {
                toast("请重新输入密码")
                return@onClick
            }
            if (mEtPwd.value != mEtRePwd.value) {
                toast("两次输入的密码不一致")
                return@onClick
            }
            mTvSignUp.isEnabled = false
            mProgress.visible()
            (mActivity as LoginActivity).register(mEtAccount.value, mEtPwd.value, mEtRePwd.value)
        }
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
    }

    fun finishRegister() {
        mTvSignUp.isEnabled = true
        mProgress.gone()
    }


}
