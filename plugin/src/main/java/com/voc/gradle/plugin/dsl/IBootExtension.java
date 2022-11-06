package com.voc.gradle.plugin.dsl;

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/30 22:02
 * @since 0.0.3
 */
public interface IBootExtension {

    /**
     * 是否组件库开发
     *
     * @return boolean
     */
    boolean isLibrary();

    /**
     * 设置是否组件库开发
     *
     * @param enabled boolean
     */
    void library(boolean enabled);
}
