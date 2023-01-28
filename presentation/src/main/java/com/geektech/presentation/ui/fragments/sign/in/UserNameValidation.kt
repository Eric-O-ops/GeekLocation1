package com.geektech.presentation.ui.fragments.sign.`in`

import android.widget.EditText
import com.geektech.presentation.R

interface UserNameValidation {

    fun checkValidation()

    class Base(private val editText: EditText) : UserNameValidation {
        override fun checkValidation() {
            if (editText.length() == 0) {
                editText.apply {
                    setBackgroundResource(R.drawable.error_edit_text)
                    setCompoundDrawablesWithIntrinsicBounds(
                        0,
                        0,
                        R.drawable.ic_priority_high,
                        0
                    )
                }
            } else {
                editText.apply {
                    setBackgroundResource(R.drawable.normal_edit_text)
                    setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0)
                }
            }
        }
    }
}