package com.lambreta.rendafixa.features.bonds.presentation.enum

enum class BondType {
    CDI,
    IPCA,
    PRE;

    val value: String
        get() =  when(this) {
            CDI -> "CDI"
            IPCA -> "IPCA"
            PRE -> "PRÉ"
        }
}