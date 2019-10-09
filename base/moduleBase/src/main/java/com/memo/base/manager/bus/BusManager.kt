package com.memo.base.manager.bus

import com.blankj.rxbus.RxBus

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-25 15:00
 */
class BusManager private constructor() {

    val TAG_MAIN: String = "MainActivity"

    private object Holder {
        val instance: BusManager = BusManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    fun subscribeMain(subscriber: Any, onNext: (String) -> Unit) {
        RxBus.getDefault()
            .subscribe(subscriber, TAG_MAIN, object : RxBus.Callback<String>() {
                override fun onEvent(t: String?) {
                    t?.let { onNext(it) }
                }
            })
    }

    fun postMain(message: String) {
        RxBus.getDefault().post(message, TAG_MAIN)
    }

    fun unregister(subscriber: Any) {
        RxBus.getDefault().unregister(subscriber)
    }
}