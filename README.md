# AmazingWeather [![Build Status](https://circleci.com/gh/Edydaoud/AmazingWeather.svg?style=shield)](https://circleci.com/gh/Edydaoud/AmazingWeather)

## Summary

Android weather app using OpenWeatherMap, written in Kotlin which uses the following Architecture Components:

- ViewModel
- UseCase
- Repository
- Retrofit

It uses Dagger 2 and Dagger-Android for dependency injection.

## Pre-requisites

Require up-to-date versions of the Android build tools and the Android support repository.

## Getting Started

This project use the Gradle build system.

First download the project by cloning this repository or downloading an archived
snapshot. (See the options at the top of the page.)

In Android Studio, use the "Import non-Android Studio project" or
"Import Project" option. Next select one of the sample directories that you downloaded from this
repository.
If prompted for a gradle configuration accept the default settings.

Alternatively use the `gradlew build` command to build the project directly.

The demo apps require that you add your own Google Maps API key:

1. [Get a Maps API key](https://developers.google.com/maps/documentation/android-sdk/get-api-key)
2. Edit the file in resources directory called `google_maps_api.xml` and add your key
3. [Get an OpenWeather API key](https://openweathermap.org/)
4. Edit `QueryInterceptor` file and replace `API_KEY` with your API key

## Build

From the command line

`./gradlew test` to run local unit test

`./gradlew connectedAndroidTest` to run instrumented unit test

`./gradlew assembleDebug` to run the project

`./gradlew jacocoTestReport` to generate a code coverage report

## Key Feature

ViewModels don't receive a repository but a set of Use Cases, which are reused throughout the presentation layer.
Business logic that was present in ViewModels is moved to Use Cases. This is important because ViewModels tend to grow quickly in size in real applications.

## Libraries

- Android support Libraries
- Kotlin Coroutines
- Retrofit
- Gson
- Dagger
- Mockito
- Espresso

## License

```
   Copyright 2020 Google, Inc.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
