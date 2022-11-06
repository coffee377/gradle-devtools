package com.voc.gradle.plugin;

import org.gradle.api.JavaVersion;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Configuration;
import org.gradle.api.artifacts.ConfigurationContainer;
import org.gradle.api.file.DuplicatesStrategy;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.tasks.Delete;
import org.gradle.api.tasks.compile.CompileOptions;
import org.gradle.api.tasks.compile.JavaCompile;
import org.gradle.jvm.tasks.Jar;
import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/27 20:19
 */
public class BasePluginAction implements Plugin<Project> {
    @Override
    public void apply(@NotNull Project project) {
        configureAnnotationProcessor(project);
        configureJavaCompile(project);
        configureJar(project);
        configureClean(project);
    }

    /**
     * 清理任务增加删除目录
     *
     * @param project Project
     */
    private void configureClean(Project project) {
        /* 清理任务增加删除目录 */
        project.getTasks().withType(Delete.class, delete -> delete.delete("out", "build"));
    }

    /**
     * 注解处理器配置
     *
     * @param project Project
     */
    private void configureAnnotationProcessor(Project project) {
        ConfigurationContainer configurations = project.getConfigurations();

        Configuration annotationProcessor = configurations.getByName(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME);
        Configuration testAnnotationProcessor = configurations.getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME);
        Configuration compileOnly = configurations.getByName(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME);
        Configuration testCompileOnly = configurations.getByName(JavaPlugin.TEST_COMPILE_ONLY_CONFIGURATION_NAME);

        testAnnotationProcessor.extendsFrom(annotationProcessor);
        compileOnly.extendsFrom(annotationProcessor);
        testCompileOnly.extendsFrom(compileOnly, testAnnotationProcessor);
    }

    /**
     * 编译配置
     *
     * @param project Project
     */
    private void configureJavaCompile(Project project) {
        project.getTasks().withType(JavaCompile.class, (compile) -> {
            CompileOptions options = compile.getOptions();
            options.setEncoding("UTF-8");
            options.getCompilerArgs().add("-parameters");
            if (JavaVersion.current().isJava11Compatible()) {
                options.getRelease().set(8);
            }

//            Configuration configuration = project.getConfigurations().getByName(JavaPlugin.TEST_ANNOTATION_PROCESSOR_CONFIGURATION_NAME);
//            if (annotationProcessorPath != null) {
//                annotationProcessorPath.plus(configuration);
//            } else {
//                options.setAnnotationProcessorPath(configuration);
//            }
        });

    }

    public void exclude(String... patterns){
        Set<String> set = new HashSet<>();
        set.addAll(Arrays.asList(patterns));
    }

    /**
     * 配置打包策略
     *
     * @param project Project
     */
    private void configureJar(Project project) {
        project.getTasks().withType(Jar.class, jar -> {
            /* 重复文件策略，排除 */
            jar.setDuplicatesStrategy(DuplicatesStrategy.EXCLUDE);
            jar.setIncludeEmptyDirs(false);
            /* 排除 JRebel 配置文件 */
            jar.exclude("rebel.xml");
            jar.manifest(manifest -> {
                Map<String, String> attributes = new HashMap<>();
                attributes.put("Created-By", String.format("%s (%s)", System.getProperty("java.version"), System.getProperty("java.specification.vendor")));
                attributes.put("Implementation-Title", project.getName());
                attributes.put("Implementation-Version", project.getVersion().toString());
                attributes.put("Automatic-Module-Name", project.getName().replace("-", "."));
                manifest.attributes(attributes);
            });
        });
    }

    /**
     * 依赖缓存时间配置
     *
     * @param project Project
     */
    public void configureConfigurations(Project project) {
        ConfigurationContainer configurations = project.getConfigurations();

        configurations.all(configuration -> configuration.resolutionStrategy(resolutionStrategy -> {
            /* 动态版本依赖缓存 10 minutes */
            resolutionStrategy.cacheDynamicVersionsFor(10, TimeUnit.MINUTES);
            /* SNAPSHOT版本依赖缓存 0 seconds */
            resolutionStrategy.cacheChangingModulesFor(10, TimeUnit.SECONDS);
        }));
    }

}
