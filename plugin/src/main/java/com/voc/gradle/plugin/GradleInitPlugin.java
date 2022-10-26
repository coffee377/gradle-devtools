package com.voc.gradle.plugin;

import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Plugin;
import org.gradle.api.invocation.Gradle;
import org.gradle.api.model.ObjectFactory;

import javax.inject.Inject;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/17 09:31
 */
@Slf4j
public class GradleInitPlugin implements Plugin<Gradle> {
    private final ObjectFactory objectFactory;

    @Inject
    public GradleInitPlugin(ObjectFactory objectFactory) {
        this.objectFactory = objectFactory;
    }

    @Override
    public void apply(Gradle gradle) {

        gradle.beforeSettings(settings -> {
            settings.getExtensions().create(DevToolsSettingsExtension.NAME,
                    DefaultDevToolsSettingsExtension.class, objectFactory);
        });
        gradle.settingsEvaluated(settings ->
                settings.apply(objectConfigurationAction -> {
                    objectConfigurationAction.plugin(AutoModulePlugin.class);
                })
        );
    }


}
