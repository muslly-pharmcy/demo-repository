# VOLTA Android APK

Android WebView wrapper for the current VOLTA preview. Requires internet and is not an offline-native app.

## Build
The root GitHub Actions workflow named **Build VOLTA Android APK** installs JDK 17, Gradle 8.9 and Android SDK 35, builds a debug APK, verifies it exists and uploads artifact `VOLTA-debug-apk`.

This APK is for testing only. Before production distribution, replace the preview URL in `app/src/main/java/com/muslly/volta/MainActivity.kt` with a verified production URL and rebuild.
