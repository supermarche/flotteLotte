import kotlinx.serialization.json.Json

object Konstanten {

    val JSON = Json {
        prettyPrint = true
//        explicitNulls = true
        encodeDefaults = true
        coerceInputValues = true
        ignoreUnknownKeys=true
//        isLenient = true
    }
}