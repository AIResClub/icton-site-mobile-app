package com.itmo.ictmobile.screens.askIct

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.itmo.IctApp
import com.itmo.ictmobile.R
import com.itmo.ictmobile.adapters.recycler.QuestionAdapter
import com.itmo.ictmobile.data.models.Question
import com.itmo.ictmobile.dialog.AddQuestionDialogFragment
import com.itmo.ictmobile.util.Preferences
import com.itmo.ictmobile.util.Strings
import com.itmo.ictmobile.util.toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.askict_fragment.*

class AskIctFragment : Fragment(R.layout.askict_fragment) {

    private lateinit var askIctViewModel: AskIctViewModel

    private val disposables = CompositeDisposable()

    private lateinit var questionAdapter: QuestionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        askIctViewModel = ViewModelProviders.of(this).get(AskIctViewModel::class.java)
    }

    override fun onStart() {
        super.onStart()

        setHasOptionsMenu(true)

        questionAdapter = QuestionAdapter()

        question_rv.adapter = questionAdapter

        disposables.add(
            askIctViewModel.getQuestions()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { questionAdapter.updateQuestions(it) },
                    { toast(Strings.get(R.string.sign_failed)) }
                )

        )
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.action_bar_menu_main, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.add) {
            val addQuestionDialog = AddQuestionDialogFragment {
                val currentUser = Preferences(IctApp.sharedPreferences).getUser()

                if (it.text.isEmpty()) {
                    toast("Введите вопрос")
                    return@AddQuestionDialogFragment
                }

                val question = Question(
                    "",
                    "${currentUser!!.firstName} ${currentUser.secondName}",
                    currentUser.username,
                    it.text.toString(),
                    System.currentTimeMillis()
                )

                disposables.add(
                    askIctViewModel.createQuestion(question)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                            {  },
                            { toast(Strings.get(R.string.sign_failed)) }
                        )
                )
            }

            addQuestionDialog.show(requireActivity().supportFragmentManager, QUESTION_TAG)

            return true
        }

        return super.onOptionsItemSelected(item)
    }

    companion object {
        private const val QUESTION_TAG = "QUESTION_TAG"
    }

}