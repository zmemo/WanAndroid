package com.memo.system.ui.activity

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 22:55
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemClassicPresenter:BasePresenter<SystemClassicModel,SystemClassicView>() {
    override fun buildModel(): SystemClassicModel = SystemClassicModel()

    fun getArticles(cid:Int,page:Int){
        mModel.getSystemClassicArticles(cid,page)
            .execute(mView,isFirstLoad,{
                isFirstLoad = false
                mView.getArticleSuccess(it)
            },{
                mView.getArticleFailure()
            })
    }
}