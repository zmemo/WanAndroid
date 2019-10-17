package com.memo.system.ui.activity

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 22:49
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface SystemClassicView:IView {
    fun getArticleSuccess(response: ArrayList<ArticleInfo>)
    fun getArticleFailure()
}