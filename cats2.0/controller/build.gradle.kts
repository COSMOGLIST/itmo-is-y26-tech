plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
}

group = "ru.itmo"
version = "1.0-SNAPSHOT"


repositories {
    mavenCentral()
}

springBoot {
    mainClass.value("ru.itmo.Main")
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation(project(":cats2.0:service"))
    implementation(project(":cats2.0:data"))
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:3.0.6")
    implementation("org.springframework.boot:spring-boot-starter-web:3.0.6")
}

tasks.test {
    useJUnitPlatform()
}