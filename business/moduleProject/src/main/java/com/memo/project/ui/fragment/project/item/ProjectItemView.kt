package com.memo.project.ui.fragment.project.item

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 17:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface ProjectItemView : IView {
    fun getArticlesSuccess(response: ArrayList<ArticleInfo>)
    fun getArticlesFailure()
}