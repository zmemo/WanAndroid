package com.memo.widget.lottieview

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.airbnb.lottie.LottieAnimationView

/**
 * title:LottieView
 * describe: 在界面可见的时候开启动画，不可见的时候暂停动画
 *
 * @author memo
 * @date 2019-10-25 13:24
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class LottieView : LottieAnimationView {

    private var isAnimatingWhenVisibilityChanged = false

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    /**
     * 在界面可见切换的时候进行判断
     * @param changedView View
     * @param visibility 可见性
     */
    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (visibility == View.VISIBLE && isAnimatingWhenVisibilityChanged) {
            resumeAnimation()
        } else {
            if (isAnimating) {
                isAnimatingWhenVisibilityChanged = true
                pauseAnimation()
            } else {
                isAnimatingWhenVisibilityChanged = false
            }
        }
    }

}