package com.memo.base.common.adapter

import android.graphics.Color
import com.daimajia.swipe.SwipeLayout
import com.memo.base.R
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.utils.IconHelper
import com.memo.tool.ext.fromHtml
import com.memo.tool.adapter.recyclerview.BaseRecyclerAdapter
import com.memo.tool.adapter.recyclerview.ViewHolder
import com.memo.tool.ext.color
import com.memo.tool.helper.ImageLoadHelper

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 10:30
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class ArticleAdapter(val enableSwipe: Boolean = false) : BaseRecyclerAdapter<ArticleInfo>(R.layout.item_article) {

    override fun converts(helper: ViewHolder, item: ArticleInfo) {

        val showPic = item.envelopePic.isNotEmpty()
        helper
            .setImageResource(R.id.mIvIcon, IconHelper.randomIcon(item.chapterId))
            .setText(
                R.id.mTvName, if (item.author.isEmpty()) {
                    "作者：匿名"
                } else {
                    "作者：${item.author}"
                }
            )
            .setText(R.id.mTvTitle, item.title.fromHtml())
            .setText(R.id.mTvDesc, item.desc.fromHtml())
            .setText(
                R.id.mTvChapter, if (item.superChapterName.isNotEmpty()) {
                    "${item.superChapterName} · ${item.chapterName}"
                } else {
                    item.chapterName
                }
            )
            .setText(R.id.mTvTime, item.niceDate)
            .setGone(R.id.mIvPic, showPic)
            .setGone(R.id.mTvTop, item.isTop)
            .setBackgroundColor(
                R.id.mClContent, if (item.isTop) {
                    color(R.color.color_bg_top)
                } else {
                    Color.WHITE
                }
            )
            .addOnClickListener(R.id.mClContent)
            .addOnClickListener(R.id.mFlDelete)
        helper.getView<SwipeLayout>(R.id.mSwipeLayout).isSwipeEnabled = enableSwipe
        if (showPic) {
            ImageLoadHelper.loadImage(mContext, item.envelopePic, helper.getView(R.id.mIvPic))
        }
    }
}