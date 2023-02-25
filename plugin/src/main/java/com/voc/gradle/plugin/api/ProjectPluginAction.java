package com.voc.gradle.plugin.api;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/27 20:21
 */
public interface ProjectPluginAction extends Action<Project> {

    /**
     * 获取插件类
     *
     * @return {@code Class<? extends Plugin < ? extends Project>>}
     */
    Class<? extends Plugin<? extends Project>> getPluginClass();
}
