import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.multiplatform)
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
                api(project.dependencies.platform(libs.ktor.bom))

                implementation(libs.koin.core)
                implementation(libs.kotlin.stdlib)
                api(libs.bundles.ktor.common)
            }
        }


        androidMain {
            dependencies {
                implementation(project.dependencies.platform(libs.koin.bom))
                api(project.dependencies.platform(libs.ktor.bom))

                implementation(libs.koin.android)
                api(libs.ktor.client.okhttp)
            }
        }
    }
}

android {
    namespace = "${libs.versions.appNamespace.get()}.shared.networking"
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
}