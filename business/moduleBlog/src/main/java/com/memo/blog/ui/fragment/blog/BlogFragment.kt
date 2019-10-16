package com.memo.blog.ui.fragment.blog


import com.alibaba.android.arouter.facade.annotation.Route
import com.memo.base.manager.router.RouterPath
import com.memo.base.ui.fragment.BaseMvpFragment
import com.memo.blog.R

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

    /*** 绑定Presenter ***/
    override fun buildPresenter(): BlogPresenter = BlogPresenter()

    /*** 绑定布局 ***/
    override fun bindLayoutResId(): Int = R.layout.fragment_blog

    override fun initData() {
    }

    override fun initView() {
    }

    override fun initListener() {
    }

    override fun start() {
    }

}
