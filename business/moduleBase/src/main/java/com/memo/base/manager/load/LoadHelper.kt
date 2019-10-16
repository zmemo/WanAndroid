package com.memo.base.manager.load

import android.view.View
import com.kingja.loadsir.core.LoadService
import com.kingja.loadsir.core.LoadSir

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 09:11
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
object LoadHelper {

    fun register(view: View, onRetry: () -> Unit): LoadService<*> {
        return LoadSir.getDefault().register(view) {
            onRetry()
        }
    }
}