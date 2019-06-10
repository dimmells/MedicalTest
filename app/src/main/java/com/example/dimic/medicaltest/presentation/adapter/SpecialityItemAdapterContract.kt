package com.example.dimic.medicaltest.presentation.adapter

interface SpecialityItemAdapterContract {

    interface AdapterPresenter {
        fun getItemsCount(): Int
        fun onBindTestItemView(view: SpecialityItemView, position: Int)
    }

    interface SpecialityItemPresenter {
        fun onItemClick(position: Int)
    }

    interface SpecialityItemView {
        fun setSpeciality(speciality: String)
    }
}