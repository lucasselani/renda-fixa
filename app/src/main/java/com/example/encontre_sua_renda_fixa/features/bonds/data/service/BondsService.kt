package com.example.encontre_sua_renda_fixa.features.bonds.data.service

import android.content.Context
import com.example.encontre_sua_renda_fixa.core.functional.State
import com.example.encontre_sua_renda_fixa.features.bonds.data.api.BondsApi
import com.example.encontre_sua_renda_fixa.features.bonds.data.mapper.mapTo
import com.example.encontre_sua_renda_fixa.features.bonds.data.response.BondsResponse
import com.example.encontre_sua_renda_fixa.features.bonds.data.repository.BondsRepository
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDomainModel

class BondsService(private val bondsApi: BondsApi,
                   private val context: Context): BondsRepository() {

    override fun bonds(): State<List<BondDomainModel>> =
            request(context, bondsApi.bonds(), { it.mapTo() },
                BondsResponse()
            )
}