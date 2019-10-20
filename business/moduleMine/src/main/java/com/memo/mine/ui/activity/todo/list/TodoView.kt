package com.memo.mine.ui.activity.todo.list

import com.memo.base.entity.remote.TodoInfo
import com.memo.base.ui.mvp.IView
import java.util.*

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
interface TodoView : IView {
    fun queryAllTodoSuccess(response: ArrayList<TodoInfo>)
    fun queryAllTodoFailure()
    fun updateStatusSuccess(position: Int, done: Boolean)
    fun deleteTodoSuccess(position: Int)
}