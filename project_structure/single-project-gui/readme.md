Single Desktop Application
(c) 2022 Jeff Avery

Demonstrates how to structure a single desktop project in Gradle.
It includes a main JavaFX project in the application/ folder, 
and a shared library in the shared/ folder.

Supported gradle tasks:

| Tasks   | Description                                                |
|:--------|:-----------------------------------------------------------|
| clean   | Remove build/ directory                                    |
| build   | Build the application project in build/ directory          |
| run     | Run the application project                                |
| distZip | Create run scripts in application/build/distribution       |
| distTar | Create run scripts in application/build/distribution       |