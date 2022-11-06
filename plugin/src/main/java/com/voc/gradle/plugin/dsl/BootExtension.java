package com.voc.gradle.plugin.dsl;

import org.gradle.api.Project;
import org.gradle.api.provider.Property;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2020/12/17 09:17
 */
public class BootExtension implements IBootExtension {

    private final Property<Boolean> library;

    public BootExtension(Project project) {
        this.library = project.getObjects().property(Boolean.class).value(false);
    }

    @Override
    public boolean isLibrary() {
        return this.library.get();
    }

    @Override
    public void library(boolean enabled) {
        this.library.set(enabled);
    }
}
