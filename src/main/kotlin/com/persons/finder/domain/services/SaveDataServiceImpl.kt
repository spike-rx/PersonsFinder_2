package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.repository.LocationRepository
import com.persons.finder.repository.PersonRepository
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * Asynchronously store big data for testing
 */
@Service
class SaveDataServiceImpl(
    private val personRepository: PersonRepository,
    private val locationsRepository: LocationRepository
) : SaveDataService {
    @Async
    override fun saveMillionData() {

        val locationList: MutableList<Location> = mutableListOf()
        for (i in 3..1000000) {
            val location = Location(null, i.toLong(), 9999.9999, 99999.9999)
            locationList.add(location);
        }
        locationsRepository.saveAll(locationList)

    }

    @Async
    override fun saveTenMillionData() {

        val personList: MutableList<Person> = mutableListOf()
        for (i in 1..10000000) {
            val person = Person(null, "xu")
            personList.add(person);
        }
        personRepository.saveAll(personList);
    }

    @Async
    override fun saveHundredMillionData() {
        val personList: MutableList<Person> = mutableListOf()
        for (i in 1..100000000) {
            val person = Person(null, "xu")
            personList.add(person);
        }
        personRepository.saveAll(personList);
    }
}