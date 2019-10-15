package com.memo.tool.helper

import com.blankj.utilcode.util.ToastUtils
import com.memo.tool.R
import com.scwang.smartrefresh.layout.SmartRefreshLayout
import com.scwang.smartrefresh.layout.constant.RefreshState
import kotlinx.android.synthetic.main.layout_toast.view.*

/**
 * title:全局通用方法
 * describe:
 *
 * @author zhou
 * @date 2019-03-28 10:24
 */

// ------------------------------- Toast相关 -------------------------------//

fun toast(message: Any?) {
    message?.let {
        if (it.toString().isNotEmpty()) {
            ToastUtils.setGravity(-1, -1, -1)
            ToastUtils.showCustomShort(R.layout.layout_toast).mTvMessage.text = it.toString()
        }
    }
}

fun toastCancel() {
    ToastUtils.cancel()
}


// ------------------------------- SmartRefreshLayout相关 -------------------------------//

/**
 * 关闭刷新
 * @param noMoreData true -> 不再上拉加载更多 false -> 正常上拉加载
 */
fun SmartRefreshLayout.finish(noMoreData: Boolean) {
    when (state) {
        RefreshState.Refreshing -> finishRefresh(400)
        RefreshState.Loading -> finishLoadMore(400, true, noMoreData)
        else -> {
        }
    }
}