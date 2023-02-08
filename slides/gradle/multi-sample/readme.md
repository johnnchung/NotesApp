# Multi-sample
Demonstrates how to structure a project with a JavaFX app, a console app and a shared library.

There are three subprojects:

* app/ is a JavaFX 'Hello World' app
* console/ is a simple Kotlin console app
* lib/ is a shared library that each app uses

You can build from the top-level, or build/run each project individually.

./gradlew build  // build everything
./gradlew :app:build // just build the app and dependencies
./gradlew :console:run // just build and run the console

