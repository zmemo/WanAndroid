package com.memo.base.common.ui.article

import com.memo.base.common.api.mCommonApiService
import com.memo.base.manager.retrofit.convertAny
import com.memo.base.ui.mvp.IModel
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 15:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ArticleModel : IModel {
    fun collect(id: Int): Observable<Any> {
        return mCommonApiService.collect(id).convertAny()
    }

    fun unCollectInArticle(id: Int): Observable<Any> {
        return mCommonApiService.unCollectInArticle(id).convertAny()
    }
}