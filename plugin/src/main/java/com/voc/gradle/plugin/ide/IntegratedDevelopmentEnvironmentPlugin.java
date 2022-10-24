package com.voc.gradle.plugin.ide;

import com.voc.gradle.utils.ExtraPropsUtils;
import org.gradle.api.Plugin;
import org.gradle.api.Project;
import org.gradle.api.plugins.PluginContainer;
import org.gradle.ide.visualstudio.plugins.VisualStudioPlugin;
import org.gradle.ide.xcode.plugins.XcodePlugin;
import org.gradle.plugins.ide.eclipse.EclipsePlugin;
import org.gradle.plugins.ide.idea.IdeaPlugin;

/**
 * @author WuYujie
 * @email coffee377@dingtalk.com
 * @time 2022/10/24 20:06
 */
public class IntegratedDevelopmentEnvironmentPlugin implements Plugin<Project> {
    public static String IDE_PROPERTY_NAME = "ide";

    @Override
    public void apply(Project project) {
        PluginContainer plugins = project.getPlugins();
        String stringValue = ExtraPropsUtils.getStringValue(project, IDE_PROPERTY_NAME, IDE.IDEA.getName());
        IDE ide = IDE.of(stringValue);
        switch (ide) {
            case ECLIPSE:
                plugins.apply(EclipsePlugin.class);
                break;
            case VISUAL_STUDIO:
                plugins.apply(VisualStudioPlugin.class);
                break;
            case XCODE:
                plugins.apply(XcodePlugin.class);
                break;
            case IDEA:
            default:
                plugins.apply(IdeaPlugin.class);
        }
    }
}
