package com.example.fruitsapp.presentation.ui.dialogs

import android.app.Activity
import android.app.AlertDialog
import com.example.fruitsapp.R

class RequestErrorDialog {

    companion object {
        fun newInstance(activity: Activity, yesButtonListener: ()-> Unit): AlertDialog {
            val dialogBuilder = AlertDialog.Builder(activity)
                    .setTitle(activity.getString(R.string.dialog_request_error_title))
                    .setMessage(activity.getString(R.string.dialog_request_error_text))
                    .setCancelable(false)
                    .setPositiveButton(activity.getString(R.string.dialog_request_error_yes_button)){ dialog, which ->
                        yesButtonListener()
                    }
                    .setNegativeButton(activity.getString(R.string.dialog_request_error_no_button)) { dialog, which ->
                        dialog.cancel()
                    }
            return dialogBuilder.create()
        }
    }
}