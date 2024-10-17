// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        val nav_version = "2.7.4" // Replace with the desired version
        classpath("androidx.navigation:navigation-safe-args-gradle-plugin:$nav_version")
        classpath("com.android.tools.build:gradle:8.4.0") // Ensure this is 8.4.0
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.5.30")
    }
}
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}