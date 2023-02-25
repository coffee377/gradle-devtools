package com.voc.gradle.plugin.repository;

import java.util.Map;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @since 0.1.3
 */
public interface VersionRepositoryInfo extends RepositoryInfo {

    /**
     * 获取所有版本仓库地址
     *
     * @return {@code Map<VersionType, String>}
     */
    Map<VersionType, String> getUrls();

    /**
     * 根据 {@link VersionType} 获取仓库地址
     *
     * @param versionType
     * @return
     */
    String getUrl(VersionType versionType);

    /**
     * 配置不同类型仓库地址
     *
     * @param versionType
     * @param url
     */
    void url(VersionType versionType, String url);

    /**
     * 移除指定配置 URL
     *
     * @param versionType
     */
    void removeUrl(VersionType versionType);
}
