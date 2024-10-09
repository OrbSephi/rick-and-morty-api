package org.mathieu.domain.models.locationPreview

import org.mathieu.domain.models.character.Character

/**
 * Représente une localisation.
 *
 * @property id identifiant unique.
 * @property name nom de la localisation.
 * @property type le type de la localisation.
 * @property dimension la dimension dans laquelle se trouve la localisation.
 */
data class LocationPreview(
    val id: Int,
    val name: String,
    val type: String,
    val dimension: String,
)
