package com.memo.blog.api

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.ArticleData
import com.memo.base.entity.remote.ArticleTree
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 23:02
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
val mApiService = RetrofitManager.create(BlogApiService::class.java)

interface BlogApiService {

    /**
     * 获取博客的标签
     */
    @GET(value = "wxarticle/chapters/json")
    fun getBlogTree(): Observable<BaseResponse<ArrayList<ArticleTree>>>

    /**
     * 获取博客的文章列表
     * @param id 博客id
     * @param page 页码
     */
    @GET(value = "wxarticle/list/{id}/{page}/json")
    fun getBlogArticles(@Path("id") id: Int, @Path("page") page: Int): Observable<BaseResponse<ArticleData>>
}