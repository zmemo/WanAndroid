package com.memo.project.ui.fragment.project.main

import com.memo.base.entity.remote.ProjectTree
import com.memo.base.ui.mvp.IView
import java.util.*

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:10
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
interface ProjectView : IView {
    fun getProjectTreeSuccess(response: ArrayList<ProjectTree>)
}