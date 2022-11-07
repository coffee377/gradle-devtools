package com.voc.gradle.plugin

import com.voc.gradle.ProjectInfo
import com.voc.gradle.plugin.extensions.AutoIncludeProjectExtension
import com.voc.gradle.utils.FileOperationUtils
import org.gradle.api.Plugin
import org.gradle.api.file.ConfigurableFileTree
import org.gradle.api.initialization.Settings
import org.gradle.api.internal.file.FileOperations
import org.gradle.api.model.ObjectFactory
import org.gradle.api.provider.Property
import javax.inject.Inject

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/06 10:21
 */


open class DefaultAutoIncludeProjectExtension(objectFactory: ObjectFactory) :
    AutoIncludeProjectExtension {

    private val enabled: Property<Boolean>

    private val patterns: MutableSet<String>

    init {
        enabled = objectFactory.property(Boolean::class.java).value(true)
        patterns = HashSet()
    }

    override fun isEnable(): Boolean {
        return enabled.get()
    }

    override fun setEnable(enable: Boolean) {
        enabled.set(enable)
    }

    override fun getExcludeProject(): MutableSet<String> {
        return patterns
    }

    override fun exclude(vararg pattern: String) {
        patterns.addAll(pattern)
    }

}

/**
 * @author  WuYujie
 * @email  coffee377@dingtalk.com
 * @time  2022/10/23 21:11
 */
class AutoIncludeProjectPlugin @Inject constructor(objectFactory: ObjectFactory) : Plugin<Settings> {
    private var objectFactory: ObjectFactory
    private var configurableFileTree: ConfigurableFileTree
    private lateinit var fileOperations: FileOperations


    init {
        this.objectFactory = objectFactory
        configurableFileTree = objectFactory.fileTree()
    }

    override fun apply(settings: Settings) {

        fileOperations = FileOperationUtils.fileOperationsFor(settings)

        /* 创建扩展配置 */
        settings.extensions.create(
            AutoIncludeProjectExtension.NAME,
            DefaultAutoIncludeProjectExtension::class.java,
            objectFactory
        )

        /* 配置插件仓库 */
        settings.pluginManagement.repositories.apply {
            maven { it.apply { url = uri("${settings.rootDir}/build/publications/repos") } }
            gradlePluginPortal()
            maven { it.apply { url = uri("https://repo.spring.io/plugins-release") } }
        }


//        val versionCatalogs = settings.dependencyResolutionManagement.versionCatalogs
//        versionCatalogs.create("testLibs") {
////            it.version("", "")
//
//        }

        /* 常用插件管理 */
        settings.pluginManagement.plugins.apply {
            id("org.jetbrains.kotlin.jvm").version("1.6.21")
            id("org.asciidoctor.jvm.convert").version("3.3.2")
            id("org.asciidoctor.jvm.pdf").version("3.3.2")
            id("org.asciidoctor.jvm.epub").version("3.3.2")
            id("com.github.shalousun.smart-doc").version("2.6.0-release")
            id("com.gradle.plugin-publish").version("1.0.0")
            id("org.springframework.boot").version("2.5.0.RELEASE")
            id("io.spring.dependency-management").version("1.0.11.RELEASE")
        }

        /* 配置评估后自动引入子项目 */
        settings.gradle.settingsEvaluated {
            autoInclude(it)
        }
    }

    private fun autoInclude(settings: Settings) {
        val autoIncludeProjectExtension = settings.extensions.getByType(AutoIncludeProjectExtension::class.java)
        if (autoIncludeProjectExtension.isEnable.not()) return

        val rootDir = settings.rootDir

        val projectInfos = fileTree(rootDir) {
            include("**/*.gradle", "**/*.gradle.kts")
            exclude("build", "**/gradle", "settings.gradle", "buildSrc", "/build.gradle", ".*", "out")
            val excludes = settings.gradle.startParameter.projectProperties["excludeProjects"]?.split(",")
            excludes?.let { exclude(it) }
        }
            .files
            .asSequence()
            .filter { file -> !file.parentFile.relativeTo(rootDir).name.isNullOrEmpty() }
            .map { file -> ProjectInfo(rootDir, file) }.sorted()
            .filter { projectInfo ->
                val find = autoIncludeProjectExtension.excludeProject.map { it.toRegex() }
                    .find {
                        it.matches(input = projectInfo.name)
                    }
                find == null
            }
            .toList()

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

