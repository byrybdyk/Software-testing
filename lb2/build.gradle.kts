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

    implementation("org.projectlombok:lombok:1.18.30")
    annotationProcessor("org.projectlombok:lombok:1.18.30")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.30")
    testImplementation("org.projectlombok:lombok:1.18.30")

    implementation("org.apache.commons:commons-csv:1.13.0")
    testImplementation("org.mockito:mockito-core:4.8.1")
}

tasks.test {
    useJUnitPlatform()
}

