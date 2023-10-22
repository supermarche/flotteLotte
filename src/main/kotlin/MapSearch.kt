import io.ktor.client.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import model.Place
import org.slf4j.LoggerFactory

class MapSearch {

    /**
     * liefert eine Liste möglicher Koordinaten zu der angegebenen Adresse
     */
    fun getPlaces(adresse: String): List<Place> {
        val LOG = LoggerFactory.getLogger(MapSearch::class.java)
        LOG.info("GET-Place for Adress: $adresse")
        val client = HttpClient() {
//            install(Logging) {
////                level = LogLevel.ALL
//            }
        }
        val jsonString = runBlocking {
            client.request {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "nominatim.openstreetmap.org"
                    path("search")
                    parameters.append("format", "json")
                    parameters.append("q", adresse)
                }
            }.bodyAsText()
        }

        client.close()

        return Place.fromJsonList(jsonString)
    }
}