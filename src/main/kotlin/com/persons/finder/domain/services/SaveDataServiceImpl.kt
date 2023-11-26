package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.repository.LocationRepository
import com.persons.finder.repository.PersonRepository
import org.springframework.data.geo.Point
import org.springframework.data.redis.connection.RedisGeoCommands
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * Asynchronously store big data for testing
 */
const val size = 1000
@Service
class SaveDataServiceImpl(
    private val personRepository: PersonRepository,
    private val locationsRepository: LocationRepository,
    private val redisTemplate: RedisTemplate<String, Any>
) : SaveDataService {
    @Async
    override fun saveMillionData() {

        val locationList: MutableList<Location> = mutableListOf()
        redisTemplate.executePipelined{connection ->
            for (i in 3..1000000) {
                val location = Location(null, i.toLong(), 10.123456, 20.654321)
                connection.geoAdd(userLocationKey.toByteArray(), RedisGeoCommands.GeoLocation(i.toString().toByteArray(), Point(location.longitude!!, location.latitude)))
                locationList.add(location)
                if (locationList.size == 1000){
                    locationsRepository.saveAll(locationList)
                    locationsRepository.flush()
                }

            }
            null

        }

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