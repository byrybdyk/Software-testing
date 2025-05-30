plugins {
    id("java")
}

group = "com.zarubov"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.seleniumhq.selenium:selenium-java:4.10.0")

    implementation("org.seleniumhq.selenium:selenium-chrome-driver:4.10.0")
    implementation("org.seleniumhq.selenium:selenium-firefox-driver:4.10.0")
    implementation("org.seleniumhq.selenium:selenium-edge-driver:4.10.0")

    implementation("org.seleniumhq.selenium:selenium-support:4.10.0")
    implementation("io.github.bonigarcia:webdrivermanager:5.4.1")

    implementation ("org.slf4j:slf4j-simple:2.0.13")

    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.2")
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.9.2")
}

tasks.test {
    useJUnitPlatform()
}