# Task Management API

A comprehensive REST API built with Spring Boot showcasing modern Java development practices and enterprise-level features.

## 🚀 Features

### ✅ Core API Structure
- **RESTful endpoints** for full CRUD operations
- **Service layer** separation for business logic
- **Repository pattern** for data access
- **Global exception handling** with structured error responses

### ✅ Data Validation & Conversion
- **Bean Validation** with custom error messages
- **DTOs** for request/response data transfer
- **Mapper classes** for entity-DTO conversion
- **Input sanitization** and comprehensive field validation

### ✅ External API Integration
- **WebClient** for REST client calls
- **Notification service** integration with JSONPlaceholder
- **Async processing** for non-blocking operations
- **Error handling** for external service failures

### ✅ Database Integration
- **JPA/Hibernate** for ORM
- **Custom repository methods** for complex queries
- **H2 in-memory database** for development
- **Automatic schema generation**

## 🛠️ Technical Stack

- **Java 17+** - Modern Java features
- **Spring Boot 3.2** - Framework
- **Spring Data JPA** - Database operations
- **Spring WebFlux** - REST client
- **Bean Validation** - Input validation
- **H2 Database** - In-memory database
- **Maven** - Build tool

## 📋 API Endpoints

### Task Management
```
GET    /api/tasks              - Get all tasks
GET    /api/tasks/{id}         - Get specific task
GET    /api/tasks/completed    - Get completed tasks
GET    /api/tasks/pending      - Get pending tasks
GET    /api/tasks/search       - Search tasks by title
POST   /api/tasks              - Create new task
PUT    /api/tasks/{id}         - Update existing task
DELETE /api/tasks/{id}         - Delete task
```

## 🚀 Getting Started

### Prerequisites
- Java 17 or higher
- Maven 3.6+
- Git

### Installation & Running
```bash
# Clone the repository
git clone https://github.com/[your-username]/task-management-api.git
cd task-management-api

# Run the application
mvn spring-boot:run

# Or build and run JAR
mvn clean package
java -jar target/task-management-api-1.0.0.jar
```

The API will be available at: `http://localhost:8080`

### Database Console
H2 Database console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (empty)

## 📝 API Usage Examples

### Create a Task
```bash
curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Spring Boot",
    "description": "Build a comprehensive REST API",
    "dueDate": "2024-12-31T23:59:59"
  }'
```

### Get All Tasks
```bash
curl http://localhost:8080/api/tasks
```

### Search Tasks
```bash
curl "http://localhost:8080/api/tasks/search?title=Spring"
```

### Update Task
```bash
curl -X PUT http://localhost:8080/api/tasks/1 \
  -H "Content-Type: application/json" \
  -d '{
    "title": "Learn Spring Boot - Updated",
    "description": "Build a comprehensive REST API with validation",
    "isCompleted": true
  }'
```

## 🏗️ Project Structure

```
src/main/java/com/example/taskmanagement/
├── controller/          # REST endpoints
├── service/            # Business logic
├── repository/         # Data access layer
├── model/             # JPA entities
├── dto/               # Data transfer objects
├── mapper/            # Entity-DTO conversion
├── exception/         # Global error handling
└── TaskManagementApplication.java
```

## 🧪 Testing

```bash
# Run all tests
mvn test

# Run with coverage
mvn test jacoco:report
```

## 🔧 Configuration

Key configuration in `application.properties`:
```properties
# Database
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop

# External API
notification.service.url=https://jsonplaceholder.typicode.com

# Server
server.port=8080
```

## 🌟 Key Learning Demonstrations

### For Technical Interviews:
1. **Layered Architecture** - Clean separation of concerns
2. **Data Validation** - Comprehensive input validation with custom messages
3. **External Integration** - REST client implementation with error handling
4. **Database Design** - JPA relationships and custom queries
5. **Error Handling** - Global exception handling with structured responses
6. **Modern Spring Boot** - Latest features and best practices

### Code Quality Features:
- Comprehensive validation with meaningful error messages
- Proper exception handling and logging
- Clean code structure with separation of concerns
- RESTful API design principles
- External service integration patterns

## 🚀 Deployment Ready

This application is configured for easy deployment to:
- **Heroku** - Include Procfile
- **AWS** - Docker ready
- **Railway** - Zero config deployment
- **Local** - H2 database for development

## 🔮 Future Enhancements

- [ ] JWT Authentication & Authorization
- [ ] PostgreSQL integration
- [ ] Unit & Integration tests
- [ ] Docker containerization
- [ ] CI/CD pipeline
- [ ] API documentation with Swagger
- [ ] Caching with Redis
- [ ] Metrics and monitoring

## 📄 License

This project is licensed under the MIT License - see the LICENSE file for details.

## 🤝 Contributing

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/amazing-feature`)
3. Commit your changes (`git commit -m 'Add amazing feature'`)
4. Push to the branch (`git push origin feature/amazing-feature`)
5. Open a Pull Request

---

**Built with ❤️ using Spring Boot**