pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
    versionCatalogs {
        create("libs") { from(files("gradle/libs.version.toml")) }
    }
}

rootProject.name = "Satellite"
include(":app")
include(":feature")
include(":core")
include(":feature:dashboard")
include(":feature:detail")
