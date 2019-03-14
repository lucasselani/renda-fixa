package com.lambreta.rendafixa.core.view

import androidx.fragment.app.Fragment
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.exception.Failure

abstract class BaseFragment : Fragment() {

    protected val baseActivity: BaseActivity
        get() = activity as BaseActivity

    protected fun handleError(failure: Failure?) {
        when(failure) {
            is Failure.NetworkConnection -> baseActivity.showSnackbar(textResourceId = R.string.no_internet)
            is Failure.ServerError -> baseActivity.showSnackbar(textResourceId = R.string.list_error)
        }
    }
}