# Migration Plan: Ant to Maven

## Plan Creation Timestamp
2025-08-07 23:14:10

## Project Overview
This plan outlines the steps to migrate the OpenLibertyApp from Ant build system to Maven build system. The application is a web application built with Java 11, using Spring MVC, MyBatis (iBatis), and Log4j.

## Files to be Changed

### 1. Project Structure
- [x] Create Maven standard directory structure
  - Create `src/main/java` directory for Java source files
  - Create `src/main/resources` directory for configuration files
  - Create `src/main/webapp` directory for web content

### 2. Maven Configuration Files
- [x] Create `pom.xml` file at project root with appropriate dependencies
  - Based on Spring MVC, MyBatis, and other dependencies found in the project

### 3. Source Code Migration
- [x] Move Java source files from `src` to `src/main/java`
  - Files: All Java files under `src/ca/on/gov/edu/coreft/`

### 4. Resources Migration
- [x] Move resource files from `resources` to `src/main/resources`
  - Files: `resources/applicationContext-service.xml`, `resources/log4j.properties`, `resources/sql-map-config.xml`
  - Files: `resources/ca/on/gov/edu/msfaa/shared/persistence/xml/Student_SqlMap.xml`

### 5. Web Content Migration
- [x] Move web content from `WebContent` to `src/main/webapp`
  - Files: `WebContent/*.jsp`, `WebContent/WEB-INF/web.xml`, `WebContent/WEB-INF/applicationContext.xml`, `WebContent/WEB-INF/spring-servlet.xml`

### 6. Build File Removal
- [x] Remove Ant build files after successful Maven build
  - Files: `build.xml`, `build.properties`

## Dependencies Identified

Based on the code analysis, we need the following dependencies:

1. Spring MVC (Web, Core)
2. MyBatis (iBatis)
3. Log4j
4. Java Servlet API
5. JDBC (for database connections)

## Maven pom.xml Template

The following Maven POM file will be created:

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0
                             http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>ca.on.gov.edu</groupId>
    <artifactId>open-liberty-app</artifactId>
    <version>1.0.0-SNAPSHOT</version>
    <packaging>war</packaging>

    <name>OpenLibertyApp</name>
    <description>Student Web Application</description>

    <properties>
        <maven.compiler.source>11</maven.compiler.source>
        <maven.compiler.target>11</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring.version>5.3.25</spring.version>
    </properties>

    <dependencies>
        <!-- Spring Framework -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>${spring.version}</version>
        </dependency>
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <!-- MyBatis (iBatis) -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.11</version>
        </dependency>

        <!-- Log4j -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>1.2.17</version>
        </dependency>

        <!-- Servlet API -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>javax.servlet-api</artifactId>
            <version>4.0.1</version>
            <scope>provided</scope>
        </dependency>
        
        <!-- JSTL -->
        <dependency>
            <groupId>javax.servlet</groupId>
            <artifactId>jstl</artifactId>
            <version>1.2</version>
        </dependency>
    </dependencies>

    <build>
        <finalName>OpenLibertyApp</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-war-plugin</artifactId>
                <version>3.4.0</version>
                <configuration>
                    <failOnMissingWebXml>false</failOnMissingWebXml>
                </configuration>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>11</source>
                    <target>11</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
</project>
```

## Execution Steps

1. Create Maven directory structure
2. Create pom.xml file
3. Move source files to Maven structure
4. Move resources to Maven structure
5. Move web content to Maven structure
6. Test Maven build
7. Remove Ant build files after successful migration
