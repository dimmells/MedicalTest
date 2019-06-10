package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.MenuModule
import com.example.dimic.medicaltest.presentation.presenter.MenuPresenter
import com.example.dimic.medicaltest.presentation.view.MenuView
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment: BaseFragment(), MenuView {

    companion object {
        fun newInstance(): MenuFragment = MenuFragment()
    }

    @InjectPresenter
    lateinit var menuPresenter: MenuPresenter

    @ProvidePresenter
    fun providePresenter(): MenuPresenter = ApplicationLoader.applicationComponent
            .menuComponent(MenuModule())
            .menuPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_menu, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        image_view_menu_power.setOnClickListener { menuPresenter.onPowerClick() }
        button_menu_preparation.setOnClickListener { menuPresenter.onPreparationClick() }
        button_menu_exam.setOnClickListener { menuPresenter.onExamClick() }
        button_menu_result.setOnClickListener { menuPresenter.onResultClick() }
        button_menu_create_exam.setOnClickListener { menuPresenter.onCreateExamClick() }
    }

    override fun setUserNickName(name: String) { text_view_menu_name.text = name }

    override fun disableTeacherButtons() {
        button_menu_result.visibility = View.GONE
        button_menu_create_exam.visibility = View.GONE
    }

    override fun restartActivity() {
        val intent = activity?.intent
        activity?.finish()
        startActivity(intent)
    }

    override fun navigateToPreparation(code: Int) = (router as MenuRouter).navigateToSubject(code)

    override fun navigateToExams() = (router as MenuRouter).navigateToExams(ExamListFragment.EXAM_LIST_EXAM)

    override fun navigateToResults() = (router as MenuRouter).navigateToResult(ExamListFragment.EXAM_LIST_RESULT)

    override fun navigateToCreateExam() = (router as MenuRouter).navigateToCreateExam()
}