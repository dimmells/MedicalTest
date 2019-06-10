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
import com.example.dimic.medicaltest.di.module.SpecialityModule
import com.example.dimic.medicaltest.presentation.presenter.SpecialityPresenter
import com.example.dimic.medicaltest.presentation.view.SpecialityView
import com.example.dimic.medicaltest.ui.adapter.SpecialityItemAdapter
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import kotlinx.android.synthetic.main.fragment_speciality.*

class SpecialityFragment: BaseFragment(), SpecialityView {

    companion object {
        fun newInstance() = SpecialityFragment()
    }

    @InjectPresenter
    lateinit var specialityPresenter: SpecialityPresenter

    @ProvidePresenter
    fun providePresenter(): SpecialityPresenter = ApplicationLoader.applicationComponent
            .specialityComponent(SpecialityModule())
            .specialityPresenter()

    private lateinit var specialityItemLayoutManager: LinearLayoutManager
    private lateinit var specialityItemAdapter: SpecialityItemAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_speciality, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        specialityItemLayoutManager = LinearLayoutManager(context)
        specialityItemAdapter = SpecialityItemAdapter(specialityPresenter, specialityPresenter)

        recycle_view_speciality_list.apply {
            layoutManager = specialityItemLayoutManager
            adapter = specialityItemAdapter
        }
    }

    override fun notifyMenuItemsChanged() = specialityItemAdapter.notifyDataSetChanged()

    override fun navigateToCourse(specCode: Int) = (router as MenuRouter).navigateToCourse(specCode)

}