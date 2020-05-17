object Releases {
    const val versionCode = 1
    const val versionName = "1.0"
}

object DefaultConfig {
    const val minSdk = 24
    const val targetSdk = 29
    const val compileSdk = 29
}

object AndroidConfiguration {
    const val buildToolsVersion = "29.0.3"
}

object Versions {
    const val gradle = "3.6.2"
    const val kotlin = "1.3.72"
    const val coroutines = "1.3.6"

    const val support = "1.1.0"
    const val constraint = "1.1.3"

    const val daggerVersion = "2.27"
    const val lifecycleVersion = "2.2.0"

    const val okhttpVersion = "4.3.1"
    const val retrofitVersion = "2.7.1"
    const val gsonVersion = "2.8.6"

    const val lifecycleTestingVersion = "2.1.0"
    const val junitVersion = "4.13"
    const val mockk = "1.9.3"

}

object Deps {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val appcompat = "androidx.appcompat:appcompat:${Versions.support}"
    const val constraint = "androidx.constraintlayout:constraintlayout:${Versions.constraint}"
    const val coordinator = "androidx.coordinatorlayout:coordinatorlayout:${Versions.support}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.support}"
    const val design = "com.google.android.material:material:${Versions.support}"
    const val cardview = "androidx.cardview:cardview:1.0.0"
    const val core = "androidx.core:core-ktx:1.2.0"

    const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val androidCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofitVersion}"
    const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttpVersion}"
    const val gson = "com.google.code.gson:gson:${Versions.gsonVersion}"
    const val converterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofitVersion}"

    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val daggerAndroidSupport = "com.google.dagger:dagger-android-support:${Versions.daggerVersion}"
    const val daggerAndroidProcessor = "com.google.dagger:dagger-android-processor:${Versions.daggerVersion}"

    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.lifecycleVersion}"
    const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleVersion}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel:${Versions.lifecycleVersion}"
    const val lifecycleTesting = "androidx.arch.core:core-testing:${Versions.lifecycleTestingVersion}"

    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:1.8.0"
    const val okhttpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpVersion}"

    const val junit = "junit:junit:${Versions.junitVersion}"
    const val mockk = "io.mockk:mockk:${Versions.mockk}"
}