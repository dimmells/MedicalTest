package com.example.dimic.medicaltest.ui.activity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import com.arellomobile.mvp.presenter.InjectPresenter
import com.arellomobile.mvp.presenter.ProvidePresenter
import com.example.dimic.medicaltest.R
import com.example.dimic.medicaltest.di.ApplicationLoader
import com.example.dimic.medicaltest.di.module.MainModule
import com.example.dimic.medicaltest.ui.navigation.MenuRouter
import com.example.dimic.medicaltest.presentation.presenter.MainPresenter
import com.example.dimic.medicaltest.presentation.view.MainView
import com.example.dimic.medicaltest.ui.fragment.*

class MainActivity : BaseActivity(), MainView, MenuRouter {

    @InjectPresenter
    lateinit var mainPresenter: MainPresenter

    @ProvidePresenter
    fun providePresenter(): MainPresenter = ApplicationLoader.applicationComponent
            .mainComponent(MainModule())
            .mainPresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun navigateToSpeciality() = setFragment(SpecialityFragment.newInstance(), true, true)

    override fun navigateToCourse(specCode: Int) = setFragment(CourseFragment.newInstance(specCode), true, true)

    override fun navigateToSubject(courseCode: Int) = setFragment(SubjectFragment.newInstance(courseCode), true, true)

    override fun navigateToFile(subjectCode: Int) = setFragment(FileFragment.newInstance(subjectCode), true, true)

    override fun navigateToTest(code: Int, count: Int, start: Int) = setFragment(TestFragment.newInstance(code, count, start), true, true)

    override fun navigateToRegistration() = setFragment(RegistrationFragment.newInstance(), true, true)

    override fun navigateToLogin() = setFragment(LoginFragment.newInstance(), false, true)

    override fun navigateToMenu() = setFragment(MenuFragment.newInstance(), true, true)

    override fun navigateToPreparation() {}

    override fun navigateToExams(type: Int) { setFragment(ExamListFragment.newInstance(type), true, true) }

    override fun navigateToResult(type: Int) { setFragment(ExamListFragment.newInstance(type), true, true) }

    override fun navigateToCreateExam() = setFragment(CreateExamFragment.newInstance(), true, true)

    private fun setFragment(fragment: Fragment, addToBackStack: Boolean, menuAnimation: Boolean = false) {
        supportFragmentManager.beginTransaction()
                .apply {
                    if (menuAnimation)
                        setCustomAnimations(R.anim.menu_translation_from_right,
                                R.anim.menu_translation_to_left,
                                R.anim.menu_translation_from_left,
                                R.anim.menu_translation_to_right)
                    else
                        setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                }
                .replace(R.id.frame_layout_main_fragment_container, fragment)
                .apply { if (addToBackStack) addToBackStack(null) }
                .commit()
    }
}
