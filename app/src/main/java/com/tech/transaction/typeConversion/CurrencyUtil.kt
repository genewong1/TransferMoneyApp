package com.tech.transaction.typeConversion

import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.ParseException
import java.util.*

@Throws(ParseException::class)
fun String.parseCurrencyString(): BigDecimal {
    // Pick a suitable locale.  GERMANY, for example, uses the 1.234,56 format.
    // You could call .getCurrencyInstance() instead, but then it will
    // require the value to contain the correct currency symbol at a
    // specific position.
    val fmt = NumberFormat.getNumberInstance(Locale.GERMANY)

    // By default, fmt.parse() returns a Number that is a Double.
    // However, some decimals, such as 0.10, cannot be represented
    // exactly in floating point, so it is considered best practice to
    // use BigDecimal for storing monetary values.
    (fmt as DecimalFormat).isParseBigDecimal = true

    val number = fmt.parse(this)

    return number as BigDecimal
}