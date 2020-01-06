package com.memo.project.ui.fragment.project.main


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.entity.remote.ArticleTreeZip
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.project.R
import com.memo.project.ui.fragment.project.item.ProjectItemFragment
import com.memo.tool.adapter.BaseFragmentPagerAdapter
import com.memo.tool.ext.paddingStatusBar
import kotlinx.android.synthetic.main.fragment_project.*

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

    override fun initData() {}

    override fun initView() {
        // 填充状态栏
        mRootView.paddingStatusBar()
    }

    override fun initListener() {}

    override fun start() {
        mPresenter.getProjectTree()
    }

    override fun getProjectTreeSuccess(response: ArticleTreeZip) {
        val array = response.articleTrees.map { it.name }.toTypedArray()
        mViewPager.offscreenPageLimit = 1
        val mAdapter = BaseFragmentPagerAdapter(childFragmentManager) {
            if (it == 0) ProjectItemFragment.newInstance(response.articleTrees[it].id, response.articles)
            else ProjectItemFragment.newInstance(response.articleTrees[it].id)
        }
        mAdapter.setData(response.articleTrees.size, array)
        mViewPager.adapter = mAdapter
        mTabLayout.setViewPager(mViewPager, array)
    }
}
