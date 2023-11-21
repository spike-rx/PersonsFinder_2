package com.persons.finder.domain.services

import com.persons.finder.data.Location
import com.persons.finder.repository.LocationRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.geo.*
import org.springframework.data.redis.connection.RedisGeoCommands
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.util.concurrent.TimeUnit
import javax.transaction.Transactional


const val radiusInKm: Double = 10000.0
const val userLocationKey = "user_location"
const val expirationTimeInSeconds: Long = 180

@Service
class LocationsServiceImpl(
    @Autowired
    private val locationRepository: LocationRepository,
    @Autowired
    private val redisTemplate: RedisTemplate<String, Any>
) : LocationsService {

    /**
     * add or update location
     * @param location
     */
    @Transactional
    override fun addLocation(location: Location): Location {
        // saving data to redis
        redisTemplate.opsForGeo().add(
            userLocationKey,
            Point(location.longitude!!, location.latitude), location.referenceId!!
        )

        return locationRepository.save(location);
    }

    /**
     * delete location
     * @param id  location id
     */
    @Transactional
    override fun removeLocation(locationReferenceId: Long): Long{
        val location = locationRepository.deleteByReferenceId(locationReferenceId)
        // delete date
        redisTemplate.opsForGeo().remove(userLocationKey,
            Point(location.longitude!!, location.latitude), location.referenceId!!)

        return location.referenceId;
    }

    /**
     * calculate people around 50km
     * use redis geo radius
     *  @param list - reference id list
     *
     */
    override fun findAround(referenceId: Long): List<Any> {
        // get cache nearby + id as key for caching
        val cacheKey = "nearby"
        val size = redisTemplate.opsForList().size(cacheKey + referenceId);
        val data = redisTemplate.opsForList().range(cacheKey + referenceId, 0, size?.minus(1) ?: 0)
        if (data != null && data.isNotEmpty()) {
            return data
        }
        val location = locationRepository.findFirstByReferenceId(referenceId)
        val circle = Circle(location?.longitude!!, location.latitude, radiusInKm)
        // sort by distance
        val geoResults = redisTemplate.opsForGeo().radius(userLocationKey, circle, RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().sortAscending())
        val nearbyUser = geoResults!!.map { it.content.name }
        redisTemplate.opsForList().rightPushAll(cacheKey + referenceId, nearbyUser, expirationTimeInSeconds, TimeUnit.SECONDS);

        return nearbyUser
    }

}