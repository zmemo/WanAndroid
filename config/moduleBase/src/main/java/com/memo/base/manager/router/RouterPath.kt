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

}
