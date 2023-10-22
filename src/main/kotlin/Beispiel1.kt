import model.Fahrer
import model.Mitfahrer
import model.Place

fun main() {
    val fahrer = Fahrer()
    fahrer.name = "Marc"
    fahrer.limitFahrzeit = 60 * 30L // 30 Minuten
    fahrer.limitStrecke = 1000 * 5 // 5 km

    fahrer.strecke.startpunkt = "Bahnhofstraße 1, Görlitz"
    fahrer.strecke.endpunkt = "Muskauer Str. 28, Niesky"
    fahrer.startzeitDesFahrers = 0L

    val mitfahrer1 = Mitfahrer()
    mitfahrer1.name = "Roland"
    mitfahrer1.startzeitVon = 0L
    mitfahrer1.startzeitBis = Long.MAX_VALUE
    mitfahrer1.endzeitVon = 0L
    mitfahrer1.endzeitBis = Long.MAX_VALUE
    mitfahrer1.vorlaufzeit = 0L
    mitfahrer1.strecke.startpunkt = "Obermarkt, Görlitz"
    mitfahrer1.strecke.endpunkt = "Kodersdorf"


    val mitfahrer2 = Mitfahrer()
    mitfahrer2.name = "Markus"
    mitfahrer2.startzeitVon = 0L
    mitfahrer2.startzeitBis = Long.MAX_VALUE
    mitfahrer2.endzeitVon = 0L
    mitfahrer2.endzeitBis = Long.MAX_VALUE
    mitfahrer2.vorlaufzeit = 0L
    mitfahrer2.strecke.startpunkt = "Schlesische Str., Görlitz"
    mitfahrer2.strecke.endpunkt = "Kodersdorf"


    /* Strecke für Fahrer berechnen
     */
    val startPlace: Place = MapSearch().getPlaces(fahrer.strecke.startpunkt).first()
    val endPlace: Place = MapSearch().getPlaces(fahrer.strecke.endpunkt).first()
    MapRoute.getRoute(startPlace, endPlace).also {
        fahrer.strecke.fahrzeit = it.fahrzeit
        fahrer.strecke.laenge = it.laenge
    }

    /* Matching */
    val matchingPoints: List<Mitfahrer> =
        AlgorithmusFahrerStartetSpontan().match(fahrer, listOf(mitfahrer1, mitfahrer2))

    println(matchingPoints)


//    TODO: Rückgabewerte haben noch einen Logikfehler. Es werden die Werte nciht korrekt aktualisiert.
}