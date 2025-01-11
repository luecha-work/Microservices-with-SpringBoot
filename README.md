# Spring Boot Project Setup

This guide walks through the steps to create a Spring Boot project with various dependencies and containerize the project using Docker, Buildpacks, and Google Cloud Tools.

## 1. Create Project using Spring Initializr

Create a new Spring Boot project by visiting [Spring Initializr](https://start.spring.io/) and selecting the following dependencies:

- **spring-boot-starter-actuator**
- **spring-boot-starter-data-jpa**
- **spring-boot-devtools**
- **spring-boot-starter-web**
- **spring-boot-starter-validation**
- **Lombok**

## Create Controller

Add the `@RestController` annotation to the class to declare it as a Controller.

## Build Container with Dockerfile (for Project: Account)

1. Build Image with: docker build . -t {YOUR_REPO_HUB}/account:0.0.1-SNAPSHOT

2. Run Image with: docker run -d -p 8080:8080 --name account-container {YOUR_REPO_HUB}/account:0.0.1-SNAPSHOT

## Build Container with Buildpacks.io (for Project: Loans)

1. Add the following configuration in the pom.xml:

```
<image>
    <name>lkanmaneekul/${project.artifactId}:${project.version}</name>
</image>
```

2. Build Image using Buildpacks: mvn spring-boot:build-image

3. Build Container and Run: docker run -d -p 8090:8090 --name loans-container {YOUR_REPO_HUB}/loans:0.0.1-SNAPSHOT

## Build Container with Google Cloud Tools (Jib Plugin) for Project: Cards

1. Add the following Jib Plugin to your pom.xml:

```
<plugin>
    <groupId>com.google.cloud.tools</groupId>
    <artifactId>jib-maven-plugin</artifactId>
    <version>3.3.2</version>
    <configuration>
        <to>
            <image>lkanmaneekul/${project.artifactId}:${project.version}</image>
        </to>
    </configuration>
</plugin>
```

2. Build Docker Image with Jib: mvn compile jib:dockerBuild
3. Build Container and Run: docker run -d -p 9000:9000 --name cards-container {YOUR_REPO_HUB}/cards:0.0.1-SNAPSHOT
