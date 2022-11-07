package com.voc.gradle.plugin.base;

import de.skuzzle.semantic.Version;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/11/06 21:55
 */
public class ProjectVersionPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        Project parent = project.getParent();
        if (parent != null) {
            String parentVersion = (String) parent.getVersion();
            String version = (String) project.getVersion();
            if (Project.DEFAULT_VERSION.equals(version)) {

            }
            Version version1 = Version.parseVersion(parentVersion);
            Version version2 = Version.parseVersion(version);
            if (version1.isGreaterThanOrEqualTo(version2)) {
                project.setVersion(version1.toString());
            } else {
                project.setVersion(version2.toString());
            }
        }
    }
}
