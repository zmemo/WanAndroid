package com.memo.mine.ui.fragment.mine

import com.memo.base.entity.remote.RankPoint
import com.memo.base.ui.mvp.IView

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 11:28
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface MineView : IView {
    fun getUserCoinSuccess(response: RankPoint)
}