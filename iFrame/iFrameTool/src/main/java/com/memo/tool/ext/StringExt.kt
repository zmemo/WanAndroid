package com.memo.tool.ext

import android.text.Html
import com.blankj.utilcode.util.EncryptUtils
import java.util.*

/**
 * title:对于String的拓展
 * describe:
 *
 * @author zhou
 * @date 2019-01-29 14:32
 */

private const val httpRegex =
    "(((https|http)?://)?([a-z0-9]+[.])|(www.))\\w+[.|/]([a-z0-9]{0,})?[[.]([a-z0-9]{0,})]+((/[\\S&&[^,;\u4E00-\u9FA5]]+)+)?([.][a-z0-9]{0,}+|/?)"
private const val phoneRegex = "1\\d{10}$"
private const val emailRegex = "\\w+@\\w+\\.[a-z]+(\\.[a-z]+)?"
private const val idCardRegex = "[1-9]\\d{16}[a-zA-Z0-9]"
private const val chineseRegex = "^[\u4E00-\u9FA5]+$"

/**
 * 是否是手机号
 * 1+后面10位
 */
fun String?.isPhone(): Boolean = this != null && phoneRegex.toRegex().matches(this.trim())

/**
 * 是否是邮箱地址
 */
fun String?.isEmail(): Boolean = this != null && emailRegex.toRegex().matches(this.trim())

/**
 * 是否是身份证号码
 */
fun String?.isIDCard(): Boolean = this != null && idCardRegex.toRegex().matches(this.trim())

/**
 * 是否是中文字符
 */
fun String?.isChinese(): Boolean = this != null && chineseRegex.toRegex().matches(this.trim())

/**
 * 判断字符串是否是网址
 */
fun String?.isHttpUrl(): Boolean = this != null && httpRegex.toRegex().matches(this.trim())

/**
 * 获取当前字符串的md5
 */
fun String.md5(): String = EncryptUtils.encryptMD5ToString(this)

/**
 * 判断字符串是否为空或者是null的任意变化
 * 曾经出现过后台返回"Null" 然后判断isNullOrEmpty()通过 显示在界面上的时候悲剧了
 */
fun String?.isNull() = isNullOrEmpty() || this!!.trim().toLowerCase(Locale.getDefault()) == "null"

/**
 * Html格式化
 */
fun String.fromHtml() =
    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
        Html.fromHtml(this, Html.FROM_HTML_MODE_LEGACY).toString()
    } else {
        Html.fromHtml(this).toString()
    }

/**
 * 最后一个字符
 */
fun String.lastChar() = this[length - 1]

/**
 * 第一个字符
 */
fun String.firstChar() = this[0]

