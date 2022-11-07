package com.voc.gradle.plugin.extensions;

import java.util.Set;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/06 10:21
 * @since 0.1.1
 */
public interface AutoIncludeProjectExtension {
    String NAME = "auto";

    boolean isEnable();

    void setEnable(boolean enable);

    Set<String> getExcludeProject();

    void exclude(String... pattern);

}
