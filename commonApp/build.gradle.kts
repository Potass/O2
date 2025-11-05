import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.kotlin.serialization)
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
                implementation(libs.bundles.navigation.common)
                implementation(libs.bundles.koin.common)
                implementation(libs.kotlin.stdlib)
                implementation(libs.kotlinx.coroutines.core)

                implementation(project(":shared:networking"))
                api(project(":shared:common"))
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

                implementation(libs.androidx.navigation.compose)
                implementation(libs.bundles.koin.android)
                implementation(libs.kotlinx.coroutines.android)
            }
        }
    }
}

private val moduleNamespace = "${libs.versions.appNamespace.get()}.common"

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

// See 'composeResources' directory in 'commonMain'.
compose.resources {
    publicResClass = true
    packageOfResClass = moduleNamespace
    generateResClass = always
}