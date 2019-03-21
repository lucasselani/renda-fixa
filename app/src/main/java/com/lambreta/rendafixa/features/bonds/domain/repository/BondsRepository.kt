package com.lambreta.rendafixa.features.bonds.domain.repository

import com.lambreta.rendafixa.core.exception.Failure
import com.lambreta.rendafixa.core.functional.Either
import com.lambreta.rendafixa.features.bonds.domain.model.Bond as BondDomainModel

interface BondsRepository {
    fun bonds(): Either<Failure, List<BondDomainModel>>
}