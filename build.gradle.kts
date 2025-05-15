plugins {
    id("java")
    id("application")
}

group = "org.vulkano"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

application {
    // Replace with your actual fully-qualified main class name
    mainClass.set("org.vulkano.Main")
}

tasks.test {
    useJUnitPlatform()
}