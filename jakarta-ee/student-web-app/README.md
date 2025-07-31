# Open Liberty Ant Project

A Java EE web application running on Open Liberty with a hybrid architecture that supports both traditional servlets and Spring MVC. The application manages student profiles with CRUD operations and demonstrates migrating from Ant to Maven and Java EE to Jakarta EE. 

## Project Architecture

See [project details](doc/architecture.md).

## Getting Started

### Prerequisites
- **Java 11** or higher
- **Apache Ant** (for building)
- **Docker & Docker Compose** (recommended)

### Quick Start (Docker - Recommended)

1. **Clone and navigate to the project:**
   ```bash
   cd student-web-app-ant-springframework
   ```

2. **Run setup script:**
   ```bash
   # Linux/Mac
   ./setup-docker.sh
   
   # Windows
   setup-docker.bat
   ```

3. **Start the application:**
   ```bash
   docker-compose up --build
   ```

4. **Access the application:**
   - Home page: http://localhost:9080/

### Manual Setup (Without Docker)

See [maunual setup](doc/manual-setup.md).

### Verification

Test the application with:
```bash
# Add a student
curl -X POST "http://localhost:9080/app/add-student" \
  -d "name=Alice Johnson" \
  -d "email=alice@example.com" \
  -d "major=Biology"

# View students
curl http://localhost:9080/studentProfileList
# or
# curl http://localhost:9080/app/students
```

**Student Profiles List View:**
Access the pages from browser: http://localhost:9080/

<div align="left">
  <img src="assets/student_profiles_list.png" alt="Student Profiles List" width="50%"/>
</div>



