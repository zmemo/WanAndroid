package com.memo.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 17:29
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

data class ArticleTree(
    val children: ArrayList<Int> = arrayListOf(),
    val courseId: Int = 0, // 13
    val id: Int = 0, // 294
    val name: String = "", // 完整项目
    val order: Int = 0, // 145000
    val parentChapterId: Int = 0, // 293
    val userControlSetTop: Boolean = false, // false
    val visible: Int = 0 // 0
)