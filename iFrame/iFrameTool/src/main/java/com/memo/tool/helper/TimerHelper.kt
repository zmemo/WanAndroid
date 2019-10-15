package com.memo.tool.helper

import androidx.lifecycle.LifecycleOwner
import com.memo.tool.ext.bindLifecycle
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

/**
 * title: 延时操作
 * describe:
 *
 * @author zhou
 * @date 2019-10-12 11:27
 *
 * Talk is cheap, Show me the code.
 */
object TimerHelper {

    private val mCache: HashMap<Any, Disposable> by lazy { HashMap<Any, Disposable>() }

    /**
     * 进行延时操作
     * @param lifecycleOwner 生命周期控制
     * @param delay 延时
     * @param task 操作方法
     */
    fun postDelay(lifecycleOwner: LifecycleOwner, delay: Long, task: () -> Unit) {
        Observable.timer(delay, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .bindLifecycle(lifecycleOwner)
            .subscribe { task() }
    }

    /**
     * 进行循环操作
     * @param lifecycleOwner 生命周期控制
     * @param period 延时
     * @param task 操作方法
     */
    fun interval(lifecycleOwner: LifecycleOwner, period: Long, task: () -> Unit) {
        Observable.interval(period, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .bindLifecycle(lifecycleOwner)
            .subscribe { task() }
    }

    /**
     * 进行延时操作
     * @param subscriber 缓存key
     * @param delay 延时
     * @param task 操作方法
     */
    fun postDelay(subscriber: Any, delay: Long, task: () -> Unit) {
        val disposable = Observable.timer(delay, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { task() }
        mCache[subscriber] = disposable
    }


    /**
     * 进行循环操作
     * @param subscriber 缓存key
     * @param period 延时
     * @param task 操作方法
     */
    fun interval(subscriber: Any, period: Long, task: () -> Unit) {
        val disposable = Observable.interval(period, TimeUnit.MILLISECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { task() }
        mCache[subscriber] = disposable
    }

    /**
     * 取消Rx操作
     * @param subscriber 缓存key
     */
    fun cancel(subscriber: Any) {
        mCache[subscriber]?.let {
            if (!it.isDisposed) {
                it.dispose()
            }
        }
        mCache.remove(subscriber)
    }

}