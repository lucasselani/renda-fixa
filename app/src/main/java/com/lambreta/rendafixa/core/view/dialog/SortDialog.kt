package com.lambreta.rendafixa.core.view.dialog

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.annotation.StringRes
import androidx.core.view.setPadding
import com.lambreta.rendafixa.R
import com.lambreta.rendafixa.core.model.Sort
import kotlinx.android.synthetic.main.dialog_sort.*
import org.koin.android.ext.android.inject

class SortDialog<T>: BaseDialog() {

    interface OnDialogListener<T> {
        fun onSort(sort: Sort<T>?)
    }

    private var titleRes: Int = 0
    private var buttonRes: Int = 0

    private var listener: OnDialogListener<T>? = null
    private val items: List<Sort<T>> by inject()
    private var selected: Sort<T>? = null

    companion object {
        val TAG = SortDialog::class.qualifiedName
        private const val ARG_TITLE = "title"
        private const val ARG_BUTTON = "button"

        @JvmStatic
        fun <T> newInstance(@StringRes titleRes: Int,
                        @StringRes buttonRes: Int = R.string.ok) =
            SortDialog<T>().apply {
                arguments = Bundle().apply {
                    putInt(ARG_TITLE, titleRes)
                    putInt(ARG_BUTTON, buttonRes)
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titleRes = it.getInt(ARG_TITLE)
            buttonRes = it.getInt(ARG_BUTTON)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?) =
        inflater.inflate(R.layout.dialog_sort, container, false)!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = true
        setupView()
    }

    @Suppress("UNCHECKED_CAST")
    override fun onAttach(context: Context?) {
        super.onAttach(context)
        listener = context as? OnDialogListener<T>
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun setupView() {
        title.text = getString(titleRes)
        button.text = getString(buttonRes)
        button.setOnClickListener {
            dismiss()
            selected?.selected = true
            listener?.onSort(selected)
        }
        items.forEachIndexed { index, item ->
            radioGroup.addView(RadioButton(context).apply {
                setPadding(8)
                text = item.name
                isChecked = item.selected
                id = index
            })
        }
        radioGroup.setOnCheckedChangeListener { _, id -> selected = items[id] }
    }
}
