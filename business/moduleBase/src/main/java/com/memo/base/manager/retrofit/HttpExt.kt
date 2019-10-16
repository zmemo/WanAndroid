package com.memo.base.manager.retrofit

import com.memo.base.api.ApiException
import com.memo.base.api.BaseResponse
import com.memo.base.api.ExceptionHandler
import com.memo.base.manager.load.StateError
import com.memo.base.ui.mvp.IView
import com.memo.tool.ext.io2MainLifecycle
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-09 10:19
 */


/**
 * 建议为了简化请求方法，可以按照不同接口的具体逻辑进行简化传参，创建多个方法
 */
fun <T> Observable<T>.execute(
    view: IView,
    onSuccess: (response: T) -> Unit,
    onError: () -> Unit = {}
) {
    this.io2MainLifecycle(view.lifecycleOwner())
        .subscribe({
            onSuccess(it)
            view.hideLoading()
        }, {
            ExceptionHandler.handleException(it)
            onError()
            view.hideLoading()
        })
}


fun <T> Observable<T>.execute(
    view: IView,
    isFirstLoad: Boolean,
    onSuccess: (response: T) -> Unit,
    onError: () -> Unit = {}
) {
    this.io2MainLifecycle(view.lifecycleOwner())
        .subscribe({
            onSuccess(it)
            view.loadService().showSuccess()
            view.hideLoading()
        }, {
            ExceptionHandler.handleException(it)
            onError()
            if (isFirstLoad) view.loadService().showCallback(StateError::class.java)
            view.hideLoading()
        })
}


/**
 * 数据源转化
 */
fun <T> Observable<BaseResponse<T>>.convert(): Observable<T> {
    return this.flatMap {
        if (it.isSuccess()) {
            Observable.just(it.data)
        } else {
            Observable.error(ApiException(it.errorCode, it.errorMsg))
        }
    }
}

/**
 * 数据源转化
 */
fun <T> Observable<BaseResponse<T>>.convertAny(): Observable<Any> {
    return this.flatMap {
        if (it.isSuccess()) {
            Observable.just(Any())
        } else {
            Observable.error(ApiException(it.errorCode, it.errorMsg))
        }
    }
}