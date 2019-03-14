package com.lambreta.rendafixa.features.bonds.data.service

import android.content.Context
import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.functional.Either
import com.lambreta.rendafixa.core.repository.NetworkRepository
import com.lambreta.rendafixa.features.bonds.data.api.BondsApi
import com.lambreta.rendafixa.features.bonds.data.mapper.mapToList
import com.lambreta.rendafixa.features.bonds.data.response.BondsResponse
import com.lambreta.rendafixa.features.bonds.domain.repository.BondsRepository
import com.lambreta.rendafixa.features.bonds.domain.model.Bond as BondDomainModel

class BondsService(private val bondsApi: BondsApi,
                   private val context: Context): NetworkRepository(), BondsRepository {

    override fun bonds(): Either<Failure, List<BondDomainModel>> =
            request(context, bondsApi.bonds(), { it.mapToList() }, BondsResponse())
}