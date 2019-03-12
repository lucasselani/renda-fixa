package com.example.encontre_sua_renda_fixa.core.extension

fun String.capitalizeFirstOfEachWord() =
    this.toLowerCase()
        .split(' ')
        .joinToString(" ") { it.capitalize() }
