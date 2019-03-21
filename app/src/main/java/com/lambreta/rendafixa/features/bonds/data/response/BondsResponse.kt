package com.lambreta.rendafixa.features.bonds.data.response

import com.lambreta.rendafixa.features.bonds.data.model.Bond
import com.squareup.moshi.Json

class BondsResponse {

    @field:Json(name = "bonds")
    var bonds: List<Bond> = emptyList()
}