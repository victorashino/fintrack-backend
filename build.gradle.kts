plugins {
	java
	id("org.springframework.boot") version "3.2.4"
	id("io.spring.dependency-management") version "1.1.7"
	id("application")
}

group = "dev.bicutoru"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion.set(JavaLanguageVersion.of(21))
	}
}

application {
	mainClass.set("dev.bicutoru.fintrack.FinTrackApplication")
}

tasks.named<org.springframework.boot.gradle.tasks.bundling.BootJar>("bootJar") {
	archiveFileName.set("app.jar")
}

repositories {
	mavenCentral()
}

configurations {
	compileOnly {
		extendsFrom(configurations.annotationProcessor.get())
	}
}

dependencies {
	// Web
	implementation("org.springframework.boot:spring-boot-starter-web")

	// Tests
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Validation
	implementation("org.springframework.boot:spring-boot-starter-validation")

	// Swagger
	implementation("org.springdoc:springdoc-openapi-starter-webmvc-ui:2.5.0")

	// Spring Security
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	// JWT
	implementation("com.auth0:java-jwt:4.5.0")

	// JPA
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")

	// .env
	implementation("me.paulschwarz:spring-dotenv:3.0.0")

	// Lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	// MariaDB
	runtimeOnly("org.mariadb.jdbc:mariadb-java-client")

	// H2
	runtimeOnly("com.h2database:h2")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
