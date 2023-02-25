package io.github.coffee377.gradle.plugin.extensions;


import com.voc.gradle.core.DevType;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @since 0.1.1
 */
public interface DevToolsExtension {
    String NAME = "devOpts";

    /**
     * 模块类型
     *
     * @return DevType
     */
    DevType getType();

    /**
     * 设置开发模块类型
     *
     * @param devType DevType
     */
    void type(DevType devType);

    /**
     * 设置开发模块类型
     *
     * @param devTypeName String
     * @see DevType#name()
     */
    void type(String devTypeName);

    /**
     * 使用 <a href="https://maven.aliyun.com/mvn/guide">阿里云代理的仓库服务</a>
     */
    void useAliMavenProxy();

    /**
     * 使用 本地 maven 地址
     */
    void useMavenLocalRepository();

    /**
     * 使用 本地 maven 地址
     *
     * @param url 仓库地址
     */
    void useMavenLocalRepository(String url);

    /**
     * 使用 Kotlin 插件
     */
    void useKotlin();

    /**
     * 使用 Kotlin 插件
     *
     * @param version 版本
     */
    void useKotlin(String version);

    /**
     * 使用 groovy 插件
     */
    void useGroovy();

    /**
     * 使用 groovy 插件
     *
     * @param version 版本
     */
    void useGroovy(String version);

    /**
     * 使用 Google AutoService 注解处理器
     */
    void useGoogleAutoService();

    /**
     * 使用 Google AutoService 注解处理器
     *
     * @param version 版本
     */
    void useGoogleAutoService(String version);

    /**
     * 使用 java tools
     */
    void useJavaTools();

    /**
     * 使用 lombok 注解处理器
     */
    void useLombok();

    /**
     * 使用 Lombok 注解处理器
     *
     * @param version 版本
     */
    void useLombok(String version);

    /**
     * 是否开启单元测试
     */
    void useJunit();

    /**
     * 设置是否启用 Junit 单元测试
     *
     * @param version 版本
     */
    void junit(String version);

}
