# vicuna-core-lib

Reference Implementations - Core library for Java Projects - Onion Architecture

## Introduction

This is a reference implementation (in the meaning of the microservice pattern) of the core library for Java projects, following the Onion Architecture principles. The library provides a solid foundation for building maintainable and scalable Java applications with Domain-Driven Design (DDD) concepts.

## Features

* **Onion Architecture Implementation** - Clean separation of concerns with proper layering
* **Domain-Driven Design (DDD)** - Based on DDD principles for better domain modeling
* **Java Module System** - Uses `module-info.java` (Project Jigsaw) for modular design
* **Multi-Domain Support** - Core functionality for different application domains
* **Java 11+ Compatible** - Support for Java 11 and later versions
* **Maven Integration** - Maven-based build and dependency management
* **Comprehensive Testing** - Unit testing framework using JUnit and Mockito

## Project Structure

```bash
vicuna-core-lib/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── scprojekt/
│   │               ├── domain/
│   │               │   └── core/
│   │               │       ├── model/
│   │               │       │   ├── assurance/
│   │               │       │   │   └── entity/
│   │               │       │   ├── banking/
│   │               │       │   │   └── entity/
│   │               │       │   ├── customer/
│   │               │       │   │   └── entity/
│   │               │       │   └── user/
│   │               │       │       ├── dto/
│   │               │       │       ├── entity/
│   │               │       │       ├── event/
│   │               │       │       ├── exception/
│   │               │       │       ├── repository/
│   │               │       │       └── service/
│   │               │       └── shared/
│   │               │           ├── database/
│   │               │           ├── dto/
│   │               │           ├── messaging/
│   │               │           ├── misc/
│   │               │           └── service/
│   │               ├── example/
│   │               └── stereotype/
│   └── test/
│       ├── java/
│       │   └── com/
│   │       └── scprojekt/
│   │           └── domain/
│   │               └── test/
│   │                   ├── arch/
│   │                   ├── service/
│   │                   └── shared/
│       └── resources/
└── pom.xml
```