package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.CourseEntity
import com.example.dimic.medicaltest.interact.CourseInteract
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import com.example.dimic.medicaltest.presentation.view.CourseView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class CoursePresenter(private val courseInteract: CourseInteract): BaseMvpPresenter<CourseView>(),
        SpecialityItemAdapterContract.AdapterPresenter,
        SpecialityItemAdapterContract.SpecialityItemPresenter{

    private val courseList = ArrayList<CourseEntity>()
    private var specialityCode: Int? = null

    override fun getItemsCount(): Int  = courseList.size

    override fun onBindTestItemView(view: SpecialityItemAdapterContract.SpecialityItemView, position: Int) {
        val speciality = courseList[position]

        view.setSpeciality(speciality.name)
    }

    override fun onItemClick(position: Int) = viewState.navigateToSubject("$specialityCode${courseList[position].courseCode}".toInt())

    fun loadCourses(specCode: Int) {
        specialityCode = specCode
        courseInteract.getCourses(specCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {it ->
                            for (i in 0 until it.size) {
                                courseList.add(it[i].copy())
                            }
                            viewState.notifyMenuItemsChanged()
                        },
                        onError = {}
                )
                .addTo(disposables)
    }
}