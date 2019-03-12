package com.example.encontre_sua_renda_fixa.features.bonds.domain.usecase

import com.example.encontre_sua_renda_fixa.core.interactor.None
import com.example.encontre_sua_renda_fixa.core.interactor.UseCase
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond
import com.example.encontre_sua_renda_fixa.features.bonds.domain.repository.BondsRepository

class GetBonds constructor(private val bondsRepository: BondsRepository) : UseCase<List<Bond>, None>() {

    override suspend fun run(params: None) = bondsRepository.bonds()
}