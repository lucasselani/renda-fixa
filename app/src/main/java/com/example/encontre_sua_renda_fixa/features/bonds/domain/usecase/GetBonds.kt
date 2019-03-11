package com.example.encontre_sua_renda_fixa.features.bonds.domain.usecase

import com.example.encontre_sua_renda_fixa.core.interactor.UseCase
import com.example.encontre_sua_renda_fixa.features.bonds.data.repository.BondsRepository
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond

class GetBonds constructor(private val bondsRepository: BondsRepository) : UseCase<List<Bond>, UseCase.None>() {

    override suspend fun run(params: UseCase.None) = bondsRepository.bonds()
}