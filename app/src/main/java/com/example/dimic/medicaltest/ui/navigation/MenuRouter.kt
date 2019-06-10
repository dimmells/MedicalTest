package com.example.dimic.medicaltest.ui.navigation

interface MenuRouter: BaseRouter {

    fun navigateToSpeciality()

    fun navigateToCourse(specCode: Int)

    fun navigateToSubject(courseCode: Int)

    fun navigateToFile(subjectCode: Int)

    fun navigateToTest(code: Int, count: Int, start: Int)

    fun navigateToRegistration()

    fun navigateToMenu()

    fun navigateToPreparation()

    fun navigateToExams(type: Int)

    fun navigateToResult(type: Int)

    fun navigateToCreateExam()

}