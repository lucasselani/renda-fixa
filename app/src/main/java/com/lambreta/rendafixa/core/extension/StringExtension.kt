package com.lambreta.rendafixa.core.extension

import java.text.SimpleDateFormat
import java.util.*

val isoDateFormat = SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSS'Z'", Locale.US)
val brDateFormat = SimpleDateFormat("dd/MM/yyyy' às 'hh:mm", Locale.US)
val minDateFormat = SimpleDateFormat("MM/yy", Locale.US)

fun String.capitalizeFirstOfEachWord() =
    this.toLowerCase()
        .split(' ')
        .joinToString(" ") { it.capitalize() }

fun String.isoDateToBr(): String =
    try { brDateFormat.format(isoDateFormat.parse(this))}
    catch (e: Exception) { "" }

fun String.isoDateToSimplified(): String =
    try { minDateFormat.format(isoDateFormat.parse(this))}
    catch (e: Exception) { "" }
