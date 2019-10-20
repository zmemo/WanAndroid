package com.memo.mine.api

import com.memo.base.api.BaseResponse
import com.memo.base.entity.remote.ArticleData
import com.memo.base.entity.remote.RankPoint
import com.memo.base.entity.remote.TodoData
import com.memo.base.entity.remote.TodoInfo
import com.memo.base.manager.retrofit.RetrofitManager
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 11:53
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
val mApiService = RetrofitManager.create(MineApiService::class.java)

interface MineApiService {

    /**
     * 退出登陆
     */
    @GET(value = "user/logout/json")
    fun loginOut(): Observable<BaseResponse<Any>>

    /**
     * 用户积分
     */
    @GET(value = "lg/coin/userinfo/json")
    fun getUserCoin(): Observable<BaseResponse<RankPoint>>

    /**
     * 收藏列表
     * @param page 页码
     */
    @GET(value = "lg/collect/list/{page}/json")
    fun getCollects(@Path("page") page: Int): Observable<BaseResponse<ArticleData>>

    /**
     * 取消收藏
     * @param id 收藏文章id
     */
    @POST(value = "lg/uncollect/{id}/json")
    fun unCollectInCollect(@Path("id") id: Int, @Query("originId") originId: Int): Observable<BaseResponse<Any>>

    /**
     * Todo列表
     */
    @POST(value = "lg/todo/v2/list/{page}/json")
    fun queryTodo(@Path("page") page: Int, @Query("status") status: Int?): Observable<BaseResponse<TodoData>>

    /**
     * 添加Todo
     * @param request AddTodoRequest
     */
    @POST(value = "lg/todo/add/json")
    fun addTodo(@Query("title") title: String, @Query("content") content: String): Observable<BaseResponse<TodoInfo>>

    /**
     * 更新Todo详情
     * @param id Todo的id
     * @param request UpdateTodoRequest
     */
    @POST(value = "lg/todo/update/{id}/json")
    fun updateTodo(
        @Path("id") id: Int, @Query("title") title: String,
        @Query("content") content: String, @Query("date") date: String
    ): Observable<BaseResponse<TodoInfo>>

    /**
     * 更新Todo状态
     * @param id Todo的id
     * @param status 状态值
     */
    @POST(value = "lg/todo/done/{id}/json")
    fun updateTodoStatus(@Path("id") id: Int, @Query("status") status: Int): Observable<BaseResponse<Any>>

    /**
     * 删除一个Todo
     * @param id Int
     */
    @POST(value = "lg/todo/delete/{id}/json")
    fun deleteTodo(@Path("id") id: Int): Observable<BaseResponse<Any>>


}