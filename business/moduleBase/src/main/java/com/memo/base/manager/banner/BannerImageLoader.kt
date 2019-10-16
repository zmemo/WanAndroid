package com.memo.base.manager.banner

import android.content.Context
import android.widget.ImageView
import com.memo.tool.helper.ImageLoadHelper
import com.youth.banner.loader.ImageLoader

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 12:58
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        ImageLoadHelper.loadImage(context, path, imageView)
    }
}