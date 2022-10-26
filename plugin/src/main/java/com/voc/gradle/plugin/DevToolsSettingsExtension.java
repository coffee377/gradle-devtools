package com.voc.gradle.plugin;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/25 09:07
 */
public interface DevToolsSettingsExtension {

    String NAME = "devtools";

    boolean isAutomatically();

    /**
     * 自动包含项目
     */
    void automatically(boolean include);

    default String localRepositoryPath(){
        return null;
    }

}
