package com.lambreta.rendafixa.core.extension

import com.lambreta.rendafixa.core.util.brDateFormat
import com.lambreta.rendafixa.core.util.isoDateFormat
import com.lambreta.rendafixa.core.util.minDateFormat
import java.lang.Exception

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
