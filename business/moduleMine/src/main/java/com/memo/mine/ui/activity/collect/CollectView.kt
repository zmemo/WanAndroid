package com.memo.mine.ui.activity.collect

import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 14:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface CollectView : IView {
    fun getCollectsSuccess(response: ArrayList<ArticleInfo>)
    fun getCollectsFailure()
    fun unCollectSuccess(position: Int)
}