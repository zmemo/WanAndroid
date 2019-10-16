package com.memo.home.ui.fragment.home

import com.memo.base.entity.remote.ArticleData
import com.memo.base.entity.remote.ArticleInfo
import com.memo.base.entity.remote.BannerInfo
import com.memo.base.entity.remote.HomeData
import com.memo.base.manager.retrofit.convert
import com.memo.base.ui.mvp.IModel
import com.memo.home.api.mApiService
import io.reactivex.Observable
import io.reactivex.functions.Function3

/**
 * title:
 * describe:
 *
 * @author memo
 * @date 2019-10-15 16:43
 * @email zhou_android@163.com
 *
 * Talk is cheap, Show me the code.
 */
class HomeModel : IModel {

    fun getHomeData(): Observable<HomeData> {
        return Observable.zip(
            mApiService.getBanner().convert(),
            mApiService.getHomeTopArticle().convert(),
            mApiService.getHomeArticle(0).convert(),
            Function3<ArrayList<BannerInfo>, ArrayList<ArticleInfo>, ArticleData, HomeData> { banners, topArticles, homeArticle ->
                topArticles.forEach { it.isTop = true }
                topArticles.addAll(homeArticle.datas)
                HomeData(banners, topArticles)
            }
        )
    }

    fun getArticleData(page: Int): Observable<ArrayList<ArticleInfo>> {
        return mApiService.getHomeArticle(page).convert().map { it.datas }
    }
}