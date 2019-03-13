package com.lambreta.rendafixa.core.extension

fun String.capitalizeFirstOfEachWord() =
    this.toLowerCase()
        .split(' ')
        .joinToString(" ") { it.capitalize() }
