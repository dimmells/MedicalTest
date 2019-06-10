package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.ExamListModule
import com.example.dimic.medicaltest.presentation.presenter.ExamListPresenter
import com.example.dimic.medicaltest.presentation.view.ExamListView
import com.example.dimic.medicaltest.ui.adapter.SpecialityItemAdapter
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_exam_list.*

class ExamListFragment: BaseFragment(), ExamListView {

    companion object {
        const val KEY_TYPE = "type"
        const val EXAM_LIST_RESULT = 1
        const val EXAM_LIST_EXAM = 2

        fun newInstance(type: Int): ExamListFragment {
            val fragment = ExamListFragment()
            val args = Bundle()
            args.putInt(KEY_TYPE, type)
            fragment.arguments = args
            return fragment
        }
    }

    private var type: Int? = null

    private lateinit var examItemLayoutManager: LinearLayoutManager
    private lateinit var examItemAdapter: SpecialityItemAdapter

    @InjectPresenter
    lateinit var examListPresenter: ExamListPresenter

    @ProvidePresenter
    fun providePresenter(): ExamListPresenter = ApplicationLoader.applicationComponent
            .examListComponent(ExamListModule())
            .examListPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { type = it.getInt(KEY_TYPE) }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_exam_list, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        type?.let { examListPresenter.onCreate(it) }

        examItemLayoutManager = LinearLayoutManager(context)
        examItemAdapter = SpecialityItemAdapter(examListPresenter, examListPresenter)

        recycle_view_exam_list.apply {
            layoutManager = examItemLayoutManager
            adapter = examItemAdapter
        }
    }

    override fun notifyDataSetUpdate() = examItemAdapter.notifyDataSetChanged()

    override fun navigateToTest(code: Int, count: Int, start: Int) = (router as MenuRouter).navigateToTest(code, count, start)
}