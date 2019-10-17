package com.memo.mine.ui.fragment.mine

import com.memo.base.entity.remote.RankPoint
import com.memo.base.manager.retrofit.convert
import com.memo.base.manager.retrofit.convertAny
import com.memo.base.ui.mvp.IModel
import com.memo.mine.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 11:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MineModel : IModel {

    fun getUserCoin(): Observable<RankPoint> {
        return mApiService.getUserCoin().convert()
    }

    fun loginOut(): Observable<Any> {
        return mApiService.loginOut().convertAny()
    }

}