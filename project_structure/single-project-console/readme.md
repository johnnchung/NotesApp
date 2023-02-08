Single-Project Console Application
(c) 2022 Jeff Avery

Demonstrates how to structure a single console project in Gradle.
We only have a single project in the application/ folder, but we 
still set things up in a way that supports adding more projects if needed. 
Compare to the multi-project-build to see the differences.

Supported gradle tasks:

| Tasks   | Description                                                |
|:--------|:-----------------------------------------------------------|
| clean   | Remove build/ directory                                    |
| build   | Build the application project in build/ directory          |
| run     | Run the application project                                |
| distZip | Create run scripts in application/build/distribution       |
| distTar | Create run scripts in application/build/distribution       |
