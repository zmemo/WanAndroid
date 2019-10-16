package com.memo.system.ui.fragment.system.item

import com.memo.base.entity.remote.SystemTreeItem
import com.memo.base.ui.mvp.IView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 00:04
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface SystemItemView : IView {
    fun getSystemTreeSuccess(response: ArrayList<SystemTreeItem>)

}