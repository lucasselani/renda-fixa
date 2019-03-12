package com.example.encontre_sua_renda_fixa.features.bonds.data.mapper

import com.example.encontre_sua_renda_fixa.core.extension.capitalizeFirstOfEachWord
import com.example.encontre_sua_renda_fixa.features.bonds.data.response.BondsResponse
import com.example.encontre_sua_renda_fixa.features.bonds.util.brDateFormat
import com.example.encontre_sua_renda_fixa.features.bonds.util.isoDateFormat
import com.example.encontre_sua_renda_fixa.features.bonds.data.model.Bond as BondDataModel
import com.example.encontre_sua_renda_fixa.features.bonds.domain.model.Bond as BondDomainModel

fun BondsResponse.mapTo(): List<BondDomainModel> {
    val bonds = arrayListOf<BondDomainModel>()
    this.bonds.forEach { bonds.add(it.mapTo()) }
    return bonds
}

fun BondDataModel.mapTo(): BondDomainModel {
    return BondDomainModel().apply {
        issuer = this@mapTo.issuer.capitalizeFirstOfEachWord()
        liquidity = this@mapTo.liquidity
        isLiquidity = this@mapTo.isLiquidity
        this@mapTo.encouraged?.let {
            encouraged = if(it is Boolean) it else false
        }
        this@mapTo.qualified?.let {
            qualified = if(it is Boolean) it else false
        }
        maturityDays = this@mapTo.maturityDays
        maturityDate = brDateFormat.format(isoDateFormat.parse(this@mapTo.maturityDate))
        interest = this@mapTo.interest
        rating = this@mapTo.rating
        agency = this@mapTo.agency
        unitPrice = this@mapTo.unitPrice
        category = this@mapTo.category
        dealer = this@mapTo.dealer.capitalizeFirstOfEachWord()
        index = this@mapTo.index
    }
}
