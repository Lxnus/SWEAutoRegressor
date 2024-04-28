import com.google.protobuf.gradle.*

plugins {
    id("java-library")
    id("com.google.protobuf") version("0.9.4")
}

repositories {
    mavenCentral()
}

sourceSets {
    main {
        java {
            srcDirs("src/main/java")
        }
        proto {
            srcDirs("src/main/protobuf")
        }
    }
}

dependencies {
    implementation("com.google.inject", "guice", "4.2.1")
    implementation("com.google.inject.extensions", "guice-assistedinject", "4.2.1")
    implementation("com.google.protobuf", "protobuf-java", "4.0.0-rc-2")

    implementation("org.hibernate", "hibernate-core", "5.4.29.Final")

    implementation("io.grpc", "grpc-all", "1.26.0")
    implementation("io.grpc", "grpc-services", "1.28.0")

    implementation("javax.annotation", "javax.annotation-api", "1.2-b01")
    implementation("mysql", "mysql-connector-java", "8.0.23")
    implementation("io.netty", "netty-tcnative-boringssl-static", "2.0.26.Final")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.17.3"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.26.0"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}