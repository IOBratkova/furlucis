import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	val kotlinVersion = "1.4.30"

	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("org.jetbrains.kotlin.plugin.jpa") version "1.5.0-M1"
	kotlin("jvm") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("kapt") version kotlinVersion
}

group = "furlucis"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

buildscript {
	repositories {
		mavenCentral()
	}
	dependencies {
		classpath("org.jetbrains.kotlin:kotlin-noarg:1.4.30")
		classpath("com.google.cloud.tools:appengine-gradle-plugin:2.4.1")
	}

}



repositories {
	mavenCentral()
}

val springDocVersion by extra("1.5.5")
val mapStructVersion by extra("1.4.2.Final")
val jwtVersion by extra("0.11.2")

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-jdbc")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.springframework.boot:spring-boot-starter-security")
	testImplementation("org.springframework.boot:spring-boot-starter-test")

	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.liquibase:liquibase-core")
	runtimeOnly("org.postgresql:postgresql")

	api("org.mapstruct", "mapstruct", mapStructVersion)
	kapt("org.mapstruct", "mapstruct-processor", mapStructVersion)
	testImplementation("org.springframework.boot", "spring-boot-starter-test")

	implementation("com.fasterxml.jackson.module", "jackson-module-kotlin")
	implementation("io.jsonwebtoken", "jjwt-api", jwtVersion)
	runtimeOnly("io.jsonwebtoken", "jjwt-impl", jwtVersion)
	runtimeOnly("io.jsonwebtoken", "jjwt-jackson", jwtVersion)

	implementation("org.springdoc", "springdoc-openapi-kotlin", springDocVersion)
	implementation("org.springdoc", "springdoc-openapi-ui", springDocVersion)
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "11"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
