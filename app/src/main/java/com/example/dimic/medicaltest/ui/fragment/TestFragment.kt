package com.example.dimic.medicaltest.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.TestModule
import com.example.dimic.medicaltest.presentation.presenter.TestPresenter
import com.example.dimic.medicaltest.presentation.view.TestView
import com.example.dimic.medicaltest.ui.adapter.TestItemAdapter
import kotlinx.android.synthetic.main.fragment_test.*

class TestFragment: BaseFragment(), TestView {

    companion object {

        const val KEY_FILE_CODE = "fileCode"
        const val KEY_START = "start"
        const val KEY_COUNT = "count"

        fun newInstance(fileCode: Int, count: Int, start: Int): TestFragment {
            val args = Bundle()
            with (args) {
                putInt(KEY_FILE_CODE, fileCode)
                putInt(KEY_START, start)
                putInt(KEY_COUNT, count)
            }
            val fragment = TestFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var code = 0
    private var start = 0
    private var count = 0

    @InjectPresenter
    lateinit var testPresenter: TestPresenter

    @ProvidePresenter
    fun providePresenter(): TestPresenter = ApplicationLoader.applicationComponent
            .testComponent(TestModule())
            .testPresenter()

    private lateinit var testItemLayoutManager: LinearLayoutManager
    private lateinit var testItemAdapter: TestItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { with (it) {
            code = getInt(KEY_FILE_CODE)
            start = getInt(KEY_START)
            count = getInt(KEY_COUNT)
        }}
        testPresenter.loadQuestions(code, count, start)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
            inflater.inflate(R.layout.fragment_test, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        testItemLayoutManager = LinearLayoutManager(context)
        testItemAdapter = TestItemAdapter(testPresenter, testPresenter)

        recycle_view_test_list.apply {
            layoutManager = testItemLayoutManager
            adapter = testItemAdapter
        }
    }

    override fun notifyMenuItemsChanged() = testItemAdapter.notifyDataSetChanged()

    override fun notifyMenuItemChanged(position: Int) = testItemAdapter.notifyItemChanged(position)
}