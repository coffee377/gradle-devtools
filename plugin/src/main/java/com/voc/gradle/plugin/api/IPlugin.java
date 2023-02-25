package com.voc.gradle.plugin.api;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.List;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2019/08/13 16:19
 */
public interface IPlugin extends Plugin<Project>, ProjectAware {

    /**
     * 官方插件入口
     *
     * @param project Project
     */
    @Override
    default void apply(Project project) {
        this.setProject(project);
        this.onApply(project);
        this.registerPluginActions(project);
    }

    /**
     * 应用插件
     *
     * @param project Project
     */
    void onApply(Project project);

    /**
     * 注册插件 Action
     *
     * @param project Project
     */
    default void registerPluginActions(Project project) {
        List<ProjectPluginAction> actions = this.getPluginActions();
        if (actions == null) {
            return;
        }

        for (ProjectPluginAction pluginAction : actions) {
            try {
                project.getPlugins().withType(pluginAction.getPluginClass(), plugin -> pluginAction.execute(project));
            } catch (Exception ignored) {
            }
        }
    }

    /**
     * 获取插件 Action 集合
     *
     * @return List
     */
    default List<ProjectPluginAction> getPluginActions() {
        return null;
    }
}
