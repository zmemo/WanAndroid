package com.memo.other.api

import com.memo.base.api.BaseResponse
import com.memo.base.common.api.CommonApiService
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

val mApiService = RetrofitManager.create(CommonApiService::class.java)
