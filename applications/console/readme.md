# Console
Command-line applications.

All demos are built as Gradle projects and use the Gradle application plugin to support building/running with the JVM.

Plugins
* application

Tasks supported
`./gradlew build`
`./gradlew run`

To pass arguments on the command-line, use `gradle run` with a flag:
`gradle run --args="p1"`

Reference
* https://docs.gradle.org/current/userguide/application_plugin.html
* https://docs.gradle.org/current/userguide/command_line_interface.html