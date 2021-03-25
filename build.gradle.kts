plugins {
    kotlin("multiplatform") version "1.4.31"
    id("org.jetbrains.compose") version "0.3.2"
    id("org.jetbrains.dokka") version "1.4.30"
    id("com.android.library")
    `maven-publish`
}

group = "org.kodein.di"
version = "1.0-SNAPSHOT"

repositories {
    mavenLocal()
    mavenCentral()
    google()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/compose/dev") }
    jcenter()
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig { minSdkVersion(21) ; targetSdkVersion(30) }
}

kotlin {
    val kodeinDIVersion = "7.4.0"

    explicitApi()

    android { publishLibraryVariants("debug", "release") }
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

    sourceSets["commonMain"].dependencies {
        compileOnly(compose.runtime)
        compileOnly(compose.foundation)
        implementation("org.kodein.di:kodein-di:$kodeinDIVersion")
    }
}


val dokkaOutputDir = buildDir.resolve("dokka")

tasks.dokkaHtml {
    outputDirectory.set(file(dokkaOutputDir))
    dokkaSourceSets {
        configureEach {
            val platformName = when (platform.get()) {
                org.jetbrains.dokka.Platform.jvm -> "jvm"
                org.jetbrains.dokka.Platform.js -> "js"
                org.jetbrains.dokka.Platform.native -> "native"
                org.jetbrains.dokka.Platform.common -> "common"
            }
            displayName.set(platformName)

            perPackageOption {
                matchingRegex.set(".*\\.internal.*") // will match all .internal packages and sub-packages
                suppress.set(true)
            }
        }
    }
}

val deleteDokkaOutputDir by tasks.register<Delete>("deleteDokkaOutputDirectory") {
    delete(dokkaOutputDir)
}

val javadocJar = tasks.create<Jar>("javadocJar") {
    archiveClassifier.set("javadoc")
    duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    dependsOn(deleteDokkaOutputDir, tasks.dokkaHtml)
    from(dokkaOutputDir)
}

publishing {
    publications.withType<MavenPublication>().configureEach {
        version = project.version.toString()
        artifact(javadocJar)
        pom {
            name.set("Kodein-DI-Compose")
            description.set("Experimental project incubating DI capabilities for (Android or Desktop) Compose projects.")
            licenses {
                license {
                    name.set("MIT")
                    url.set("https://opensource.org/licenses/MIT")
                }
            }
            url.set("http://kodein.org")
            issueManagement {
                system.set("Github")
                url.set("https://github.com/Kodein-Framework/Kodein-DI-Compose/issues")
            }
            scm {
                connection.set("https://github.com/Kodein-Framework/Kodein-DI-Compose.git")
                url.set("https://github.com/Kodein-Framework/Kodein-DI-Compose")
            }
            developers {
                developer {
                    name.set("Kodein Koders")
                    email.set("dev@kodein.net")
                }
            }
        }
    }
}