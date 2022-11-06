//package com.voc.gradle.plugin.dsl;
//
//import com.voc.gradle.core.DevType;
//import org.gradle.api.Project;
//import org.gradle.api.model.ObjectFactory;
//import org.gradle.api.provider.Property;
//
///**
// * @author WuYujie
// * @email coffee377@dingtalk.com
// * @time 2022/11/03 22:15
// */
//public class DefaultDevToolsExtension implements DevToolsExtension {
//
//    public final Property<DevType> type;
//    private final Property<Boolean> aliMavenProxy;
//    //    private final NamedDomainObjectContainer<? extends RepositoryInfo> maven;
////    private final NamedDomainObjectContainer<? extends AliYunRepositoryInfo> ali;
//    private final Property<String> localMavenRepository;
//    private final Property<Boolean> kotlin;
//    private final Property<Boolean> groovy;
//    private final Property<Boolean> googleAutoService;
//    private final Property<Boolean> javaTools;
//    private final Property<Boolean> lombok;
//    private final Property<Boolean> junit;
//
//    public DefaultDevToolsExtension(Project project) {
//        ObjectFactory objectFactory = project.getObjects();
//        type = objectFactory.property(DevType.class).value(DevType.LIB);
//        aliMavenProxy = objectFactory.property(Boolean.class).value(false);
//        localMavenRepository = objectFactory.property(String.class);
//        kotlin = objectFactory.property(Boolean.class).value(false);
//        groovy = objectFactory.property(Boolean.class).value(false);
//        googleAutoService = objectFactory.property(Boolean.class).value(false);
//        javaTools = objectFactory.property(Boolean.class).value(false);
//        lombok = objectFactory.property(Boolean.class).value(false);
//        junit = objectFactory.property(Boolean.class).value(false);
//    }
//
//    @Override
//    public DevType getType() {
//        return type.get();
//    }
//
//    @Override
//    public void type(DevType devType) {
//        type.set(devType);
//    }
//
//    @Override
//    public void type(String devTypeName) {
//        DevType devType = DevType.valueOf(devTypeName);
//        this.type.set(devType);
//    }
//
//    @Override
//    public boolean isAliMavenProxy() {
//        return aliMavenProxy.get();
//    }
//
//    @Override
//    public void aliMavenProxy(boolean enabled) {
//        aliMavenProxy.set(enabled);
//    }
//
//    @Override
//    public String getLocalMavenRepository() {
//        return localMavenRepository.get();
//    }
//
//    @Override
//    public void localMavenRepository(String url) {
//        localMavenRepository.set(url);
//    }
//
//    @Override
//    public boolean useKotlin() {
//        return kotlin.get();
//    }
//
//    @Override
//    public void kotlin(boolean enabled) {
//        kotlin.set(enabled);
//    }
//
//    @Override
//    public boolean useGroovy() {
//        return groovy.get();
//    }
//
//    @Override
//    public void groovy(boolean enabled) {
//        groovy.set(enabled);
//    }
//
//    @Override
//    public boolean useAutoService() {
//        return googleAutoService.get();
//    }
//
//    @Override
//    public void autoService(boolean enabled) {
//        googleAutoService.set(enabled);
//    }
//
//    @Override
//    public boolean useJavaTools() {
//        return javaTools.get();
//    }
//
//    @Override
//    public void javaTools(boolean enabled) {
//        javaTools.set(enabled);
//    }
//
//    @Override
//    public boolean useLombok() {
//        return lombok.get();
//    }
//
//    @Override
//    public void lombok(boolean enabled) {
//        lombok.set(enabled);
//    }
//
//    @Override
//    public boolean useJunit() {
//        return junit.get();
//    }
//
//    @Override
//    public void junit(boolean enabled) {
//        junit.set(enabled);
//    }
//}
