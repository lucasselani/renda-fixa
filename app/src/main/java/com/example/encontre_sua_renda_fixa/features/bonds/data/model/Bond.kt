package com.example.encontre_sua_renda_fixa.features.bonds.data.model

import com.squareup.moshi.Json

class Bond {

    @field:Json(name = "_id")
    var id: String = ""

    @field:Json(name = "issuer")
    var issuer: String = ""

    @field:Json(name = "liquidity")
    var liquidity: String = ""

    @field:Json(name = "isLiquidity")
    var isLiquidity: Boolean = false

    @field:Json(name = "maturity")
    var maturity: String = ""

    @field:Json(name = "maturityDays")
    var maturityDays: Long = 0

    @field:Json(name = "maturityDate")
    var maturityDate: String = ""

    @field:Json(name = "rate")
    var rate: String = ""

    @field:Json(name = "interest")
    var interest: Double = 0.0

    @field:Json(name = "amortization")
    var amortization: String = ""

    @field:Json(name = "grace")
    var grace: String = ""

    @field:Json(name = "rating")
    var rating: String = ""

    @field:Json(name = "agency")
    var agency: String = ""

    @field:Json(name = "quantity")
    var quantity: Double = 0.0

    @field:Json(name = "unitPrice")
    var unitPrice: Double = 0.0

    @field:Json(name = "category")
    var category: String = ""

    @field:Json(name = "dealer")
    var dealer: String = ""

    @field:Json(name = "index")
    var index: String = ""
}