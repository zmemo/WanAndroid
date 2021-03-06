package com.memo.home.ui.fragment.home


import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.google.android.material.appbar.AppBarLayout
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.common.ui.article.ArticleActivity
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.BannerInfo
import com.memo.base.entity.remote.HomeData
import com.memo.base.manager.banner.BannerImageLoader
import com.memo.base.manager.router.RouterManager
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.home.R
import com.memo.home.ui.activity.search.SearchActivity
import com.memo.tool.ext.marginStatusBar
import com.memo.tool.ext.onClick
import com.memo.tool.ext.paddingStatusBar
import com.memo.tool.ext.startActivity
import com.memo.tool.helper.StatusBarHelper
import com.memo.tool.helper.finish
import com.scwang.smartrefresh.layout.api.RefreshLayout
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.math.abs

/**
 * title:主页
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:42
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Home.HomeFragment)
class HomeFragment : BaseMvpFragment<HomeView, HomePresenter>(), HomeView {

    /*** 加载文章的页码 ***/
    private var page = 0

    /*** 轮播图数据源 ***/
    private var mBannerInfos: ArrayList<BannerInfo> = arrayListOf()

    /*** 列表适配器 ***/
    private val mAdapter by lazy { ArticleAdapter() }

    /*** 绑定Presenter ***/
    override fun buildPresenter(): HomePresenter = HomePresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_home

    override fun initData() {}

    override fun initView() {
        // 状态栏字体黑暗模式
        StatusBarHelper.setStatusTextDarkMode(mActivity)
        // 设置ToolBar距顶
        mToolBar.paddingStatusBar()
        mFlSearch.marginStatusBar()
        // 设置Banner图片加载
        mBanner.setImageLoader(BannerImageLoader()).start()
        // 配置RecyclerView
        mRvList.run {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    override fun initListener() {
        // 设置AppBar的透明度
        mAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
            mToolBar.alpha = abs(offset).toFloat() / appBar.totalScrollRange
        })
        // 标题栏点击标题滑动到顶部
        mTitleView.setOnTitleClickListener {
            // 但顶部完全显示 滑动到顶部
            if (mToolBar.alpha == 1f) {
                val behavior = (mAppBar.layoutParams as CoordinatorLayout.LayoutParams).behavior
                if (behavior is AppBarLayout.Behavior) {
                    behavior.topAndBottomOffset = 0
                }
                mRvList.scrollToPosition(0)
            }
        }
        // 搜索
        mFlSearch.onClick {
            startActivity<SearchActivity>()
        }
        // 刷新 加载 监听
        mRefreshLayout.setOnRefreshLoadMoreListener(object : OnRefreshLoadMoreListener {
            override fun onLoadMore(refreshLayout: RefreshLayout) {
                page++
                mPresenter.getArticleData(page)
            }

            override fun onRefresh(refreshLayout: RefreshLayout) {
                page = 0
                mPresenter.getHomeData()
            }
        })
        // 列表点击监听
        mAdapter.setOnItemChildClickListener { _, _, position ->
            val article = mAdapter.data[position]
            ArticleActivity.start(mActivity, article.id, article.title, article.link)
        }
        //轮播图
        mBanner.setOnBannerListener {
            val info = mBannerInfos[it]
            RouterManager.get().startWebActivity(info.url, info.title)
        }
    }

    override fun start() {
        mPresenter.getHomeData()
    }

    override fun getHomeDataSuccess(response: HomeData) {
        mRefreshLayout.finish(response.articles.isEmpty())
        // 轮播图
        if (response.banners.isNotEmpty()) {
            mBannerInfos = response.banners
            val imageList = arrayListOf<String>()
            imageList.clear()
            response.banners.forEach {
                imageList.add(it.imagePath)
            }
            mBanner.setImages(imageList).start()
        }

        // 列表文章
        mAdapter.setNewData(response.articles)
    }

    override fun getArticleSuccess(response: ArrayList<ArticleInfo>) {
        mRefreshLayout.finish(response.isEmpty())
        mAdapter.addData(response)
    }

    override fun getDataFailure() {
        mRefreshLayout.finish(false)
        // 如果是刷新的时候错误page不变 如果是加载发生的错误page-1
        if (page > 0) page--
    }

    override fun onStart() {
        super.onStart()
        mBanner.startAutoPlay()
    }

    override fun onStop() {
        super.onStop()
        mBanner.stopAutoPlay()
    }


}
