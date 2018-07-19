package com.tech.transaction.typeConversion

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

@Throws(ParseException::class)
fun String.parseCurrencyString(): BigDecimal {

    val fmt = NumberFormat.getNumberInstance(Locale.TRADITIONAL_CHINESE)

    (fmt as DecimalFormat).isParseBigDecimal = true

    val number = fmt.parse(this)

    return number as BigDecimal
}