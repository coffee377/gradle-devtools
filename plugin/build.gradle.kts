plugins {
    idea
    `java-gradle-plugin`
    `java-library`
    `maven-publish`
    id("org.jetbrains.kotlin.jvm")
    id("com.gradle.plugin-publish") version "1.0.0"
}

group = "com.voc"

configurations {
    all {
        resolutionStrategy {
//            cacheDynamicVersionsFor(0, TimeUnit.MINUTES)
//            cacheChangingModulesFor(0, TimeUnit.MINUTES)
        }
    }
}

sourceSets {
//    create("mongodb") {
//        java {
//            srcDir("src/mongodb/java")
//        }
//    }
}

java {
//    registerFeature("mongodb") {
////        usingSourceSet(sourceSets["main"])
//        usingSourceSet(sourceSets["mongodb"])
//    }
}

repositories {
    mavenLocal()
    maven {
        isAllowInsecureProtocol = true
        url = uri("http://nexus.jqk8s.jqsoft.net/repository/maven-public/")
    }
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    maven { url = uri("https://maven.aliyun.com/repository/gradle-plugin") }
    maven { url = uri("https://maven.aliyun.com/repository/spring") }
    maven { url = uri("https://maven.aliyun.com/repository/google") }
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.24")
    annotationProcessor("org.projectlombok:lombok:1.18.24")
//    implementation("org.junit.jupiter:junit-jupiter:5.7.2")
//    implementation("org.springframework.security:spring-security-oauth2-authorization-server:0.3.2")
//    mongodbSupportImplementation("org.mongodb:mongodb-driver-sync:3.9.1")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.7.2")
        }
//
//        // Create a new test suite
//        val functionalTest by registering(JvmTestSuite::class) {
//            dependencies {
//                // functionalTest test suite depends on the production code in tests
//                implementation(project)
//            }
//
//            targets {
//                all {
//                    // This test suite should run after the built-in test suite has run its tests
//                    testTask.configure { shouldRunAfter(test) }
//                }
//            }
//        }
    }
}

gradlePlugin {
    plugins {
        create("Init") {
            id = "com.voc.init"
            displayName = "Init"
            description = "initial configuration"
            implementationClass = "com.voc.gradle.plugin.GradleInitPlugin"
        }
        create("AutoModule") {
            id = "com.voc.auto"
            displayName = "AutoModule"
            description = "auto include project"
            implementationClass = "com.voc.gradle.plugin.AutoModulePlugin"
        }
    }
}

publishing {
    repositories {
        maven {
            name = "local"
            url = uri("${rootProject.buildDir}/publications/repos")
        }
    }
}

pluginBundle {
    website = "https://github.com/coffee377/gradle-devtools"
    vcsUrl = "https://github.com/coffee377/gradle-devtools.git"
    tags = listOf("devtools", "auto", "include")
}

//gradlePlugin.testSourceSets(sourceSets["functionalTest"])

tasks.named<Task>("check") {
    // Include functionalTest as part of the check lifecycle
//    dependsOn(testing.suites.named("functionalTest"))
}

tasks {
    withType<Jar> {
//        archiveBaseName.set("gradle-plugin")
        /* 重复文件策略，排除 */
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<Javadoc>() {
        options.encoding = "UTF-8"
        if (options is StandardJavadocDocletOptions) {
            val standardJavadocDocletOptions = options as StandardJavadocDocletOptions
            standardJavadocDocletOptions.charSet("UTF-8")
            standardJavadocDocletOptions.tags?.add("email")
            standardJavadocDocletOptions.tags?.add("time")
        }
    }

    withType<JavaCompile> {
        options.release.set(8)
        options.encoding = "UTF-8"
//        options.compilerArgs.add("-parameters")
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "11"
    }

    withType<Delete> {
        delete("out", "build", "${rootProject.buildDir}")
    }
}
