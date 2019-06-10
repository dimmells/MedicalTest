package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.CourseModule
import com.example.dimic.medicaltest.presentation.presenter.CoursePresenter
import com.example.dimic.medicaltest.presentation.view.CourseView
import com.example.dimic.medicaltest.ui.adapter.SpecialityItemAdapter
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_speciality.*

class CourseFragment: BaseFragment(), CourseView {

    companion object {

        const val KEY_SPEC_CODE = "specCode"

        fun newInstance(specCode: Int): CourseFragment {
            val args = Bundle()
            args.putInt(KEY_SPEC_CODE, specCode)
            val fragment = CourseFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var specCode: Int = 0

    @InjectPresenter
    lateinit var coursePresenter: CoursePresenter

    @ProvidePresenter
    fun providePresenter(): CoursePresenter = ApplicationLoader.applicationComponent
            .courseComponent(CourseModule())
            .coursePresenter()

    private lateinit var courseItemLayoutManager: LinearLayoutManager
    private lateinit var courseItemAdapter: SpecialityItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { specCode = it.getInt(KEY_SPEC_CODE) }
        coursePresenter.loadCourses(specCode)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_speciality, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        courseItemLayoutManager = LinearLayoutManager(context)
        courseItemAdapter = SpecialityItemAdapter(coursePresenter, coursePresenter)

        recycle_view_speciality_list.apply {
            layoutManager = courseItemLayoutManager
            adapter = courseItemAdapter
        }
    }

    override fun notifyMenuItemsChanged() = courseItemAdapter.notifyDataSetChanged()

    override fun navigateToSubject(courseCode: Int) = (router as MenuRouter).navigateToSubject(courseCode)

}