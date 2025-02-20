plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    implementation(project(":compiler:psi"))
    implementation(intellijCoreDep()) { includeJars("intellij-core", rootProject = rootProject) }
}

sourceSets {
    "main" { projectDefault() }
    "test" { none() }
}

kotlin {
    explicitApi()
}