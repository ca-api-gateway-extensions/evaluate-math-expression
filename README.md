# About
 
# Build
In order to build the modular assertion run `gradle build`.
 
This will compile, test, and create the jar file. It will be available in `build/libs`
 
# Adding Libraries
In order to build the custom assertions the CA API Gateway API jar is required to be put into the lib directory.
 
# Run
Docker version of Gateway greatly helps us to deploy assertions / RESTMAN bundles quickly. Please follow the steps below to run the container Gateway prepopulated with this example assertion and example services.
1) Open Shell or Command Prompt and navigate to the directory where this repository is cloned.
2) Build the Assertion
   ```
     gradle build
   ```
3) Ensure your docker environment is properly setup.
4) Provide the CA API Gateway license at `docker/license.xml`.
5) Execute the below docker-compose command to run the CA API Gateway container.
   ```
     docker-compose up
   ```
   * Provided `docker-compose.yml` ensures pulling the latest CA API Gateway image from the Docker Hub public repository and deploys the assertion that was just build.
   * In addition, it publishes ... [Add info about what else is published]
6) Wait until the Gateway container is started and is ready to receive messages.
 
# Usage
 
# How You Can Contribute
Contributions are welcome and much appreciated. To learn more, see the [Contribution Guidelines][contributing].
 
# License
 
Copyright (c) 2018 CA. All rights reserved.
 
This software may be modified and distributed under the terms
of the MIT license. See the [LICENSE][license-link] file for details.
 
 
 [license-link]: /LICENSE
 [contributing]: /CONTRIBUTING.md
