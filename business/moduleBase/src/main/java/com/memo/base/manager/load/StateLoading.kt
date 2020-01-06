package com.memo.base.manager.load

import android.content.Context
import android.view.View
import com.kingja.loadsir.callback.Callback
import com.memo.base.R

/**
 * title:加载状态
 * describe:
 *
 * @author memo
 * @date 2019-10-15 20:55
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class StateLoading :Callback(){
    override fun onCreateView(): Int  = R.layout.layout_state_loading

    override fun onReloadEvent(context: Context?, view: View?): Boolean = true
}