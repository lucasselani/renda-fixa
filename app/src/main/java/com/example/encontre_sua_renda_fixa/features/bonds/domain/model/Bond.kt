package com.example.encontre_sua_renda_fixa.features.bonds.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.time.Instant

@Parcelize
data class Bond(
    var issuer: String = "",
    var liquidity: String = "",
    var isLiquidity: Boolean = false,
    var maturityDays: Long = 0,
    var maturityDate: String = "",
    var interest: Double = 0.0,
    var rating: String = "",
    var agency: String = "",
    var unitPrice: Double = 0.0,
    var category: String = "",
    var dealer: String = "",
    var index: String = "") : Parcelable