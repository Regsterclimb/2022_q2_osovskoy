package com.example.a2022_q2_osovskoy.extentions

import android.graphics.Color
import android.view.View
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputLayout

fun TextInputLayout.getTrimmedText(): String =
    this.editText?.text?.toString()?.trim().orEmpty()

fun TextInputLayout.showErrorResId(@StringRes stringResId: Int) =
    showErrorWithEnable(errorMessage = this.context.getString(stringResId))

fun TextInputLayout.showErrorWithEnable(errorMessage: String) {
    this.isErrorEnabled = true
    this.error = errorMessage
}

fun TextInputLayout.clearError() {
    this.isErrorEnabled = false
    this.error = null
}

fun TextView.addText(text: String) = run { this.text = String.format(this.text.toString() + text) }

fun TextView.changeColor(state: String) = when (state) {
    "APPROVED" -> this.setTextColor(Color.GREEN)
    "REGISTERED" -> this.setTextColor(Color.BLUE)
    "REJECTED" -> this.setTextColor(Color.RED)
    else -> this.setTextColor(Color.BLACK)
}

fun TextInputLayout.clearErrorOnAnyInput() =
    this.editText?.doAfterTextChanged {
        this.clearError()
    }

fun String.addPercent() = "$this %"


fun String.addRub() = "$this Rub"

fun View.hide() {
    this.isVisible = false
}

fun View.show() {
    this.isVisible = true
}