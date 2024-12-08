# Medi App

Is a simple app that has:
1. Simple login screen, no validation present save for field must be field.
2. Simple dashboard that has time-based greeting for username provided during login
3. Add show data medicine hosted in [mocky.io](https://designer.mocky.io/), displayed on card
   views [see data used](https://run.mocky.io/v3/07f53008-73c9-41bd-af9c-284a8bc2bc32)
4. On medicine item clicked it opens, detailed screen

## Dependencies

1. Jetpack Compose -> for ui
2. Coroutines - For Concurrency and Asynchronous tasks
3. Ktor - For network requests
4. Hilt - For Dependency Injection
5. Room - For persistence
6. Timber - For Logging
7. Nav grapg - for navigation,

## Architecture

The used architecture is as follows;

### :app
This layer includes;
1. Relevant ui for login,dashboard and details screen
2. LoginViewModel to handle login and MainViewModel for the rest of the logic

### :domain

This layer contains;
1. Data Models Used
2. Navigation destinations i.e LOGIN, HOME, DETAIL
3. App Business logic
4. Relevant mappers i.e dto to model
### :local
This layer contains:
1. Room DB
2. Entities
3. Dao
it is used for data persistence

## :remote
This layer has 
1. Network logic
2. Dtos
3. Response parser logic 

## Dependencies

The project uses [Versions Catalog](https://docs.gradle.org/current/userguide/platforms.html#sub:version-catalog)
to set up and share dependencies across the modules. The main reasons for choosing to adopt Versions
Catalog are:

- Central place to define dependencies.
- Easy syntax.
- Does not compromise on build speeds as changes do not need the module to be compiled.

To add a dependency, navigate to **gradle/libs.versions.toml*** file, which has all the dependencies
for the whole project. This file has the following sections:

[versions] is used to declare the version numbers that will be referenced later by plugins and
libraries.
[libraries] Define the libraries that will be later accessed in our Gradle files.
[bundles] Are used to define a set of dependencies. For this, we have `compose`, `room`, `lifecycle`
and `ktor` as examples.
[plugins] Used to define plugins.

You need to add your dependency version in [versions]. This is unnecessary if you are not sharing
the version across different dependencies. After defining the version, add your library in
the [libraries] section as:

```toml
androidx-core-ktx = "androidx.core:core-ktx:1.15.0"
```

Moreover, if you have already defined the version in [versions], you define it as:

```toml
androidx-core-ktx = { group = "androidx.core", name = "core-ktx", version.ref = "coreKtx" }
```

**Note**:

- You can use separators such as -, _v, . that will be normalized by Gradle to . in the Catalog and
  allow you to create subsections.
- Define variables using **CamelCase**.\
- Check if the library can be added to any existing bundles.

