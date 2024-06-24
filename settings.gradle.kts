pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io")  }
    }
}

rootProject.name = "Frajola-Patches"
include(":app")
include(":brasilian_currency")
include(":google_sign_in")
include(":frajola:patches:brasilian_currency")
include(":frajola:patches:google_sign_in")
