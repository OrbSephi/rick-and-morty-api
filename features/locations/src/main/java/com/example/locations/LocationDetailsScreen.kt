package com.example.locations

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import org.mathieu.domain.models.character.Character

private typealias UIState = LocationDetailsState

@Composable
fun LocationDetailsScreen(
    navController: NavController,
    id: Int
){
    val viewModel: LocationDetailsViewModel = viewModel()
    val state by viewModel.state.collectAsState()

    viewModel.init(locationId = id)

    LocationDetailsContent(
        state = state,
        onClickBack = navController::popBackStack
    )
}

@Composable
fun LocationDetailsContent(
    state: UIState,
    onClickBack: () -> Unit = { }
) {
    Column(
    ) {
        // Bouton retour
        IconButton(onClick = onClickBack) {
            Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
        }

        // Affichage du nom de la localisation
        Text(
            text = state.location?.name ?: "Nom non disponible",
            style = MaterialTheme.typography.bodyLarge,
        )

        // Affichage du type de localisation
        Text(
            text = state.location?.type ?: "Type non disponible",
            style = MaterialTheme.typography.bodySmall,
        )

        // Liste des résidents dans cette localisation
        Text(
            text = "Résidents :",
            style = MaterialTheme.typography.bodyLarge,
        )

        if (state.location?.residents?.isNotEmpty()!!) {
            // Liste des item résidents
            LazyColumn {
                items(state.location.residents.size) { index ->
                    ResidentItem(resident = state.location.residents[index])
                }
            }
        } else {
            // Message si la liste des résidents est vide
            Text(
                text = "Aucun résident trouvé.",
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
fun ResidentItem(resident: Character) {
    Text(
        text = resident.name,
        style = MaterialTheme.typography.bodyLarge,
    )
}

