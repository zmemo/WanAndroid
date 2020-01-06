package com.memo.blog.ui.fragment.blog.main

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.ArticleTree
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.blog.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogModel : IModel{

    fun getBlogData(): Observable<ArrayList<ArticleTree>> {
        return mApiService.getBlogTree().convert()
    }

    fun getArticles(id: Int, page: Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.getBlogArticles(id, page).convert().map { it.datas }
    }
}