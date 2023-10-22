import com.graphhopper.GHRequest
import com.graphhopper.GraphHopper
import com.graphhopper.ResponsePath
import com.graphhopper.config.CHProfile
import com.graphhopper.config.Profile
import model.Place
import model.Strecke
import java.io.File
import java.util.*

object MapRoute {
    val file = File("data/sachsen-latest.osm.pbf").also {
        if (!it.exists()) {
            throw RuntimeException("File ${it.absolutePath} does not exist")
        }
    }

    val hopper = createGraphHopperInstance()


    fun createGraphHopperInstance(): GraphHopper {
        val hopper = GraphHopper()
        hopper.setOSMFile(file.absolutePath)
        // specify where to store graphhopper files
        hopper.setGraphHopperLocation("target/routing-graph-cache")

        // see docs/core/profiles.md to learn more about profiles
        hopper.setProfiles(
            Profile("car")
                .setVehicle("car")
//                .setTurnCosts(false)
        )

        // this enables speed mode for the profile we called car
        hopper.chPreparationHandler.setCHProfiles(CHProfile("car"))

        // now this can take minutes if it imports or a few seconds for loading of course this is dependent on the area you import
        hopper.importOrLoad()
        return hopper
    }

    /* Liefert die Strecke zwischen zwei Orten */
    fun getRoute(start: Place, end: Place): Strecke {
        // simple configuration of the request object
        val req = GHRequest(
            start.lat.toDouble(),
            start.lon.toDouble(),
            end.lat.toDouble(),
            end.lon.toDouble()
        ).setProfile("car") // note that we have to specify which profile we are using even when there is only one like here
            .setLocale(Locale.GERMAN) // define the language for the turn instructions
        val rsp = hopper.route(req)

        // handle errors
        if (rsp.hasErrors()) throw RuntimeException(rsp.getErrors().toString())


        val best: ResponsePath = rsp.best
        println("Fahrzeit: " + best.time / 60000 + " Minuten")
        println("Entfernung: " + best.distance / 1000 + " km")

        return Strecke(
            startpunktDisplayname = start.display_name,
            endpunktDisplayname = end.display_name,
            fahrzeit = best.distance.toLong(),
            laenge = best.time / 1000
        )
    }

}