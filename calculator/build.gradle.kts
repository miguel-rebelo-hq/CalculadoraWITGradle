plugins {
    id("org.springframework.boot") version "3.1.0"
    id("io.spring.dependency-management") version "1.1.0"
    id("java")
}

group = "com.example"
version = "1.0.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.kafka:spring-kafka")
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.0")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.1")
}

