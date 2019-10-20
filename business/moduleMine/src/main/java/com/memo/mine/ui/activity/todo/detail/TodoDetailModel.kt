package com.memo.mine.ui.activity.todo.detail

import android.annotation.SuppressLint
import com.blankj.utilcode.util.TimeUtils
import com.memo.base.entity.remote.TodoInfo
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.mine.api.mApiService
import io.reactivex.Observable
import java.text.SimpleDateFormat

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-18 15:18
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoDetailModel : IModel {

    fun addTodo(title: String, content: String): Observable<TodoInfo> {
        return mApiService.addTodo(title, content).convert()
    }

    @SuppressLint("SimpleDateFormat")
    fun updateTodo(id: Int, title: String, content: String): Observable<TodoInfo> {
        return mApiService.updateTodo(id, title, content, TimeUtils.getNowString(SimpleDateFormat("yyyy-MM-dd"))).convert()
    }
}