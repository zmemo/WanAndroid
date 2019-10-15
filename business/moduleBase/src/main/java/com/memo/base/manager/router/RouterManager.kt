package com.memo.base.manager.router

import android.app.Activity
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-20 11:42
 */
class RouterManager {

    private object Holder {
        val instance = RouterManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 获取二维码扫描界面
     * @param activity Activity
     * @param requestCode Int 请求码
     */
    fun startQrCodeActivityForResult(activity: Activity, requestCode: Int) {
        ARouter.getInstance().build(RouterPath.Ui.QrcodeScanActivity)
            .navigation(activity, requestCode)
    }

    /**
     * 跳转到一个网页
     * @param url String 网页地址
     * @param title String 标题
     */
    fun startWebActivity(@NonNull url: String, @Nullable title: String) {
        ARouter.getInstance().build(RouterPath.Ui.WebActivity)
            .withString("url", url)
            .withString("title", title)
            .navigation()
    }

    /**
     * 跳转ui界面
     */
    fun startLauncherUi() {
        ARouter.getInstance().build(RouterPath.Launcher.UiActivity).navigation()
    }


    //---------------------------------------- Home -------------------------------------

    /**
     * 获取首页
     */
    fun getHomeFragment(): Fragment {
        return ARouter.getInstance().build(RouterPath.Home.HomeFragment).navigation() as Fragment
    }

    /**
     * 项目界面
     */
    fun getProjectFragment(): Fragment {
        return ARouter.getInstance().build(RouterPath.Project.ProjectFragment).navigation() as Fragment
    }

    /**
     * 博客界面
     */
    fun getBlogFragment(): Fragment {
        return ARouter.getInstance().build(RouterPath.Blog.BlogFragment).navigation() as Fragment
    }

    /**
     * 体系界面
     */
    fun getSystemFragment(): Fragment {
        return ARouter.getInstance().build(RouterPath.System.SystemFragment).navigation() as Fragment
    }

    /**
     * 我的界面
     */
    fun getMineFragment(): Fragment {
        return ARouter.getInstance().build(RouterPath.Mine.MineFragment).navigation() as Fragment
    }
}