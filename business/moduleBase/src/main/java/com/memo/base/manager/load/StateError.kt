package com.memo.base.manager.load

import com.kingja.loadsir.callback.Callback
import com.memo.base.R

/**
 * title:错误状态
 * describe:
 *
 * @author memo
 * @date 2019-10-15 20:55
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class StateError :Callback(){
    override fun onCreateView(): Int = R.layout.layout_state_error
}