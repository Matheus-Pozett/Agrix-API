# Agrix: Farm Management API

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0.0-brightgreen)](https://spring.io/projects/spring-boot)
[![Maven](https://img.shields.io/badge/Maven-3.8-red)](https://maven.apache.org/)
[![Docker](https://img.shields.io/badge/Docker-blue)](https://www.docker.com/)

An API for managing farms, crops, and fertilizers, including user authentication and role-based authorization.

## 📖 About the Project

Agrix is a farm management system designed to improve efficiency in crop cultivation, reduce resource waste, and promote responsible land use. This API serves as the backend for the Agrix platform, allowing users to manage farm data, track crops and their life cycles, and manage fertilizer associations. The system also includes a robust security layer with authentication and role-based access control.

## ✨ Features

- **User Management**:
    - User registration with different roles (USER, MANAGER, ADMIN).
    - JWT-based authentication.
- **Farm Management**:
    - CRUD operations for farms.
- **Crop Management**:
    - CRUD operations for crops.
    - Associate crops with farms.
    - Track planting and harvesting dates.
    - Search for crops by harvest date.
- **Fertilizer Management**:
    - CRUD operations for fertilizers.
    - Associate fertilizers with crops.
- **Role-Based Access Control**:
    - Different levels of access to endpoints based on user roles (USER, MANAGER, ADMIN).

## 🛠️ Technologies Used

- **Backend**:
    - [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
    - [Spring Boot 3](https://spring.io/projects/spring-boot)
    - [Spring Web](https://docs.spring.io/spring-framework/docs/current/reference/html/web.html)
    - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
    - [Spring Security](https://spring.io/projects/spring-security)
- **Database**:
    - [H2 Database](https://www.h2database.com/html/main.html) (for testing)
    - [MySQL](https://www.mysql.com/)
- **Build & Dependency Management**:
    - [Apache Maven](https://maven.apache.org/)
- **Containerization**:
    - [Docker](https://www.docker.com/)

## 🚀 How to Run

### Prerequisites

- [Java 17](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
- [Apache Maven](https://maven.apache.org/download.cgi)
- [Docker](https://www.docker.com/get-started) (optional, for containerized execution)

### From Source

1.  **Clone the repository:**
    ```bash
    git clone git@github.com:Matheus-Pozett/Agrix-API.git
    cd Agrix-API
    ```

2.  **Install dependencies:**
    ```bash
    mvn install
    ```

3.  **Run the application:**
    ```bash
    mvn spring-boot:run
    ```
    The application will be available at `http://localhost:8080`.

### Using Docker

1.  **Build the Docker image:**
    ```bash
    docker build -t agrix-api .
    ```

2.  **Run the Docker container:**
    ```bash
    docker run -p 8080:8080 agrix-api
    ```
    The application will be available at `http://localhost:8080`.

## 🧪 How to Run Tests

To run the automated tests, execute the following command in the project's root directory:

```bash
mvn test
```

To run a specific test class:

```bash
mvn test -Dtest="TestClassName"
```

## 🔌 API Endpoints

### Authentication

#### `POST /auth/login`
Authenticates a user and returns a JWT token.

- **Request Body:**
  ```json
  {
    "username": "zerocool",
    "password": "supersecretpassword"
  }
  ```

- **Success Response (200 OK):**
  ```json
  {
    "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
  }
  ```

- **Error Response (403 Forbidden):** If credentials are invalid.

---

### Persons

#### `POST /persons`
Creates a new user.

- **Request Body:**
  ```json
  {
    "username": "newuser",
    "password": "newpassword",
    "role": "USER"
  }
  ```

- **Success Response (201 Created):**
  ```json
  {
    "id": 1,
    "username": "newuser",
    "role": "USER"
  }
  ```

---

### Farms

#### `POST /farms`
Creates a new farm.
- **Required Role**: Authenticated User
- **Request Body:**
  ```json
  {
    "name": "Green Valley Farm",
    "size": 150.5
  }
  ```
- **Success Response (201 Created):**
  ```json
  {
    "id": 1,
    "name": "Green Valley Farm",
    "size": 150.5
  }
  ```

#### `GET /farms`
Returns all farms.
- **Required Role**: `USER`, `MANAGER`, or `ADMIN`
- **Success Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "name": "Green Valley Farm",
      "size": 150.5
    }
  ]
  ```

#### `GET /farms/{id}`
Returns a specific farm by its ID.
- **Required Role**: Authenticated User
- **Success Response (200 OK):**
  ```json
  {
    "id": 1,
    "name": "Green Valley Farm",
    "size": 150.5
  }
  ```
- **Error Response (404 Not Found):** If the farm is not found.

---

### Crops

#### `POST /farms/{farmId}/crops`
Creates a new crop for a specific farm.
- **Required Role**: Authenticated User
- **Request Body:**
  ```json
  {
    "name": "Tomato",
    "plantedArea": 25.0,
    "plantedDate": "2023-01-15",
    "harvestDate": "2023-07-20"
  }
  ```
- **Success Response (201 Created):**
  ```json
  {
    "id": 1,
    "name": "Tomato",
    "plantedArea": 25.0,
    "farmId": 1,
    "plantedDate": "2023-01-15",
    "harvestDate": "2023-07-20"
  }
  ```
- **Error Response (404 Not Found):** If the farm is not found.

#### `GET /farms/{farmId}/crops`
Returns all crops for a specific farm.
- **Required Role**: Authenticated User
- **Success Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "name": "Tomato",
      "plantedArea": 25.0,
      "farmId": 1,
      "plantedDate": "2023-01-15",
      "harvestDate": "2023-07-20"
    }
  ]
  ```
- **Error Response (404 Not Found):** If the farm is not found.

#### `GET /crops`
Returns all crops.
- **Required Role**: `MANAGER` or `ADMIN`
- **Success Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "name": "Tomato",
      "plantedArea": 25.0,
      "farmId": 1,
      "plantedDate": "2023-01-15",
      "harvestDate": "2023-07-20"
    }
  ]
  ```

#### `GET /crops/{id}`
Returns a specific crop by its ID.
- **Required Role**: Authenticated User
- **Success Response (200 OK):**
  ```json
  {
    "id": 1,
    "name": "Tomato",
    "plantedArea": 25.0,
    "farmId": 1,
    "plantedDate": "2023-01-15",
    "harvestDate": "2023-07-20"
  }
  ```
- **Error Response (404 Not Found):** If the crop is not found.

#### `GET /crops/search`
Searches for crops within a harvest date range.
- **Required Role**: Authenticated User
- **Query Parameters**: `start` (YYYY-MM-DD), `end` (YYYY-MM-DD)
- **Example**: `/crops/search?start=2023-01-01&end=2023-12-31`
- **Success Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "name": "Tomato",
      "plantedArea": 25.0,
      "farmId": 1,
      "plantedDate": "2023-01-15",
      "harvestDate": "2023-07-20"
    }
  ]
  ```

---

### Fertilizers

#### `POST /fertilizers`
Creates a new fertilizer.
- **Required Role**: Authenticated User
- **Request Body:**
  ```json
  {
    "name": "SuperGrow",
    "brand": "AgriCorp",
    "composition": "Nitrogen, Phosphorus, Potassium"
  }
  ```
- **Success Response (201 Created):**
  ```json
  {
    "id": 1,
    "name": "SuperGrow",
    "brand": "AgriCorp",
    "composition": "Nitrogen, Phosphorus, Potassium"
  }
  ```

#### `GET /fertilizers`
Returns all fertilizers.
- **Required Role**: `ADMIN`
- **Success Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "name": "SuperGrow",
      "brand": "AgriCorp",
      "composition": "Nitrogen, Phosphorus, Potassium"
    }
  ]
  ```

#### `GET /fertilizers/{id}`
Returns a specific fertilizer by its ID.
- **Required Role**: Authenticated User
- **Success Response (200 OK):**
  ```json
  {
    "id": 1,
    "name": "SuperGrow",
    "brand": "AgriCorp",
    "composition": "Nitrogen, Phosphorus, Potassium"
  }
  ```
- **Error Response (404 Not Found):** If the fertilizer is not found.

#### `POST /crops/{cropId}/fertilizers/{fertilizerId}`
Associates a fertilizer with a crop.
- **Required Role**: Authenticated User
- **Success Response (201 Created):**
  ```
  Fertilizer and crop associated successfully!
  ```
- **Error Response (404 Not Found):** If the crop or fertilizer is not found.

#### `GET /crops/{cropId}/fertilizers`
Returns all fertilizers associated with a specific crop.
- **Required Role**: Authenticated User
- **Success Response (200 OK):**
  ```json
  [
    {
      "id": 1,
      "name": "SuperGrow",
      "brand": "AgriCorp",
      "composition": "Nitrogen, Phosphorus, Potassium"
    }
  ]
  ```
- **Error Response (404 Not Found):** If the crop is not found.
