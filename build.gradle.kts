plugins {
    id("java")
    id("application")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    testImplementation("org.mockito:mockito-core:5.17.0")
    testImplementation("org.mockito:mockito-junit-jupiter:5.3.1")

    compileOnly("org.projectlombok:lombok:1.18.38")
    annotationProcessor("org.projectlombok:lombok:1.18.38")
}

tasks.test {
    useJUnitPlatform()
    outputs.upToDateWhen { false } // всегда прогонять тесты
}

application {
    mainClass.set("AppMain") // укажи свой класс с main
}

tasks.named("run") {
    dependsOn("test")
}

tasks.named<JavaExec>("run") {
    standardInput = System.`in`
}
