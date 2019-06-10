package com.example.dimic.medicaltest.presentation.presenter

import com.arellomobile.mvp.InjectViewState
import com.example.dimic.medicaltest.data.server.responce.ExamResponceEntity
import com.example.dimic.medicaltest.interact.ExamListInteract
import com.example.dimic.medicaltest.presentation.adapter.SpecialityItemAdapterContract
import com.example.dimic.medicaltest.presentation.view.ExamListView
import com.example.dimic.medicaltest.ui.fragment.ExamListFragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class ExamListPresenter(private val examListInteract: ExamListInteract): BaseMvpPresenter<ExamListView>(),
        SpecialityItemAdapterContract.AdapterPresenter,
        SpecialityItemAdapterContract.SpecialityItemPresenter {

    private var type: Int? = null
    private var examList = ArrayList<ExamResponceEntity>()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        examListInteract.getExams()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            examList = it
                            viewState.notifyDataSetUpdate()
                        },
                        { viewState.showToast(it.localizedMessage) }
                )
                .let { disposables.add(it) }
    }

    fun onCreate(type: Int) { this.type = type }

    override fun getItemsCount(): Int = examList.size

    override fun onBindTestItemView(view: SpecialityItemAdapterContract.SpecialityItemView, position: Int) {
        view.setSpeciality(examList[position].name)
    }

    override fun onItemClick(position: Int) {
        if (type == ExamListFragment.EXAM_LIST_EXAM) {
        } else {
        }
    }
}