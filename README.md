# EducationFinder

_The service serves as a proxy to a third party database and provides information and statistics for courses and educations offered within a municipality._

## Getting Started

### Prerequisites

- **Java 21 or higher**
- **Maven**
- **Git**

### Installation

1. **Clone the repository:**

```bash
git clone https://github.com/Sundsvallskommun/api-service-education-finder.git
cd api-service-education-finder
```

2. **Configure the application:**

   Before running the application, you need to set up configuration settings.
   See [Configuration](#configuration)

   **Note:** Ensure all required configurations are set; otherwise, the application may fail to start.

3. **Ensure dependent services are running:**

   This microservice does not depend on any other services but has a dependency to a third party MicroSoft SQL database.

4. **Build and run the application:**

- Using Maven:

```bash
mvn spring-boot:run
```

- Using Gradle:

```bash
gradle bootRun
```

## Dependencies

The microservice depends on the following database integrations:

- ** MS SQL database**
  - **Purpose:** Database where information and statistics for offered educations are stored
  - **Repository:** External database managed by third party

Ensure that the database used by this microservice is properly configured and running before starting this microservice.

## API Documentation

Access the API documentation via:

- **Swagger UI:** [http://localhost:8080/api-docs](http://localhost:8080/api-docs)

## Usage

### API Endpoints

See the [API Documentation](#api-documentation) for detailed information on available endpoints.

### Example Request

```bash
curl -X 'GET' 'http://localhost:8080/2281/courses?startAfter=2025-07-16&studyLocations=Sundsvall'
```

## Configuration

Configuration is crucial for the application to run successfully. Ensure all necessary settings are configured in `application.yml`.

### Key Configuration Parameters

- **Server Port:**

```yaml
server:
  port: 8080
```

- **Database Settings**

```yaml
spring:
  datasource:
    url: jdbc:sqlserver://<database>
    username:<username>
    password:<password>
  cache:
    cache-names:
      - course-filters
      - subject-filters
```

### Additional Notes

- **Application Profiles:**

  Use Spring profiles (`dev`, `prod`, etc.) to manage different configurations for different environments.

- **Logging Configuration:**

  Adjust logging levels if necessary.

## Contributing

Contributions are welcome! Please see [CONTRIBUTING.md](https://github.com/Sundsvallskommun/.github/blob/main/.github/CONTRIBUTING.md) for guidelines.

## License

This project is licensed under the [MIT License](LICENSE).

## Status

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-education-finder&metric=alert_status)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-education-finder)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-education-finder&metric=reliability_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-education-finder)
[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-education-finder&metric=security_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-education-finder)
[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-education-finder&metric=sqale_rating)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-education-finder)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-education-finder&metric=vulnerabilities)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-education-finder)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=Sundsvallskommun_api-service-education-finder&metric=bugs)](https://sonarcloud.io/summary/overall?id=Sundsvallskommun_api-service-education-finder)

## 

&copy; 2023 Sundsvalls kommun
