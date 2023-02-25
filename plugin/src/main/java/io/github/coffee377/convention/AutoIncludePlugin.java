package io.github.coffee377.convention;

import com.voc.gradle.plugin.AutoIncludeProjectPlugin;
import io.github.coffee377.gradle.utils.GradleUtils;
import lombok.extern.slf4j.Slf4j;
import org.gradle.api.Plugin;
import org.gradle.api.initialization.Settings;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/17 09:31
 */
@Slf4j
public class AutoIncludePlugin implements Plugin<Settings> {
    public static final String ID = "io.github.coffee377.auto-include";

    @Override
    public void apply(Settings settings) {
        GradleUtils.verifyGradleVersion();
        settings.apply(objectConfigurationAction -> objectConfigurationAction.plugin(AutoIncludeProjectPlugin.class));
    }
}
