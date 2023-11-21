package com.persons.finder.dto

data class SearchLocation(
    val currentLongitude: Double? = 0.0,
    val currentLatitude: Double? = 0.0,
    val maxLongitude: Double ? = 0.0,
    val minLongitude: Double ? = 0.0,
    val maxLatitude: Double ? = 0.0,
    val minLatitude: Double ?=0.0
)