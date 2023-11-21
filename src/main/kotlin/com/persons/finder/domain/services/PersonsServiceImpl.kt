package com.persons.finder.domain.services

import com.persons.finder.data.Person
import com.persons.finder.repository.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.transaction.Transactional

/**
 * person service
 */
@Service
class PersonsServiceImpl(
    @Autowired
    private val personRepository: PersonRepository
) : PersonsService {

    /**
     * find person by person id
     * @param id person id
     */
    override fun getById(id: Long): Person {
        return personRepository.findFirstById(id) ?: throw NoSuchElementException("No such person with id $id")
    }

    /**
     * save person
     * @param person person object
     */
    @Transactional
    override fun save(person: Person): Person {
        return personRepository.save(person)
    }

    /**
     * find person by list of person ids
     * @param list person ids
     */
    override fun getPersonsByIds(ids: List<String>): List<Person> {
        val longList: List<Long> = ids.map { it.toLong() }
        return personRepository.findAllByIdIn(longList);
    }

}