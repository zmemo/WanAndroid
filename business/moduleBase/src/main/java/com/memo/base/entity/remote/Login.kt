package com.memo.base.entity.remote

import android.os.Parcel
import android.os.Parcelable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 15:45
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
data class UserInfo(
    val admin: Boolean = false, // false
    val collectIds: ArrayList<Int> = arrayListOf(),
    val email: String = "",
    val icon: String = "",
    val id: Int = 0, // 15368
    val nickname: String = "", // Mr.Memo
    val password: String = "",
    val publicName: String = "", // Mr.Memo
    val token: String = "",
    val type: Int = 0, // 0
    val username: String = "" // Mr.Memo
)

data class User(
    var collectIds: String = "",
    val email: String = "",
    val icon: String = "",
    val id: Int = 0, // 15368
    val nickname: String = "", // Mr.Memo
    val username: String = "" // Mr.Memo
) : Parcelable {

    fun hasCollected(id: Int): Boolean {
        val split = ArrayList(collectIds.split(","))
        return split.contains(id.toString())
    }

    fun addCollect(id: Int) {
        if (collectIds.isNotEmpty()) {
            val split = collectIds.split(",")
            if (!split.contains(id.toString())) {
                collectIds += ",$id"
            }
        } else {
            collectIds = id.toString()
        }
    }

    fun removeCollect(id: Int) {
        if (collectIds.isNotEmpty()) {
            val split = ArrayList(collectIds.split(","))
            if (split.contains(id.toString())) {
                split.remove(id.toString())
            }
            val builder = StringBuilder()
            split.forEachIndexed { index, idStr ->
                if (idStr.isNotEmpty()) {
                    if (index == 0) {
                        builder.append(idStr)
                    } else {
                        builder.append(",").append(idStr)
                    }
                }
            }
            collectIds = builder.toString()
        }
    }

    constructor(source: Parcel) : this(
        source.readString() ?: "",
        source.readString() ?: "",
        source.readString() ?: "",
        source.readInt(),
        source.readString() ?: "",
        source.readString() ?: ""
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(collectIds)
        writeString(email)
        writeString(icon)
        writeInt(id)
        writeString(nickname)
        writeString(username)
    }

    companion object {
        @JvmField
        val CREATOR: Parcelable.Creator<User> = object : Parcelable.Creator<User> {
            override fun createFromParcel(source: Parcel): User = User(source)
            override fun newArray(size: Int): Array<User?> = arrayOfNulls(size)
        }
    }
}