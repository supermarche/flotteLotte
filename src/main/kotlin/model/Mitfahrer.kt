package model

data class Mitfahrer(
    var name: String = "",
    val strecke: Strecke = Strecke(),
    var vorlaufzeit: Long = 0,
    var startzeitVon: Long = 0,
    var startzeitBis: Long = 0,
    var endzeitVon: Long = 0,
    var endzeitBis: Long = 0
) {

    /**
     * prüft ob die Startzeit für die Abholung des Mitfahrers
     * innerhalb der Nutzerdefinierten Grenzwerte liegt.
     */
    fun isValidStartzeit(startzeit: Long) =
        startzeit >= (startzeitVon - vorlaufzeit)
                && startzeit <= (startzeitBis - vorlaufzeit)

    /**
     * prüft ob die Ankunftszeit am Zielort innerhalb der Range liegt.
     */
    fun isValidEndzeit(endzeit: Long) =
        endzeit >= endzeitVon
                && endzeit <= endzeitBis
}