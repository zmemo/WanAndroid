package com.memo.mine.ui.activity.todo.detail

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 15:19
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoDetailPresenter : BasePresenter<TodoDetailModel, TodoDetailView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): TodoDetailModel = TodoDetailModel()

    fun addTodo(title: String, content: String) {
        mView.showLoading("提交中")
        mModel.addTodo(title, content)
            .execute(mView, { mView.onSuccess() })
    }


    fun updateTodo(id: Int, title: String, content: String) {
        mView.showLoading("更新中")
        mModel.updateTodo(id, title, content)
            .execute(mView, { mView.onSuccess() })
    }
}