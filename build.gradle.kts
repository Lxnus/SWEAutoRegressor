plugins {
    id("java-library")
}

repositories {
    mavenCentral()
    mavenLocal()
    maven {
        setUrl("https://jitpack.io");
    }
}
group = "de.dhbw.swe"
version = "0.0.1"
