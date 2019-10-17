package com.memo.system.ui.activity

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.system.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 22:49
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemClassicModel:IModel {

    fun getSystemClassicArticles(cid:Int,page:Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.getSystemClassicArticles(page,cid).convert().map { it.datas }
    }
}