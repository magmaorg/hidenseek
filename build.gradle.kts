plugins {
    java
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "dev.tylerm"
version = "1.7.7"

java {
    sourceCompatibility = JavaVersion.toVersion(11)
    targetCompatibility = JavaVersion.toVersion(11)

    disableAutoTargetJvm()
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://papermc.io/repo/repository/maven-public/")
    maven("https://repo.dmulloy2.net/repository/public/")
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:1.20.2-R0.1-SNAPSHOT")
    compileOnly("com.comphenix.protocol:ProtocolLib:5.2.0-SNAPSHOT")
    compileOnly("me.clip:placeholderapi:2.11.6")

    implementation("org.jetbrains:annotations:24.1.0")
    implementation("org.mariadb.jdbc:mariadb-java-client:3.1.4")
    implementation("com.zaxxer:HikariCP:5.1.0")
    implementation("org.xerial:sqlite-jdbc:3.46.0.0")
    implementation("com.github.cryptomorin:XSeries:11.2.0.1") {
        isTransitive = false
    }
}

tasks {
    processResources {
        filesMatching("plugin.yml") {
            expand("version" to version)
        }
    }

    shadowJar {
        archiveClassifier.set("")

        relocate("com.cryptomorin.xseries", "dev.tylerm.depend.xseries")
        relocate("com.zaxxer.hikari", "dev.tylerm.depend.hikari")

        exclude("META-INF/maven/**")
        exclude("org/sqlite/native/Windows/**")
        exclude("org/sqlite/native/Mac/**")
        exclude("org/sqlite/native/Linux-Android/**")
        exclude("org/sqlite/native/Linux/ppc64/**")
        exclude("org/sqlite/native/Linux/armv7/**")
        exclude("org/sqlite/native/Linux/aarch64/**")
        exclude("org/sqlite/native/Linux/arm/**")
        exclude("org/sqlite/native/Linux/armv6/**")
        exclude("org/sqlite/native/Linux/x86/**")
        exclude("org/sqlite/native/Linux-Musl/**")
        exclude("org/sqlite/native/FreeBSD/**")
    }
}
