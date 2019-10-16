package com.memo.base.common.adapter

import android.graphics.Color
import com.memo.base.R
import com.memo.base.entity.remote.ArticleInfo
import com.memo.iframe.tools.ext.fromHtml
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
class ArticleAdapter : BaseRecyclerAdapter<ArticleInfo>(R.layout.item_article) {
    /**
     * 转换 数据都不为空
     */
    override fun converts(helper: ViewHolder, item: ArticleInfo) {
        val showPic = item.envelopePic.isNotEmpty()
        helper.setText(
            R.id.mTvName, "作者：${if (item.author.isEmpty()) {
                "匿名"
            } else {
                item.author
            }}"
        )
            .setText(R.id.mTvTitle, item.title)
            .setText(R.id.mTvDesc, item.desc.fromHtml())
            .setText(R.id.mTvChapter, "${item.superChapterName} · ${item.chapterName}")
            .setText(R.id.mTvTime, item.niceDate)
            .setGone(R.id.mIvPic, showPic)
            .setGone(R.id.mTvTop, item.isTop)
            .setBackgroundColor(
                R.id.mItemView, if (item.isTop) {
                    color(R.color.color_bg_top)
                } else {
                    Color.WHITE
                }
            )
            .addOnClickListener(R.id.mClContent)

        if (showPic) {
            ImageLoadHelper.loadImage(mContext, item.envelopePic, helper.getView(R.id.mIvPic))
        }
    }
}