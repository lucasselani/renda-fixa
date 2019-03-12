package com.example.encontre_sua_renda_fixa.features.bonds.data.service

import android.content.Context
import com.example.encontre_sua_renda_fixa.core.exception.Failure
import com.example.encontre_sua_renda_fixa.core.functional.Either
import com.example.encontre_sua_renda_fixa.core.repository.NetworkRepository
import com.example.encontre_sua_renda_fixa.features.bonds.data.api.BondsApi
import com.example.encontre_sua_renda_fixa.features.bonds.data.mapper.mapTo
import com.example.encontre_sua_renda_fixa.features.bonds.data.response.BondsResponse
import com.example.encontre_sua_renda_fixa.features.bonds.domain.repository.BondsRepository
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDomainModel

class BondsService(private val bondsApi: BondsApi,
                   private val context: Context): NetworkRepository(), BondsRepository {

    override fun bonds(): Either<Failure, List<BondDomainModel>> =
            request(context, bondsApi.bonds(), { it.mapTo() }, BondsResponse())
}