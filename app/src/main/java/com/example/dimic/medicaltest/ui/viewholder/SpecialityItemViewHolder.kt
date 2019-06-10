package com.example.dimic.medicaltest.ui.viewholder

import android.view.View
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import kotlinx.android.synthetic.main.fragment_speciality_item.view.*

class SpecialityItemViewHolder(itemView: View, presenter: SpecialityItemAdapterContract.SpecialityItemPresenter):
        BaseViewHolder(itemView),
        SpecialityItemAdapterContract.SpecialityItemView  {

    init {
        itemView.setOnClickListener { presenter.onItemClick(adapterPosition) }
    }

    override fun setSpeciality(speciality: String) {
        itemView.text_view_speciality_item_name.text = speciality
    }
}