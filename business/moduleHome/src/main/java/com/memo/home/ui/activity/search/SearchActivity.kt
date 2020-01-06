package com.memo.home.ui.activity.search

import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.KeyboardUtils
import com.blankj.utilcode.util.LogUtils
import com.google.android.flexbox.FlexboxLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.manager.data.DataManager
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.home.R
import com.memo.home.ui.adapter.FlexAdapter
import com.memo.tool.ext.*
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_search.*

/**
 * title:搜索界面
 * describe:
 *
 * @author memo
 * @date 2019-10-18 00:29
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SearchActivity : BaseMvpActivity<SearchView, SearchPresenter>(), SearchView {

    private val mAdapter by lazy { ArticleAdapter() }

    private val mHotAdapter by lazy { FlexAdapter() }

    private val mHistoryAdapter by lazy { FlexAdapter() }

    private val mHistories by lazy { arrayListOf<String>() }

    private var mSets: Set<String> = emptySet()

    private var page = 0

    private var keyword = ""

    override fun buildPresenter(): SearchPresenter = SearchPresenter()

    override fun bindLayoutResId(): Int = R.layout.activity_search

    override fun initData() {
        mSets = DataManager.get().getSearchHistory()
        LogUtils.iTag("history", mSets.size)
        if (mSets.isNotEmpty()) {
            visible(mTvHistory, mRvHistory)
        }
        mSets.forEach { mHistories.add(it) }
    }

    override fun initView() {
        mLoadService.showSuccess()
        mRvHot.run {
            layoutManager = FlexboxLayoutManager(mContext)
            adapter = mHotAdapter
        }

        mRvHistory.run {
            layoutManager = FlexboxLayoutManager(mContext)
            mHistoryAdapter.setNewData(mHistories)
            adapter = mHistoryAdapter
        }

        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        // 清空输入框
        mTitleView.setOnRightClickListener {
            mEtSearch.setText("")
            mllSearch.visible()
            mLlResult.gone()
        }
        // 清空历史记录
        mTvHistory.onClick {
            mHistories.clear()
            mSets = setOf()
            mHistoryAdapter.setNewData(mHistories)
            DataManager.get().removeSearchHistory()
            gone(mTvHistory, mRvHistory)
        }
        // 点击软键盘的搜索
        mEtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 添加历史
                keyword = mEtSearch.value
                if (!mSets.contains(keyword)) {
                    mSets = mSets.plus(keyword)
                    mHistoryAdapter.addData(keyword)
                    DataManager.get().putSearchHistory(mSets)
                    visible(mTvHistory, mRvHistory)
                }
                showLoading("正在搜索")
                page = 0
                mPresenter.queryArticles(keyword, page)
                KeyboardUtils.hideSoftInput(mEtSearch)
            }
            true
        }
        // 搜索热词
        mHotAdapter.setOnItemClickListener { _, _, position ->
            val keyword = mHotAdapter.data[position]
            mEtSearch.setText(keyword)
            if (!mSets.contains(keyword)) {
                mSets = mSets.plus(keyword)
                mHistoryAdapter.addData(keyword)
                DataManager.get().putSearchHistory(mSets)
                visible(mTvHistory, mRvHistory)
            }
            showLoading("正在搜索")
            page = 0
            mPresenter.queryArticles(keyword, page)
        }
        // 历史记录
        mHistoryAdapter.setOnItemClickListener { _, _, position ->
            val keyword = mHistoryAdapter.data[position]
            mEtSearch.setText(keyword)
            mHistoryAdapter.setNewData(mHistories)
            showLoading("正在搜索")
            page = 0
            mPresenter.queryArticles(keyword, page)
        }
        // 列表点击监听
        mAdapter.setOnItemChildClickListener { _, _, position ->
            val article = mAdapter.data[position]
            ArticleActivity.start(mContext, article.id, article.title, article.link)
        }
        // 刷新加载
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.queryArticles(keyword, page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.queryArticles(keyword, page)
            }

        })
    }

    override fun start() {
        mPresenter.getHotKey()
    }

    override fun getHotKeySuccess(response: ArrayList<String>) {
        visible(mTvHot, mRvHot)
        mHotAdapter.setNewData(response)
    }

    override fun queryArticleSuccess(response: ArrayList<ArticleInfo>) {
        mllSearch.gone()
        mLlResult.visible()
        mRefreshLayout.finish(response.isEmpty())
        if (page == 0) {
            mAdapter.setNewData(response)
        } else {
            mAdapter.addData(response)
        }
    }

    override fun onBackPressed() {
        if (mllSearch.isGone && mLlResult.isVisible) {
            mllSearch.visible()
            mLlResult.gone()
        } else {
            super.onBackPressed()
        }
    }

}
