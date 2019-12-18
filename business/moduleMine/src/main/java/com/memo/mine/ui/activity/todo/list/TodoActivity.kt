package com.memo.mine.ui.activity.todo.list

import android.app.Activity
import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.memo.base.entity.remote.STATUS_TODO_DONE
import com.memo.base.entity.remote.STATUS_TODO_UNDONE
import com.memo.base.entity.remote.TodoInfo
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.mine.R
import com.memo.mine.ui.activity.todo.detail.TodoDetailActivity
import com.memo.mine.ui.adapter.TodoAdapter
import com.memo.tool.dialog.dialog.LocateListDialog
import com.memo.tool.ext.dimen
import com.memo.tool.ext.onClick
import com.memo.tool.helper.finish
import com.memo.widget.recyclerview.RecyclerItemDecoration
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.activity_todo.*
import java.util.*

/**
 * title:Todo列表
 * describe:
 *
 * @author memo
 * @date 2019-10-18 15:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoActivity : BaseMvpActivity<TodoView, TodoPresenter>(), TodoView {

    private var page = 0

    private var status: Int? = null

    private val filters = arrayListOf("未完成", "已完成", "全部")

    private val mFilterDialog by lazy { LocateListDialog(mContext, filters) }

    private val REQUEST_CODE_REFRESH = 1

    private val mAdapter by lazy { TodoAdapter() }

    private val mDivider by lazy {
        RecyclerItemDecoration.Builder(mContext)
            .setMarginLeftDp(15f)
            .setMarginRightDp(15f)
            .setColorRes(R.color.color_F5F5F5)
            .build()
    }

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): TodoPresenter = TodoPresenter()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_todo

    override fun initData() {
    }

    override fun initView() {
        mLoadService.showSuccess()
        mBtnAdd.setMargin(dimen(R.dimen.dp5))

        mRvList.run {
            layoutManager = LinearLayoutManager(mContext)
            addItemDecoration(mDivider)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mTitleView.setOnRightClickListener {
            mFilterDialog.showVertical(mTitleView.getRightView())
        }

        mFilterDialog.setOnItemClickListener { position, _ ->
            status = if (position == 2) {
                null
            } else {
                position
            }
            mPresenter.queryAllTodo(page, status)
        }


        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.queryAllTodo(page, status)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.queryAllTodo(page, status)
            }

        })
        mBtnAdd.onClick {
            TodoDetailActivity.start(mContext, null, REQUEST_CODE_REFRESH)
        }
        mAdapter.setOnItemChildClickListener { _, view, position ->
            val todo = mAdapter.data[position]
            when (view.id) {
                R.id.mTvUnDone -> {
                    showLoading("更新中")
                    mPresenter.updateStatus(position, todo.id, false)
                }
                R.id.mTvDone -> {
                    showLoading("更新中")
                    mPresenter.updateStatus(position, todo.id, true)
                }
                R.id.mTvDelete -> {
                    showLoading("删除中")
                    mPresenter.deleteTodo(position, todo.id)
                }
                R.id.mItemView -> {
                    TodoDetailActivity.start(mContext, todo, REQUEST_CODE_REFRESH)
                }
            }
        }
    }

    override fun start() {
        mPresenter.queryAllTodo(page, status)
    }

    override fun queryAllTodoSuccess(response: ArrayList<TodoInfo>) {
        mRefreshLayout.finish(response.isEmpty())
        if (page == 0) {
            mAdapter.setNewData(response)
        } else {
            mAdapter.addData(response)
        }
    }

    override fun queryAllTodoFailure() {
        mRefreshLayout.finish(false)
        if (page > 0) page--
    }

    override fun updateStatusSuccess(position: Int, done: Boolean) {
        mAdapter.data[position].status = if (done) STATUS_TODO_DONE else STATUS_TODO_UNDONE
        mAdapter.notifyItemChanged(position)
    }

    override fun deleteTodoSuccess(position: Int) {
        mAdapter.remove(position)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (Activity.RESULT_OK == resultCode && REQUEST_CODE_REFRESH == requestCode) {
            page = 0
            status = null
            mPresenter.queryAllTodo(page, status)
        }
    }

}
