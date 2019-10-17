package com.memo.mine.ui.activity.about

import com.memo.base.ui.activity.BaseActivity
import com.memo.mine.R
import kotlinx.android.synthetic.main.activity_about.*

/**
 * title:关于
 * describe:
 *
 * @author memo
 * @date 2019-10-17 14:18
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class AboutActivity : BaseActivity() {
    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_about

    /*** 进行初始化操作 ***/
    override fun initialize() {
        mTvThanks.setText(R.string.about_thanks)
    }

}
