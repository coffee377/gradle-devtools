package com.voc.gradle.plugin;

import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/25 09:37
 */
public class DefaultDevToolsSettingsExtension implements DevToolsSettingsExtension {
    private final Property<Boolean> automatically;

    public DefaultDevToolsSettingsExtension(ObjectFactory objectFactory) {
        this.automatically = objectFactory.property(Boolean.class).value(true);
    }

    @Override
    public boolean isAutomatically() {
        return automatically.get();
    }

    @Override
    public void automatically(boolean include) {
        automatically.set(include);
    }
}
