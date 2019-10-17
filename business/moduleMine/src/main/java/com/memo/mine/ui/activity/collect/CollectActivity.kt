package com.memo.mine.ui.activity.collect

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.mine.R
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_collect.*
import java.util.*

/**
 * title:我的收藏
 * describe:
 *
 * @author memo
 * @date 2019-10-17 14:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectActivity : BaseMvpActivity<CollectView, CollectPresenter>(), CollectView {


    private val REQUEST_CODE_COLLECT = 1

    private var page: Int = 0

    private val mAdapter by lazy { ArticleAdapter(true) }

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): CollectPresenter = CollectPresenter()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_collect

    override fun initData() {}

    override fun initView() {
        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            mAdapter.bindToRecyclerView(this)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.getCollects(page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.getCollects(page)
            }
        })

        mAdapter.setOnItemChildClickListener { _, view, position ->
            val article = mAdapter.data[position]
            when (view.id) {
                R.id.mClContent -> {
                    // 列表点击监听 如果在文章界面取消了喜欢 那么需要在当前页面删除条目
                    ArticleActivity.startForResult(mContext, article.originId, article.title, article.link, REQUEST_CODE_COLLECT)
                }
                R.id.mFlDelete -> {
                    // 取消收藏
                    mPresenter.unCollectInCollect(article.id, article.originId, position)
                }
            }
        }
    }

    override fun start() {
        mPresenter.getCollects(page)
    }

    override fun getCollectsSuccess(response: ArrayList<ArticleInfo>) {
        mRefreshLayout.finish(response.isEmpty())
        if (page == 0) {
            mAdapter.setNewData(response)
        } else {
            mAdapter.addData(response)
        }
    }

    override fun getCollectsFailure() {
        mRefreshLayout.finish(false)
        if (page > 0) page--
    }

    override fun unCollectSuccess(position: Int) {
        if (mAdapter.data.size > position) {
            mAdapter.remove(position)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //移除收藏的条目
        if (requestCode == REQUEST_CODE_COLLECT && resultCode == Activity.RESULT_OK && data != null) {
            val originId = data.getIntExtra("originId", Int.MIN_VALUE)
            run label@{
                mAdapter.data.forEachIndexed { index, articleInfo ->
                    if (articleInfo.originId == originId) {
                        mAdapter.remove(index)
                        return@label
                    }
                }
            }
        }
    }

}
