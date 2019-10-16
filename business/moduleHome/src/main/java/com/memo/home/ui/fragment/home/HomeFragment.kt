package com.memo.home.ui.fragment.home


import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.google.android.material.appbar.AppBarLayout
import com.memo.base.common.adapter.ArticleAdapter
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.HomeData
import com.memo.base.manager.banner.BannerImageLoader
import com.memo.base.manager.load.LoadHelper
import com.memo.base.manager.router.RouterManager
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.home.R
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
    private val mImages: ArrayList<String> = arrayListOf()

    /*** 列表适配器 ***/
    private val mAdapter by lazy { ArticleAdapter() }

    /*** 绑定Presenter ***/
    override fun buildPresenter(): HomePresenter = HomePresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_home

    /*** 在视图加载完毕的时候初始化 ***/
    override fun initialize() {
        initView()
        initListener()
    }

    /*** 在界面可见的时候进行初始化 ***/
    override fun lazyInitialize() {
        start()
    }

    private fun initView() {
        // 状态栏字体黑暗模式
        StatusBarHelper.setStatusTextDarkMode(mActivity)
        mLoadService = LoadHelper.register(mRootView) { mPresenter.getHomeData() }
        // 设置ToolBar距顶
        mToolBar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0)
        // 设置Banner图片加载
        mBanner.setImageLoader(BannerImageLoader()).start()
        // 配置RecyclerView
        mRvList.run {
            layoutManager = LinearLayoutManager(mActivity)
            adapter = mAdapter
        }
    }

    private fun initListener() {
        // 设置AppBar的透明度
        mAppBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, offset ->
            mToolBar.alpha = abs(offset).toFloat() / appBar.totalScrollRange
        })
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
        mAdapter.setOnItemChildClickListener { _, view, position ->
            when (view.id) {
                R.id.mClContent -> RouterManager.get().startWebActivity(mAdapter.data[position].link, mAdapter.data[position].title)
            }
        }
    }

    private fun start() {
        mPresenter.getHomeData()
    }

    override fun getHomeDataSuccess(response: HomeData) {
        mLoadService?.showSuccess()
        LogUtils.iTag("aaa", "success", mLoadService == null)
        mRefreshLayout.finish(response.articles.isEmpty())
        // 轮播图
        if (response.banners.isNotEmpty()) {
            mImages.clear()
            response.banners.forEach {
                mImages.add(it.imagePath)
            }
            mBanner.setImages(mImages).start()
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
