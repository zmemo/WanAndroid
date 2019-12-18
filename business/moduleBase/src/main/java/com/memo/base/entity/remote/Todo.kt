package com.memo.base.entity.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 14:27
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class TodoData(val datas: ArrayList<TodoInfo> = arrayListOf())

@Parcelize
data class TodoInfo(
    var completeDate: Long = 0, // 1571328000000
    var completeDateStr: String = "",
    var content: String = "", // 未完成
    var date: Long = 0, // 1571328000000
    var dateStr: String = "", // 2019-10-18
    val id: Int = 0, // 17041
    var priority: Int = 0, // 0
    var status: Int = 0, // 0
    var title: String = "", // 测试
    var type: Int = 0, // 1
    val userId: Int = 0 // 15368
) : Parcelable

const val STATUS_TODO_UNDONE = 0
const val STATUS_TODO_DONE = 1
