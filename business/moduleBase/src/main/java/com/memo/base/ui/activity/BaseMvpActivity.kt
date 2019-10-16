package com.memo.base.ui.activity

import android.app.Activity
import androidx.lifecycle.LifecycleOwner
import com.kingja.loadsir.core.LoadService
import com.memo.base.manager.load.LoadHelper
import com.memo.base.ui.mvp.IPresenter
import com.memo.base.ui.mvp.IView


/**
 * title:基础的Mvp模式Activity
 * describe: 具体的逻辑还是要根据需求进行改变
 *
 * @author zhou
 * @date 2019-01-24 14:08
 */
@Suppress("UNCHECKED_CAST")
abstract class BaseMvpActivity<in V : IView, P : IPresenter<V>> : BaseActivity(), IView {

    protected lateinit var mLoadService: LoadService<*>

    protected lateinit var mPresenter: P

    override fun baseInit() {
        super.baseInit()
        mPresenter = buildPresenter()
        mPresenter.attachView(this as V)
        mLoadService = LoadHelper.register(mRootView) { start() }
    }

    override fun initialize() {
        initData()
        initView()
        initListener()
        start()
    }

    abstract fun initData()

    abstract fun initView()

    abstract fun initListener()

    abstract fun start()

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    protected abstract fun buildPresenter(): P

    /*** 回调上下文 ***/
    override fun context(): Activity = mContext

    /*** 状态控制 ***/
    override fun loadService(): LoadService<*> = mLoadService

    /*** 回调生命周期控制 ***/
    override fun lifecycleOwner(): LifecycleOwner = mLifecycleOwner

    /*** 显示加载框 ***/
    override fun showLoading(tip: String) = mLoadDialog.show(tip)

    /*** 隐藏加载框 ***/
    override fun hideLoading() = mLoadDialog.dismiss()
}