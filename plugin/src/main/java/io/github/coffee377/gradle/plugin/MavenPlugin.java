package io.github.coffee377.gradle.plugin;

import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginManager;
import org.gradle.api.publish.maven.plugins.MavenPublishPlugin;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/04 14:47
 */
public class MavenPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        PluginManager pluginManager = project.getPluginManager();
        pluginManager.apply(MavenPublishPlugin.class);
//        pluginManager.apply(RepositoryPlugin.class);
    }
}
