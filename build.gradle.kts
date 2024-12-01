import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

val junitJupiterVersion = "5.11.3"


plugins {
   kotlin("jvm") version "2.1.0"
}

repositories {
   mavenCentral()
}

dependencies {
   implementation(kotlin("stdlib"))
   testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
   testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

kotlin {
   jvmToolchain(21)
}

tasks {
   withType<Test> {
      useJUnitPlatform()
      testLogging {
         showExceptions = true
      }
      testLogging {
         exceptionFormat = FULL
      }
   }

   withType<Wrapper> {
      gradleVersion = "8.11.1"
   }
}

