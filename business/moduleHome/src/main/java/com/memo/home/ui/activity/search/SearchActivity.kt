package com.memo.home.ui.activity.search

import android.view.inputmethod.EditorInfo
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexboxLayoutManager
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.home.R
import com.memo.home.ui.adapter.FlexAdapter
import com.memo.tool.ext.gone
import com.memo.tool.ext.visible
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

    override fun buildPresenter(): SearchPresenter = SearchPresenter()

    override fun bindLayoutResId(): Int = R.layout.activity_search

    override fun initData() {}

    override fun initView() {
        mLoadService.showSuccess()
        mRvHot.run {
            layoutManager = FlexboxLayoutManager(mContext)
            adapter = mHotAdapter
        }

        mRvHistory.run {
            layoutManager = FlexboxLayoutManager(mContext)
            adapter = mHistoryAdapter
        }

        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mTitleView.setOnRightClickListener {
            mEtSearch.setText("")
            mllSearch.visible()
            mllResult.gone()
        }
        mEtSearch.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                // 点击软键盘的搜索
            }
            true
        }
        mHotAdapter.setOnItemClickListener { _, _, position ->
            val keyword = mHotAdapter.data[position]
            mEtSearch.setText(keyword)
        }
        mHistoryAdapter.setOnItemClickListener { _, _, position ->
            val keyword = mHistoryAdapter.data[position]
            mEtSearch.setText(keyword)
        }
        // 列表点击监听
        mAdapter.setOnItemChildClickListener { _, _, position ->
            val article = mAdapter.data[position]
            ArticleActivity.start(mContext, article.id, article.title, article.link)
        }
    }

    override fun start() {
        mPresenter.getHotKey()
    }

    override fun getHotKeySuccess(response: ArrayList<String>) {
        mHotAdapter.setNewData(response)
    }

    override fun queryArticleSuccess(response: ArrayList<ArticleInfo>) {
        mllSearch.gone()
        mllResult.visible()
    }

}
