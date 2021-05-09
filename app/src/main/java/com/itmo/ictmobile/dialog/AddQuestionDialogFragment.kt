package com.itmo.ictmobile.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import com.itmo.ictmobile.R
import kotlinx.android.synthetic.main.dialog_quest_add.*

class AddQuestionDialogFragment(private val yesOption: (editText: EditText) -> Unit) :
    DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val inflater = requireActivity().layoutInflater
            val builder = AlertDialog.Builder(it)
//            val editText = EditText(activity)
            builder.setView(inflater.inflate(R.layout.dialog_quest_add, null))
                .setPositiveButton(
                    R.string.add_question_btn
                ) { dialogInterface, i ->
                    yesOption(edit_quest)
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