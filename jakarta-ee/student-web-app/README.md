# Migrate a legacy Java EE Ant project to a Jakarta EE 10 Maven project

This workshop guides you through migrating a legacy Java EE Ant project to a Jakarta EE 10 Maven project. After completing the workshop, the project will be fully migrated from Java EE to Jakarta EE 10, upgraded from Spring Framework 5.3.39 to 6.2.x, and ready to build with Maven.

## About this Sample Project

A Java EE web application running on Open Liberty with a hybrid architecture that supports both traditional servlets and Spring MVC. The application manages student profiles with CRUD operations and demonstrates migrating from Ant to Maven and Java EE to Jakarta EE. 

For the project architecture, see [project details](doc/architecture.md).
For how to start this project, see [getting started](doc/getting-started.md)

## Prerequisites

- **Java 17** or higher
- **Apache Ant**, tested version: `1.10.14`.
- **Maven**, tested version: `3.8.7`.
- **Docker & Docker Compose** (Optional, for running the sample application)
- [Visual Studio Code](https://code.visualstudio.com/download)
- [VS Code Extension: GitHub Copilot App Modernization](https://marketplace.visualstudio.com/items?itemName=vscjava.migrate-java-to-azure)
  - This extension depends on [VS Code Extension: GitHub Copilot App Modernization – Upgrade for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-upgrade). Both extensions will be installed automatically when you install **GitHub Copilot App Modernization**.

## Assess the Project



