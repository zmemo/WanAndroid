package com.memo.system.ui.adapter

import com.memo.base.entity.remote.SystemTreeItem
import com.memo.tool.adapter.recyclerview.BaseMultiAdapter
import com.memo.tool.adapter.recyclerview.BaseMultiProvider

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 01:00
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TreeAdapter:BaseMultiAdapter<SystemTreeItem>() {
    override fun bindMultiType(): ArrayList<BaseMultiProvider<SystemTreeItem>> {
        return arrayListOf(TitleProvider(), ContentProvider())
    }
}