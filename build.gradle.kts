plugins {
    id("com.android.application") version "8.1.4" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("io.github.takahirom.roborazzi") version "1.8.0-alpha-5" apply false
}

allprojects {

    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        predictiveSelection {
            enabled.set(true)
        }
    }
}
