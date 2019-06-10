package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.QuestionEntity
import com.example.dimic.medicaltest.interact.TestInteract
import com.example.dimic.medicaltest.presentation.adapter.TestItemAdapterContract
import com.example.dimic.medicaltest.presentation.view.TestView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class TestPresenter(private val testInteract: TestInteract): BaseMvpPresenter<TestView>(),
        TestItemAdapterContract.AdapterPresenter,
        TestItemAdapterContract.TestItemPresenter {

    private var questionList = ArrayList<QuestionEntity>()

    override fun getItemsCount(): Int = questionList.size

    override fun onBindTestItemView(view: TestItemAdapterContract.TestItemView, position: Int) {
        val question = questionList[position]
        view.setQuestion(question.question)
        view.setAnswer(question.wrongAnswer)
        view.setQuestionNumber(position + 1)
        view.applyUserAnswer(question.trueAnswer, question.userAnswer)
    }

    override fun onItemClick(position: Int, userAnswer: String) {
        if (questionList[position].userAnswer == null) {
            questionList[position].userAnswer = userAnswer
            viewState.notifyMenuItemChanged(position)
        }
    }

    fun loadQuestions(fileCode: Int, count: Int, start: Int) {
        testInteract.getQuestions(fileCode, count, start)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {it ->
                            for (i in 0 until it.size) {
                                it[i].wrongAnswer.add(it[i].trueAnswer)
                                it[i].wrongAnswer.shuffle()
                                questionList.add(it[i].copy())
                            }
                            viewState.notifyMenuItemsChanged()
                        },
                        onError = {}
                )
                .addTo(disposables)
    }

}