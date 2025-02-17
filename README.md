# vicuna-core-lib
Reference Implementations - Core library for Java Projects - Onion Architecture

## Introduction

This is a reference (in the meaning of the microservice pattern) 
implementation of the core library for Java projects, trying to follow the Onion Architecture principles.


### Build and Run
1. Navigate to the project root directory
2. Run `mvn clean package` to build and package the library
3. Run `mvn test` to run the unit tests

### Overview
* Onion Architecture implementation for core library functionality
* Support for Java 11 and later versions
* Maven-based build and dependency management
* Unit testing framework using JUnit and Mockito

## Features
* Library with DDD based design
* uses package.info (Jigsaw)
* core for different parts of an onion application

## Structure
```bash
 ───src
    ├───main
    │   └───java
    │       └───com
    │           └───scprojekt
    │               ├───domain
    │               │   └───core
    │               │       ├───model
    │               │       │   ├───assurance
    │               │       │   │   └───entity
    │               │       │   ├───banking
    │               │       │   │   └───entity
    │               │       │   ├───customer
    │               │       │   │   └───entity
    │               │       │   └───user
    │               │       │       ├───dto
    │               │       │       ├───entity
    │               │       │       ├───event
    │               │       │       ├───exception
    │               │       │       ├───repository
    │               │       │       └───service
    │               │       └───shared
    │               │           ├───database
    │               │           ├───dto
    │               │           ├───messaging
    │               │           ├───misc
    │               │           └───service
    │               ├───example
    │               └───stereotype
    └───test
        ├───java
        │   └───com
        │       └───scprojekt
        │           └───domain
        │               └───test
        │                   ├───arch
        │                   ├───service
        │                   └───shared
        └───resources
```



