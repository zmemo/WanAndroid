package com.memo.base.manager.init

import android.app.Application
import androidx.core.content.ContextCompat
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.ProcessUtils
import com.blankj.utilcode.util.Utils
import com.didichuxing.doraemonkit.DoraemonKit
import com.kingja.loadsir.core.LoadSir
import com.memo.base.BuildConfig
import com.memo.base.R
import com.memo.base.config.config.Config
import com.memo.base.manager.load.StateError
import com.memo.base.manager.load.StateLoading
import com.memo.tool.helper.OOMHelper
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.footer.BallPulseFooter
import com.scwang.smartrefresh.layout.header.ClassicsHeader
import com.tencent.mmkv.MMKV

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-30 09:59
 */
class InitManager {

    private var isInitInApp = false

    private var isInitInSplash = false

    private object Holder {
        val instance = InitManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    /**
     * 在App中进行初始化
     * @param app Application
     */
    fun initInApp(app: Application) {
        if (ProcessUtils.isMainProcess() && !isInitInApp) {
            // 哆啦A梦插件初始化
            DoraemonKit.install(app)

            // 初始化AndroidUtilCode
            Utils.init(app)

            // 初始化MMKV
            MMKV.initialize(app)

            // 初始化日志打印
            LogUtils.getConfig()
                .setLogSwitch(Config.isOpenLog)
                .globalTag = "WanAndroid"


            // 初始化ARouter
            if (BuildConfig.DEBUG) {
                ARouter.openLog()
                ARouter.openDebug()
            }
            ARouter.init(app)

            isInitInApp = true
        }
    }

    fun initInSplash() {
        if (!isInitInSplash) {
            // 定时检查应用内存 清理图片内存
            OOMHelper.startMonitorLowMemory()

            //初始化刷新框架
            SmartRefreshLayout.setDefaultRefreshInitializer { _, refreshLayout ->
                refreshLayout
                    .setEnableAutoLoadMore(false)
                    .setEnableOverScrollBounce(true)
                    .setEnableOverScrollDrag(true)
                    .setEnableLoadMoreWhenContentNotFull(false)
            }
            SmartRefreshLayout.setDefaultRefreshHeaderCreator { context, _ ->

                val classicsHeader = ClassicsHeader(context)
                classicsHeader
            }
            SmartRefreshLayout.setDefaultRefreshFooterCreator { context, _ ->
                BallPulseFooter(context)
                    .setNormalColor(ContextCompat.getColor(context, R.color.color_main))
                    .setAnimatingColor(ContextCompat.getColor(context, R.color.color_main))
            }

            LoadSir.beginBuilder()
                .addCallback(StateLoading())
                .addCallback(StateError())
                .setDefaultCallback(StateLoading::class.java)
                .commit()

            isInitInSplash = true
        }
    }

}