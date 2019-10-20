package com.memo.mine.ui.activity.setting

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter
import com.memo.base.ui.mvp.IView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 09:23
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SettingPresenter : BasePresenter<SettingModel, IView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): SettingModel = SettingModel()

    fun loginOut() {
        mModel.loginOut().execute(mView, {})
    }
}