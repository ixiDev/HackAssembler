import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}
val appVersion = "0.0.1"
group = "com.ixidev"
version = appVersion

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}
tasks.withType<Jar> {
    manifest {
        attributes["Manifest-Version"] = appVersion
        attributes["Main-Class"] = "com.ixidev.hackassembler.MainKt"
    }
}
application {
    mainClass.set("MainKt")
}