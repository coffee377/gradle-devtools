package com.voc.gradle.plugin;

import lombok.extern.slf4j.Slf4j;
import org.gradle.BuildListener;
import org.gradle.BuildResult;
import org.gradle.api.Plugin;
import org.gradle.api.initialization.Settings;
import org.gradle.api.invocation.Gradle;
import org.jetbrains.annotations.NotNull;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/17 09:31
 */
@Slf4j
public class GradleInitPlugin implements Plugin<Gradle> {

    @Override
    public void apply(Gradle gradle) {
        log.warn("Gradle init => {}", gradle.getGradleVersion());
        gradle.addBuildListener(new BuildListener() {
            @Override
            public void settingsEvaluated(@NotNull Settings settings) {
                // var1.gradle.rootProject 这里访问 Project 对象时会报错，
                // 因为还未完成 Project 的初始化。
                log.warn("settings 评估完成（settings.gradle 中代码执行完毕）");
            }

            @Override
            public void projectsLoaded(@NotNull Gradle gradle) {
                log.warn("项目结构加载完成（初始化阶段结束），可访问根项目：{}", gradle.getRootProject());
            }

            @Override
            public void projectsEvaluated(@NotNull Gradle gradle) {
                log.warn("所有项目评估完成（配置阶段结束）");
            }

            @Override
            public void buildFinished(@NotNull BuildResult result) {
                log.warn("构建结束");
            }
        });
    }
}
