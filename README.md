Kotlin DEpendency INjection for Desktop/Android Compose
===========================

This project is an experimental project to bring [Kodein-DI](https://github.com/Kodein-Framework/Kodein-DI) capabilities to the Compose world. 
At some point this project should be integrated into the Kodein-DI main repository.

Installation
------------
Current version: `0.1.0-SNAPSHOT`

To use Kodein-DI-Compose you also need to add explicitly your dependencies on [Kodein-DI](https://docs.kodein.org/kodein-di/7.4/core/install.html). 

Note that whatever you are using Android or Desktop Compose, you can declare your Kodein-DI-Compose dependency with the same configuration. 
As this project is incubating Compose compatibilities we do not follow the Kodein-DI versions, and we do not lock you with a specific version of Kodein-DI neither. 

This also means that along we the Kodein-DI-Compose dependency, you need to add the Kodein-DI dependency you want to use.

- With Kotlin

build.gradle
```kotlin
repositories {
    mavenCentral()
    maven(url="https://oss.sonatype.org/content/repositories/snapshots")
}

dependencies {
    implementation("org.kodein.di:kodein-di:7.4.0")
    implementation("org.kodein.di:kodein-di-framework-compose:0.1.0-SNAPSHOT")
}
```

- With Groovy

build.gradle
```groovy
repositories {
    mavenCentral()
    maven { url = uri("https://oss.sonatype.org/content/repositories/snapshots") }
}

dependencies {
    implementation 'org.kodein.di:kodein-di:7.4.0'
    implementation 'org.kodein.di:kodein-di-framework-compose:0.1.0-SNAPSHOT'
}
```

Usage
-----

```kotlin
val di = DI {
    bindConstant("me") { "Romain" }
}

@Composable
fun App() = withDI(di) {
    SubView()
}

@Composable
fun SubView() {
    val me: String by instance()
    Text("Hey! It's $me")
}
```