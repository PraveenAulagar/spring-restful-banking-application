# RESTful Banking API

A robust, backend-focused RESTful web application built using Spring Boot, Spring Data JPA, and MySQL. This application manages core banking operations, prioritizing strict server-side business logic, clean data encapsulation, and transactional integrity.

## 🛠️ Tech Stack & Dependencies

* **Java 17**
* **Spring Boot** (Web & Data JPA)
* **MySQL Database** (Production-ready relational storage)
* **Maven** (Dependency management)
* **Tools & IDE:** Spring Tool Suite (STS), Postman (API Testing)

## 🏗️ Architecture & Component Design

The project follows a decoupled layered architecture using Data Transfer Objects (DTOs) to safely pass data between the client and the controller layers:

```plaintext
in.pa.bankingApplication
├── controller   # Exposes REST endpoints and processes client interactions(Get, Post, Put, Delete)
├── dto          # Enforces clean request contracts (Deposit, Withdraw, Transfer payloads).
├── entities     # Maps database schemas (AccountDetails) via JPA annotations.
├── repository   # Handles direct database CRUD operations using JpaRepository.
└── service      # Implements core transactional banking logic and safety checks.
```

## 🚀 Key Features & Business Logic

* **Automated Account Number Generation**: Utilizes a database sequence generator to issue unique 9-digit account numbers starting from `500500001`.
* **Data Transfer Objects (DTOs)**: Decouples internal entities from incoming API data contracts using optimized request payloads for Deposit, Withdraw, and Transfer operations.
* **Fund Transfer Engine**: Safely processes inter-account transfers, updating balances dynamically across sender and receiver records.
* **Strict Validation & Overdraft Control**: Validates bank account funds prior to executing any debit or withdrawal operations to prevent overdraft states.
* **Secure Account Deletion**: Requires a combination of account number and PAN card verification before permanently closing accounts.
## 🛣️ API Endpoints

All endpoints are prefixed with `/api`.


| HTTP Method | Endpoint | Description | Payload / Path Variables |
| :--- | :--- | :--- | :--- |
| **POST** | `/api/create` | Opens a new bank account | `AccountDetails` (JSON) |
| **GET** | `/api/fetchAll` | Retrieves all registered accounts | None |
| **GET** | `/api/fetchOne/{accountNumber}` | Retrieves details for a specific account | `accountNumber` |
| **PUT** | `/api/update/{accountNumber}` | Updates profile details for an account | `accountNumber`, `AccountDetails` (JSON) |
| **PUT** | `/api/deposit` | Deposits cash into an account | `Deposit` (DTO JSON) |
| **PUT** | `/api/withDraw` | Withdraws cash from an account | `Withdraw` (DTO JSON) |
| **PUT** | `/api/transfer` | Transfers funds between two accounts | `Transfer` (DTO JSON) |
| **DELETE**| `/api/delete/{accountNumber}/{panNumber}` | Permanently closes an account | `accountNumber`, `panNumber` |

## 🧪 API Testing with Postman

The endpoints can be fully tested using Postman. Since the application leverages optimized Data Transfer Objects (DTOs), ensure your request headers are set to `Content-Type: application/json` and use the following sample payloads:

### 1. Create Account
* **HTTP Method:** POST
* **URL:** `http://localhost:8050/api/create`
* **Body (JSON):**
```json
{
  "firstName": "Praveen",
  "address": "Nasik",
  "dob": "1989/05/15",
  "accOpeningdate": "2026/06/01",
  "mobileNumber": 9876543210,
  "email": "praveen@email.com",
  "aadharNumber": "1234-5678-9012",
  "panNumber": "ABCDE1234F",
  "balance": 50000.00
}
```

### 2. Update Account Details
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/update/500500001`
* **Path Variable:** Replace 500500001 with the target account number.
* **Body Type:** JSON (raw)
* **Sample Request Body:**
```json
{
  "firstName": "Praveen",
  "address": "Mumbai",
  "dob": "1989/05/15",
  "accOpeningdate": "2026/06/01",
  "mobileNumber": 9876543210,
  "email": "praveen@email.com",
  "aadharNumber": "1234-5678-9012",
  "panNumber": "ABCDE1234F",
  "balance": 50000.00
}
```

### 3. Deposit Funds
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/deposit`
* **Body (JSON):**
```json
{
  "accNum": 500500001,
  "depositAmt": 5000.00
}
```

### 4. Withdraw Funds
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/withDraw`
* **Body (JSON):**
```json
{
  "accNum": 500500001,
  "withdrawAmt": 1500.00
}
```

### 5. Fund Transfer (Inter-Account)
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/transfer`
* **Body (JSON):**
```json
{
  "fromAccNum": 500500001,
  "debitAmt": 2000.00,
  "toAccNum": 500500002,
  "creditAmt": 2000.00
}
```

### 6. Fetch Single Customer
* **HTTP Method:** GET
* **URL:** `http://localhost:8050/api/fetchOne/500500001`
* **Path Variable:** Replace 500500001 with the specific account number you want to look up.
* **Body:** None

### 7. Fetch All Customer Details
* **HTTP Method:** GET
* **URL:** `http://localhost:8050/api/fetchAll`
* **Body:** None

### 8. Delete Account
* **HTTP Method:** DELETE
* **URL:** `http://localhost:8050/api/delete/500500001/ABCDE1234F`
* **Path Variables:** `/delete/{accountNumber}/{panNumber}`
* **Body:** None

## 🔮 What's Next (Future Roadmap)

To bring this application closer to a production-ready enterprise system, the following features are planned for implementation:

* **Global Exception Handling**: Implementing a centralized @ControllerAdvice layer to cleanly intercept runtime exceptions (like insufficient funds or account mismatches) and return standardized, user-friendly error responses (e.g., 400 Bad Request or 404 Not Found) instead of raw stack traces.

