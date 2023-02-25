package com.voc.gradle.plugin.repository.impl;

import com.voc.gradle.plugin.api.ProjectBase;
import com.voc.gradle.plugin.repository.VersionRepositoryInfo;
import com.voc.gradle.plugin.repository.VersionType;
import org.gradle.api.Project;
import org.gradle.api.model.ObjectFactory;
import org.gradle.api.provider.Property;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
public class DefaultRepositoryInfo extends ProjectBase implements VersionRepositoryInfo {

    private final Property<String> name;
    protected final Property<String> username;
    private final Property<String> password;
    private final Property<Boolean> publish;

    private final Map<VersionType, String> urls;

    public DefaultRepositoryInfo(String name, Project project) {
        super(project);
        ObjectFactory objectFactory = project.getObjects();
        this.name = objectFactory.property(String.class).value(name);
        this.username = objectFactory.property(String.class);
        this.password = objectFactory.property(String.class);
        this.publish = objectFactory.property(Boolean.class).value(false);
        this.urls = new HashMap<>(3);
    }

    public DefaultRepositoryInfo(Project project) {
        this("null", project);
    }

    @Override
    public String getUrl() {
        VersionType versionType = VersionType.forProject(getProject());
        return this.getUrl(versionType);
    }

    @Override
    public void url(String url) {
        VersionType versionType = VersionType.forProject(getProject());
        this.url(versionType, url);
    }

    @Override
    public String getUsername() {
        return this.username.getOrNull();
    }

    @Override
    public void username(String username) {
        this.username.set(username);
    }

    @Override
    public String getPassword() {
        return this.password.getOrNull();
    }

    @Override
    public void password(String password) {
        this.password.set(password);
    }

    @Override
    public boolean isAllowPublish() {
        return this.publish.get();
    }

    @Override
    public void publish(boolean allowed) {
        this.publish.set(allowed);
    }

    @Override
    public void enablePublish() {
        this.publish.set(true);
    }

    @Override
    public void disablePublish() {
        this.publish.set(false);
    }

    @Override
    public void usernameFromEnvironment(String key) {
        this.username.set(System.getenv(key));
    }

    @Override
    public void passwordFromEnvironment(String key) {
        this.password.set(System.getenv(key));
    }

    @Override
    public Map<VersionType, String> getUrls() {
        return this.urls;
    }

    @Override
    public String getUrl(VersionType versionType) {
        return this.urls.get(versionType);
    }

    @Override
    public void url(VersionType versionType, String url) {
        this.urls.put(versionType, url);
    }

    @Override
    public void removeUrl(VersionType versionType) {
        this.urls.remove(versionType);
    }

    @Override
    public String getName() {
        return this.name.get();
    }
}
