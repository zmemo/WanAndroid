package com.memo.system.ui.fragment.system.item

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 00:05
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemItemPresenter : BasePresenter<SystemItemModel, SystemItemView>() {

    override fun buildModel(): SystemItemModel = SystemItemModel()

    fun getData(type:Int){
        when(type){
            SystemItemFragment.TYPE_SYSTEM ->{
                mModel.getSystemTree()
                    .execute(mView,isFirstLoad,{
                        isFirstLoad = false
                        mView.getSystemTreeSuccess(it)
                    })
            }

            SystemItemFragment.TYPE_NAVIGATION->{
                mModel.getNavigationTree()
                    .execute(mView,isFirstLoad,{
                        isFirstLoad = false
                        mView.getSystemTreeSuccess(it)
                    })
            }
        }
    }
}