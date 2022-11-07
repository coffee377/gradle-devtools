package com.voc.gradle.utils;

import org.gradle.api.artifacts.dsl.RepositoryHandler;
import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/28 14:59
 */
public class RepositoryUtils {

//    /**
//     * 添加仓库
//     *
//     * @param repositoryHandler RepositoryHandler
//     * @param repositoryInfo    RepositoryInfo
//     * @deprecated
//     */
//    @Deprecated
//    public static void add(RepositoryHandler repositoryHandler, RepositoryInfo repositoryInfo) {
//        RepositoryUtil.addRepository(repositoryHandler, repositoryInfo);
//    }
//
//    /**
//     * 添加依赖仓库
//     *
//     * @param repositoryHandler RepositoryHandler
//     * @param repositoryInfo    RepositoryInfo
//     */
//    public static void addRepository(@NonNull RepositoryHandler repositoryHandler, @Nullable RepositoryInfo repositoryInfo) {
//        RepositoryUtil.addRepository(repositoryHandler, repositoryInfo, null);
//    }

//    /**
//     * 添加依赖仓库
//     *
//     * @param repositoryHandler RepositoryHandler
//     * @param repositoryInfo    RepositoryInfo
//     * @param url               String
//     */
//    private static void addRepository(@NonNull RepositoryHandler repositoryHandler, @Nullable RepositoryInfo repositoryInfo) {
//        Optional.ofNullable(repositoryInfo).ifPresent(info -> {
//            String url = info.getUrl(VersionType.MILESTONE);
//            String realUrl = StringUtils.hasText(url) ? url : info.getUrl();
//            if (StringUtils.hasText(realUrl)) {
//                repositoryHandler.maven(mavenArtifactRepository -> {
//                    mavenArtifactRepository.setAllowInsecureProtocol(true);
//                    Optional.of(info.getName()).ifPresent(mavenArtifactRepository::setName);
//                    mavenArtifactRepository.setUrl(realUrl);
//                    mavenArtifactRepository.credentials(credentials -> {
//                        Optional.ofNullable(info.getUsername()).ifPresent(credentials::setUsername);
//                        Optional.ofNullable(info.getPassword()).ifPresent(credentials::setPassword);
//                    });
//                });
//            }
//        });
//    }

    public static void addRepository(@NonNull RepositoryHandler repositoryHandler, @NonNull String url){
        repositoryHandler.maven(mavenArtifactRepository -> {
            mavenArtifactRepository.setUrl(url);
            mavenArtifactRepository.setAllowInsecureProtocol(true);
        });
    }

//    /**
//     * 添加依赖仓库（阿里云效）
//     *
//     * @param repositoryHandler    RepositoryHandler
//     * @param aliYunRepositoryInfo AliYunRepositoryInfo
//     */
//    public static void addAliYunRepository(@NonNull RepositoryHandler repositoryHandler,
//                                           @Nullable AliYunRepositoryInfo aliYunRepositoryInfo) {
//        Optional.ofNullable(aliYunRepositoryInfo).ifPresent(info -> {
//            info.getValidUrl().forEach(url -> RepositoryUtil.addRepository(repositoryHandler, info, url));
//        });
//
//    }

}
