package com.itmo.ictmobile.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.itmo.ictmobile.R

class AddQuestionDialogFragment(private val yesOption: (editText: EditText) -> Unit) :
    DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val editText = EditText(activity)
            builder
                .setTitle(R.string.add_question)
                .setView(editText)
                .setPositiveButton(
                    R.string.add_question_btn
                ) { dialogInterface, i ->
                    yesOption(editText)
                }
                .setNegativeButton(
                    R.string.cancel_question_btn
                ) { dialogInterface, i ->
                    dialogInterface.cancel()
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

}