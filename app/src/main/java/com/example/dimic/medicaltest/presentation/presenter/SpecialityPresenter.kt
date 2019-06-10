package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.interact.SpecialityInteract
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import com.example.dimic.medicaltest.presentation.view.SpecialityView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class SpecialityPresenter(private val specialityInteract: SpecialityInteract): BaseMvpPresenter<SpecialityView>(),
        SpecialityItemAdapterContract.AdapterPresenter,
        SpecialityItemAdapterContract.SpecialityItemPresenter{

    private val specialitiesList = ArrayList<SpecialityEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadSpecialities()
    }

    override fun getItemsCount(): Int  = specialitiesList.size

    override fun onBindTestItemView(view: SpecialityItemAdapterContract.SpecialityItemView, position: Int) {
        val speciality = specialitiesList[position]

        view.setSpeciality(speciality.name)
    }

    override fun onItemClick(position: Int) = viewState.navigateToCourse(specialitiesList[position].code)

    private fun loadSpecialities() {
        specialityInteract.getSpecialities()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {it ->
                            for (i in 0 until it.size) {
                                specialitiesList.add(it[i].copy())
                            }
                            viewState.notifyMenuItemsChanged()
                        },
                        onError = {}
                ).addTo(disposables)
    }
}