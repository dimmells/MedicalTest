package com.example.dimic.medicaltest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import com.example.dimic.medicaltest.ui.viewholder.SpecialityItemViewHolder

class SpecialityItemAdapter(private val adapterPresenter: SpecialityItemAdapterContract.AdapterPresenter,
                            private val itemPresenter: SpecialityItemAdapterContract.SpecialityItemPresenter): RecyclerView.Adapter<SpecialityItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): SpecialityItemViewHolder =
            SpecialityItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_speciality_item, parent, false), itemPresenter)

    override fun getItemCount(): Int = adapterPresenter.getItemsCount()

    override fun onBindViewHolder(viewHolder: SpecialityItemViewHolder, position: Int) =
            adapterPresenter.onBindTestItemView(viewHolder as SpecialityItemAdapterContract.SpecialityItemView, position)
}