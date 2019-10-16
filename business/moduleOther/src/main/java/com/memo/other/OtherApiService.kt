package com.memo.other

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.UserInfo
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * title:Api接口
 * describe:
 *
 * @author memo
 * @date 2019-10-15 15:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

val mApiService = RetrofitManager.create(OtherApiService::class.java)

interface OtherApiService {

    /**
     * 登陆
     * @param username 用户名
     * @param password 密码
     */
    @POST(value = "user/login")
    fun login(
        @Query("username") username: String,
        @Query("password") password: String
    ): Observable<BaseResponse<UserInfo>>

    /**
     * 注册
     * @param username 用户名
     * @param password 密码
     * @param repassword 重复密码
     */
    @POST(value = "user/register")
    fun register(
        @Query("username") username: String,
        @Query("password") password: String,
        @Query("repassword") repassword: String
    ): Observable<BaseResponse<Any>>
}