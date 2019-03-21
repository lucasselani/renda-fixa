package com.lambreta.rendafixa.features.bonds.data.api

import com.lambreta.rendafixa.features.bonds.data.response.BondsResponse
import retrofit2.Call
import retrofit2.http.GET
import com.lambreta.rendafixa.features.bonds.data.model.Bond as BondDataModel

interface BondsApi {

    @GET("bonds")
    fun bonds(): Call<BondsResponse>
}