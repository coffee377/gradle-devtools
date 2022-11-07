package com.voc.gradle.plugin.repository;

import lombok.Getter;
import org.gradle.api.Project;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:57
 */
@Getter
public enum VersionType {
    /**
     * 快照版本
     */
    SNAPSHOT("snapshot","快照版本"),
    /**
     * 里程碑版本
     */
    MILESTONE("milestone","里程碑版本"),
    /**
     * 正式版
     */
    RELEASE("release","正式版本");

    private final String type;

    private final String description;

    VersionType(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public static VersionType forProject(@NonNull Project project) {
        String version = project.getVersion().toString();
        int modifierIndex = version.lastIndexOf('-');
        if (modifierIndex == -1) {
            return VersionType.RELEASE;
        }
        String type = version.substring(modifierIndex + 1);
        if (type.startsWith("M") || type.startsWith("RC")) {
            return VersionType.MILESTONE;
        }
        return VersionType.SNAPSHOT;
    }

}
