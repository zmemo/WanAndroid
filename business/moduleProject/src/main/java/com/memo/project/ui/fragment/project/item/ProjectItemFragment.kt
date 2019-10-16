package com.memo.project.ui.fragment.project.item


import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.manager.load.LoadHelper
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.project.R
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_project_item.*
import java.util.*

/**
 * title:不同标签的项目界面
 * describe:
 *
 * @author memo
 * @date 2019-10-16 17:40
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ProjectItemFragment : BaseMvpFragment<ProjectItemView, ProjectItemPresenter>(), ProjectItemView {

    private var cid: Int = 0

    private var page: Int = 0

    private val mAdapter by lazy { ArticleAdapter() }

    companion object {
        fun newInstance(cid: Int): ProjectItemFragment {
            val fragment = ProjectItemFragment()
            fragment.arguments = bundleOf("cid" to cid)
            return fragment
        }
    }

    /*** 绑定Presenter ***/
    override fun buildPresenter(): ProjectItemPresenter = ProjectItemPresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_project_item

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        initData()
        initView()
        initListener()
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
        start()
    }

    private fun initData() {
        cid = arguments?.getInt("cid", cid) ?: cid
    }

    private fun initView() {
        mLoadService = LoadHelper.register(mRootView) { mPresenter.getArticles(cid, page) }
        mRvList.run {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    private fun initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.getArticles(cid, page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.getArticles(cid, page)
            }
        })
    }

    private fun start() {
        mPresenter.getArticles(cid, page)
    }

    override fun getArticlesSuccess(response: ArrayList<ArticleInfo>) {
        mRefreshLayout.finish(response.isEmpty())
        mAdapter.setNewData(response)
    }

    override fun getArticlesFailure() {
        mRefreshLayout.finish(false)
        if (page > 0) page--
    }

}
