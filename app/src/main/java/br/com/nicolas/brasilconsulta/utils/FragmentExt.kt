package br.com.nicolas.brasilconsulta.utils

import android.content.Context.INPUT_METHOD_SERVICE
import android.os.Build
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.view.inputmethod.InputMethodManager.SHOW_FORCED

fun View.hideKeyboard(view: View?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        view?.hideKeyboard(view)
    } else {
        inputMethodManager()?.hideSoftInputFromWindow(view?.applicationWindowToken, 0)
    }
}

fun View.showKeyboard(view: View?) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
        view?.showKeyboard(view)
    } else {
        view?.let {
            it.postDelayed({
                it.requestFocus()
                inputMethodManager()?.showSoftInput(it, SHOW_FORCED)
            }, 100)
        }
    }
}

fun View.inputMethodManager() =
    context?.getSystemService(INPUT_METHOD_SERVICE) as? InputMethodManager