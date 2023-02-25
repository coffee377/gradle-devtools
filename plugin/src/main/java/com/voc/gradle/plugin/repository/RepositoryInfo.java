package com.voc.gradle.plugin.repository;

import org.gradle.api.Named;

import java.io.Serializable;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 15:29
 */
public interface RepositoryInfo extends Named, Serializable {
    /**
     * 仓库地址
     *
     * @return String
     * @since 0.0.1
     */
    String getUrl();

    /**
     * 设置仓库地址
     *
     * @param url String
     * @since 0.0.2
     */
    void url(String url);

    /**
     * 用户名
     *
     * @return String
     * @since 0.0.1
     */
    String getUsername();

    /**
     * 设置用户名
     *
     * @param username String
     * @since 0.0.2
     */
    void username(String username);

    /**
     * 密码
     *
     * @return String
     * @since 0.0.1
     */
    String getPassword();


    /**
     * 设置密码
     *
     * @param password String
     * @since 0.0.2
     */
    void password(String password);

    /**
     * 是否允许 {@link org.gradle.api.publish.maven.plugins.MavenPublishPlugin} 发布到此仓库
     *
     * @return boolean
     * @since 0.0.1
     */
    boolean isAllowPublish();

    /**
     * 设置是否允许发布至此仓库
     *
     * @param allowed boolean
     * @since 0.0.2
     */
    void publish(boolean allowed);

    /**
     * 启用发布
     *
     * @since 0.0.3
     */
    void enablePublish();

    /**
     * 禁用发布
     *
     * @since 0.0.3
     */
    void disablePublish();

    /**
     * 从环境变量获取用户名
     *
     * @param key 环境变量
     * @since 0.0.3
     */
    void usernameFromEnvironment(String key);

    /**
     * 从环境变量获取密码
     *
     * @param key 环境变量
     * @since 0.0.3
     */
    void passwordFromEnvironment(String key);
}
