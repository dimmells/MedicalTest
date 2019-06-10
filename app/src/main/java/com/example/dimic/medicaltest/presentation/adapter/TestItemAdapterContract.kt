package com.example.dimic.medicaltest.presentation.adapter

interface TestItemAdapterContract {

    interface AdapterPresenter {
        fun getItemsCount(): Int
        fun onBindTestItemView(view: TestItemView, position: Int)
    }

    interface TestItemPresenter {
        fun onItemClick(position: Int, userAnswer: String)
    }

    interface TestItemView {
        fun setQuestion(question: String)
        fun setAnswer(answers: ArrayList<String>)
        fun setQuestionNumber(questionNumber: Int)
        fun applyUserAnswer(trueAnswer: String, userAnswer: String?)
    }

}