package com.memo.project.ui.fragment.project.item

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.project.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 17:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectItemModel : IModel {

    fun getArticles(cid: Int, page: Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.getProjectArticles(page, cid).convert().map { it.datas }
    }
}