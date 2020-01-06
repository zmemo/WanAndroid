package com.memo.tool.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter


/**
 * title:ViewPager和Fragment结合的Adapter
 * describe:
 * 注意使用情况添加了behavior，当前不可见的Fragment会执行生命周期到onResume之前（不执行onResume），可以作为懒加载使用
 *
 * @author zhou
 * @date 2019-09-26 15:41
 *
 * Talk is cheap, Show me the code.
 */
@SuppressLint("WrongConstant")
class BaseFragmentPagerAdapter<T : Fragment>(fm: FragmentManager, val getItemFragment: (Int) -> T) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private var withTabLayoutTitles: Array<String>? = null
    private var size = 0

    fun setData(size: Int) {
        this.size = size
        this.notifyDataSetChanged()
    }

    fun setData(size: Int, withTabLayoutTitles: Array<String>) {
        this.size = size
        this.withTabLayoutTitles = withTabLayoutTitles
        this.notifyDataSetChanged()
    }

    override fun getItem(position: Int): Fragment {
        return getItemFragment(position)
    }

    override fun getCount(): Int {
        return size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return if (withTabLayoutTitles != null) {
            withTabLayoutTitles!![position]
        } else super.getPageTitle(position)
    }
}