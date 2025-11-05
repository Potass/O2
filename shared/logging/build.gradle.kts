import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlin.multiplatform)
}

kotlin {
    jvm {
        compilerOptions {
            jvmTarget = JvmTarget.JVM_17
        }
    }
    // Or any other target. We just need one to avoid 'Please declare at least one Kotlin target.' error.
    // Why? This library declares code just in commonMain source set, therefore it can by used by all targets.

    sourceSets {
        commonMain {
            dependencies {
                implementation(project.dependencies.platform(libs.koin.bom))

                // 'api' declaration makes dependency transitive to caller module.
                api(libs.kermit)
                implementation(libs.koin.core)
                implementation(libs.kotlin.stdlib)
            }
        }
    }
}