plugins {
    kotlin("jvm") version "1.9.0"
    application
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.amqp:spring-rabbit:2.4.0")
    implementation(kotlin("stdlib"))
    implementation("ch.qos.logback:logback-classic:1.2.11")
}

application {
    mainClass.set("com.example.service2.Service2Kt")
}
