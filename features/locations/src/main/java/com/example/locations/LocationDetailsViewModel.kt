package com.example.locations

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.repositories.LocationRepository
import org.mathieu.ui.ViewModel

class LocationDetailsViewModel(application: Application) : ViewModel<LocationDetailsState>(
    LocationDetailsState(), application){

    private val locationRepository: LocationRepository by inject()

    fun init(locationId: Int) {
        fetchData(
            source = { locationRepository.getLocation(id = locationId); }
        ) {

            onSuccess {
                updateState { copy(location = it, error = null) }
                }

            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }

}

data class LocationDetailsState(
    val isLoading: Boolean = true,
    val location: Location? = null,
    val error: String? = null
)