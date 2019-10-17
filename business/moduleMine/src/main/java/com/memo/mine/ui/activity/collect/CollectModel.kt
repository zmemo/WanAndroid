package com.memo.mine.ui.activity.collect

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.manager.retrofit.convert
import com.memo.base.manager.retrofit.convertAny
import com.memo.base.ui.mvp.IModel
import com.memo.mine.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 14:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectModel : IModel {

    fun getCollects(page: Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.getCollects(page).convert().map { it.datas }
    }

    fun unCollectInCollect(id: Int, originId: Int): Observable<Any> {
        return mApiService.unCollectInCollect(id, originId).convertAny()
    }
}