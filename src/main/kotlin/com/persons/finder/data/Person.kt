package com.persons.finder.data

import org.hibernate.annotations.Comment
import javax.persistence.*

/**
 * person
 * @param person id
 * @name name person name
 */
@Entity
data class Person(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("primary key id(auto-increment)")
    val id: Long? =null,

    @Column(nullable = true, length = 50)
    @Comment("name")
    val name: String? = null,

    )
