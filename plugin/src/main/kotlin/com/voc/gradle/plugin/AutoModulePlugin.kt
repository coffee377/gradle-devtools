package com.voc.gradle.plugin

import com.voc.gradle.ProjectInfo
import com.voc.gradle.utils.FileOperationUtils
import org.gradle.api.Plugin
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.model.ObjectFactory
import javax.inject.Inject

/**
 * @author  WuYujie
 * @email  coffee377@dingtalk.com
 * @time  2022/10/23 21:11
 */
open class AutoModulePlugin @Inject constructor(objectFactory: ObjectFactory) : Plugin<Settings> {
    private var objectFactory: ObjectFactory
    private var configurableFileTree: ConfigurableFileTree
    private lateinit var settingsExtension: DevToolsSettingsExtension
    private lateinit var fileOperations: FileOperations

    init {
        this.objectFactory = objectFactory
        configurableFileTree = objectFactory.fileTree()
    }

    override fun apply(settings: Settings) {

        fileOperations = FileOperationUtils.fileOperationsFor(settings)

        settingsExtension = settings.extensions.getByType(DevToolsSettingsExtension::class.java)

        /* 配置插件仓库 */
        settings.pluginManagement.repositories.apply {
            maven { it.apply { url = uri("${settings.rootDir}/build/publications/repos") } }
            gradlePluginPortal()
            maven { it.apply { url = uri("https://repo.spring.io/plugins-release") } }
        }

        /* 常用插件管理 */
        settings.pluginManagement.plugins.apply {
            id("org.jetbrains.kotlin.jvm").version("1.6.21")
            id("org.asciidoctor.jvm.convert").version("3.3.2")
            id("org.asciidoctor.jvm.pdf").version("3.3.2")
            id("org.asciidoctor.jvm.epub").version("3.3.2")
            id("com.github.shalousun.smart-doc").version("2.6.0-release")
            id("com.voc.auto").version("0.1.0")
            id("org.springframework.boot").version("2.5.0.RELEASE")
            id("io.spring.dependency-management").version("1.0.11.RELEASE")
        }

        /* 自动引入子项目 */
        autoInclude(settings)
    }

    private fun autoInclude(settings: Settings) {
        if (settingsExtension.isAutomatically.not()) return

        val rootDir = settings.rootDir

        val projectInfos = fileTree(rootDir) {
            include("**/*.gradle", "**/*.gradle.kts")
            exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out")
            val excludes = settings.gradle.startParameter.projectProperties["excludeProjects"]?.split(",")
            excludes?.let { exclude(it) }
        }
            .files
            .filter { file -> !file.parentFile.relativeTo(rootDir).name.isNullOrEmpty() }
            .map { file -> ProjectInfo(rootDir, file) }.sorted()
            .filter { info -> !Regex(".*(examples|restful|persist).*$").matches(info.name) }

        projectInfos.forEach { settings.include(it.path) }

        projectInfos.forEach { projectInfo(settings, it) }

    }

    private fun projectInfo(settings: Settings, it: ProjectInfo) {
        val project = settings.findProject(it.dir)
        project?.name = it.name
        project?.projectDir = it.dir
        project?.buildFileName = it.buildFileName
    }

    /**
     * 获取文件树
     */
    private fun fileTree(baseDir: Any, configuration: ConfigurableFileTree.() -> Unit): ConfigurableFileTree {
        return fileOperations.fileTree(baseDir).also(configuration)
    }

    /**
     * 获取 URI
     */
    private fun uri(path: Any) = fileOperations.uri(path)

}
