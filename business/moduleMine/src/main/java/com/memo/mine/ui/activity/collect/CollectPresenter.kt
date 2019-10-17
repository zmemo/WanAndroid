package com.memo.mine.ui.activity.collect

import com.memo.base.manager.data.DataManager
import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 14:25
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class CollectPresenter : BasePresenter<CollectModel, CollectView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): CollectModel = CollectModel()

    fun getCollects(page: Int) {
        mModel.getCollects(page)
            .doOnNext { articles ->
                val user = DataManager.get().getUser()
                user?.let {
                    articles.forEach { article ->
                        user.addCollect(article.originId)
                    }
                    DataManager.get().putUser(it)
                }
            }
            .execute(mView, isFirstLoad, {
                isFirstLoad = false
                mView.getCollectsSuccess(it)
            }, {
                mView.getCollectsFailure()
            })
    }

    fun unCollectInCollect(id: Int, originId: Int, position: Int) {
        mView.showLoading("取消收藏")
        mModel.unCollectInCollect(id, originId)
            .doOnNext {
                DataManager.get().getUser()?.let {
                    it.removeCollect(originId)
                    DataManager.get().putUser(it)
                }
            }
            .execute(mView, { mView.unCollectSuccess(position) })
    }
}