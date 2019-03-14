package com.lambreta.rendafixa.features.bonds.domain.usecase

import com.lambreta.rendafixa.core.functional.None
import com.lambreta.rendafixa.core.interactor.UseCase
import com.lambreta.rendafixa.features.bonds.domain.model.Bond
import com.lambreta.rendafixa.features.bonds.domain.repository.BondsRepository

class GetBonds constructor(private val bondsRepository: BondsRepository) : UseCase<List<Bond>, None>() {

    override suspend fun run(params: None) = bondsRepository.bonds()
}