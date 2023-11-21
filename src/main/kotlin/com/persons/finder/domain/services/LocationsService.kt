package com.persons.finder.domain.services

import com.persons.finder.data.Location

interface LocationsService {
    fun addLocation(location: Location): Location
    fun removeLocation(locationReferenceId: Long): Long
    fun findAround(referenceId: Long) : List<Any>
}