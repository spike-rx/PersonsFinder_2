package com.persons.finder.repository

import com.persons.finder.data.Person
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepository: JpaRepository<Person, Long> {
    /**
     * find person
     * @param id user id
     * @return person object
     */
    fun findFirstById(id: Long): Person?

    /**
     * find all person in id list
     * @param id list
     * @return list persons
     */
    fun findAllByIdIn(ids: List<Long>): List<Person>
}