rootProject.name = "gradle-devtools"

include(":plugin")

findProject(":plugin")?.name = "gradle-plugin"

/* 插件管理 */
pluginManagement {
    /* 插件仓库 */
    repositories {
        gradlePluginPortal()
        maven {
            url = uri("https://repo.spring.io/plugins-release")
        }
    }

    /* 插件版本管理 */
    plugins {
        id("org.jetbrains.kotlin.jvm") version "1.6.21"
    }
}

