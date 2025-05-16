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

    implementation("org.xerial.snappy:snappy-java:1.1.10.7")
    implementation ("io.grpc:grpc-netty-shaded:1.63.0")
    implementation ("io.grpc:grpc-protobuf:1.63.0")
    implementation ("io.grpc:grpc-stub:1.63.0")
    implementation ("com.google.protobuf:protobuf-java:3.25.3")
}

application {
    // Replace with your actual fully-qualified main class name
    mainClass.set("org.vulkano.Main")
}

tasks.test {
    useJUnitPlatform()
}