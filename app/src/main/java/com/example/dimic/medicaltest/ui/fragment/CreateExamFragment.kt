package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.CreateExamModule
import com.example.dimic.medicaltest.presentation.presenter.CreateExamPresenter
import com.example.dimic.medicaltest.presentation.view.CreateExamView
import kotlinx.android.synthetic.main.fragment_create_exam.*

class CreateExamFragment: BaseFragment(), CreateExamView {

    companion object {
        fun newInstance(): CreateExamFragment = CreateExamFragment()
    }

    @InjectPresenter
    lateinit var createExamPresenter: CreateExamPresenter

    @ProvidePresenter
    fun providePresenter(): CreateExamPresenter = ApplicationLoader.applicationComponent
            .createExamComponent(CreateExamModule())
            .createExamPresenter()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_create_exam, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        spinner_create_exam_speciality.onItemSelectedListener = (object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                createExamPresenter.loadCourses(position)
            }
        })

        spinner_create_exam_course.onItemSelectedListener = (object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                createExamPresenter.loadSubjects(position)
            }
        })

        spinner_create_exam_subject.onItemSelectedListener = (object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                createExamPresenter.loadFiles(spinner_create_exam_course.selectedItemPosition, position)
            }
        })

        number_picker_create_exam_time.apply {
            minValue = 10
            maxValue = 120
            setOnValueChangedListener { _, i, _ ->
                number_picker_create_exam_count.apply {
                    minValue = 5
                    maxValue = i / 2
                }
            }
        }

        button_menu_create_exam_create.setOnClickListener {
            createExamPresenter.onCreateClick(
                    edit_text_create_exam_name.text.toString(),
                    spinner_create_exam_course.selectedItemPosition,
                    spinner_create_exam_file.selectedItemPosition,
                    number_picker_create_exam_time.value,
                    edit_text_create_exam_max_result.text.toString().toInt(),
                    number_picker_create_exam_count.value
            )
        }
    }

    override fun setSpecialitySpinnerList(array: Array<String?>) {
        context?.let { context -> spinner_create_exam_speciality.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, array) }
    }

    override fun setCourseSpinnerList(array: Array<String?>) {
        context?.let { context -> spinner_create_exam_course.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, array) }
    }

    override fun setSubjectSpinnerList(array: Array<String?>) {
        context?.let { context -> spinner_create_exam_subject.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, array) }
    }

    override fun setFileSpinnerList(array: Array<String?>) {
        context?.let { context -> spinner_create_exam_file.adapter = ArrayAdapter(context, android.R.layout.simple_spinner_item, array) }
    }
}