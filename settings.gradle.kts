pluginManagement {
    repositories {
        jcenter()
        mavenLocal()
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    }
}

buildscript {
    repositories {
        jcenter()
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:4.0.2")
    }
}

rootProject.name = "kodein-di-framework-compose"

