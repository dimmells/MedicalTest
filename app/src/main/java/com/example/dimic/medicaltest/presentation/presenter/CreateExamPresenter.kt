package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.CourseEntity
import com.example.dimic.medicaltest.data.FileEntity
import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.interact.CreateExamInteract
import com.example.dimic.medicaltest.presentation.view.CreateExamView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class CreateExamPresenter(private val createExamInteract: CreateExamInteract): BaseMvpPresenter<CreateExamView>() {

    private var specialityList: List<SpecialityEntity> = listOf()
    private var courseList: List<CourseEntity> = listOf()
    private var subjectList: List<SpecialityEntity> = listOf()
    private var fileList: List<FileEntity> = listOf()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        createExamInteract.getSpecialities()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            specialityList = it
                            val array = arrayOfNulls<String>(specialityList.size)
                            specialityList.forEachIndexed { index, specialityEntity ->
                                array[index] = specialityEntity.name
                            }
                            viewState.setSpecialitySpinnerList(array)
                        },
                        { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
    }

    fun loadCourses(index: Int) {
        createExamInteract.getCourses(specialityList[index].code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            courseList = it
                            val array = arrayOfNulls<String>(courseList.size)
                            courseList.forEachIndexed { index, courseEntity ->
                                array[index] = courseEntity.name
                            }
                            viewState.setCourseSpinnerList(array)
                        },
                        { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
    }

    fun loadSubjects(courseIndex: Int) {
        createExamInteract.getSubjects("${courseList[courseIndex].specCode}${courseList[courseIndex].courseCode}".toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            subjectList = it
                            val array = arrayOfNulls<String>(subjectList.size)
                            subjectList.forEachIndexed { index, subjectEntity ->
                                array[index] = subjectEntity.name
                            }
                            viewState.setSubjectSpinnerList(array)
                        },
                        { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
    }

    fun loadFiles(courseIndex: Int, subjectIndex: Int) {
        createExamInteract.getFiles("${courseList[courseIndex].specCode}${courseList[courseIndex].courseCode}${subjectList[subjectIndex].code}".toInt())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            fileList = it
                            val array = arrayOfNulls<String>(fileList.size)
                            fileList.forEachIndexed { index, fileEntity ->
                                array[index] = fileEntity.name
                            }
                            viewState.setFileSpinnerList(array)
                        },
                        { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
    }

    fun onCreateClick(name: String, groupIndex: Int, fileIndex: Int, time: Int, marResult: Int, count: Int) {
        createExamInteract.createExam(name, courseList[groupIndex].courseCode, fileList[fileIndex].fileCode, time, marResult, count)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { viewState.showToast("Екзамен створено") },
                        { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
    }
}