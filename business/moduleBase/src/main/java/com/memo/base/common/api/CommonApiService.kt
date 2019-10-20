package com.memo.base.common.api

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.UserInfo
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 15:27
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

val mCommonApiService = RetrofitManager.create(CommonApiService::class.java)

interface CommonApiService {

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

    /**
     * 收藏文章
     * @param id 文章id
     */
    @POST(value = "lg/collect/{id}/json")
    fun collect(@Path("id") id: Int): Observable<BaseResponse<Any>>

    /**
     * 在文章列表中取消收藏
     * @param id 文章id
     */
    @POST(value = "lg/uncollect_originId/{id}/json")
    fun unCollectInArticle(@Path("id") id: Int): Observable<BaseResponse<Any>>


}