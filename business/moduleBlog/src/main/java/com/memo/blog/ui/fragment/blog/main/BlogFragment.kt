package com.memo.blog.ui.fragment.blog.main


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.entity.remote.ArticleTreeZip
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.blog.R
import com.memo.blog.ui.fragment.blog.item.BlogItemFragment
import com.memo.tool.adapter.BaseFragmentPagerAdapter
import com.memo.tool.ext.fromHtml
import com.memo.tool.ext.paddingStatusBar
import kotlinx.android.synthetic.main.fragment_blog.*

/**
 * title:博客界面
 * describe:
 *
 * @author memo
 * @date 2019-10-15 17:16
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
@Route(path = RouterPath.Blog.BlogFragment)
class BlogFragment : BaseMvpFragment<BlogView, BlogPresenter>(), BlogView {

    private val mAdapter by lazy { BaseFragmentPagerAdapter<BlogItemFragment>(childFragmentManager) }

    /*** 绑定Presenter ***/
    override fun buildPresenter(): BlogPresenter = BlogPresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_blog

    override fun initData() {}

    override fun initView() {
        mRootView.paddingStatusBar()
    }

    override fun initListener() {}

    override fun start() {
        mPresenter.getBlogTree()
    }

    override fun getBlogTreeSuccess(response: ArticleTreeZip) {
        val titles = arrayListOf<String>()
        val fragments = arrayListOf<BlogItemFragment>()
        response.articleTrees.forEachIndexed { index, articleTree ->
            titles.add(articleTree.name.fromHtml())
            if (index == 0) {
                fragments.add(BlogItemFragment.newInstance(articleTree.id, response.articles))
            } else {
                fragments.add(BlogItemFragment.newInstance(articleTree.id))
            }
        }
        val array = titles.toTypedArray()
        mAdapter.setData(fragments, array)
        mViewPager.offscreenPageLimit = 2
        mViewPager.adapter = mAdapter
        mTabLayout.setViewPager(mViewPager, array)

    }

}
