package com.memo.mine.ui.activity.todo.list

import com.memo.base.manager.retrofit.execute
import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 15:07
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoPresenter : BasePresenter<TodoModel, TodoView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): TodoModel = TodoModel()

    fun queryAllTodo(page: Int, status: Int?) {
        mModel.queryTodo(page, status)
            .execute(mView, isFirstLoad, {
                isFirstLoad = false
                mView.queryAllTodoSuccess(it)
            }, {
                mView.queryAllTodoFailure()
            })
    }

    fun updateStatus(position: Int, id: Int, isDone: Boolean) {
        mModel.updateTodoStatus(id, isDone)
            .execute(mView, {
                mView.updateStatusSuccess(position, isDone)
            })
    }

    fun deleteTodo(position: Int, id: Int) {
        mModel.deleteTodo(id)
            .execute(mView, {
                mView.deleteTodoSuccess(position)
            })
    }
}