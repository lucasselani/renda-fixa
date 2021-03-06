package com.lambreta.rendafixa.features.bonds.data.mapper

import com.lambreta.rendafixa.core.extension.capitalizeFirstOfEachWord
import com.lambreta.rendafixa.core.extension.isoDateToBr
import com.lambreta.rendafixa.core.extension.isoDateToSimplified
import com.lambreta.rendafixa.features.bonds.data.response.BondsResponse
import java.text.NumberFormat
import java.util.*
import com.lambreta.rendafixa.features.bonds.data.model.Bond as BondDataModel
import com.lambreta.rendafixa.features.bonds.domain.model.Bond as BondDomainModel

fun BondsResponse.mapToList(): List<BondDomainModel> {
    val bonds = arrayListOf<BondDomainModel>()
    this.bonds.forEach { bonds.add(it.mapTo()) }
    return bonds
}

fun BondDataModel.mapTo(): BondDomainModel =
    BondDomainModel().apply {
        issuer = this@mapTo.issuer.capitalizeFirstOfEachWord()
        liquidity = this@mapTo.liquidity
        isLiquidity = this@mapTo.isLiquidity
        this@mapTo.encouraged?.let {
            encouraged = if (it is Boolean) it else false
        }
        this@mapTo.qualified?.let {
            qualified = if (it is Boolean) it else false
        }
        maturityDays = this@mapTo.maturityDays
        maturityDate = this@mapTo.maturityDate.isoDateToBr()
        simplifiedDate = this@mapTo.maturityDate.isoDateToSimplified()
        interest = this@mapTo.interest
        rate = this@mapTo.rate.replace(".", ",")
        rating = this@mapTo.rating
        agency = this@mapTo.agency
        unitPrice = this@mapTo.unitPrice
        price = NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this@mapTo.unitPrice)
        category = this@mapTo.category
        dealer = this@mapTo.dealer.capitalizeFirstOfEachWord()
        index = this@mapTo.index
    }
