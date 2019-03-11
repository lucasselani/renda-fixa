package com.example.encontre_sua_renda_fixa.core.view

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

abstract class BaseFragment : Fragment() {

    protected val baseActivity: BaseActivity
        get() = activity as BaseActivity

    fun BaseFragment.close() = fragmentManager?.popBackStack()
}