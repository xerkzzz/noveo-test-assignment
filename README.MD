# Spring Boot Project

Test task for Java/Kotlin Noveo.
This project uses Spring Boot, Java 17, and Gradle to create a RESTful service.
It includes two main endpoints for handling purchases and price calculations.

## Technology Stack

- **Spring Boot**: Framework for simplifying Java application development.
- **Java 17**: Java version used for the project.
- **Gradle**: Build automation system used for dependency management and project builds.
- **Testcontainers**: Library for providing lightweight, disposable containers for testing.
- **PostgreSQL 15.5**: Database used by the project.

## Setup and Running

### 1. Clone the Project

To get started, clone the project from GitHub:

```bash
git clone https://github.com/xerkzzz/noveo-test-assignment
cd noveo-test-assignment
```

### 2. Build the Project

To build the project, run the following command:

```bash
./gradlew clean bootJar
```

### 3. Running with Docker

To run the application using Docker and Docker Compose, follow these steps:

1. Start the containers:

    ```bash
    docker-compose up -d
    ```

2. Stop and remove the containers:

    ```bash
    docker-compose down
    ```

### Default Products

```yaml
app:
  productsToInit:
    - name: "Iphone"
      price: 100.0
    - name: "Headphones"
      price: 20.0
    - name: "Case"
      price: 10.0
    - name: "Patek Philippe Grandmaster Chime"
      price: 31194000.0
```

### Default Coupons

```yaml
app:
  couponsToInit:
    - code: "D15"
      discount: 15.0
      coupon-type: D
    - code: "D40"
      discount: 40.0
      coupon-type: D
    - code: "P10"
      discount: 20.0
      coupon-type: P
    - code: "P100"
      discount: 100.0
      coupon-type: P
```

### Default Taxes

```yaml
app:
  taxesToInit:
    - taxPercent: 19.0
      regex-pattern: "^DE\\d{9}$"
      country-code: DE
    - taxPercent: 22.0
      regex-pattern: "^IT\\d{11}$"
      country-code: IT
    - taxPercent: 24.0
      regex-pattern: "^GR\\d{9}$"
      country-code: GR
    - taxPercent: 20.0
      regex-pattern: "^FR[A-Z]{2}\\d{9}$"
      country-code: FR
```

## API Usage

### Endpoints

#### 1. Create Purchase

- **URL**: `http://127.0.0.1:8337/purchase`
- **Method**: `POST`
- **Headers**:
    - `Content-Type: application/json`
- **Request Body**:

  ```json
  {
      "product": 1,
      "taxNumber": "IT12345678900",
      "couponCode": "D15",
      "paymentProcessor": "paypal"
  }
  ```

Example usage with `curl`:

```bash
curl --location 'http://127.0.0.1:8337/purchase' \
--header 'Content-Type: application/json' \
--data '{
    "product": 1,
    "taxNumber": "IT12345678900",
    "couponCode": "D15",
    "paymentProcessor": "paypal"
}'
```

#### 2. Calculate Price

- **URL**: `http://127.0.0.1:8337/calculate-price`
- **Method**: `POST`
- **Headers**:
    - `Content-Type: application/json`
- **Request Body**:

  ```json
  {
      "product": 1,
      "taxNumber": "DE123456789",
      "couponCode": "D40"
  }
  ```

Example usage with `curl`:

```bash
curl --location 'http://127.0.0.1:8337/calculate-price' \
--header 'Content-Type: application/json' \
--data '{
    "product": 1,
    "taxNumber": "DE123456789",
    "couponCode": "D40"
}'
```