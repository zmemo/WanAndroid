package com.memo.home.ui.fragment.home

import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomePresenter : BasePresenter<HomeModel, HomeView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): HomeModel = HomeModel()
}