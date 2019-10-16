package com.memo.blog.ui.fragment.blog.item

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 23:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BlogItemPresenter : BasePresenter<BlogItemModel, BlogItemView>() {

    override fun buildModel(): BlogItemModel = BlogItemModel()

    fun getArticles(id:Int,page:Int){
        mModel.getArticles(id,page)
            .execute(mView,isFirstLoad,{
                isFirstLoad = false
                mView.getArticleSuccess(it)
            },{
                mView.getArticleFailure()
            })
    }

}