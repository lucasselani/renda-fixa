package com.lambreta.rendafixa.core.view.dialog

import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.lambreta.rendafixa.R

abstract class BaseDialog : DialogFragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Dialog)
    }
}