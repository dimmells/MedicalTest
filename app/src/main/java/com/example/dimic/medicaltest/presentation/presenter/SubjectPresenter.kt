package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.interact.SubjectInteract
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import com.example.dimic.medicaltest.presentation.view.SubjectView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class SubjectPresenter(private val subjectInteract: SubjectInteract): BaseMvpPresenter<SubjectView>(),
        SpecialityItemAdapterContract.AdapterPresenter,
        SpecialityItemAdapterContract.SpecialityItemPresenter{

    private val subjectList = ArrayList<SpecialityEntity>()

    private var userCourseCode: Int? = null

    override fun getItemsCount(): Int  = subjectList.size

    override fun onBindTestItemView(view: SpecialityItemAdapterContract.SpecialityItemView, position: Int) {
        val subject = subjectList[position]

        view.setSpeciality(subject.name)
    }

    override fun onItemClick(position: Int) = viewState.navigateToFile("$userCourseCode${subjectList[position].code}".toInt())

    fun loadSubjects(courseCode: Int) {
        userCourseCode = courseCode
        subjectInteract.getSubjects(courseCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {it ->
                            for (i in 0 until it.size) {
                                subjectList.add(it[i].copy())
                            }
                            viewState.notifyMenuItemsChanged()
                        },
                        onError = {}
                )
                .addTo(disposables)
    }
}