plugins {
    java
    kotlin("jvm")
    id("jps-compatible")
}

val ktorExcludesForDaemon: List<Pair<String, String>> by rootProject.extra

dependencies {
    compileOnly(project(":daemon-common"))
    api(kotlinStdlib())
    compileOnly(intellijCoreDep()) { includeJars("intellij-core") }
    compileOnly(intellijDep()) { includeIntellijCoreJarDependencies(project) }
    api(commonDep("org.jetbrains.kotlinx", "kotlinx-coroutines-core")) {
        isTransitive = false
    }
    api(commonDep("io.ktor", "ktor-network")) {
        ktorExcludesForDaemon.forEach { (group, module) ->
            exclude(group = group, module = module)
        }
    }
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}

tasks {
    val compileKotlin by existing(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class) {
        kotlinOptions {
            freeCompilerArgs += "-opt-in=kotlinx.coroutines.DelicateCoroutinesApi"
        }
    }
}
