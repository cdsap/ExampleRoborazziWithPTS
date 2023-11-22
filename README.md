## Example Predictive Test Selection with Roborazzi

### Usage
`settings.gradle`:
```kotlin
plugins {
    id("com.gradle.common-custom-user-data-gradle-plugin") version "<latest-version>"
    id("com.gradle.enterprise") version <latest-version>"
}

gradleEnterprise {
    server = "$DV_URL"
    buildScan {
        publishAlways()
        capture {
            isTaskInputFiles = true
        }
        isUploadInBackground = System.getenv("CI") == null
    }
}
```

Root project `build.gradle.kts`:
```kotlin
allprojects {
    tasks.withType<Test>().configureEach {
        useJUnitPlatform()
        predictiveSelection {
            enabled.set(true)
        }
    }
}
```

Additional dependencies in the app module:
```kotlin
testRuntimeOnly("org.junit.vintage:junit-vintage-engine:<latest-version>")
```

### Results
