plugins {
    kotlin("jvm") version "1.9.10"
    application
    kotlin("plugin.serialization") version "1.9.10"
}

group = "de"
version = "1.0-SNAPSHOT"
//
repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}
//
dependencies {
    // add kotlin stdlibs
    implementation(kotlin("stdlib-jdk8"))
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1")

    implementation("org.slf4j:slf4j-simple:2.0.9")

//    implementation("com.graphhopper:directions-api-client:1.0")
    implementation("com.graphhopper:graphhopper-core:8.0")
//    implementation("khttp:khttp:1.0.0")

    implementation("io.ktor:ktor-client-core:2.3.5")
    implementation("io.ktor:ktor-client-cio:2.3.5")
    implementation("io.ktor:ktor-client-json:2.3.5")
    implementation("io.ktor:ktor-client-json-jvm:2.3.5")
    implementation("io.ktor:ktor-client-serialization-jvm:2.3.5")
    implementation("io.ktor:ktor-client-logging-jvm:2.3.5")
}
//
//tasks.test {
//    useJUnitPlatform()
//}
//
//kotlin {
//    jvmToolchain(8)
//}
//
//application {
//    mainClass.set("MainKt")
//}