rootProject.name = "AboNutriSport"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")

pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}
include(":core:ui")
include(":data")
include(":di")

include(":feature:home")
include(":feature:auth")
include(":navigation")
include(":androidApp")
include(":shared")
