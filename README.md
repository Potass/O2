# Overview #

* Written as `kotlin multiplatform` project with `android target`.
* Only `debug` build is supported. Release build is not part of the delivery. Command line prompt: `./gradlew :androidApp:assembleDebug`
* To run all unit tests from command line, use `./gradlew testDebugUnitTest`. When run from Android Studio UI, only android target is supported, not jvm.
* Tested on Pixel 6a device with Android 16.