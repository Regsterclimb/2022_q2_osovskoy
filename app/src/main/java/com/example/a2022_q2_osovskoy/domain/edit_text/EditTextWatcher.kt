package com.example.a2022_q2_osovskoy.domain.edit_text

class EditTextWatcher {

    companion object {
        fun countDigitAfterDot(string: String) = EditTextWatcher().countAfterDot(string)
    }

    private fun countAfterDot(string: String): Boolean {
        if (string.contains('.')) {
            val subStr = string.substring(string.indexOf('.'), string.length - 1)
            if (subStr.length > 2) return true
        }
        return false
    }
}