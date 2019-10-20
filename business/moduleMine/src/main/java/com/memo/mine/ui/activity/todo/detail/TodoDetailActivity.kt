package com.memo.mine.ui.activity.todo.detail

import android.app.Activity
import com.memo.base.entity.remote.TodoInfo
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.mine.R
import com.memo.tool.ext.*
import kotlinx.android.synthetic.main.activity_todo_detail.*

class TodoDetailActivity : BaseMvpActivity<TodoDetailView, TodoDetailPresenter>(), TodoDetailView {

    private var mInfo: TodoInfo? = null


    companion object {
        fun start(activity: Activity, info: TodoInfo?, requestCode: Int) {
            activity.startActivityForResult<TodoDetailActivity>("info" to info, requestCode = requestCode)
        }
    }

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): TodoDetailPresenter = TodoDetailPresenter()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_todo_detail

    override fun initData() {
        mInfo = intent.getParcelableExtra("info")
    }

    override fun initView() {
        mLoadService.showSuccess()
        setEditMode(mInfo == null)
    }

    private fun setEditMode(isEdit: Boolean) {
        mTitleView.setRightText(
            if (isEdit) {
                ""
            } else {
                "修改"
            }
        )
        mEtTitle.isFocusable = isEdit
        mEtTitle.isFocusableInTouchMode = isEdit
        mEtContent.isFocusable = isEdit
        mEtContent.isFocusableInTouchMode = isEdit
        if (isEdit) {
            mEtTitle.setSelection(mEtTitle.value.length)
            mEtTitle.requestFocus()
            mEtContent.setSelection(mEtContent.value.length)
            mEtContent.requestFocus()
        }
        mTvSave.setVisible(isEdit)
        mInfo?.let {
            if (!isEdit) {
                mEtTitle.setText(it.title)
                mEtContent.setText(it.content)
            }
        }
    }

    override fun initListener() {
        mTitleView.setOnRightClickListener {
            setEditMode(true)
        }
        mTvSave.onClick {
            if (mInfo == null) {
                //增加
                mPresenter.addTodo(mEtTitle.value, mEtContent.value)
            } else {
                //修改
                mPresenter.updateTodo(mInfo!!.id, mEtTitle.value, mEtContent.value)
            }
        }

    }

    override fun start() {}

    override fun onSuccess() {
        finishActivityWithResult()
    }

}
