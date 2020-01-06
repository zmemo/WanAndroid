package com.memo.system.ui.fragment.system.main

import androidx.fragment.app.Fragment
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
    private val fragments by lazy {
        arrayListOf(
            SystemItemFragment.newInstance(SystemItemFragment.TYPE_SYSTEM),
            SystemItemFragment.newInstance(SystemItemFragment.TYPE_NAVIGATION)
        )
    }
    private val mAdapter by lazy { BaseFragmentPagerAdapter<Fragment>(childFragmentManager) }

    override fun bindLayoutResId(): Int = R.layout.fragment_system

    override fun initialize() {
        mRootView.paddingStatusBar()
        mViewPager.run {
            offscreenPageLimit = 2
            mAdapter.setData(fragments,titles)
            adapter = mAdapter
        }

        mTabLayout.setTabData(titles)
        mTabLayout.setOnTabSelectListener(object :OnTabSelectListener{
            override fun onTabReselect(position: Int) {}
            override fun onTabSelect(position: Int) {
                mViewPager.currentItem = position
            }
        })
        mViewPager.addOnPageChangeListener(object :ViewPager.SimpleOnPageChangeListener(){
            override fun onPageSelected(position: Int) {
                mTabLayout.currentTab = position
            }
        })
    }

    override fun lazyInitialize() {}


}
