package com.memo.base.common.ui.article

import android.animation.Animator
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.KeyEvent
import com.blankj.utilcode.util.LogUtils
import com.just.agentweb.AgentWeb
import com.memo.base.R
import com.memo.base.manager.data.DataManager
import com.memo.base.ui.activity.BaseMvpActivity
import com.memo.tool.ext.gone
import com.memo.tool.ext.startActivity
import com.memo.tool.ext.startActivityForResult
import com.memo.tool.ext.visible
import com.memo.tool.helper.WebHelper
import com.memo.tool.simple.SimpleAnimatorListener
import kotlinx.android.synthetic.main.activity_article.*

/**
 * title:文章网页
 * describe:
 *
 * @author memo
 * @date 2019-10-17 15:38
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ArticleActivity : BaseMvpActivity<ArticleView, ArticlePresenter>(), ArticleView {

    private var title: String = "详情"

    private var url: String = ""

    private var id: Int = -1

    private lateinit var mAgentWeb: AgentWeb

    /*** 判断是否已经收藏 ***/
    private var isCollected = false

    private val mAnimListener: Animator.AnimatorListener by lazy {
        object : SimpleAnimatorListener() {
            override fun onAnimationEnd(animation: Animator?, isReverse: Boolean) {
                mLottieView.gone()
            }
        }
    }

    companion object {
        fun start(context: Context, id: Int, title: String, url: String) {
            context.startActivity<ArticleActivity>("id" to id, "title" to title, "url" to url)
        }

        fun startForResult(activity: Activity, id: Int, title: String, url: String, requestCode: Int) {
            activity.startActivityForResult<ArticleActivity>("id" to id, "id" to id, "title" to title, "url" to url, requestCode = requestCode)
        }
    }

    override fun alwaysPortrait(): Boolean = false

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): ArticlePresenter = ArticlePresenter()

    /*** 绑定布局id ***/
    override fun bindLayoutResId(): Int = R.layout.activity_article

    override fun initData() {
        title = intent.getStringExtra("title") ?: title
        url = intent.getStringExtra("url") ?: url
        id = intent.getIntExtra("id", id)
    }

    override fun initView() {
        mLoadService.showSuccess()

        if (title.isEmpty()) {
            mTitleView.gone()
        } else {
            mTitleView.setTitle(title)
        }
        mAgentWeb = WebHelper.init(mContext, mFlContainer, url)

        // 设置是否收藏
        val user = DataManager.get().getUser()
        LogUtils.iTag("collect", user?.collectIds)
        if (user == null) {
            mTitleView.setRightDrawable(R.drawable.ic_uncollect)
            isCollected = false
        } else {
            mTitleView.setRightDrawable(
                if (user.hasCollected(id)) {
                    isCollected = true
                    R.drawable.ic_collected
                } else {
                    isCollected = false
                    R.drawable.ic_uncollect
                }
            )
        }
    }

    override fun initListener() {
        mLottieView.addAnimatorListener(mAnimListener)
        mTitleView.setOnRightClickListener {
            if (isCollected) {
                mPresenter.unCollectInArticle(id)
            } else {
                mPresenter.collect(id)
                mLottieView.visible()
                mLottieView.playAnimation()
            }
        }
    }

    override fun start() {}

    /**
     * 收藏成功之后手动维护用户收藏数据
     */
    override fun collectSuccess() {
        LogUtils.iTag("collect", DataManager.get().getUser()?.collectIds)
        DataManager.get().getUser()?.let {
            it.addCollect(id)
            DataManager.get().putUser(it)
        }
        mTitleView.setRightDrawable(R.drawable.ic_collected)
        isCollected = true
        LogUtils.iTag("collect", DataManager.get().getUser()?.collectIds)
    }

    /**
     * 取消收藏之后手动维护用户收藏数据
     */
    override fun unCollectSuccess() {
        LogUtils.iTag("collect", DataManager.get().getUser()?.collectIds)
        DataManager.get().getUser()?.let {
            it.removeCollect(id)
            DataManager.get().putUser(it)
        }
        mTitleView.setRightDrawable(R.drawable.ic_uncollect)
        isCollected = false
        LogUtils.iTag("collect", DataManager.get().getUser()?.collectIds)
    }

    override fun finish() {
        if (isCollected) {
            super.finish()
        } else {
            val intent = Intent()
            intent.putExtra("originId", id)
            setResult(Activity.RESULT_OK, intent)
            super.finish()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (WebHelper.onKeyDown(mAgentWeb, keyCode, event)) {
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    override fun onPause() {
        WebHelper.onPause(mAgentWeb)
        super.onPause()
    }

    override fun onResume() {
        WebHelper.onResume(mAgentWeb)
        super.onResume()
    }

    override fun onDestroy() {
        WebHelper.onDestroy(mAgentWeb)
        mLottieView.removeAllAnimatorListeners()
        super.onDestroy()
    }

}
