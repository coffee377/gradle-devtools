package com.voc.gradle

import java.io.File

/**
 * 项目详细实体类
 * @author  Wu Yujie
 * @email  coffee377@dingtalk.com
 * @time  2022/08/06 12:18
 */
class ProjectInfo(rootFile: File, buildFile: File) : Comparable<ProjectInfo> {
  /**
   * 项目所在目录
   */
  val dir: File

  /**
   * gradle build 文件名称
   */
  val buildFileName: String

  /**
   * 项目路径
   */
  val path: String

  /**
   * 项目名称
   */
  var name: String

  init {
    dir = buildFile.parentFile
    buildFileName = buildFile.name
    val isDefaultName = buildFile.name == "build.gradle" || buildFile.name == "build.gradle.kts"
    val isKotlin = buildFile.name.endsWith(".kts")
    val paths = dir.relativeTo(rootFile).path.split(File.separator)
    path = ":${paths.joinToString(":")}"
    name = if (!isDefaultName) {
      if (isKotlin) {
        buildFile.name.replace(".gradle.kts", "")
      } else {
        buildFile.name.replace(".gradle", "")
      }
    } else {
      "${rootFile.name}-${paths.joinToString("-")}"
    }
  }

  override fun compareTo(other: ProjectInfo): Int {
    return this.path.compareTo(other.path)
  }

  override fun toString(): String {
    return "Project Name\t\t-> ${name}\n" +
            "Project Path\t\t-> ${path}\n" +
            "Project Dir\t\t\t-> ${dir.absolutePath}\n" +
            "buildFileName\t\t-> ${buildFileName}\n"
  }
}
