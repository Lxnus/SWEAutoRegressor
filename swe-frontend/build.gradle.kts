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
        dependencies {
            api("com.google.inject", "guice", "4.2.1")
            api("com.google.inject.extensions", "guice-assistedinject", "4.2.1")
            api("com.google.protobuf", "protobuf-java", "4.0.0-rc-2")

            api("org.hibernate", "hibernate-core", "5.4.29.Final")

            api("io.grpc", "grpc-all", "1.26.0")
            api("io.grpc", "grpc-services", "1.28.0")

            api("javax.annotation", "javax.annotation-api", "1.2-b01")
            api("mysql", "mysql-connector-java", "8.0.23")
            api("io.netty", "netty-tcnative-boringssl-static", "2.0.26.Final")
        }
    }

    create("runtime") {
        val main = sourceSets.getByName("main")
        compileClasspath += main.output
        runtimeClasspath += main.output
        compileClasspath += main.compileClasspath
        runtimeClasspath += main.runtimeClasspath

        proto {
            srcDirs("src/runtime/protobuf")
        }

        dependencies {
            implementation("org.reflections:reflections:0.10.2")
        }
    }
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
        ofSourceSet("runtime").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}