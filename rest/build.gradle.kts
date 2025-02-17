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
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.kafka:spring-kafka")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

dependencies {
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("org.springframework.boot:spring-boot-starter-logging")
    implementation("org.apache.logging.log4j:log4j-slf4j-impl:2.17.1")
}

dependencies {
    implementation("org.springframework.kafka:spring-kafka")
}
