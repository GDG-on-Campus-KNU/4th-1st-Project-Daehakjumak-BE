plugins {
	java
	id("org.springframework.boot") version "3.4.3"
	id("io.spring.dependency-management") version "1.1.7"
}

group = "gdg.daejuju"
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

    //spring
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-validation")
	/*developmentOnly("org.springframework.boot:spring-boot-devtools")*/

	//mysql
	runtimeOnly("com.mysql:mysql-connector-j")

	//jwt
	implementation("io.jsonwebtoken:jjwt-api:0.12.6")
	implementation("io.jsonwebtoken:jjwt-impl:0.12.6")
	runtimeOnly("io.jsonwebtoken:jjwt-gson:0.12.6")

	//Oauth2
	 implementation("org.springframework.boot:spring-boot-starter-oauth2-client")
	 implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
	 implementation("org.springframework.boot:spring-boot-starter-security")

	//lombok
	compileOnly("org.projectlombok:lombok")
	annotationProcessor("org.projectlombok:lombok")

	//SMS
	implementation("net.nurigo:sdk:4.3.2")

	//DynamoDB
	implementation("software.amazon.awssdk:dynamodb-enhanced:2.20.26")
	implementation("software.amazon.awssdk:dynamodb:2.20.26")

	//test
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
	useJUnitPlatform()
}
