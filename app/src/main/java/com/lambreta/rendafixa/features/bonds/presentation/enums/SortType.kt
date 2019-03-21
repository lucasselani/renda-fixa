package com.lambreta.rendafixa.features.bonds.presentation.enums

enum class SortType {
    MINAPPLICATION,
    MATURITYDATE,
    INTEREST,
    NAME;

    val value: String
        get() =  when(this) {
            MINAPPLICATION -> "Aplicação Mínima"
            MATURITYDATE -> "Data de Vencimento"
            INTEREST -> "Taxa"
            NAME -> "Nome"
        }
}