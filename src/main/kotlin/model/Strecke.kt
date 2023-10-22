package model

data class Strecke (
    var startpunkt:String="",
    var endpunkt:String="",
    // in Sekunden
    var fahrzeit:Long=0,
    // in Metern
    var laenge:Long=0,
    var startzeit:Long=0,
    var endzeit:Long=0,
    var startpunktDisplayname : String = "",
    var endpunktDisplayname : String = ""
)