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
            val inflater = requireActivity().layoutInflater
            val builder = AlertDialog.Builder(it)
            val input = EditText(it)
            builder.setView(inflater.inflate(R.layout.dialog_quest_add, null))
                .setTitle("Создать тред")
                .setView(input)
                .setPositiveButton(
                    R.string.add_question_btn
                ) { dialogInterface, i ->
                    yesOption(input)
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