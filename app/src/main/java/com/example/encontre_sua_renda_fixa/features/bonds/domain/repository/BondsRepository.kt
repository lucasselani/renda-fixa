package com.example.encontre_sua_renda_fixa.features.bonds.domain.repository

import com.example.encontre_sua_renda_fixa.core.functional.State
import com.example.encontre_sua_renda_fixa.core.repository.BaseRepository
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDataModel

abstract class BondsRepository : BaseRepository() {
    abstract fun bonds(): State<List<BondDataModel>>
}