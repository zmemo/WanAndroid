package com.memo.base.entity.remote

import com.chad.library.adapter.base.entity.MultiItemEntity

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 00:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
const val TYPE_SYSTEM_TITLE = 1
const val TYPE_SYSTEM_CONTENT = 2

data class SystemTree(
    val children: ArrayList<SystemTreeItem> = arrayListOf(),
    val articles: ArrayList<SystemTreeItem> = arrayListOf(),
    val id: Int = 0, // 493
    val name: String = "" // 广场Tab
)

data class SystemTreeItem(
    val id: Int = 0, // 494
    val name: String = "", // 广场
    val title: String = "",
    val link: String = "",
    var multiType: Int = TYPE_SYSTEM_CONTENT
) : MultiItemEntity {
    override fun getItemType(): Int = multiType

    fun getContent() = if (name.isEmpty()) {
        title
    } else {
        name
    }
}
