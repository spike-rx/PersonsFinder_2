package com.persons.finder.domain.services

import org.springframework.stereotype.Service

interface SaveDataService {

    fun saveMillionData()

    fun saveTenMillionData()

    fun saveHundredMillionData()
}