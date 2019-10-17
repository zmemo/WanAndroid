package com.memo.home.ui.adapter

import com.memo.home.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 01:08
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class FlexAdapter : BaseRecyclerAdapter<String>(R.layout.item_search) {
    override fun converts(helper: ViewHolder, item: String) {
        helper.setText(R.id.mTvWord, item)
    }
}