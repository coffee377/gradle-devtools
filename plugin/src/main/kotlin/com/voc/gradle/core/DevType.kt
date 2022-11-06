package com.voc.gradle.core

import java.util.*
import java.util.stream.Collectors

/**
 * @author Wu Yujie
 * @email coffee377@dingtalk.com
 * @time 2021/12/30 09:04
 * @since 0.1.0
 */
enum class DevType(
    /**
     * @see org.gradle.api.publish.maven.MavenPom.setPackaging
     */
    packaging: String,
    /**
     * @see org.gradle.api.publish.maven.MavenPublication.getName
     */
    pubName: String, description: String
) {
    BOM("pom", "Pom", "依赖管理"),
    LIB("jar", "Lib", "类库开发"),
    APP("jar", "App", "应用开发");

    private val packaging: String
    private val pubName: String
    private val description: String

    init {
        this.packaging = packaging
        this.pubName = pubName
        this.description = description
    }

    companion object {
        fun isJar(devType: DevType): Boolean {
            return "jar" == devType.packaging
        }

        fun isPom(devType: DevType): Boolean {
            return "pom" == devType.packaging
        }

        fun isWar(devType: DevType): Boolean {
            return "war" == devType.packaging
        }

        fun of(name: String?): DevType {
            DevType.values().filter {
                it.name.equals(name, true)
            }
            val first = Arrays.stream(DevType.values())
                .filter {
                    it.name.equals(name, true)
                }.findFirst()
            return first.orElseThrow {
                val collect =
                    Arrays.stream(values()).map {
                        it.name
                    }.collect(Collectors.toList())
                RuntimeException(String.format("name must be in %s", collect))
            }
        }
    }
}
