package com.lambreta.rendafixa.core.view

import androidx.fragment.app.Fragment

abstract class BaseFragment : Fragment() {

    protected val baseActivity: BaseActivity
        get() = activity as BaseActivity

    fun BaseFragment.close() = fragmentManager?.popBackStack()
}