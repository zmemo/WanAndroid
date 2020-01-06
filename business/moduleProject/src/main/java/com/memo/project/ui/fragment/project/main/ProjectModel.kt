package com.memo.project.ui.fragment.project.main

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.ArticleTree
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.project.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:10
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectModel : IModel {

    fun getProjectTree(): Observable<ArrayList<ArticleTree>> {
        return mApiService.getProjectTree().convert()
    }

    fun getArticles(cid: Int, page: Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.getProjectArticles(page, cid).convert().map { it.datas }
    }
}