package com.memo.project.api

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.ArticleData
import com.memo.base.entity.remote.ProjectTree
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
 * @date 2019-10-16 17:48
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
val mApiService = RetrofitManager.create(ProjectApiService::class.java)

interface ProjectApiService {

    /**
     * 获取项目树类型
     */
    @GET(value = "project/tree/json")
    fun getProjectTree(): Observable<BaseResponse<ArrayList<ProjectTree>>>

    /**
     * 获取具体项目文章列表
     * @param page 页码
     * @param cid 项目类型
     */
    @GET(value = "project/list/{page}/json")
    fun getProjectArticles(@Path("page") page: Int, @Query("cid") cid: Int): Observable<BaseResponse<ArticleData>>
}