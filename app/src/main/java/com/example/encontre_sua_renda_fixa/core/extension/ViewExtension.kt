package com.example.encontre_sua_renda_fixa.core.extension

import android.view.View

fun View.isVisible() = this.visibility == View.VISIBLE

fun View.visible() { this.visibility = View.VISIBLE }

fun View.gone() { this.visibility = View.GONE }