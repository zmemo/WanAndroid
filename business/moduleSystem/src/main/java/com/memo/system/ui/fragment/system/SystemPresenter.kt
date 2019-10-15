package com.memo.system.ui.fragment.system

import com.memo.base.ui.mvp.BasePresenter

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:03
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemPresenter : BasePresenter<SystemModel, SystemView>() {
    /**
     * 绑定Model
     */
    override fun buildModel(): SystemModel = SystemModel()


}