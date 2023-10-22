import model.Fahrer
import model.Mitfahrer
import model.Place
import model.Strecke
import org.slf4j.LoggerFactory

/**
 * Berechnung möglicher Matches wenn ein Fahrer spontan startet.
 * Der Algorithmus kann in abgewandelter Form auch für die Abfrage von Mitfahrern genutzt werden,
 * die wissen möchten, ob sie zu einer bestimmten Zeit mitgenommen werden können.
 */
class AlgorithmusFahrerStartetSpontan {
    companion object {
        val LOG = LoggerFactory.getLogger(AlgorithmusFahrerStartetSpontan::class.java)
    }

    /**
     * Sucht zu einer Strecke des Fahrers passende Mitfahrer.
     * @return Liste möglicher Mitfahrer, sortiert nach Länge der Strecke. Der Mitfahrer mit der längsten Strecke
     * erscheint zuerst in der Liste.
     */
    fun match(fahrer: Fahrer, mitfahrer: List<Mitfahrer>):List<Mitfahrer> {
        /* Vergangene Zeit in Sekunden seit dem 01.01.1970 */
        val moeglicheMitfahrer = mutableListOf<Mitfahrer>()

        /* potentielle Mitfahrer prüfen, ob diese mitgenommen werden können*/
//        getFilteredMitfahrer(fahrer, mitfahrer)
            
            mitfahrer.forEach { mitfahrer ->
            val streckenabschnitte = mutableListOf<Strecke>()

            /* Strecke A1-B1 */
            println("Berechne A1->B1 für Mitfahrer $mitfahrer.name von ${fahrer.strecke.startpunkt} nach ${mitfahrer.strecke.startpunkt}")
            streckenabschnitte += getStrecke(
                fahrer.startzeitDesFahrers,
                fahrer.strecke.startpunkt,
                mitfahrer.strecke.startpunkt
            )
            if (!fahrer.isValid(streckenabschnitte)) {
                // Abweichung zu groß, nächsten Mitfahrer prüfen
                println("Abweichung für Fahrer zu groß. Nächsten Mitfahrer prüfen")
                return@forEach
            }

            if (!mitfahrer.isValidStartzeit(streckenabschnitte.last().endzeit)) {
                println("Mitfahrer kann startzeit nicht erreichen. Nächsten Mitfahrer prüfen")
                return@forEach
            }

            /* Strecke B1-B2 */
            println("Berechne B1->B2 für Mitfahrer $mitfahrer.name von ${mitfahrer.strecke.startpunkt} nach ${mitfahrer.strecke.endpunkt}")
            streckenabschnitte += getStrecke(
                streckenabschnitte.last().endzeit,
                mitfahrer.strecke.startpunkt,
                mitfahrer.strecke.endpunkt
            ).also {
                mitfahrer.strecke.laenge = it.laenge
                mitfahrer.strecke.fahrzeit = it.fahrzeit
            }
            if (!fahrer.isValid(streckenabschnitte)) {
                // Abweichung zu groß, nächsten Mitfahrer prüfen
                println("Abweichung für Fahrer zu groß. Nächsten Mitfahrer prüfen")
                return@forEach
            }
            if (!mitfahrer.isValidEndzeit(streckenabschnitte.last().endzeit)) {
                // Endzeit des Mitfahrers passt nicht, nächsten Mitfahrer prüfen
                println("Enzeit für Mitfahrer außerhalb des zulässigen Bereich. Nächsten Mitfahrer prüfen")
                return@forEach
            }

            /* Strecke B2-A2 */
            println("Berechne B2->A2 für Mitfahrer $mitfahrer.name von ${mitfahrer.strecke.endpunkt} nach ${fahrer.strecke.endpunkt}")
            streckenabschnitte += getStrecke(
                streckenabschnitte.last().endzeit,
                mitfahrer.strecke.endpunkt,
                fahrer.strecke.endpunkt
            )
            if (!fahrer.isValid(streckenabschnitte)) {
                // Abweichung zu groß, nächsten Mitfahrer prüfen
                println("Abweichung für Fahrer zu groß. Nächsten Mitfahrer prüfen")
                return@forEach
            }

            /* Mitfahrer kann mitgenommen werden */
            println("Mitfahrer $mitfahrer.name kann mitgenommen werden")
            moeglicheMitfahrer.add(mitfahrer)
        }

        return moeglicheMitfahrer
    }


    /**
     * berechnet die Strecke von Startpunkt zu Endpunkt
     * @return Strecke mit Länge und Fahrtzeit
     */
    fun getStrecke(startzeit: Long, startpunkt: String, endpunkt: String): Strecke {
        val startPlace: Place = MapSearch().getPlaces(startpunkt).first()
        val endPlace: Place = MapSearch().getPlaces(endpunkt).first()
        val strecke = MapRoute.getRoute(startPlace, endPlace)
        strecke.startzeit += startzeit
        println("STRECKE:"  + strecke)
        return strecke
    }

    /* liefert eine Liste potentieller Mitfahrer
    * TODO: Einchränkung nach Radius und zeit*/
    fun getFilteredMitfahrer(fahrer: Fahrer, mitfahrer: List<Mitfahrer>): List<Mitfahrer> {
        return mitfahrer.filter { mitfahrer: Mitfahrer ->
            /* grobe Prüfung ob die zeitliche Voraussetzung passt */
            fahrer.strecke.startzeit <= mitfahrer.strecke.startzeit - mitfahrer.vorlaufzeit
                    && fahrer.strecke.startzeit <= mitfahrer.startzeitVon - mitfahrer.startzeitBis
                    && fahrer.strecke.endzeit >= mitfahrer.endzeitVon
                    && fahrer.strecke.endzeit >= mitfahrer.endzeitBis
        }.filter {
            /* TODO : prüfen ob die Strecke des Mitfahrers in der Nähe des Fahrers liegt */
            true
        }


    }
}

