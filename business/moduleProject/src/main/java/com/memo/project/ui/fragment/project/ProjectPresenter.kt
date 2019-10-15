package com.memo.project.ui.fragment.project

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

}