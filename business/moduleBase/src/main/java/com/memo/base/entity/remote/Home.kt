package com.memo.base.entity.remote

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-16 10:33
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */

data class HomeData(
    val banners: ArrayList<BannerInfo>,
    val articles: ArrayList<ArticleInfo>
)

data class BannerInfo(
    val desc: String = "", // Android高级进阶直播课免费学习
    val id: Int = 0, // 23
    val imagePath: String = "", // https://wanandroid.com/blogimgs/67c28e8c-2716-4b78-95d3-22cbde65d924.jpeg
    val isVisible: Int = 0, // 1
    val order: Int = 0, // 0
    val title: String = "", // Android高级进阶直播课免费学习
    val type: Int = 0, // 0
    val url: String = "" // https://url.163.com/4bj
)

data class ArticleData(
    val curPage: Int = 0, // 2
    val datas: ArrayList<ArticleInfo> = arrayListOf(),
    val offset: Int = 0, // 20
    val over: Boolean = false, // false
    val pageCount: Int = 0, // 364
    val size: Int = 0, // 20
    val total: Int = 0 // 7265
)

data class ArticleInfo(
    val apkLink: String = "",
    val audit: Int = 0, // 1
    val author: String = "", // xiaoyang
    val chapterId: Int = 0, // 440
    val chapterName: String = "", // 官方
    val collect: Boolean = false, // false
    val courseId: Int = 0, // 13
    val desc: String = "", // <p>作为 Android 开发，这个异常一定都在自家崩溃平台上见过，那么</p><ol><li>有哪些场景下会出现这个异常呢？</li><li>分别如何解决？</li><li>有无一些开源方案参考？</li></ol><p>知道多少答多少哈，别冷场呀~</p>
    val envelopePic: String = "",
    val fresh: Boolean = false, // true
    val id: Int = 0, // 9702
    val link: String = "", // https://www.wanandroid.com/wenda/show/9702
    val niceDate: String = "", // 11小时前
    val niceShareDate: String = "", // 11小时前
    val origin: String = "",
    val originId: Int = -1,
    val prefix: String = "",
    val projectLink: String = "",
    val publishTime: Long = 0, // 1571153172000
    val selfVisible: Int = 0, // 0
    val shareDate: Long = 0, // 1571153150000
    val shareUser: String = "",
    val superChapterId: Int = 0, // 440
    val superChapterName: String = "", // 问答
    val tags: ArrayList<Tag> = arrayListOf(),
    val title: String = "", // 每日一问 BadTokenException 你知道多少？
    val type: Int = 0, // 1
    val userId: Int = 0, // 2
    val visible: Int = 0, // 1
    val zan: Int = 0, // 0
    var isTop: Boolean = false // 是否置顶
)

data class Tag(
    val name: String = "", // 问答
    val url: String = "" // /article/list/0?cid=440
)