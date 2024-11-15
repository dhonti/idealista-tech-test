# Idealista -- Technical test Challenge

## Sample videos

- [idealista_sample_1.webm](https://github.com/user-attachments/assets/2d932e59-96c2-4d35-9842-86e36c04931a)

- [idealista_sample_2.webm](https://github.com/user-attachments/assets/2119240d-31d8-4629-ab5b-53d94fc5ae2c)

- [idealista_sample_3.webm](https://github.com/user-attachments/assets/7d9604c3-bb1d-487c-86ca-3beb2eaccaab)

- [idealista_sample_4.webm](https://github.com/user-attachments/assets/28553ae8-89ad-42cf-8a9f-ddd58f0502d2)


## Tech Stacks

- [Kotlin](https://kotlinlang.org/) 100% coverage

- Dependency Injection (DI)
    - [HILT](https://developer.android.com/training/dependency-injection/hilt-android)is used for Dependency Injection as a wrapper on top of [Dagger](https://github.com/google/dagger).

- [Jetpack](https://developer.android.com/jetpack)
    - [AndroidX](https://developer.android.com/jetpack/androidx) - Major improvement to the original
      Android [Support Library](https://developer.android.com/topic/libraries/support-library/index),
      which is no longer maintained.
    - [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - Perform
      actions in response to a change in the lifecycle status of another component, such as
      activities and fragments.
    - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Designed
      to store and manage UI-related data in a lifecycle conscious way. The ViewModel class allows
      data to survive configuration changes such as screen rotations.
    - [Room](https://developer.android.com/training/data-storage/room) - Provides an abstraction
      layer over SQLite used for offline data caching.
    - [Navigation](https://developer.android.com/guide/navigation) -Navigation refers to the
      interactions that let users navigate across, into, and back out from the different pieces of
      content within your app.

- Others
  - [Retrofit](https://square.github.io/retrofit/)  for networking
  - [OkHttp-Logging-Interceptor](https://github.com/square/okhttp/blob/master/okhttp-logging-interceptor/README.md)  -
  Logs HTTP request and response data.
  - [Flow](https://developer.android.com/kotlin/flow) - Flows are built on top of coroutines and
  can provide multiple values.
  - [Material Design](https://material.io/develop/android/docs/getting-started/) - Build awesome
  beautiful UIs.
  - [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) - Library Support for
  coroutines,provides runBlocking coroutine builder used in tests.
  - [Moshi](https://github.com/square/moshi) - A modern JSON library for Kotlin and Java.


  ## Architecture

* Modern Architecture
  * Single activity architecture (
  with [Navigation component](https://developer.android.com/guide/navigation/navigation-getting-started))
  that defines navigation graphs
  * [MVI Clean Architecture](https://blog.stackademic.com/mvi-architecture-explained-on-android-e36ee66bceaa)
  * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation))
  * [Android KTX](https://developer.android.com/kotlin/ktx) - Jetpack Kotlin extensions
* UI
  * [Material design](https://material.io/design)
