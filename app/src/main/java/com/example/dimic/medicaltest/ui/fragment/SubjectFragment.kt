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
import com.example.dimic.medicaltest.di.module.SubjectModule
import com.example.dimic.medicaltest.presentation.presenter.SubjectPresenter
import com.example.dimic.medicaltest.presentation.view.SubjectView
import com.example.dimic.medicaltest.ui.adapter.SpecialityItemAdapter
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_speciality.*

class SubjectFragment: BaseFragment(), SubjectView {

    companion object {

        const val KEY_COURSE_CODE = "courseCode"

        fun newInstance(courseCode: Int): SubjectFragment {
            val args = Bundle()
            args.putInt(KEY_COURSE_CODE, courseCode)
            val fragment = SubjectFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var courseCode: Int = 0

    @InjectPresenter
    lateinit var subjectPresenter: SubjectPresenter

    @ProvidePresenter
    fun providePresenter(): SubjectPresenter = ApplicationLoader.applicationComponent
            .subjectComponent(SubjectModule())
            .subjectPresenter()

    private lateinit var subjectItemLayoutManager: LinearLayoutManager
    private lateinit var subjectItemAdapter: SpecialityItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { courseCode = it.getInt(KEY_COURSE_CODE) }
        subjectPresenter.loadSubjects(courseCode)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_speciality, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subjectItemLayoutManager = LinearLayoutManager(context)
        subjectItemAdapter = SpecialityItemAdapter(subjectPresenter, subjectPresenter)

        recycle_view_speciality_list.apply {
            layoutManager = subjectItemLayoutManager
            adapter = subjectItemAdapter
        }
    }

    override fun notifyMenuItemsChanged() = subjectItemAdapter.notifyDataSetChanged()

    override fun navigateToFile(subjectCode: Int) = (router as MenuRouter).navigateToFile(subjectCode)

}