package com.lambreta.rendafixa.core.extension

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment

fun <T : DialogFragment> AppCompatActivity.showDialogFragment(dialogFragmentTag: String?, dialogFragment: T) {
    val fragmentManager = this.supportFragmentManager
    val fragmentTransaction = fragmentManager.beginTransaction()

    val fragment = fragmentManager.findFragmentByTag(dialogFragmentTag)
    if (fragment != null) {
        fragmentTransaction.remove(fragment)
    }

    fragmentTransaction.addToBackStack(null)
    dialogFragment.show(fragmentTransaction, dialogFragmentTag)
}