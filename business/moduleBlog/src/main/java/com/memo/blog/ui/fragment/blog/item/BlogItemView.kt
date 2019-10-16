package com.memo.blog.ui.fragment.blog.item

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 23:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface BlogItemView:IView {
    fun getArticleSuccess(response: ArrayList<ArticleInfo>)
    fun getArticleFailure()
}