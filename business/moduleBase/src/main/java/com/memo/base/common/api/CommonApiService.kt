package com.memo.base.common.api

import com.memo.base.api.BaseResponse
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.POST
import retrofit2.http.Path

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