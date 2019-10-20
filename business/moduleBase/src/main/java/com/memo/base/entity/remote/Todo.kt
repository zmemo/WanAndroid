package com.memo.base.entity.remote

import android.os.Parcel
import android.os.Parcelable
import com.blankj.utilcode.util.TimeUtils

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
) : Parcelable {
    constructor(source: Parcel) : this(
        source.readLong(),
        source.readString() ?: "",
        source.readString() ?: "",
        source.readLong(),
        source.readString() ?: "",
        source.readInt(),
        source.readInt(),
        source.readInt(),
        source.readString() ?: "",
        source.readInt(),
        source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeLong(completeDate)
        writeString(completeDateStr)
        writeString(content)
        writeLong(date)
        writeString(dateStr)
        writeInt(id)
        writeInt(priority)
        writeInt(status)
        writeString(title)
        writeInt(type)
        writeInt(userId)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<TodoInfo> = object : Parcelable.Creator<TodoInfo> {
            override fun createFromParcel(source: Parcel): TodoInfo = TodoInfo(source)
            override fun newArray(size: Int): Array<TodoInfo?> = arrayOfNulls(size)
        }
    }
}

const val TYPE_TODO_ALL = 0
const val TYPE_TODO_WORK = 1
const val TYPE_TODO_LIFE = 2
const val TYPE_TODO_STUDY = 3
const val TYPE_TODO_OTHER = 4

const val PRIORITY_TODO_IMPORTANCE = 1
const val PRIORITY_TODO_NORMAL = 2

const val STATUS_TODO_UNDONE = 0
const val STATUS_TODO_DONE = 1

const val ORDER_BY_POSITIVE = 1
const val ORDER_BY_NEGATIVE = 2
const val ORDER_BY_CREATE = 3
const val ORDER_BY_COMPLETE = 4

data class QueryTodoRequest(
    val type: Int,
    val orderBy: Int
)

data class AddTodoRequest(
    val title: String,
    val content: String,
    //2019-09-09
    val date: String = TimeUtils.millis2String(System.currentTimeMillis(), "yyyy-MM-dd"),
    //用于自定义的类型 自己判断
    val type: Int = TYPE_TODO_ALL,
    //重要程度
    val priority: Int = PRIORITY_TODO_NORMAL
)

data class UpdateTodoRequest(
    val title: String,
    val content: String,
    val date: String = TimeUtils.millis2String(System.currentTimeMillis(), "yyyy-MM-dd"),
    val status: Int,
    val type: Int,
    val priority: Int
)