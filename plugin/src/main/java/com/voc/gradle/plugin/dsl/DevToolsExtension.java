//package com.voc.gradle.plugin.dsl;
//
//
//import com.voc.gradle.core.DevType;
//
///**
// * @author Wu Yujie
// * @email coffee377@dingtalk.com
// * @time 2021/12/30 08:48
// * @since 0.0.2
// */
//public interface DevToolsExtension {
//    String NAME = "devtools";
//
//    /**
//     * 模块类型
//     *
//     * @return DevType
//     */
//    DevType getType();
//
//    /**
//     * 设置开发模块类型
//     *
//     * @param devType DevType
//     */
//    void type(DevType devType);
//
//    /**
//     * 设置开发模块类型
//     *
//     * @param devTypeName String
//     * @see DevType#name()
//     */
//    void type(String devTypeName);
//
//    /**
//     * 是否使用了 <a href="https://maven.aliyun.com/mvn/guide">阿里云代理的仓库服务</a>
//     *
//     * @return boolean
//     */
//    boolean isAliMavenProxy();
//
//    /**
//     * 配置是否开启阿里仓库代理
//     *
//     * @param enabled boolean
//     */
//    void aliMavenProxy(boolean enabled);
//
////    /**
////     * 获取所有 maven 仓库配置
////     *
////     * @return NamedDomainObjectContainer<? extends RepositoryInfo>
////     */
////    NamedDomainObjectContainer<? extends RepositoryInfo> mavenRepository();
//
////    /**
////     * 自定义 maven 仓库配置
////     *
////     * @param action Action<? super NamedDomainObjectContainer<? extends RepositoryInfo>>
////     */
////    void maven(Action<? super NamedDomainObjectContainer<? extends RepositoryInfo>> action);
////
////    /**
////     * 获取所有阿里云效仓库配置
////     *
////     * @return NamedDomainObjectContainer<? extends AliYunRepositoryInfo>
////     */
////    NamedDomainObjectContainer<? extends AliYunRepositoryInfo> aliMavenRepository();
////
////    /**
////     * 自定义阿里云效仓库配置
////     *
////     * @param action Action<? super NamedDomainObjectContainer<? extends AliYunRepositoryInfo>>
////     */
////    void ali(Action<? super NamedDomainObjectContainer<? extends AliYunRepositoryInfo>> action);
//
//    /**
//     * 本地 maven 地址
//     *
//     * @return String
//     */
//    String getLocalMavenRepository();
//
//    /**
//     * 配置本地仓库地址
//     *
//     * @param url String
//     */
//    void localMavenRepository(String url);
//
//    /**
//     * 是否使用 kotlin
//     *
//     * @return boolean
//     */
//    boolean useKotlin();
//
//    /**
//     * 设置是否启用 kotlin
//     *
//     * @param enabled boolean
//     */
//    void kotlin(boolean enabled);
//
//    /**
//     * 是否使用 groovy
//     *
//     * @return boolean
//     */
//    boolean useGroovy();
//
//    /**
//     * 设置是否启用 groovy
//     *
//     * @param enabled boolean
//     */
//    void groovy(boolean enabled);
//
//    /**
//     * 是否使用 Google AutoService
//     *
//     * @return boolean
//     */
//    boolean useAutoService();
//
//    /**
//     * 设置是否启用 Google AutoService
//     *
//     * @param enabled boolean
//     */
//    void autoService(boolean enabled);
//
//    /**
//     * 是否使用 java tools
//     *
//     * @return boolean
//     */
//    boolean useJavaTools();
//
//    /**
//     * 设置是否启用 Java Tools
//     *
//     * @param enabled boolean
//     */
//    void javaTools(boolean enabled);
//
//    /**
//     * 是否使用 lombok
//     *
//     * @return boolean
//     */
//    boolean useLombok();
//
//    /**
//     * 设置是否启用 Lombok
//     *
//     * @param enabled boolean
//     */
//    void lombok(boolean enabled);
//
//    /**
//     * 是否开启单元测试
//     *
//     * @return boolean
//     */
//    boolean useJunit();
//
//    /**
//     * 设置是否启用 Junit 单元测试
//     *
//     * @param enabled boolean
//     */
//    void junit(boolean enabled);
//
//}
