package com.memo.other.ui.activity.login

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.rxbus.RxBus
import com.blankj.utilcode.util.ActivityUtils
import com.flyco.tablayout.listener.OnTabSelectListener
import com.memo.base.entity.event.LoginEvent
import com.memo.base.manager.data.DataManager
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.other.R
import com.memo.other.ui.activity.main.MainActivity
import com.memo.other.ui.fragment.login.SignInFragment
import com.memo.other.ui.fragment.login.SignUpFragment
import com.memo.tool.adapter.BaseFragmentPagerAdapter
import com.memo.tool.ext.startActivity
import kotlinx.android.synthetic.main.activity_login.*

/**
 * title:登录界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 20:54
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Other.LoginActivity)
class LoginActivity : BaseMvpActivity<LoginView, LoginPresenter>(), LoginView {

    /*** Tab 标题 登陆 注册 ***/
    private val mTitles: Array<String> = arrayOf("SIGN IN", "SIGN UP")

    private val mSignInFragment: SignInFragment by lazy { SignInFragment() }
    private val mSignUpFragment: SignUpFragment by lazy { SignUpFragment() }

    private val mFragments: ArrayList<Fragment> = arrayListOf(mSignInFragment, mSignUpFragment)

    private val mAdapter: BaseFragmentPagerAdapter<Fragment> = BaseFragmentPagerAdapter(supportFragmentManager)

    override fun statusBarColor(): Int = Color.parseColor("#9FD2F7")

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): LoginPresenter = LoginPresenter()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_login


    override fun initData() {
        // 清除用户Cookie
        DataManager.get().removeCookie()
    }

    override fun initView() {
        mLoadService.showSuccess()
        mTabLayout.setTabData(mTitles)
        mAdapter.setData(mFragments)
        mViewPager.adapter = mAdapter
    }

    override fun initListener() {
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {}
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }
        })
    }

    override fun start() {}

    /**
     * 登陆
     */
    fun login(account: String, pwd: String) {
        mPresenter.login(account, pwd)
    }

    /**
     * 注册
     */
    fun register(account: String, pwd: String, rePwd: String) {
        mPresenter.register(account, pwd, rePwd)
    }

    /**
     * 登陆成功
     */
    override fun loginSuccess() {
        RxBus.getDefault().post(LoginEvent())
        startActivity<MainActivity>()
        finish()
    }

    /**
     * 登陆失败
     */
    override fun onError() {
        if (mViewPager.currentItem == 0) {
            mSignInFragment.finishLogin()
        } else {
            mSignUpFragment.finishRegister()
        }
    }

    override fun onBackPressed() {
        ActivityUtils.finishAllActivities(true)
    }

}
