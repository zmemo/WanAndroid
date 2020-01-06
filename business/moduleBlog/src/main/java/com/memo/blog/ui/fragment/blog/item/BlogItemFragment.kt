package com.memo.blog.ui.fragment.blog.item


import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.blog.R
import com.memo.tool.ext.withArguments
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_blog_item.*

/**
 * title：不同博客的文章列表界面
 * describe:
 *
 * @author memo
 * @date 2019-10-16 23:18
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogItemFragment : BaseMvpFragment<BlogItemView, BlogItemPresenter>(), BlogItemView {

    private var mData = arrayListOf<ArticleInfo>()

    private var blogId = 0

    private var page = 1

    private val mAdapter by lazy { ArticleAdapter() }

    companion object {
        fun newInstance(id: Int): BlogItemFragment {
            return BlogItemFragment().withArguments("id" to id)
        }

        fun newInstance(id: Int, data: ArrayList<ArticleInfo>): BlogItemFragment {
            return BlogItemFragment().withArguments("id" to id, "data" to data)
        }
    }

    override fun buildPresenter(): BlogItemPresenter = BlogItemPresenter()

    override fun bindLayoutResId(): Int = R.layout.fragment_blog_item

    override fun initData() {
        blogId = arguments?.getInt("id", blogId) ?: blogId
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
                mPresenter.getArticles(blogId, page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.getArticles(blogId, page)
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
            mPresenter.getArticles(blogId, page)
        }
    }

    override fun getArticleSuccess(response: ArrayList<ArticleInfo>) {
        mRefreshLayout.finish(response.isEmpty())
        if (page == 0) {
            mAdapter.setNewData(response)
        } else {
            mAdapter.addData(response)
        }
    }

    override fun getArticleFailure() {
        mRefreshLayout.finish(false)
        if (page > 0) page--
    }

}
