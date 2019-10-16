package com.memo.project.ui.fragment.project.main


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.entity.remote.ProjectTree
import com.memo.base.manager.load.LoadHelper
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.iframe.tools.ext.fromHtml
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

    private val mAdapter by lazy { BaseFragmentPagerAdapter<ProjectItemFragment>(childFragmentManager) }

    /*** 绑定Presenter ***/
    override fun buildPresenter(): ProjectPresenter = ProjectPresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_project

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        initView()
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
        start()
    }

    private fun initView() {
        // 填充状态栏
        mRootView.paddingStatusBar()

        mLoadService = LoadHelper.register(mRootView) {
            mPresenter.getProjectTree()
        }
    }


    private fun start() {
        mPresenter.getProjectTree()
    }

    override fun getProjectTreeSuccess(response: ArrayList<ProjectTree>) {
        val titles = ArrayList<String>()
        val fragments = arrayListOf<ProjectItemFragment>()
        response.forEach {
            titles.add(it.name.fromHtml())
            fragments.add(ProjectItemFragment.newInstance(it.id))
        }
        val arrays = titles.toTypedArray()
        mAdapter.setData(fragments, arrays)
        mViewPager.adapter = mAdapter
        mViewPager.offscreenPageLimit = titles.size
        mTabLayout.setViewPager(mViewPager, arrays)
    }
}
