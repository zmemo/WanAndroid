package com.memo.home.ui.activity.search

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 00:20
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchPresenter : BasePresenter<SearchModel, SearchView>() {
    override fun buildModel(): SearchModel = SearchModel()

    fun getHotKey() {
        mModel.getHotKey()
            .execute(mView, {
                mView.getHotKeySuccess(it)
            })
    }

    fun queryArticles(keyword: String, page: Int) {
        mModel.queryArticle(keyword, page)
            .execute(mView, {
                mView.queryArticleSuccess(it)
            })
    }
}