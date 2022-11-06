package io.github.coffee377.convention;

import com.voc.gradle.plugin.BasePluginAction;
import com.voc.gradle.plugin.MavenPlugin;
import com.voc.gradle.plugin.ide.IntegratedDevelopmentEnvironmentPlugin;
import com.voc.gradle.utils.GradleUtils;
import lombok.Getter;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/23 14:27
 */
@Getter
public class DevToolsPlugin implements Plugin<Project> {
    public static final String ID = "com.voc.devtools";

    @Override
    public void apply(@NotNull Project project) {
        GradleUtils.verifyGradleVersion();
        createExtension(project);
        PluginContainer plugins = project.getPlugins();
        plugins.apply(BasePlugin.class);
        plugins.apply(IntegratedDevelopmentEnvironmentPlugin.class);
        plugins.apply(BasePluginAction.class);
        plugins.apply(MavenPlugin.class);
    }

    /**
     * 创建配置扩展
     *
     * @param project Project
     */
    private void createExtension(Project project) {
        ExtensionContainer extensions = project.getExtensions();

//        extensions.create(DevToolsExtension.class, DevToolsExtension.NAME, DefaultDevToolsExtension.class, project);
    }

    /**
     * 配置使用插件
     *
     * @param project Project
     */
    private void applyPlugins(Project project) {
        PluginContainer plugins = project.getPlugins();
        ExtraPropertiesExtension extraProperties = project.getExtensions().getExtraProperties();
        Map<String, Object> properties = extraProperties.getProperties();
        /* 1. 应用开发工具插件，默认使用 idea */
//        String ide = ExtraPropsUtils.getStringValue(project, "ide", "idea");
//        this.applyIdePlugin(plugins, IDE.of(ide));

        /* 2.应用 Java Library 插件 */
        plugins.apply(JavaLibraryPlugin.class);
        // TODO: 2022/10/23 16:58 BOM 使用 JavaPlatformPlugin
//        plugins.apply(JavaPlatformPlugin.class);

        /* 3.应用 MavenPublish 插件 */
//        plugins.apply(MavenPublishPlugin.class);

        /* 4.依赖管理插件 */
//        plugins.apply(DEPENDENCY_MANAGEMENT_PLUGIN_ID);

    }

}
