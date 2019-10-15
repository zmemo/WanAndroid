package com.memo.mine.ui.fragment.mine


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.mine.R

/**
 * title:我的界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Mine.MineFragment)
class MineFragment : BaseMvpFragment<MineView, MinePresenter>(), MineView {
    /*** 绑定Presenter ***/
    override fun buildPresenter(): MinePresenter = MinePresenter()


    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_mine

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
    }


}
