package com.memo.base.entity.remote

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
    val collectIds: List<Int> = arrayListOf(),
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