package com.memo.system.ui.fragment.system.item

import com.memo.base.entity.remote.SystemTreeItem
import com.memo.base.entity.remote.TYPE_SYSTEM_TITLE
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.system.api.mApiService
import io.reactivex.Observable

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-17 00:04
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SystemItemModel : IModel {

    fun getSystemTree(): Observable<ArrayList<SystemTreeItem>> {
        return mApiService.getSystemTree().convert().map {
            val data = arrayListOf<SystemTreeItem>()
            it.forEach { tree ->
                data.add(SystemTreeItem(tree.id, tree.name, multiType = TYPE_SYSTEM_TITLE))
                data.addAll(tree.children)
            }
            data
        }
    }

    fun getNavigationTree(): Observable<ArrayList<SystemTreeItem>> {
        return mApiService.getNavigationTree().convert().map {
            val data = arrayListOf<SystemTreeItem>()
            it.forEach { tree ->
                data.add(SystemTreeItem(tree.id, tree.name, multiType = TYPE_SYSTEM_TITLE))
                data.addAll(tree.articles)
            }
            data
        }
    }
}