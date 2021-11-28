## MVVM News Multi-Module Sample

![build](https://github.com/KaustubhPatange/mvvm-multi-module-sample/workflows/build/badge.svg)

Sample code to demonstrate multi-module navigation with some experimental gradle's features & more.

## Compilation Guide

- Get an API Key from https://newsapi.org/ & add it in the `local.properties` file,

```
newsApiKey="API_KEY"
```

or grab an apk from the latest [release](https://github.com/KaustubhPatange/mvvm-multi-module-sample/releases/latest).

## Tech Stack

- [Kotlin](https://kotlinlang.org/) - First class and official programming language for Android development.
- [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - For asynchronous and more..
- [Android Architecture Components](https://developer.android.com/topic/libraries/architecture) - Collection of libraries that help you design robust, testable, and maintainable apps.
  - [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - Data objects that notify views when the underlying database changes.
  - [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - Stores UI-related data that isn't destroyed on UI changes.
  - [Paging](https://developer.android.com/topic/libraries/architecture/paging) - Library helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources.
- [Navigator](https://github.com/KaustubhPatange/navigator) - A small navigation library to ease fragment transactions & handling backstack.
- [Dependency Injection](https://developer.android.com/training/dependency-injection) -
  - [Hilt-Dagger](https://dagger.dev/hilt/) - Standard library to incorporate Dagger dependency injection into an Android application.
- [Retrofit](https://square.github.io/retrofit/) - A type-safe HTTP client for Android and Java.
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html) - For writing Gradle build scripts using Kotlin.

## Contribute

If you want to contribute to this project, you're always welcome!
Check the **TODO** list section.

## TODO

- [ ] Add a Detail screen in `:detail` module.
- [ ] Add a Splash screen (maybe) in `:splash` module.
- [ ] Support for Dark theme.
- [ ] Fix the Type Accessors feature for `projects`.
- [ ] Add Tests (Retrofit & Paging).

## License

- [The Apache License Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)

```
Copyright 2020 Kaustubh Patange

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   https://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
