package io.github.coffee377.gradle.plugin.extensions;

import com.voc.gradle.core.DevType;
import org.gradle.api.Project;
import org.gradle.api.artifacts.Dependency;
import org.gradle.api.artifacts.dsl.DependencyHandler;
import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.plugins.JavaPlugin;
import org.gradle.api.provider.Property;
import org.springframework.util.StringUtils;

import java.io.File;
import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/03 22:15
 */
public class DefaultDevToolsExtension implements DevToolsExtension {
    public final Map<String, String> ALI_MAVEN_PROXY = Collections.unmodifiableMap(
            new HashMap<String, String>() {{
                put("Public", "https://maven.aliyun.com/repository/public/");
                put("GradlePlugin", "https://maven.aliyun.com/repository/gradle-plugin");
                put("Spring", "https://maven.aliyun.com/repository/spring");
                put("SpringPlugin", "https://maven.aliyun.com/repository/spring-plugin");
                put("Google", "https://maven.aliyun.com/repository/google");
            }}
    );
    public final Property<DevType> type;
    private final Property<Boolean> aliMavenProxy;
    //    private final NamedDomainObjectContainer<? extends RepositoryInfo> maven;
//    private final NamedDomainObjectContainer<? extends AliYunRepositoryInfo> ali;
    private final Property<String> localMavenRepository;
    private final Property<Boolean> kotlin;
    private final Property<Boolean> groovy;
    private final Property<Boolean> googleAutoService;
    private final Property<Boolean> javaTools;
    private final Property<Boolean> lombok;
    private final Property<Boolean> junit;
    private final Project project;

    public DefaultDevToolsExtension(Project project) {
        this.project = project;
        ObjectFactory objectFactory = project.getObjects();
        type = objectFactory.property(DevType.class).value(DevType.LIB);
        aliMavenProxy = objectFactory.property(Boolean.class).value(false);
        localMavenRepository = objectFactory.property(String.class);
        kotlin = objectFactory.property(Boolean.class).value(false);
        groovy = objectFactory.property(Boolean.class).value(false);
        googleAutoService = objectFactory.property(Boolean.class).value(false);
        javaTools = objectFactory.property(Boolean.class).value(false);
        lombok = objectFactory.property(Boolean.class).value(false);
        junit = objectFactory.property(Boolean.class).value(false);
    }

    @Override
    public DevType getType() {
        return type.get();
    }

    @Override
    public void type(DevType devType) {
        type.set(devType);
    }

    @Override
    public void type(String devTypeName) {
        DevType devType = DevType.valueOf(devTypeName);
        this.type.set(devType);
    }

    @Override
    public void useAliMavenProxy() {
        RepositoryHandler repositories = project.getRepositories();
        ALI_MAVEN_PROXY.forEach((name, url) ->
                repositories.maven(mavenArtifactRepository -> {
                    mavenArtifactRepository.setName(name);
                    mavenArtifactRepository.setUrl(url);
                }));
    }

    @Override
    public void useMavenLocalRepository() {
        RepositoryHandler repositories = project.getRepositories();
        repositories.mavenLocal();
    }

    @Override
    public void useMavenLocalRepository(String url) {
        RepositoryHandler repositories = project.getRepositories();
        repositories.mavenLocal(mavenLocal -> {
            if (StringUtils.hasText(url)) {
                File file = new File(url);
                URI uri = file.toURI();
                if (file.exists() && !mavenLocal.getUrl().equals(uri)) {
                    mavenLocal.setUrl(uri);
                }
            }
        });
    }

    @Override
    public void useKotlin() {
        useKotlin("");
    }

    @Override
    public void useKotlin(String version) {
        // 使用 kotlin 插件
//        project.getPluginManager().r
//        project.getPlugins().re
        // 添加依赖
        DependencyHandler dependencies = project.getDependencies();
        Dependency dependency = dependencies.create("");
        dependencies.add(JavaPlugin.IMPLEMENTATION_CONFIGURATION_NAME, dependency);
    }

    @Override
    public void useGroovy() {

    }

    @Override
    public void useGroovy(String version) {

    }

    @Override
    public void useGoogleAutoService() {
        useGoogleAutoService("1.0.1");
    }

    @Override
    public void useGoogleAutoService(String version) {
        DependencyHandler dependencies = project.getDependencies();
        Dependency dependency = dependencies.create("com.google.auto.service:auto-service:1.0.1");
        dependencies.add(JavaPlugin.ANNOTATION_PROCESSOR_CONFIGURATION_NAME, dependency);
    }

    @Override
    public void useJavaTools() {

    }

    @Override
    public void useLombok() {

    }

    @Override
    public void useLombok(String version) {

    }

    @Override
    public void useJunit() {

    }

    @Override
    public void junit(String version) {

    }
}
