package com.memo.base.api

/**
 * title:
 * describe:
 *
 * @author zhou
 * @date 2019-07-10 17:47
 */
object ExceptionCode {

    /*** 未知错误 ***/
    const val UnknownErrorCode = 1001

    /*** 网络错误 ***/
    const val NetworkErrorCode = 1002

    /*** 解析错误 ***/
    const val ParseErrorCode = 1003

    /*** 无法连接服务器 ***/
    const val ConnectErrorCode = 1004


    //Example：后台返回的ErrorCode

    /*** 登陆失效 ***/
    const val LoginErrorCode = -1001

    /*** 服务器异常 ***/
    const val ServerErrorCode = -1

    /*** 请求成功 ***/
    const val Success = 0


}