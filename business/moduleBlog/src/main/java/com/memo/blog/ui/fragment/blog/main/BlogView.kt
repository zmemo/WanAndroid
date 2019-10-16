package com.memo.blog.ui.fragment.blog.main

import com.memo.base.entity.remote.ArticleTree
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface BlogView : IView {
    fun getBlogTreeSuccess(response: ArrayList<ArticleTree>)
}