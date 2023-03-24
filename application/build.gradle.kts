import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    application
    distribution
    kotlin("jvm") version "1.6.20"
    id("org.openjfx.javafxplugin") version "0.0.13"
    id("org.beryx.jlink") version "2.25.0"
    id("org.jetbrains.kotlin.plugin.serialization") version "1.6.10"
}


group = "net.codebot"
version = "1.0.0"

val compileKotlin: KotlinCompile by tasks
val compileJava: JavaCompile by tasks
compileJava.destinationDirectory.set(compileKotlin.destinationDirectory)

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":shared"))
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    testImplementation(kotlin("test"))
    testImplementation("org.mockito:mockito-core:4.2.0")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
    implementation("org.controlsfx:controlsfx:11.1.0")
    implementation("org.xerial:sqlite-jdbc:3.39.3.0")
    implementation("org.jetbrains.exposed", "exposed-core", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-dao", "0.40.1")
    implementation("org.jetbrains.exposed", "exposed-jdbc", "0.40.1")
    implementation("org.jsoup:jsoup:1.15.4")
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainModule.set("application")
    mainClass.set("net.codebot.application.Main")
}

javafx {
    // version is determined by the plugin above
    version = "17.0.2"
    modules = listOf("javafx.controls", "javafx.graphics", "javafx.web")
}

// https://stackoverflow.com/questions/74453018/jlink-package-kotlin-in-both-merged-module-and-kotlin-stdlib
jlink {
    addOptions("--strip-debug", "--compress", "2", "--no-header-files", "--no-man-pages")
    if("true" == System.getenv("CI")) {
        listOf("win", "linux", "mac").forEach { name ->
            targetPlatform(name) {
                addExtraModulePath(System.getenv("OPENJFX_MODS_" + name.toUpperCase()))
            }
        }
    }
    launcher {
        name = "Notes"
    }

    jpackage {
        if (org.gradle.internal.os.OperatingSystem.current().isWindows) {
            installerType = "msi"
            installerOptions = listOf("--win-dir-chooser")
        } else if (org.gradle.internal.os.OperatingSystem.current().isLinux) {
            installerType = "deb"
        } else {
            installerType = "dmg"
        }
        outputDir = "Notes"
    }
    forceMerge("kotlin")
}