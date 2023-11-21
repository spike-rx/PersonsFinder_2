package com.persons.finder.presentation

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.domain.services.LocationsService
import com.persons.finder.domain.services.PersonsService
import com.persons.finder.domain.services.SaveDataService
import com.persons.finder.infrastructure.ApiResponse
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/persons")
class PersonController(
    private val personsService: PersonsService,
    private val locationsService: LocationsService,
    private val saveDataService: SaveDataService
) {

    /**
     * PUT API to update/create someone's location using latitude and longitude
     */
    @PutMapping("/location/createOrUpdate")
    fun updateOrCreateLocation(@RequestBody location: Location): ApiResponse<Location> {
        val data =  locationsService.addLocation(location);
        return ApiResponse(success = true, code = 200, data = data)
    }

    /**
     *         POST API to create a 'person'
     *         (JSON) Body and return the id of the created entity
     */
    @PostMapping("/create")
    fun createPerson(@RequestBody person: Person): ApiResponse<Person> {
        val data = personsService.save(person)
        return ApiResponse(success = true, code = 200, data = data)

    }
    /**
     * GET API to retrieve people around query location with a radius in KM, Use query param for radius.
     * API just return a list of persons ids (JSON)
     * Example
     * John wants to know who is around his location within a radius of 10km
     * API would be called using John's id and a radius 10km
     */
    @GetMapping("/around/{id}")
    fun getAroundPeople(@PathVariable id: Long): ApiResponse<List<Any>> {
        val startTime = System.currentTimeMillis()
        val data = locationsService.findAround(id)
        val endTime = System.currentTimeMillis()
        val elapsedTime = (endTime - startTime) / 1000.0
        return ApiResponse(success = true, code = 200, data = data, runningTime = elapsedTime)

    }

    /**
     * GET API to retrieve a person or persons name using their ids
     * Example
     * John has the list of people around them, now they need to retrieve everybody's names to display in the app
     * API would be called using person or persons ids
     */
    @GetMapping("/name")
    fun getPersonName(@RequestParam ids: String): ApiResponse<List<Person>> {
        val idList: List<String> = ids.split(",")
        val data =  personsService.getPersonsByIds(idList)
        return ApiResponse(success = true, code = 200, data = data)
    }


    /**
     * save a million fake data
     */
    @GetMapping("/million")
    fun saveMillion(): ApiResponse<String> {
        saveDataService.saveMillionData()
        return ApiResponse(success = true, code = 200, data = "Save In Processing")
    }

    /**
     * save a ten million fake data
     */
    @GetMapping("/million/ten")
    fun saveTenMillion(): ApiResponse<String> {
        saveDataService.saveTenMillionData();
        return ApiResponse(success = true, code = 200, data = "Save In Processing")
    }

    /**
     * save a hundred million fake data
     */
    @GetMapping("/million/hundred")
    fun saveHundredMillion(): ApiResponse<String> {
        saveDataService.saveHundredMillionData();
        return ApiResponse(success = true, code = 200, data = "Save In Processing")
    }

    @GetMapping("/example")
    fun getExample(): String {
        return "Hello Example"
    }

}