plugins {
    idea
    `java-library`
    id("org.jetbrains.kotlin.jvm")
    id("com.gradle.plugin-publish") version "1.0.0"
}

group = "io.github.coffee377"

configurations {
    val annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
    val testAnnotationProcessor = configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME)
    val compileOnly = configurations.getByName(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME)
    val runtimeOnly = configurations.getByName(JavaPlugin.RUNTIME_ONLY_CONFIGURATION_NAME)

    testAnnotationProcessor {
        extendsFrom(annotationProcessor)
    }

    compileOnly {
        extendsFrom(annotationProcessor)
    }

    testCompileOnly {
        extendsFrom(compileOnly, testAnnotationProcessor)
    }

    testRuntimeOnly {
        extendsFrom(runtimeOnly)
    }

    all {
        resolutionStrategy {
//            cacheDynamicVersionsFor(0, TimeUnit.MINUTES)
//            cacheChangingModulesFor(0, TimeUnit.MINUTES)
        }
    }
}

sourceSets {
    main {

    }
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
    annotationProcessor("org.projectlombok:lombok:1.18.24")

    implementation("org.asciidoctor:asciidoctor-gradle-jvm:3.3.2")
    implementation("org.asciidoctor:asciidoctor-gradle-jvm-pdf:3.3.2")
    implementation("org.springframework:spring-core:5.3.23")
    implementation("de.skuzzle:semantic-version:2.1.1")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use JUnit Jupiter test framework
            useJUnitJupiter("5.7.2")
        }

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
        create("DevTools") {
            id = "io.github.coffee377.devtools"
            implementationClass = "io.github.coffee377.convention.DevToolsPlugin"
            displayName = "devtools"
            description = "quick and easy to use common functions"
        }
        create("AutoInclude") {
            id = "io.github.coffee377.auto-include"
            implementationClass = "io.github.coffee377.convention.AutoIncludePlugin"
            displayName = "Auto Include"
            description = "auto include project for root project"
        }
    }
}

pluginBundle {
    website = "https://github.com/coffee377/gradle-devtools"
    vcsUrl = "https://github.com/coffee377/gradle-devtools.git"

//    description = "Greetings from here!"

    tags = listOf("devtools")

    pluginTags = mapOf(
//        "DevTools" to listOf("devtools"),
        "AutoInclude" to listOf("devtools", "auto", "include", "project")
    )
}

publishing {
    repositories {
        maven {
            name = "local"
            url = uri("${rootProject.buildDir}/publications/repos")
        }
    }
}

tasks {
    withType<Jar> {
        /* 重复文件策略，排除 */
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    withType<Javadoc>() {
        options.encoding = "UTF-8"
        if (options is StandardJavadocDocletOptions) {
            val standardJavadocDocletOptions = options as StandardJavadocDocletOptions
            standardJavadocDocletOptions.charSet("UTF-8")
            standardJavadocDocletOptions.addStringOption("Xdoclint:none", "-quiet")
            standardJavadocDocletOptions.tags?.add("email")
            standardJavadocDocletOptions.tags?.add("time")
        }
    }

    withType<JavaCompile> {
        options.release.set(8)
        options.encoding = "UTF-8"
    }

    withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
//    kotlinOptions.jvmTarget = "11"
    }

    withType<Delete> {
        delete("out", "build", "${rootProject.buildDir}")
    }
}
