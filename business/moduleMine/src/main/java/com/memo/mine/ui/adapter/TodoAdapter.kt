package com.memo.mine.ui.adapter

import com.memo.base.entity.remote.STATUS_TODO_DONE
import com.memo.base.entity.remote.STATUS_TODO_UNDONE
import com.memo.base.entity.remote.TodoInfo
import com.memo.mine.R
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.ext.color
import com.memo.widget.labelview.LabelView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-20 16:11
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class TodoAdapter : BaseRecyclerAdapter<TodoInfo>(R.layout.item_todo) {

    private val colorImportant = color(R.color.color_label_important)
    val colorNormal = color(R.color.color_label_normal)

    /**
     * 转换 数据都不为空
     */
    override fun converts(helper: ViewHolder, item: TodoInfo) {
        helper.setText(R.id.mTvTitle, item.title)
            .setText(R.id.mTvContent, item.content)
            .setText(R.id.mTvTime, item.dateStr)
            .setGone(R.id.mTvDone, item.status == STATUS_TODO_UNDONE)
            .setGone(R.id.mTvUnDone, item.status == STATUS_TODO_DONE)
            .addOnClickListener(R.id.mTvUnDone, R.id.mTvDone, R.id.mTvDelete, R.id.mItemView)

        val mLabel = helper.getView<LabelView>(R.id.mLabel)
        val isDone = item.status == STATUS_TODO_DONE
        mLabel.text = if (isDone) {
            "已完成"
        } else {
            "未完成"
        }
        mLabel.bgColor = if (isDone) {
            colorImportant
        } else {
            colorNormal
        }


    }
}