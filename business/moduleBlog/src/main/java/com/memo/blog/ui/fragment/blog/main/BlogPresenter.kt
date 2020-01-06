package com.memo.blog.ui.fragment.blog.main

import com.memo.base.entity.remote.ArticleTreeZip
import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

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
class BlogPresenter : BasePresenter<BlogModel, BlogView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): BlogModel = BlogModel()

    fun getBlogTree(){
        val zip = ArticleTreeZip()
        mModel.getBlogData()
            .flatMap {
                zip.articleTrees = it
                mModel.getArticles(it[0].id, 1)
            }
            .execute(mView,isFirstLoad,{
                isFirstLoad = false
                zip.articles = it
                mView.getBlogTreeSuccess(zip)
            })
    }
}