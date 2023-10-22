package model

import java.util.concurrent.TimeUnit

data class Fahrer(
    var name: String = "",
    var limitStrecke: Long = 0,
    var limitFahrzeit: Long = 0,
    val strecke: Strecke = Strecke(),
    var startzeitDesFahrers: Long = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis())
) {

    /**
     * prüft ob die Strecke vom Fahrer als Abweichung
     * akzeptiert wird, weil deren zusätzliche Länge und
     * Fahrtzeit innerhalb der Nutzerdefinierten Grenzwerte
     * liegt.
     * @return true wenn die Strecke akzeptiert wird
     */
    fun isValid(streckenabschnitte: List<Strecke>): Boolean =
        streckenabschnitte.sumOf { it.laenge } <= strecke.laenge + limitStrecke
                && streckenabschnitte.sumOf { it.fahrzeit } <= strecke.fahrzeit + limitFahrzeit
}