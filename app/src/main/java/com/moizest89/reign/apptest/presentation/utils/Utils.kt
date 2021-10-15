package com.moizest89.reign.apptest.presentation.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog
import com.moizest89.reign.apptest.R

object Utils {
    const val SEND_MAIN_DATA = "mainData"

    fun showSimpleErrorDialog(
        context: Context,
        title: String,
        message: String?,
        onPositiveAction: (() -> Unit)? = null
    ) {
        AlertDialog.Builder(context)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { _, _ ->
                onPositiveAction?.invoke()
            }
            .setCancelable(false)
            .show()
    }

}