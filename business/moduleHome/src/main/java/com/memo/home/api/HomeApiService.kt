package com.memo.home.api

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.ArticleData
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.BannerInfo
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 10:41
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

val mApiService: HomeApiService = RetrofitManager.create(HomeApiService::class.java)

interface HomeApiService {

    /**
     * 首页轮播图
     */
    @GET("banner/json")
    fun getBanner(): Observable<BaseResponse<ArrayList<BannerInfo>>>

    /**
     * 首页文章
     * @param page 页码
     */
    @GET("article/list/{page}/json")
    fun getHomeArticle(@Path("page") page: Int): Observable<BaseResponse<ArticleData>>

    /**
     * 置顶文章
     */
    @GET("article/top/json")
    fun getHomeTopArticle(): Observable<BaseResponse<ArrayList<ArticleInfo>>>


}