package com.example.encontre_sua_renda_fixa.features.bonds.data.api

import com.example.encontre_sua_renda_fixa.features.bonds.data.response.BondsResponse
import retrofit2.Call
import retrofit2.http.GET
import com.example.encontre_sua_renda_fixa.features.bonds.data.model.Bond as BondDataModel

interface BondsApi {

    @GET("view_holder_bonds")
    fun bonds(): Call<BondsResponse>
}