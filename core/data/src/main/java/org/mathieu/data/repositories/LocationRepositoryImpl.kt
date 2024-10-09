package org.mathieu.data.repositories

import android.content.Context
import org.mathieu.data.local.LocationLocal
import org.mathieu.data.local.objects.toModel
import org.mathieu.data.local.objects.toRealmObject
import org.mathieu.data.remote.LocationAPI
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.repositories.LocationRepository


internal class LocationRepositoryImpl(
    private val locationApi: LocationAPI,
    private val locationLocal: LocationLocal
) : LocationRepository {
    override suspend fun getLocation(id: Int): Location =
        locationLocal.getLocation(id)?.toModel()
            ?: locationApi.getLocation(id = id)?.let { response ->
                val obj = response.toRealmObject()
                locationLocal.insert(obj)
                obj.toModel()
            }
            ?: throw Exception("Location not found.")
}
