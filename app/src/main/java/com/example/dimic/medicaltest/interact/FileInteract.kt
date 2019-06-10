package com.example.dimic.medicaltest.interact

import com.example.dimic.medicaltest.data.FileEntity
import com.example.dimic.medicaltest.data.SpecialityEntity
import com.example.dimic.medicaltest.data.server.TestService
import io.reactivex.Single

class FileInteract(private val testService: TestService) {

    fun getFiles(subjectCode: Int): Single<List<FileEntity>> = testService.getFiles(subjectCode)
            .map { response ->
                ArrayList<FileEntity>().apply {
                    addAll(response)
                }
            }
}