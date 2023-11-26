package com.persons.finder.presentation

import com.persons.finder.data.Location
import com.persons.finder.data.Person
import com.persons.finder.repository.LocationRepository
import com.persons.finder.repository.PersonRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper


@SpringBootTest
@AutoConfigureMockMvc
class PersonControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var personRepositoryMock: PersonRepository

    @MockBean
    private lateinit var locationRepositoryMock: LocationRepository

    @AfterEach
    fun afterEach() {
        Mockito.reset(personRepositoryMock)
    }

    @Test
    fun `create or update location test`() {
        Mockito.`when`(locationRepositoryMock.save(Location(null, 3, 43.242355, 126.454656)))
            .thenReturn(Location(null, 3, 43.242355, 126.454656))
        mockMvc.perform(
            MockMvcRequestBuilders.post("/location/createOrUpdate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(Location(null, 3, 43.242355, 126.454656)))
        )
    }

    @Test
    fun `create or update person test`() {
        Mockito.`when`(personRepositoryMock.save(Person(1, "Jon")))
            .thenReturn(Person(1, "Jon"))

        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/v1/persons/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(Person(1, "Jon")))

        ).andExpect(MockMvcResultMatchers.status().isOk)
    }

    @Test
    fun `get around people test`() {
        Mockito.`when`(locationRepositoryMock.findFirstByReferenceId(1))
            .thenReturn(Location(1, 1, 43.323233, 128.4546574))

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/persons/around/1")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk).andExpect(MockMvcResultMatchers.content().json("{}"))

    }

    @Test
    fun `get person name test`() {
        val idList: List<Long> = listOf(1, 2, 3)
        Mockito.`when`(personRepositoryMock.findAllByIdIn(idList))
            .thenReturn(listOf(Person(1, "xu"), Person(2, "kd"), Person(3, "pg")))

        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/v1/persons/name?ids=1,2,3")
                .contentType(MediaType.APPLICATION_JSON)

        ).andExpect(MockMvcResultMatchers.status().isOk).andExpect(
            MockMvcResultMatchers.content()
                .json("{\"success\":true,\"code\":200,\"data\":[{\"id\":1,\"name\":\"xu\"},{\"id\":2,\"name\":\"kd\"},{\"id\":3,\"name\":\"pg\"}],\"runningTime\":0.0}")
        )
    }

}