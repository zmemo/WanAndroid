package com.memo.mine.ui.fragment.mine

import com.memo.base.manager.retrofit.execute
import com.memo.base.manager.retrofit.executeNotProcess
import com.memo.base.ui.mvp.BasePresenter

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
class MinePresenter : BasePresenter<MineModel, MineView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): MineModel = MineModel()

    fun getUserCoin() {
        mModel.getUserCoin()
            .executeNotProcess(mView, {
                mView.getUserCoinSuccess(it)
            })
    }

    fun loginOut() {
        mModel.loginOut().execute(mView, {})
    }

}