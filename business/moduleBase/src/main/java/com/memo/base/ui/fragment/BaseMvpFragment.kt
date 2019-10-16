package com.memo.base.ui.fragment

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.kingja.loadsir.core.LoadService
import com.memo.base.manager.load.LoadHelper
import com.memo.base.ui.mvp.IPresenter
import com.memo.base.ui.mvp.IView

/**
 * title:基础的MVP模式Fragment
 * describe:
 *
 * @author zhou
 * @date 2019-01-28 13:58
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpFragment<in V : IView, P : IPresenter<V>> : BaseFragment(), IView {

    protected lateinit var mLoadService: LoadService<*>

    protected lateinit var mPresenter: P

    /*** 绑定Presenter ***/
    protected abstract fun buildPresenter(): P

    override fun baseInitialize() {
        super.baseInitialize()
        mPresenter = buildPresenter()
        mPresenter.attachView(this as V)
        mLoadService = LoadHelper.register(mRootView) { start() }
    }

    override fun initialize() {
        initData()
        initView()
        initListener()
    }

    override fun lazyInitialize() {
        start()
    }

    abstract fun initData()

    abstract fun initView()

    abstract fun initListener()

    abstract fun start()

    /*** 回调上下文 ***/
    override fun context(): Activity = mActivity

    /*** 加载状态 ***/
    override fun loadService(): LoadService<*> = mLoadService

    /*** 回调生命周期控制 ***/
    override fun lifecycleOwner(): LifecycleOwner = mLifecycleOwner

    /*** 显示加载框 ***/
    override fun showLoading(tip: String) = mLoadDialog.show(tip)

    /*** 隐藏加载框 ***/
    override fun hideLoading() = mLoadDialog.dismiss()


}