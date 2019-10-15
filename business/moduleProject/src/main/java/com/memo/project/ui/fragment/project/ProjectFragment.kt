package com.memo.project.ui.fragment.project


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.project.R

/**
 * title:项目界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:14
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Project.ProjectFragment)
class ProjectFragment : BaseMvpFragment<ProjectView, ProjectPresenter>(), ProjectView {

    /*** 绑定Presenter ***/
    override fun buildPresenter(): ProjectPresenter = ProjectPresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_project

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {}

}
