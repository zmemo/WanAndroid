package com.memo.base.ui.fragment

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir
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

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = super.onCreateView(inflater, container, savedInstanceState)
        mLoadService = LoadSir.getDefault().register(rootView) { onStart() }
        return mLoadService.loadLayout
    }

    override fun baseInitialize() {
        super.baseInitialize()
        mPresenter = buildPresenter()
        mPresenter.attachView(this as V)
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