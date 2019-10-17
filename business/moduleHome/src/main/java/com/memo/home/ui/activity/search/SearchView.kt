package com.memo.home.ui.activity.search

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.mvp.IView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 00:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface SearchView : IView {
    fun getHotKeySuccess(response: ArrayList<String>)
    fun queryArticleSuccess(response: ArrayList<ArticleInfo>)
}