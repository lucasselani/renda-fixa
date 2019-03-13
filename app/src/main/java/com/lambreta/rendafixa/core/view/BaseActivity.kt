package com.lambreta.rendafixa.core.view

import android.view.View
import androidx.annotation.IdRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.extension.inTransaction
import com.google.android.material.snackbar.Snackbar

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    protected fun addFragment(fragment: Fragment, @IdRes containerViewId: Int, tag: String? = null) =
        supportFragmentManager.inTransaction { add(containerViewId, fragment, tag).addToBackStack(null) }

    protected fun replaceFragment(fragment: Fragment, @IdRes containerViewId: Int, tag: String? = null) =
        supportFragmentManager.inTransaction{ replace(containerViewId, fragment, tag) }

    protected fun removeFragment(fragment: Fragment) =
        supportFragmentManager.inTransaction{ remove(fragment) }

    fun showSnackbar(view: View,
                     @StringRes textResourceId: Int,
                     @StringRes buttonResourceId: Int = R.string.ok,
                     duration: Int = Snackbar.LENGTH_LONG) {
        val snackbar = Snackbar.make(view, textResourceId, duration)
        snackbar.setAction(buttonResourceId) { snackbar.dismiss() }
        snackbar.show()
    }
}