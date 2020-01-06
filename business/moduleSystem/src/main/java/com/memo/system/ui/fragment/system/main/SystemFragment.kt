package com.memo.system.ui.fragment.system.main

import androidx.viewpager.widget.ViewPager
import com.alibaba.android.arouter.facade.annotation.Route
import com.flyco.tablayout.listener.OnTabSelectListener
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseFragment
import com.memo.system.R
import com.memo.system.ui.fragment.system.item.SystemItemFragment
import com.memo.tool.adapter.BaseFragmentPagerAdapter
import com.memo.tool.ext.paddingStatusBar
import kotlinx.android.synthetic.main.fragment_system.*

/**
 * title:体系界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:57
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.System.SystemFragment)
class SystemFragment : BaseFragment() {

    private val titles = arrayOf("体系", "导航")

    private val mSystemFragment = SystemItemFragment.newInstance(SystemItemFragment.TYPE_SYSTEM)
    private val mNavigationFragment = SystemItemFragment.newInstance(SystemItemFragment.TYPE_NAVIGATION)


    override fun bindLayoutResId(): Int = R.layout.fragment_system

    override fun initialize() {
        mRootView.paddingStatusBar()
        mViewPager.run {
            val mAdapter = BaseFragmentPagerAdapter(childFragmentManager) {
                if (it == 0) mSystemFragment else mNavigationFragment
            }
            mAdapter.setData(2, titles)
            adapter = mAdapter
        }

        mTabLayout.setTabData(titles)
        mTabLayout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabReselect(position: Int) {}
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }
        })
        mViewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                mTabLayout.currentTab = position
            }
        })
    }

    override fun lazyInitialize() {}


}
