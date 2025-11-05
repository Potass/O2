import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
}

kotlin {
    androidTarget {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }

    sourceSets {
        commonMain {
            dependencies {
                implementation(project.dependencies.platform(libs.koin.bom))

                implementation(compose.components.resources)
                implementation(compose.components.uiToolingPreview)
                implementation(compose.foundation)
                implementation(compose.materialIconsExtended)
                implementation(compose.material3)
                implementation(compose.runtime)
                implementation(compose.ui)
                implementation(compose.uiTooling)
                implementation(libs.androidx.lifecycle.runtime)
                implementation(libs.bundles.koin.common)
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)

                api(project(":shared:logging"))
            }
        }

        commonTest {
            dependencies {
                implementation(libs.bundles.unit.testing.common)
            }
        }

        androidMain {
            dependencies {
                implementation(project.dependencies.platform(libs.koin.bom))

                implementation(libs.androidx.activity.compose)
                implementation(libs.androidx.lifecycle.runtime.compose)
                implementation(libs.androidx.lifecycle.runtime.ktx)
                implementation(libs.bundles.koin.android)
                implementation(libs.kotlinx.coroutines.android)
            }
        }
    }

    sourceSets["commonMain"].kotlin.srcDir(layout.buildDirectory.dir("generated/src"))
}

private val moduleNamespace = "${libs.versions.appNamespace.get()}.shared.common"

android {
    namespace = moduleNamespace
    compileSdk = libs.versions.androidCompileSdk.get().toInt()

    defaultConfig {
        minSdk = libs.versions.androidMinSdk.get().toInt()
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"))
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }
}

compose.resources {
    publicResClass = true
    packageOfResClass = moduleNamespace
    generateResClass = always
}

// Task to generate version information file based on version catalog.
val generateVersionInfo by tasks.registering {
    val outputDir = layout.buildDirectory.dir("generated/src")
    val appVersionCode = rootProject.extra["appVersionCode"] as String
    val appVersionName = rootProject.extra["appVersionName"] as String

    outputs.dir(outputDir)
    doLast {
        val file = outputDir.get().file("cz/jvyh/o2/scratch/shared/common/domain/VersionInfo.kt").asFile
        file.parentFile.mkdirs()
        file.writeText(
            """
            package cz.jvyh.o2.scratch.shared.common.domain

            // Generated, based on version catalog.
            object VersionInfo {
                const val APP_VERSION_CODE = "$appVersionCode"
                const val APP_VERSION_NAME = "$appVersionName"
            }
            """.trimIndent()
        )
    }
}
listOf(
    "compileKotlinMetadata",
    "compileKotlinJvm",
    "compileKotlinDesktop"
).forEach { taskName ->
    tasks.matching { it.name == taskName }.configureEach {
        dependsOn(generateVersionInfo)
    }
}
tasks.matching { it.name.startsWith("compile") && it.name.endsWith("KotlinAndroid") }.configureEach {
    dependsOn(generateVersionInfo)
}