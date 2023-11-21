package com.persons.finder.infrastructure

/**
 *
 * @property data query result
 *
 */

data class ApiResponse<T>(
    val success: Boolean,
    val code: Int? = null,
    val data: T? = null,
    val runningTime: Double= 0.00
)