package com.memo.system.ui.adapter

import android.content.Context
import com.memo.base.entity.remote.SystemTreeItem
import com.memo.base.entity.remote.TYPE_SYSTEM_TITLE
import com.memo.system.R
import com.memo.tool.adapter.recyclerview.BaseMultiProvider
import com.memo.tool.adapter.recyclerview.ViewHolder

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 01:01
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TitleProvider : BaseMultiProvider<SystemTreeItem>(TYPE_SYSTEM_TITLE, R.layout.item_tree_title) {

    override fun converts(context: Context, helper: ViewHolder, item: SystemTreeItem) {
        helper.setText(R.id.mTvTitle, item.getContent())
    }
}