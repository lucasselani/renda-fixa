package com.lambreta.rendafixa.core.view.activity

import android.view.View
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.lambreta.rendafixa.R
import kotlinx.android.synthetic.main.layout_appbar_tabs.*

/**
 * Base Activity class with helper methods for handling fragment transactions and back button
 * events.
 *
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    protected fun setupActionBar(@StringRes titleResourceId: Int? = null,
                                 @DrawableRes iconResourceId: Int? = null) {
        setSupportActionBar(toolbar)
        titleResourceId?.let { toolbar.setTitle(it) }
        iconResourceId?.let { toolbar.setNavigationIcon(it) }
    }

    fun showSnackbar(view: View = findViewById(android.R.id.content),
                     @StringRes textResourceId: Int,
                     @StringRes buttonResourceId: Int = R.string.ok,
                     duration: Int = Snackbar.LENGTH_LONG) {
        val snackbar = Snackbar.make(view, textResourceId, duration)
        snackbar.setAction(buttonResourceId) { snackbar.dismiss() }
        snackbar.show()
    }
}