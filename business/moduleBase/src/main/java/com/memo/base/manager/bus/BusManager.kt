package com.memo.base.manager.bus

import com.blankj.rxbus.RxBus
import com.memo.base.entity.event.LoginEvent

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-06-25 15:00
 */
class BusManager private constructor() {

    val TAG_LOGIN_EVENT: String = "TAG_LOGIN_EVENT"

    private object Holder {
        val instance: BusManager = BusManager()
    }

    companion object {
        fun get() = Holder.instance
    }

    fun subscribeLogin(subscriber: Any, onNext: (Boolean) -> Unit) {
        RxBus.getDefault()
            .subscribe(subscriber, TAG_LOGIN_EVENT, object : RxBus.Callback<LoginEvent>() {
                override fun onEvent(t: LoginEvent?) {
                    t?.let { onNext(it.isLogin) }
                }
            })
    }


    fun unregister(subscriber: Any) {
        RxBus.getDefault().unregister(subscriber)
    }
}