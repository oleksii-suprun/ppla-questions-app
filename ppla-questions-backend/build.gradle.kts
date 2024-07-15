plugins {
    id("org.springframework.boot") version "3.3.1"
    id("io.spring.dependency-management") version "1.1.6"
    id("com.gorylenko.gradle-git-properties") version "2.4.2"
    kotlin("jvm") version "2.0.0"
    kotlin("plugin.spring") version "1.9.24"
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

repositories {
    mavenCentral()
}

extra["caffeineVersion"] = "3.1.8"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("org.springframework.boot:spring-boot-starter-cache")
    implementation("com.github.ben-manes.caffeine:caffeine:${property("caffeineVersion")}")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-api:2.6.0")
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.springframework.boot:spring-boot-configuration-processor")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks {
    val copyFrontendApp by registering(Copy::class) {
        dependsOn(":ppla-questions-frontend:npmRunBuild")

        from("${rootProject.childProjects["ppla-questions-frontend"]?.projectDir}/build")
        into("${layout.buildDirectory.get()}/resources/main/static")
    }
}



tasks.processResources {
    dependsOn("copyFrontendApp")
}
