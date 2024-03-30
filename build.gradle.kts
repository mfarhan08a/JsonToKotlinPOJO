plugins {
    id("java")
    id("org.jetbrains.kotlin.jvm") version "1.8.21"
    id("org.jetbrains.intellij") version "1.13.3"
    id("maven-publish")
}

group = "com.mfarhan08a"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    //main
    implementation("com.fifesoft:rsyntaxtextarea:3.3.1")

    //generator
    implementation("org.json:json:20231013")
    implementation("commons-io:commons-io:2.11.0")
    implementation("com.google.guava:guava:33.0.0-jre")

    //project
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.insert-koin:koin-core:3.5.3")
}

// Configure Gradle IntelliJ Plugin
// Read more: https://plugins.jetbrains.com/docs/intellij/tools-gradle-intellij-plugin.html
intellij {
    pluginName.set("JsonToKotlinPOJO")
    localPath.set("D:\\GM\\Android Studio")
    type.set("AI")
    plugins.set(listOf("Kotlin", "android"))
}

tasks {
    // Set the JVM compatibility versions
    withType<JavaCompile> {
        sourceCompatibility = "17"
        targetCompatibility = "17"
    }
    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "17"
    }

    patchPluginXml {
        sinceBuild.set("222")
        untilBuild.set("232.*")
        changeNotes.set("This is the first version of this plugin")
    }

    signPlugin {
        certificateChain.set(System.getenv("CERTIFICATE_CHAIN"))
        privateKey.set(System.getenv("PRIVATE_KEY"))
        password.set(System.getenv("PRIVATE_KEY_PASSWORD"))
    }

    publishPlugin {
        token.set(System.getenv("PUBLISH_TOKEN"))
    }

    buildSearchableOptions {
        enabled = false
    }
}

