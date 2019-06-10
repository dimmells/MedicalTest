package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Single

class SpecialityInteract(private val testService: TestService) {

    fun getSpecialities(): Single<List<SpecialityEntity>> = testService.getSpecialities()
            .map { response ->
                ArrayList<SpecialityEntity>().apply {
                    addAll(response)
                }
            }
}