package com.memo.system.api

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.ArticleData
import com.memo.base.entity.remote.SystemTree
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 00:27
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
val mApiService = RetrofitManager.create(SystemApiService::class.java)

interface SystemApiService {

    /**
     * 获取体系数据
     */
    @GET(value = "tree/json")
    fun getSystemTree(): Observable<BaseResponse<ArrayList<SystemTree>>>

    /**
     * 获取导航数据
     */
    @GET(value = "navi/json")
    fun getNavigationTree(): Observable<BaseResponse<ArrayList<SystemTree>>>

    /**
     * 获取体系文章
     */
    @GET(value = "article/list/{page}/json")
    fun getSystemClassicArticles(@Path("page") page: Int, @Query("cid") cid: Int):Observable<BaseResponse<ArticleData>>
}