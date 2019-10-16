package com.memo.system.ui.fragment.system.item


import androidx.annotation.IntRange
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.GridLayoutManager
import com.memo.base.entity.remote.SystemTreeItem
import com.memo.base.entity.remote.TYPE_SYSTEM_TITLE
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.system.R
import com.memo.system.ui.adapter.TreeAdapter
import com.memo.tool.helper.toast
import kotlinx.android.synthetic.main.fragment_system_item.*

/**
 * title:体系和导航界面
 * describe:
 *
 * @author memo
 * @date 2019-10-17 00:04
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemItemFragment : BaseMvpFragment<SystemItemView, SystemItemPresenter>(), SystemItemView {

    /*** 界面类型 ***/
    private var type = 1

    /*** 适配器 ***/
    private val mAdapter by lazy { TreeAdapter() }

    companion object {
        // 体系
        const val TYPE_SYSTEM = 1
        // 导航
        const val TYPE_NAVIGATION = 2

        fun newInstance(@IntRange(from = 1, to = 2) type: Int): SystemItemFragment {
            val fragment = SystemItemFragment()
            fragment.arguments = bundleOf("type" to type)
            return fragment
        }
    }

    override fun buildPresenter(): SystemItemPresenter = SystemItemPresenter()

    override fun bindLayoutResId(): Int = R.layout.fragment_system_item

    override fun initData() {
        type = arguments?.getInt("type", type) ?: type
    }

    override fun initView() {
        mRvList.run {
            layoutManager = GridLayoutManager(mActivity, 3)
            mAdapter.setSpanSizeLookup { _, position ->
                when (mAdapter.data[position].itemType) {
                    TYPE_SYSTEM_TITLE -> 3
                    else -> 1
                }
            }
            adapter = mAdapter
        }
    }

    override fun initListener() {
        mAdapter.setOnItemClickListener { _, _, position ->
            val item = mAdapter.data[position]
            toast(item.id)
        }
    }

    override fun start() {
        mPresenter.getData(type)
    }

    override fun getSystemTreeSuccess(response: ArrayList<SystemTreeItem>) {
        mAdapter.setNewData(response)
    }
}
