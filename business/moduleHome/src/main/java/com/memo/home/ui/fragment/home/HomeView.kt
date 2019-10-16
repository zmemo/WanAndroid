package com.memo.home.ui.fragment.home

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.HomeData
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface HomeView : IView {
    fun getHomeDataSuccess(response: HomeData)
    fun getArticleSuccess(response: ArrayList<ArticleInfo>)
    fun getDataFailure()
}