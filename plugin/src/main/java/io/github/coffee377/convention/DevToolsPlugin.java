package io.github.coffee377.convention;

import com.voc.gradle.plugin.BasePluginAction;
import com.voc.gradle.plugin.MavenPlugin;
import com.voc.gradle.plugin.extensions.DefaultDevToolsExtension;
import com.voc.gradle.plugin.extensions.DevToolsExtension;
import com.voc.gradle.plugin.ide.IntegratedDevelopmentEnvironmentPlugin;
import com.voc.gradle.utils.GradleUtils;
import lombok.Getter;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.BasePlugin;
import org.gradle.api.plugins.ExtensionContainer;
import org.gradle.api.plugins.PluginContainer;
import org.jetbrains.annotations.NotNull;

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
        extensions.create(DevToolsExtension.class, DevToolsExtension.NAME, DefaultDevToolsExtension.class, project);
    }

}
