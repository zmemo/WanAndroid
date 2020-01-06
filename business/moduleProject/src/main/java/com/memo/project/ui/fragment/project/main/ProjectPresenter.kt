package com.memo.project.ui.fragment.project.main

import com.memo.base.entity.remote.ArticleTreeZip
import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:11
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectPresenter : BasePresenter<ProjectModel, ProjectView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): ProjectModel = ProjectModel()

    fun getProjectTree() {
        val zip = ArticleTreeZip()
        mModel.getProjectTree()
            .flatMap {
                zip.articleTrees = it
                mModel.getArticles(it[0].id, 1)
            }
            .execute(mView, isFirstLoad, {
                isFirstLoad = false
                zip.articles = it
                mView.getProjectTreeSuccess(zip)
            })
    }


}