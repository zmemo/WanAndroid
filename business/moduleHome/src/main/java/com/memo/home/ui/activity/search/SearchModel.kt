package com.memo.home.ui.activity.search

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.home.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:+
 *
 * @author memo
 * @date 2019-10-18 00:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchModel : IModel {

    fun getHotKey(): Observable<ArrayList<String>> {
        return mApiService.getHotKey().convert().map {
            ArrayList(it.map { it.name })
        }
    }

    fun queryArticle(keyword: String, page: Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.queryArticle(keyword, page).convert()
    }
}