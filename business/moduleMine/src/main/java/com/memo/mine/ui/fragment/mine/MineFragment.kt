package com.memo.mine.ui.fragment.mine


import android.annotation.SuppressLint
import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.entity.remote.RankPoint
import com.memo.base.manager.data.DataManager
import com.memo.base.manager.router.RouterPath
import com.memo.base.manager.user.AppManager
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.mine.R
import com.memo.mine.ui.activity.about.AboutActivity
import com.memo.mine.ui.activity.collect.CollectActivity
import com.memo.tool.dialog.dialog.AlertDialog
import com.memo.tool.ext.onViewsClickListener
import com.memo.tool.ext.paddingStatusBar
import com.memo.tool.ext.startActivity
import com.memo.tool.helper.AnimHelper
import com.memo.tool.helper.ImageLoadHelper
import kotlinx.android.synthetic.main.fragment_mine.*

/**
 * title:我的界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:21
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@SuppressLint("SetTextI18n")
@Route(path = RouterPath.Mine.MineFragment)
class MineFragment : BaseMvpFragment<MineView, MinePresenter>(), MineView {

    private var isHead = true

    private val toTrailAnim by lazy {
        AnimHelper.createRotate3dAnimGo(
            mFlContainer,
            mIvIconHead,
            mIvIconTail,
            200f,
            1000
        )
    }

    private val toHeadAnim by lazy {
        AnimHelper.createRotate3dAnimGo(
            mFlContainer,
            mIvIconTail,
            mIvIconHead,
            200f,
            1000
        )
    }

    private val mExitDialog by lazy {
        AlertDialog(
            mActivity,
            message = "是否退出账号？"
        ).setOnTipClickListener {
            mPresenter.loginOut()
            AppManager.get().exit()
        }
    }

    /*** 绑定Presenter 如果多个Presenter 返回建议是当前页面的Presenter ***/
    override fun buildPresenter(): MinePresenter = MinePresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_mine

    override fun initData() {
        DataManager.get().getUser()?.let {
            mTvName.text = it.nickname
            if (it.icon.isNotEmpty()) {
                ImageLoadHelper.loadImage(mActivity, it.icon, mIvIconHead)
            }
        }
    }

    override fun initView() {
        mLoadService.showSuccess()
        mIvExit.paddingStatusBar()
    }

    override fun initListener() {
        onViewsClickListener({
            when (it.id) {
                // 账号退出
                R.id.mIvExit -> {
                    mExitDialog.show()
                }
                // 头像点击
                R.id.mFlContainer -> {
                    if ((toTrailAnim.hasStarted() && toTrailAnim.hasEnded().not()) ||
                        (toHeadAnim.hasStarted() && toHeadAnim.hasEnded().not())
                    ) {
                        return@onViewsClickListener
                    }
                    if (isHead) {
                        mFlContainer.startAnimation(toTrailAnim)
                    } else {
                        mFlContainer.startAnimation(toHeadAnim)
                    }
                    isHead = !isHead
                }
                // 收藏
                R.id.mItemCollect -> {
                    startActivity<CollectActivity>()
                }
                // TODO清单
                R.id.mItemTodo -> {
                }
                // 设置
                R.id.mItemSetting -> {
                }
                // 关于
                R.id.mItemAbout -> {
                    startActivity<AboutActivity>()
                }
            }
        }, mIvExit, mFlContainer, mItemCollect, mItemTodo, mItemSetting, mItemAbout)
    }


    override fun start() {
        mPresenter.getUserCoin()
    }

    override fun getUserCoinSuccess(response: RankPoint) {
        mTvPoint.text = "积分：${response.coinCount}"
        mTvRank.text = "排名：${response.rank}"
    }

}
