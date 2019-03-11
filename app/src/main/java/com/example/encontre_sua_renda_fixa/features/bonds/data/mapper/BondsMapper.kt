package com.example.encontre_sua_renda_fixa.features.bonds.data.mapper

import com.example.encontre_sua_renda_fixa.features.bonds.data.response.BondsResponse
import com.example.encontre_sua_renda_fixa.features.bonds.util.isoDateFormat
import java.time.Instant
import com.example.encontre_sua_renda_fixa.features.bonds.data.model.Bond as BondDataModel
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDomainModel

fun BondsResponse.mapTo(): List<BondDomainModel> {
    val bonds = arrayListOf<BondDomainModel>()
    this.bonds.forEach { bonds.add(it.mapTo()) }
    return bonds
}

fun BondDataModel.mapTo(): BondDomainModel {
    return BondDomainModel().apply {
        issuer = this@mapTo.issuer
        liquidity = this@mapTo.liquidity
        isLiquidity = this@mapTo.isLiquidity
        maturityDays = this@mapTo.maturityDays
        maturityDate = this@mapTo.maturityDate
        interest = this@mapTo.interest
        rating = this@mapTo.rating
        agency = this@mapTo.agency
        unitPrice = this@mapTo.unitPrice
        category = this@mapTo.category
        dealer = this@mapTo.dealer
        index = this@mapTo.index
    }
}
