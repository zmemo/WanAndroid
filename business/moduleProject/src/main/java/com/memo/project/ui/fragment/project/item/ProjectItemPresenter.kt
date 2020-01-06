package com.memo.project.ui.fragment.project.item

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 17:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectItemPresenter : BasePresenter<ProjectItemModel, ProjectItemView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): ProjectItemModel = ProjectItemModel()

    fun getArticles(projectId: Int, page: Int) {
        mModel.getArticles(projectId, page)
            .execute(mView, isFirstLoad, {
                isFirstLoad = false
                mView.getArticlesSuccess(it)
            }, {
                mView.getArticlesFailure()
            })
    }

}