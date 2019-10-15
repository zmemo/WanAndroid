package com.memo.home.ui.fragment.home


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.home.R

/**
 * title:主页
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Home.HomeFragment)
class HomeFragment : BaseMvpFragment<HomeView, HomePresenter>() {

    /*** 绑定Presenter ***/
    override fun buildPresenter(): HomePresenter = HomePresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_home

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
    }


}
