package com.memo.base.ui.mvp

import android.app.Activity
import androidx.lifecycle.LifecycleOwner


/**
 * title: 基础View接口
 * describe:
 *
 * @author zhou
 * @date 2019-01-24 14:05
 */
interface IView {

    fun context(): Activity

    fun lifecycleOwner(): LifecycleOwner

    fun showLoading(tip: String = "加载中")

    fun hideLoading()
}