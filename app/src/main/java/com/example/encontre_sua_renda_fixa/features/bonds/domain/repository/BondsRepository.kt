package com.example.encontre_sua_renda_fixa.features.bonds.domain.repository

import com.example.encontre_sua_renda_fixa.core.exception.Failure
import com.example.encontre_sua_renda_fixa.core.functional.Either
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDataModel

interface BondsRepository {
    fun bonds(): Either<Failure, List<BondDataModel>>
}