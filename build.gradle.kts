plugins {
    id("java")
}

group = "com.github.I-am-Yes"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:6.0.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

val gdxVersion = "1.14.2"

dependencies {
    implementation("com.badlogicgames.gdx:gdx:$gdxVersion")
    implementation("com.badlogicgames.gdx:gdx-backend-lwjgl3:$gdxVersion")
    runtimeOnly("com.badlogicgames.gdx:gdx-platform:$gdxVersion:natives-desktop")

    // Keep your existing test dependencies here.
}

sourceSets {
    main {
        java.setSrcDirs(listOf("src"))
    }
}

tasks.test {
    useJUnitPlatform()
}