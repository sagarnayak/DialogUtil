package com.sagar.dialog

object NumberUtil {
    fun roundToTwoDecimal(number: Float): String {
        return "%.2f".format(number)
    }
}