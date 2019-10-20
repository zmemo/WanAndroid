package com.memo.mine.ui.activity.setting

import com.memo.base.manager.retrofit.convertAny
import com.memo.base.ui.mvp.IModel
import com.memo.mine.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 09:22
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SettingModel : IModel {
    fun loginOut(): Observable<Any> {
        return mApiService.loginOut().convertAny()
    }
}