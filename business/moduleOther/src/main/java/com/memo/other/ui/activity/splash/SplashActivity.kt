package com.memo.other.ui.activity.splash

import android.animation.Animator
import com.blankj.utilcode.util.ActivityUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.data.DataManager
import com.memo.base.manager.init.InitManager
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.other.R
import com.memo.other.ui.activity.login.LoginActivity
import com.memo.other.ui.activity.login.LoginPresenter
import com.memo.other.ui.activity.login.LoginView
import com.memo.other.ui.activity.main.MainActivity
import com.memo.tool.ext.startActivity
import com.memo.tool.helper.EncryptHelper
import com.memo.tool.helper.PermissionHelper
import com.memo.tool.simple.SimpleAnimatorListener
import kotlinx.android.synthetic.main.activity_splash.*
import java.util.concurrent.locks.ReentrantLock

/**
 * title:启动页
 * describe:重新设计思路 只有登陆后才能使用
 *
 * @author memo
 * @date 2019-10-15 10:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SplashActivity : BaseMvpActivity<LoginView, LoginPresenter>(), LoginView {

    private var isAnimationFinish = false
    private var isPermissionFinish = false
    private val mLock by lazy { ReentrantLock() }

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_splash

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): LoginPresenter = LoginPresenter()

    override fun initData() {
    }

    override fun initView() {
        // 进行初始化操作
        InitManager.get().initInSplash()
        // 直接显示内容
        mLoadService.showSuccess()
    }

    override fun initListener() {
        //权限
        PermissionHelper.requestStorageInSplash(mContext, {
            isPermissionFinish = true
            launcher()
        }, {
            ActivityUtils.finishAllActivities(true)
        })
        // 动画监听
        mLottie.addAnimatorListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                isAnimationFinish = true
                launcher()
            }
        })
    }


    private fun launcher() {
        mLock.lock()
        try {
            LogUtils.iTag("Login", "anim = $isAnimationFinish", "permission = $isPermissionFinish")
            if (isAnimationFinish && isPermissionFinish) {
                // 需要进行判断是否cookie存在
                val cookie = DataManager.get().getCookie()
                // 用户数据
                val user = DataManager.get().getUser()
                // 缓存
                val arg = DataManager.get().getArg()
                if (cookie.isEmpty() || user == null || arg == null) {
                    startActivity<LoginActivity>()
                } else {
                    startActivity<MainActivity>()
                }
                finish()
            }
        } finally {
            mLock.unlock()
        }
    }

    override fun start() {
        val arg = DataManager.get().getArg()
        if (arg == null) {
            launcher()
        } else {
            mPresenter.login(EncryptHelper.decryptRsa(arg.arg1), EncryptHelper.decryptRsa(arg.arg2))
        }
    }

    /**
     * 登陆成功
     */
    override fun loginSuccess() {
        launcher()
    }

    /**
     * 失败
     */
    override fun onError() {
        launcher()
    }


    override fun onDestroy() {
        super.onDestroy()
        mLottie.removeAllAnimatorListeners()
    }

}
