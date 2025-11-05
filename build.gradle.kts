// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    // This is necessary to avoid the plugins to be loaded multiple times in each subproject's classloader.
    // Also trick for the same plugin versions in all sub-modules.
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.kotlin.multiplatform.library) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.compose.compiler) apply false
    alias(libs.plugins.compose.multiplatform) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.multiplatform) apply false
    alias(libs.plugins.kotlin.serialization) apply false
}

val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
val appVersionCode = libs.findVersion("appVersionCode").get().requiredVersion
val appVersionName = libs.findVersion("appVersionName").get().requiredVersion
allprojects {
    extra["appVersionCode"] = appVersionCode
    extra["appVersionName"] = appVersionName
}