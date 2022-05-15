package com.assignment.jeeny.ext

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat

fun Context.showToast(message: String) {
    if (message.isNotEmpty())
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun View.hide() {
    this.visibility = View.GONE
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun EditText.onSearch(callback: () -> Unit) {
    setOnEditorActionListener { _, actionId, _ ->
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
            clearFocus()
            callback.invoke()
            return@setOnEditorActionListener true
        }
        false
    }
}

fun Activity.hideKeyboard() {
    WindowInsetsControllerCompat(window, window.decorView).hide(WindowInsetsCompat.Type.ime())
}

fun <T> Activity.startNewActivity(clazz: Class<T>) {
    startActivity(Intent(this, clazz))
}

fun <T> Activity.startActivityBundle(clazz: Class<T>, bundle: Bundle) {

    startActivity(
        Intent(this, clazz).apply { putExtras(bundle) }
    )
}
