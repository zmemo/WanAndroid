package com.memo.project.ui.fragment.project.item


import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.project.R
import com.memo.tool.ext.withArguments
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_project_item.*

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

    private var mData = arrayListOf<ArticleInfo>()

    private var projectId: Int = 0

    private var page: Int = 1

    private val mAdapter by lazy { ArticleAdapter() }

    companion object {
        fun newInstance(cid: Int): ProjectItemFragment {
            return ProjectItemFragment().withArguments("cid" to cid)
        }

        fun newInstance(cid: Int, data: ArrayList<ArticleInfo>): ProjectItemFragment {
            return ProjectItemFragment().withArguments("cid" to cid, "data" to data)
        }
    }

    /*** 绑定Presenter ***/
    override fun buildPresenter(): ProjectItemPresenter = ProjectItemPresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_project_item

    override fun initData() {
        projectId = arguments?.getInt("cid", projectId) ?: projectId
        mData = arguments?.getParcelableArrayList<ArticleInfo>("data") ?: arrayListOf()
    }

    override fun initView() {
        mRvList.run {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.getArticles(projectId, page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.getArticles(projectId, page)
            }
        })

        // 列表点击监听
        mAdapter.setOnItemChildClickListener { _, _, position ->
            val article = mAdapter.data[position]
            ArticleActivity.start(mActivity, article.id, article.title, article.link)
        }
    }

    override fun start() {
        if (mData.isNotEmpty()) {
            mAdapter.setNewData(mData)
            mPresenter.isFirstLoad = false
            mLoadDialog.dismiss()
            mLoadService.showSuccess()
        } else {
            mPresenter.getArticles(projectId, page)
        }
    }

    override fun getArticlesSuccess(response: ArrayList<ArticleInfo>) {
        mRefreshLayout.finish(response.isEmpty())
        if (page == 0) {
            mAdapter.setNewData(response)
        } else {
            mAdapter.addData(response)
        }
    }

    override fun getArticlesFailure() {
        mRefreshLayout.finish(false)
        if (page > 0) page--
    }

    /**
     * 恢复fragment的状态 判断是否显示数据
     */
    override fun onViewStateRestored(savedInstanceState: Bundle?) {
        super.onViewStateRestored(savedInstanceState)
        if (savedInstanceState?.getBoolean("isRestored") == true) {
            mLoadService.showSuccess()
        }
    }

    /**
     * 保存Fragment的状态 判断是否加载过数据了
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putBoolean("isRestored", mAdapter.data.isNotEmpty())
    }

}
