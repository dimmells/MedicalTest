package com.example.dimic.medicaltest.ui.viewholder

import android.graphics.Color
import android.view.View
import android.widget.Button
import com.example.dimic.medicaltest.presentation.adapter.TestItemAdapterContract
import kotlinx.android.synthetic.main.fragment_test_item.view.*
import com.example.dimic.medicaltest.R
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class TestItemViewHolder(itemView: View, private val presenter: TestItemAdapterContract.TestItemPresenter):
        BaseViewHolder(itemView),
        TestItemAdapterContract.TestItemView {

    init {
        with(itemView) {
            button_test_item_a.setOnClickListener {
                presenter.onItemClick(adapterPosition, button_test_item_a.text.toString())
            }
            button_test_item_b.setOnClickListener {
                presenter.onItemClick(adapterPosition, button_test_item_b.text.toString())
            }
            button_test_item_c.setOnClickListener {
                presenter.onItemClick(adapterPosition, button_test_item_c.text.toString())
            }
            button_test_item_d.setOnClickListener {
                presenter.onItemClick(adapterPosition, button_test_item_d.text.toString())
            }
            button_test_item_e.setOnClickListener {
                presenter.onItemClick(adapterPosition, button_test_item_e.text.toString())
            }
        }
    }

    override fun setQuestion(question: String) {
        itemView.text_view_test_item_question.text = question
    }

    override fun setAnswer(answers: ArrayList<String>) {
        try {
            itemView.button_test_item_a.text = answers[0]
            itemView.button_test_item_b.text = answers[1]
            itemView.button_test_item_c.text = answers[2]
            itemView.button_test_item_d.text = answers[3]
            itemView.button_test_item_e.text = answers[4]
        } catch (e: Exception) {}
    }

    override fun setQuestionNumber(questionNumber: Int) {
        val numberString = "№$questionNumber"
        itemView.text_view_test_item_number.text = numberString
    }

    override fun applyUserAnswer(trueAnswer: String, userAnswer: String?) {
        with(itemView) {
            checkAnswer(trueAnswer, userAnswer, button_test_item_a)
            checkAnswer(trueAnswer, userAnswer, button_test_item_b)
            checkAnswer(trueAnswer, userAnswer, button_test_item_c)
            checkAnswer(trueAnswer, userAnswer, button_test_item_d)
            checkAnswer(trueAnswer, userAnswer, button_test_item_e)
            if (userAnswer != null) { findTrueButton(trueAnswer) }
        }
    }

    private fun checkAnswer(trueAnswer: String, userAnswer: String?, button: Button) {
        if (userAnswer == button.text) {
            setButtonBackground(button, trueAnswer == button.text, trueAnswer)
        } else {
            button.setBackgroundResource(R.drawable.button_test_item)
            button.setTextColor(Color.BLACK)
        }
    }

    private fun setButtonBackground(button: Button, isTrueAnswer: Boolean, trueAnswer: String) {
        if (isTrueAnswer) {
            button.setBackgroundResource(R.drawable.button_true_test_item)
        } else {
            button.setBackgroundResource(R.drawable.button_wrong_test_item)
        }
        button.setTextColor(Color.WHITE)
    }

    private fun findTrueButton(trueAnswer: String) {
        with(itemView) {
            val trueButton = when (trueAnswer) {
                button_test_item_a.text.toString() -> button_test_item_a
                button_test_item_b.text.toString() -> button_test_item_b
                button_test_item_c.text.toString() -> button_test_item_c
                button_test_item_d.text.toString() -> button_test_item_d
                button_test_item_e.text.toString() -> button_test_item_e
                else -> null
            }
            trueButton?.setBackgroundResource(R.drawable.button_true_test_item)
            trueButton?.setTextColor(Color.WHITE)
            true
        }
    }
}