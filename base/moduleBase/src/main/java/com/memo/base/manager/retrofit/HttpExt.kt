package com.memo.base.manager.retrofit

import com.memo.base.api.BaseResponse
import com.memo.base.api.ApiException
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
