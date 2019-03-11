package com.example.encontre_sua_renda_fixa.features.bonds.data.response

import com.example.encontre_sua_renda_fixa.features.bonds.data.model.Bond
import com.squareup.moshi.Json

class BondsResponse {

    @field:Json(name = "view_holder_bonds")
    var bonds: List<Bond> = emptyList()
}