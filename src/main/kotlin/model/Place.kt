package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Repräsentiert einen Ort, wie er von der Nominatim-API zurückgegeben wird.
 */
@Serializable
data class Place(
    val place_id: Long,
    val licence: String,
    val osm_type: String,
    val osm_id: Long,
    val lat: String,
    val lon: String,
    @SerialName("class")
    val clazz: String,  // `class` ist ein reserviertes Wort in Kotlin, daher der Name 'clazz'
    val type: String,
    val place_rank: Int,
    val importance: Double,
    val addresstype: String,
    val name: String,
    val display_name: String,
    val boundingbox: List<String>
)  {
    companion object {
        fun fromJsonList(json: String): List<Place> {
            return Konstanten.JSON.decodeFromString<List<Place>>(json)
        }
    }
}