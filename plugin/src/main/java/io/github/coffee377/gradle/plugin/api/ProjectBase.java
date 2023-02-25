package io.github.coffee377.gradle.plugin.api;

import org.gradle.api.Project;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/29 15:17
 */
public abstract class ProjectBase implements ProjectAware {

    protected Project project;

    public ProjectBase(Project project) {
        this.project = project;
    }

    @Override
    public Project getProject() {
        return project;
    }

    @Override
    public void setProject(Project project) {
        this.project = project;
    }

}
