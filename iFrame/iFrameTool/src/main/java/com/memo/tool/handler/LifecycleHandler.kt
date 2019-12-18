package com.memo.tool.handler

import android.os.Handler
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.OnLifecycleEvent

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-11-13 17:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LifecycleHandler(private val lifecycleOwner: LifecycleOwner, callback: Callback) :
    Handler(callback), LifecycleObserver {

    init {
        lifecycleOwner.lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    private fun onDestroy() {
        removeCallbacksAndMessages(null)
        lifecycleOwner.lifecycle.removeObserver(this)
    }
}