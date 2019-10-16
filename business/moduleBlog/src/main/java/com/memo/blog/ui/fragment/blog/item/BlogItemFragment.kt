package com.memo.blog.ui.fragment.blog.item


import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.blog.R
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_blog_item.*
import java.util.*

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

    private var blogId = 0

    private var page = 0

    private val mAdapter by lazy { ArticleAdapter() }

    companion object {
        fun newInstance(id: Int): BlogItemFragment {
            val fragment = BlogItemFragment()
            fragment.arguments = bundleOf("id" to id)
            return fragment
        }
    }

    override fun buildPresenter(): BlogItemPresenter = BlogItemPresenter()

    override fun bindLayoutResId(): Int = R.layout.fragment_blog_item

    override fun initData() {
        blogId = arguments?.getInt("id", blogId) ?: blogId
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
    }

    override fun start() {
        mPresenter.getArticles(blogId, page)
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
