package org.mathieu.domain.repositories

import org.mathieu.domain.models.location.Location


interface LocationRepository {
    /**
     * récupère une location via son id
     *
     * @param id Identifiant unique.
     * @return un objet Location.
     */
    suspend fun getLocation(id: Int): Location
}