package com.lambreta.rendafixa.features.bonds.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Bond(
    var issuer: String = "",
    var liquidity: String = "",
    var isLiquidity: Boolean = false,
    var encouraged: Boolean = false,
    var qualified: Boolean = false,
    var maturityDays: Long = 0,
    var maturityDate: String = "",
    var interest: Double = 0.0,
    var rate: String = "",
    var rating: String = "",
    var agency: String = "",
    var unitPrice: Double = 0.0,
    var price: String = "",
    var category: String = "",
    var dealer: String = "",
    var index: String = "") : Parcelable