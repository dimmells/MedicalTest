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
import com.example.dimic.medicaltest.di.module.FileModule
import com.example.dimic.medicaltest.presentation.presenter.FilePresenter
import com.example.dimic.medicaltest.presentation.view.FileView
import com.example.dimic.medicaltest.ui.adapter.SpecialityItemAdapter
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_speciality.*

class FileFragment: BaseFragment(), FileView {

    companion object {

        const val KEY_SUBJECT_CODE = "subjectCode"

        fun newInstance(subjectCode: Int): FileFragment {
            val args = Bundle()
            args.putInt(KEY_SUBJECT_CODE, subjectCode)
            val fragment = FileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var subjectCode: Int = 0

    @InjectPresenter
    lateinit var filePresenter: FilePresenter

    @ProvidePresenter
    fun providePresenter(): FilePresenter = ApplicationLoader.applicationComponent
            .fileComponent(FileModule())
            .filePresenter()

    private lateinit var fileItemLayoutManager: LinearLayoutManager
    private lateinit var fileItemAdapter: SpecialityItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { subjectCode = it.getInt(KEY_SUBJECT_CODE) }
        filePresenter.loadFiles(subjectCode)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_speciality, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fileItemLayoutManager = LinearLayoutManager(context)
        fileItemAdapter = SpecialityItemAdapter(filePresenter, filePresenter)

        recycle_view_speciality_list.apply {
            layoutManager = fileItemLayoutManager
            adapter = fileItemAdapter
        }
    }

    override fun notifyMenuItemsChanged() = fileItemAdapter.notifyDataSetChanged()

    override fun navigateToTest(code: Int, count: Int, start: Int) = (router as MenuRouter).navigateToTest(code, count, start)

}