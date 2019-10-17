package com.memo.system.ui.activity

import android.content.Context
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.system.R
import com.memo.tool.ext.startActivity
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_system_classic.*
import java.util.*

/**
 * title:体系分类文章列表
 * describe:
 *
 * @author memo
 * @date 2019-10-17 22:48
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemClassicActivity : BaseMvpActivity<SystemClassicView, SystemClassicPresenter>(), SystemClassicView {

    private var id = 0

    private var page = 0

    private val mAdapter by lazy { ArticleAdapter() }

    companion object {
        fun start(context: Context, title: String, id: Int) {
            context.startActivity<SystemClassicActivity>("title" to title, "id" to id)
        }
    }

    override fun buildPresenter(): SystemClassicPresenter = SystemClassicPresenter()

    override fun bindLayoutResId(): Int = R.layout.activity_system_classic

    override fun initData() {
        id = intent.getIntExtra("id", id)
        val title = intent.getStringExtra("title") ?: ""
        mTitleView.setTitle(title)
    }

    override fun initView() {
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.getArticles(id, page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.getArticles(id, page)
            }

        })

        mAdapter.setOnItemChildClickListener { _, _, position ->
            val article = mAdapter.data[position]
            ArticleActivity.start(mContext, article.id, article.title, article.link)
        }
    }

    override fun start() {
        mPresenter.getArticles(id, page)
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
