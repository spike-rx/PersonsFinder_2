package com.persons.finder.repository

import com.persons.finder.data.Location
import com.persons.finder.dto.SearchLocation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : JpaRepository<Location, Long> {

    /**
     * delete data
     * @param referenceId person id
     */
    fun deleteByReferenceId(referenceId: Long): Location


    /**
     * find location by reference id
     *  @param referenceId person id
     *  @return location person location details
     */
    fun findFirstByReferenceId(referenceId: Long): Location?
}