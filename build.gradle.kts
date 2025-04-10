plugins {
	java
	id("org.springframework.boot") version "3.4.4"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "dev.bicutoru"
version = "0.0.1-SNAPSHOT"

java {
	toolchain {
		languageVersion = JavaLanguageVersion.of(21)
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

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-validation")
	implementation("org.springframework.boot:spring-boot-starter-web")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")

	// Spring Security
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.security:spring-security-test")

	//JWT
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
	testImplementation("com.h2database:h2")

}

tasks.withType<Test> {
	useJUnitPlatform()
}
