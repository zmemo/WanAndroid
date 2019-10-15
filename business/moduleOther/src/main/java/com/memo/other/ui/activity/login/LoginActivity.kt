package com.memo.other.ui.activity.login

import android.graphics.Color
import androidx.fragment.app.Fragment
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.AppUtils
import com.flyco.tablayout.listener.OnTabSelectListener
import com.memo.base.manager.data.DataManager
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

    /*** 进行初始化操作 ***/
    override fun initialize() {
        initData()
        initView()
        initListener()
    }

    private fun initData() {
        // 清除用户Cookie
        DataManager.get().removeCookie()
        // 关闭其他界面
        ActivityUtils.finishAllActivitiesExceptNewest()

    }

    private fun initView() {
        mTabLayout.setTabData(mTitles)
        mAdapter.setData(mFragments)
        mViewPager.adapter = mAdapter
    }

    private fun initListener() {
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {}
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }
        })
    }

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
        startActivity<MainActivity>()
        finish()
    }

    /**
     * 登陆失败
     */
    override fun loginError() {
        if (mViewPager.currentItem == 0) {
            mSignInFragment.finishLogin()
        } else {
            mSignUpFragment.finishRegister()
        }
    }

    override fun onBackPressed() {
        AppUtils.exitApp()
    }

}
