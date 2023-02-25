package com.voc.gradle.plugin.api;

import com.voc.gradle.plugin.embedded.DepEnum;
import com.voc.gradle.utils.DependencyUtils;
import com.voc.gradle.utils.ExtraPropsUtils;
import org.gradle.api.artifacts.*;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.internal.artifacts.dependencies.DefaultMinimalDependency;
import org.gradle.api.internal.catalog.DefaultExternalDependencyFactory;
import org.gradle.api.internal.catalog.ExternalModuleDependencyFactory;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.provider.Provider;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/15 10:21
 */
public interface DependencyAware extends ProjectAware {

    /**
     * 添加依赖
     *
     * @param configurationName  配置名称
     * @param dependencyNotation 依赖
     */
    default void addDependency(String configurationName, Object dependencyNotation) {
        DependencyHandler dependencies = getProject().getDependencies();
        Dependency dependency = dependencies.create(dependencyNotation);
        dependencies.add(configurationName, dependency);
    }

    default void addDependency(String configurationName, MinimalExternalModuleDependency minimalDependency) {
        DependencyHandler dependencies = getProject().getDependencies();
        ExternalModuleDependencyFactory dependencyFactory = new DefaultExternalDependencyFactory(null, null);
        Provider<MinimalExternalModuleDependency> minimalExternalModuleDependencyProvider = dependencyFactory.create("");
        Dependency dependency = dependencies.create(minimalExternalModuleDependencyProvider);
        dependencies.add(configurationName, dependency);
    }

    default void addDependency(String configurationName, ModuleIdentifier moduleIdentifier, MutableVersionConstraint versionConstraint) {
        MinimalExternalModuleDependency minimalDependency = new DefaultMinimalDependency(moduleIdentifier, versionConstraint);
        addDependency(configurationName, minimalDependency);
    }

    /**
     * 添加依赖
     *
     * @param configurationName 配置名称
     * @param dependency        依赖
     */
    default void addDependency(String configurationName, DepEnum dependency) {
        String version = ExtraPropsUtils.getStringValue(getProject(), dependency.getExtraProps());
        this.addDependency(configurationName, DependencyUtils.of(dependency, version));
    }

    /**
     * 添加依赖
     *
     * @param configurationName 配置名称
     * @param dependency        依赖
     */
    default void addDependencyWithoutVersion(String configurationName, DepEnum dependency) {
        this.addDependency(configurationName, DependencyUtils.withoutVersion(dependency));
    }

    /**
     * 添加 annotationProcessor 依赖
     *
     * @param dependency 依赖
     * @see JavaPlugin#ANNOTATION_PROCESSOR_CONFIGURATION_NAME
     */
    default void addAnnotationProcessor(DepEnum dependency) {
        this.addDependency(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 annotationProcessor 依赖
     *
     * @param dependency 依赖
     * @see JavaPlugin#ANNOTATION_PROCESSOR_CONFIGURATION_NAME
     */
    default void addAnnotationProcessorWithoutVersion(DepEnum dependency) {
        this.addDependencyWithoutVersion(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 implementation 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#IMPLEMENTATION_CONFIGURATION_NAME
     */
    default void addImplementation(DepEnum dependency) {
        this.addDependency(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 implementation 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#IMPLEMENTATION_CONFIGURATION_NAME
     */
    default void addImplementationWithoutVersion(DepEnum dependency) {
        this.addDependencyWithoutVersion(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 testImplementation 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#TEST_IMPLEMENTATION_CONFIGURATION_NAME
     */
    default void addTestImplementation(DepEnum dependency) {
        this.addDependency(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 testImplementation 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#TEST_IMPLEMENTATION_CONFIGURATION_NAME
     */
    default void addTestImplementationWithoutVersion(DepEnum dependency) {
        this.addDependencyWithoutVersion(JavaPlugin.TEST_IMPLEMENTATION_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 testRuntimeOnly 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#TEST_RUNTIME_ONLY_CONFIGURATION_NAME
     */
    default void addTestRuntimeOnly(DepEnum dependency) {
        this.addDependency(JavaPlugin.TEST_RUNTIME_ONLY_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 testRuntimeOnly 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#TEST_RUNTIME_ONLY_CONFIGURATION_NAME
     */
    default void addTestRuntimeOnlyWithoutVersion(DepEnum dependency) {
        this.addDependencyWithoutVersion(JavaPlugin.TEST_RUNTIME_ONLY_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 compileOnly 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#COMPILE_ONLY_CONFIGURATION_NAME
     */
    default void addCompileOnly(DepEnum dependency) {
        this.addDependency(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 compileOnly 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#COMPILE_ONLY_CONFIGURATION_NAME
     */
    default void addCompileOnlyWithoutVersion(DepEnum dependency) {
        this.addDependencyWithoutVersion(JavaPlugin.COMPILE_ONLY_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 api 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#API_CONFIGURATION_NAME
     */
    default void addApi(DepEnum dependency) {
        this.addDependency(JavaPlugin.API_CONFIGURATION_NAME, dependency);
    }

    /**
     * 添加 api 依赖
     *
     * @param dependency Dependency
     * @see JavaPlugin#API_CONFIGURATION_NAME
     */
    default void addApiWithoutVersion(DepEnum dependency) {
        this.addDependencyWithoutVersion(JavaPlugin.API_CONFIGURATION_NAME, dependency);
    }

}
