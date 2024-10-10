package org.mathieu.characters.details

import android.app.Application
import org.koin.core.component.inject
import org.mathieu.characters.list.CharactersAction
import org.mathieu.domain.models.character.Character
import org.mathieu.domain.models.location.Location
import org.mathieu.domain.repositories.LocationRepository
import org.mathieu.ui.Destination
import org.mathieu.ui.ViewModel



sealed interface LocationsAction {
    data class SelectedLocation(val location: Location):
        LocationsAction
}

class CharacterDetailsViewModel(application: Application) : ViewModel<CharacterDetailsState>(
    CharacterDetailsState(), application) {

    private val characterRepository: org.mathieu.domain.repositories.CharacterRepository by inject()
    private val locationRepository: LocationRepository by inject()

    fun init(characterId: Int) {
        fetchData(
            source = { characterRepository.getCharacter(id = characterId); }
        ) {

            onSuccess {
                updateState { copy(avatarUrl = it.avatarUrl, name = it.name, error = null) }
                fetchData(
                    source = { locationRepository.getLocation(id = it.location.second) }
                ){
                    onSuccess {
                        updateState { copy(location = it) }
                    }
                }
            }


            onFailure {
                updateState { copy(error = it.toString()) }
            }

            updateState { copy(isLoading = false) }
        }
    }

    fun handleAction(action: LocationsAction) {
        when(action) {
            is LocationsAction.SelectedLocation -> selectedLocation(action.location)
        }
    }


    private fun selectedLocation(location: Location) =
        sendEvent(Destination.CharacterDetails(location.id.toString()))
}


data class CharacterDetailsState(
    val isLoading: Boolean = true,
    val avatarUrl: String = "",
    val name: String = "",
    val locationName: String = "",
    val locationType: String = "",
    val location: Location? = null,
    val error: String? = null
)