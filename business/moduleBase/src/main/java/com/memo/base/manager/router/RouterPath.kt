package com.memo.base.manager.router


/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-02-20 11:42
 */
class RouterPath {

    /**
     * UI模块界面
     */
    object Ui {
        // 二维码扫描
        const val QrcodeScanActivity = "/iFrameUi/QrcodeScanActivity"
        // 网页
        const val WebActivity = "/iFrameUi/WebActivity"
    }

    /**
     * 各个模块的主界面
     */
    object Launcher {
        // UI界面
        const val UiActivity = "/Ui/UiActivity"
    }

    object Other {
        // 登陆界面
        const val LoginActivity = "/Other/MainActivity"
    }

    object Home {
        // 主页
        const val HomeFragment = "/Home/HomeFragment"
    }

    object Project {
        // 项目
        const val ProjectFragment = "/Project/ProjectFragment"
    }

    object Blog {
        // 博客
        const val BlogFragment = "/Blog/BlogFragment"
    }

    object System {
        // 体系界面
        const val SystemFragment = "/System/SystemFragment"
    }

    object Mine {
        // 我的
        const val MineFragment = "/Mine/MineFragment"
    }

}
