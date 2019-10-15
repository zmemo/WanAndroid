package com.memo.other.ui.activity.splash

import android.animation.Animator
import com.memo.base.manager.data.DataManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.other.R
import com.memo.other.ui.activity.login.LoginActivity
import com.memo.other.ui.activity.main.MainActivity
import com.memo.tool.ext.startActivity
import com.memo.tool.simple.SimpleAnimatorListener
import kotlinx.android.synthetic.main.activity_splash.*

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
class SplashActivity : BaseActivity() {


    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_splash

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mLottie.addAnimatorListener(object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                // 需要进行判断是否cookie存在
                val cookie = DataManager.get().getCookie()
                if (cookie.isEmpty()) {
                    startActivity<LoginActivity>()
                } else {
                    startActivity<MainActivity>()
                }
                finish()
            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        mLottie.removeAllAnimatorListeners()
    }

}
