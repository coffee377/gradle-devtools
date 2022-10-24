package com.voc.gradle.plugin

import com.voc.gradle.ProjectInfo
import org.gradle.api.Plugin
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.initialization.Settings
import org.gradle.api.model.ObjectFactory
import java.net.URI
import javax.inject.Inject

/**
 * @author  WuYujie
 * @email  coffee377@dingtalk.com
 * @time  2022/10/23 21:11
 */
open class AutoModulePlugin @Inject constructor(objectFactory: ObjectFactory) : Plugin<Settings> {
    var configurableFileTree: ConfigurableFileTree

    init {
        configurableFileTree = objectFactory.fileTree()
    }

    override fun apply(settings: Settings) {
        /* 开启版本目录功能 */
        settings.enableFeaturePreview("VERSION_CATALOGS")

//    val extension: RepositoriesPluginExtension =
//      settings.extensions.create("repositoriesPlugin", RepositoriesPluginExtension::class.java)
//
//        val localRepositoryName = extension.localRepositoryName?.let { it } ?: "../../localRepo"
//        val localRepositoryPath = extension.localRepositoryPath?.let { it } ?: "../../local-repo"

        /* 配置插件仓库 */
        settings.pluginManagement.repositories.apply {
            maven { it.apply { url = URI.create("") } }
            gradlePluginPortal()
            maven { it.apply { url = URI.create("https://repo.spring.io/plugins-release") } }
        }

        settings.pluginManagement.plugins.apply {
//            id("").version("")
        }

        autoInclude(settings)
    }

    private fun autoInclude(settings: Settings) {
        val rootDir = settings.rootProject.projectDir

        val projectInfos = fileTree(rootDir) {
            include("**/*.gradle", "**/*.gradle.kts")
            exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out")
            val excludes = settings.gradle.startParameter.projectProperties["excludeProjects"]?.split(",")
            if (excludes != null) {
                exclude(excludes)
            }
        }.files
            .filter { file -> !file.parentFile.relativeTo(rootDir).name.isNullOrEmpty() }
            .map { file -> ProjectInfo(rootDir, file) }.sorted()
            .filter { info -> !Regex(".*(examples|restful|persist).*$").matches(info.name) }

        projectInfos.forEach { projectInfo ->
            println(projectInfo)
            settings.include(projectInfo.path)
        }

        projectInfos.forEach { projectInfo ->
            val project = settings.project(projectInfo.dir)
            project.name = projectInfo.name
            project.projectDir = projectInfo.dir
            project.buildFileName = projectInfo.buildFileName
        }

    }

    /**
     * 获取文件树
     */
    private fun fileTree(baseDir: Any, configuration: ConfigurableFileTree.() -> Unit): ConfigurableFileTree {
        return configurableFileTree.from(baseDir).also(configuration)
    }
}

