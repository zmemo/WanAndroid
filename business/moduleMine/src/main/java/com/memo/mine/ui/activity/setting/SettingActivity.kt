package com.memo.mine.ui.activity.setting

import android.content.Intent
import android.net.Uri
import com.blankj.utilcode.util.CleanUtils
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.FileUtils
import com.blankj.utilcode.util.LogUtils
import com.memo.base.manager.router.RouterManager
import com.memo.base.manager.user.AppManager
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.base.ui.mvp.IView
import com.memo.mine.R
import com.memo.tool.ext.onViewsClickListener
import kotlinx.android.synthetic.main.activity_setting.*

/**
 * title:设置页面
 * describe:
 *
 * @author memo
 * @date 2019-10-18 09:24
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class SettingActivity : BaseMvpActivity<IView, SettingPresenter>() {

    private val feedbackUrl = "https://github.com/hongyangAndroid/wanandroid/issues"
    private val email = "zhou_android@163.com"

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): SettingPresenter = SettingPresenter()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_setting

    override fun initData() {
    }

    override fun initView() {
        mLoadService.showSuccess()
        val size = FileUtils.getFileLength(mContext.cacheDir) + FileUtils.getFileLength(mContext.externalCacheDir)
        if (size > 0) {
            val cacheSize = ConvertUtils.byte2FitMemorySize(size)
            mItemClear.setItemExtraText(cacheSize)
        }
    }

    override fun initListener() {
        onViewsClickListener({
            when (it.id) {
                R.id.mItemClear -> {
                    CleanUtils.cleanInternalCache()
                    CleanUtils.cleanExternalCache()
                    mItemClear.setItemExtraText("")
                }
                R.id.mItemContentFeedback -> {
                    RouterManager.get().startWebActivity(feedbackUrl, "意见反馈")
                }
                R.id.mItemAppFeedback -> {
                    try {
                        val uri = Uri.parse("mailto:$email")
                        val intent = Intent(Intent.ACTION_SENDTO, uri)
                        intent.putExtra(Intent.EXTRA_SUBJECT, "意见反馈")
                        startActivity(Intent.createChooser(intent, "请选邮件应用"))
                    } catch (e: Exception) {
                        LogUtils.eTag("email", e.toString())
                    }
                }
                R.id.mLoginOut -> {
                    mPresenter.loginOut()
                    AppManager.get().exit()
                }
            }

        }, mItemClear, mItemContentFeedback, mItemAppFeedback, mLoginOut)
    }

    override fun start() {
    }
}
