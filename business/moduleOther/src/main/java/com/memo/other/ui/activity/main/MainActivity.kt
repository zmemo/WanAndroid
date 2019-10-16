package com.memo.other.ui.activity.main

import com.memo.base.manager.router.RouterManager
import com.memo.base.ui.activity.BaseActivity
import com.memo.other.R
import com.memo.tool.helper.ClickHelper
import com.memo.tool.helper.FragmentHelper
import kotlinx.android.synthetic.main.activity_main.*

/**
 * title:主框架
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:55
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class MainActivity : BaseActivity() {

    private val mFragmentHelper by lazy { FragmentHelper(R.id.mFlContainer, supportFragmentManager) }
    private val mHomeFragment by lazy { RouterManager.get().getHomeFragment() }
    private val mProjectFragment by lazy { RouterManager.get().getProjectFragment() }
    private val mBlogFragment by lazy { RouterManager.get().getBlogFragment() }
    private val mSystemFragment by lazy { RouterManager.get().getSystemFragment() }
    private val mMineFragment by lazy { RouterManager.get().getMineFragment() }

    override fun transparentStatusBar(): Boolean = true

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_main

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mFragmentHelper.add(mHomeFragment, mProjectFragment, mBlogFragment, mSystemFragment, mMineFragment)
            .show()
        mBottomView.setOnItemChangeListener { menuItem, position ->
            mFragmentHelper.show(position)
        }
    }


    override fun onBackPressed() {
        ClickHelper.doubleClickExit()
    }
}
