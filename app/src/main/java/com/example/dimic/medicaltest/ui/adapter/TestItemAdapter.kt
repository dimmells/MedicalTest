package com.example.dimic.medicaltest.ui.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.presentation.adapter.TestItemAdapterContract
import com.example.dimic.medicaltest.ui.viewholder.TestItemViewHolder

class TestItemAdapter(private val adapterPresenter: TestItemAdapterContract.AdapterPresenter,
                      private val itemPresenter: TestItemAdapterContract.TestItemPresenter): RecyclerView.Adapter<TestItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, itemType: Int): TestItemViewHolder =
            TestItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.fragment_test_item, parent, false), itemPresenter)

    override fun getItemCount(): Int = adapterPresenter.getItemsCount()

    override fun onBindViewHolder(viewHolder: TestItemViewHolder, position: Int) =
            adapterPresenter.onBindTestItemView(viewHolder as TestItemAdapterContract.TestItemView, position)
}