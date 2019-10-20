package com.memo.mine.ui.activity.todo.list

import com.memo.base.entity.remote.STATUS_TODO_DONE
import com.memo.base.entity.remote.STATUS_TODO_UNDONE
import com.memo.base.entity.remote.TodoInfo
import com.memo.base.manager.retrofit.convert
import com.memo.base.manager.retrofit.convertAny
import com.memo.base.ui.mvp.IModel
import com.memo.mine.api.mApiService
import io.reactivex.Observable

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
class TodoModel : IModel {

    fun queryTodo(page: Int, status: Int?): Observable<ArrayList<TodoInfo>> {
        return mApiService.queryTodo(page, status).convert().map { it.datas }
    }

    fun deleteTodo(id: Int): Observable<Any> {
        return mApiService.deleteTodo(id).convertAny()
    }

    fun updateTodoStatus(id: Int, isDone: Boolean): Observable<Any> {
        return mApiService.updateTodoStatus(
            id, if (isDone) {
                STATUS_TODO_DONE
            } else {
                STATUS_TODO_UNDONE
            }
        ).convertAny()
    }
}