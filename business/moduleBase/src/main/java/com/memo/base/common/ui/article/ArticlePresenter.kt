package com.memo.base.common.ui.article

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 15:29
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ArticlePresenter : BasePresenter<ArticleModel, ArticleView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): ArticleModel = ArticleModel()

    fun collect(id: Int) {
        mModel.collect(id).execute(mView, { mView.collectSuccess() })
    }

    fun unCollectInArticle(id: Int) {
        mModel.unCollectInArticle(id).execute(mView, { mView.unCollectSuccess() })
    }
}