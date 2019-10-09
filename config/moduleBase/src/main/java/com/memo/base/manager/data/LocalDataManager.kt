package com.memo.base.manager.data

import com.memo.base.config.config.Config
import com.tencent.mmkv.MMKV

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-08-29 10:51
 */
class LocalDataManager {

    private val mSaver by lazy { MMKV.defaultMMKV() }

    private object Holder {
        val instance = LocalDataManager()
    }

    companion object {
        private const val GUIDE = "GUIDE"

        fun get() = Holder.instance
    }


    /**
     * 设置导航页更新版本
     * if (LocalDataManager.get().getGuideCode() < Config.guideCode) {
     *      //启动导航页
     *      LocalDataManager.get().putGuideCode()
     * } else { //启动首页 }
     *
     */
    fun putGuideCode() = mSaver.encode(GUIDE, Config.guideCode)

    /**
     * 获取本地存储的应用版本
     */
    fun getGuideCode() = mSaver.decodeInt(GUIDE)

}