package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.FileEntity
import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.interact.FileInteract
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import com.example.dimic.medicaltest.presentation.view.FileView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy

@InjectViewState
class FilePresenter(private val fileInteract: FileInteract): BaseMvpPresenter<FileView>(),
        SpecialityItemAdapterContract.AdapterPresenter,
        SpecialityItemAdapterContract.SpecialityItemPresenter{

    private val filesList = ArrayList<FileEntity>()

    private var currentSubjectCode: Int? = null

    override fun getItemsCount(): Int  = filesList.size

    override fun onBindTestItemView(view: SpecialityItemAdapterContract.SpecialityItemView, position: Int) {
        val subject = filesList[position]

        view.setSpeciality(subject.name)
    }

    override fun onItemClick(position: Int) = viewState.navigateToTest("$currentSubjectCode${filesList[position].fileCode}".toInt(), 24, 0)

    fun loadFiles(subjectCode: Int) {
        currentSubjectCode = subjectCode
        fileInteract.getFiles(subjectCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeBy(
                        onSuccess = {it ->
                            for (i in 0 until it.size) {
                                filesList.add(it[i].copy())
                            }
                            viewState.notifyMenuItemsChanged()
                        },
                        onError = {}
                )
                .addTo(disposables)
    }
}