package com.persons.finder.data

import org.hibernate.annotations.Comment
import javax.persistence.*

@Entity
data class Location(
    // Tip: Person's id can be used for this field
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Comment("primary key id(auto-increment)")
    val id:Long?=null,

    @Column(nullable = true, unique = true)
    @Comment("Person Id")
    val referenceId: Long? = null,


    @Column(nullable = true)
    @Comment("latitude value")
    val latitude: Double = 0.00,
    @Column(nullable = true)
    @Comment("longitude value")
    val longitude: Double? = 0.00,

    val distance: Double? =0.00

)