package com.example.encontre_sua_renda_fixa.core.extension

import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

inline fun FragmentManager.inTransaction(transaction: FragmentTransaction.() -> FragmentTransaction) {
    beginTransaction().transaction().commit()
}