# RESTful Banking API

A robust, backend-focused RESTful web application built using Spring Boot, Spring Data JPA, and MySQL. This application manages core banking operations, prioritizing strict server-side business logic, clean data encapsulation, and transactional integrity.

## 🛠️ Tech Stack & Dependencies

* **Java 17**
* **Spring Boot** (Web & Data JPA)
* **MySQL Database** (Production-ready relational storage)
* **Maven** (Dependency management)
* **Tools & IDE:** Spring Tool Suite (STS), Postman (API Testing)

### Project Structure

```text
src/main/java/in/pa/bankingApp/
│
├── controllers/
│   └── AccountControllers.java     # REST Controller handling all API endpoints
│
├── Dto/
│   ├── Deposit.java               # Data Transfer Object for deposit requests
│   ├── Transfer.java              # Data Transfer Object for fund transfer requests
│   └── Withdraw.java              # Data Transfer Object for withdrawal requests
│
├── entity/
│   └── Account.java               # JPA Entity mapping the account details to the database
│
├── repository/
│   └── AccountRepository.java     # Spring Data JPA Repository interface for CRUD operations
│
└── service/
    ├── AccountService.java        # Core business logic interface
    └── AccountServiceImpl.java    # Service implementation containing banking rule validations
```

## 🚀 Key Features & Business Logic

* **Automated Account Number Generation**: Utilizes a database sequence generator to issue unique 5-digit account numbers starting from `50501`.
* **Data Transfer Objects (DTOs)**: Decouples internal entities from incoming API data contracts using optimized request payloads for Deposit, Withdraw, and Transfer operations.
* **Fund Transfer Engine**: Safely processes inter-account transfers, updating balances dynamically across sender and receiver records.
* **Strict Validation & Overdraft Control**: Validates bank account funds prior to executing any debit or withdrawal operations to prevent overdraft states.
* **Secure Account Deletion**: Requires a combination of account number and PAN card verification before permanently closing accounts.
## 🛣️ API Endpoints

All endpoints are prefixed with `/api`.


### 1. Account Management Endpoints

| HTTP Method | Endpoint | Description | Request Body | Response Type / Success Code |
| :--- | :--- | :--- | :--- | :--- |
| **POST** | `/api/create` | Creates and saves a new bank account. | `Account` JSON object | `Account` object (`201 CREATED`) |
| **GET** | `/api/accounts` | Fetches a list of all registered accounts. | *None* | `List<Account>` (`200 OK`) |
| **GET** | `/api/account/{accNum}` | Retrieves details for a specific account using its number. | *None* | `Account` object (`200 OK`) or `404 NOT FOUND` |
| **PUT** | `/api/update/{accNum}` | Updates the details of an existing account. | `Account` JSON object | `Account` object (`200 OK`) or `404 NOT FOUND` |
| **DELETE** | `/api/close/{accNum}/{panNum}` | Closes and removes an account if account and PAN numbers match. | *None* | `String` message (`200 OK`) |

---

### 2. Transaction Endpoints

| HTTP Method | Endpoint | Description | Request Body | Response Type / Success Code |
| :--- | :--- | :--- | :--- | :--- |
| **PUT** | `/api/deposit` | Deposits money into a specified account. | `Deposit` JSON object | `Double` (Updated Balance) (`200 OK`) or `404 NOT FOUND` |
| **PUT** | `/api/withdrawn` | Withdraws money from a specified account. | `Withdraw` JSON object | `Double` (Updated Balance) (`200 OK`) or `404 NOT FOUND` |
| **PUT** | `/api/transfer` | Transfers funds between a sender and a receiver account. | `Transfer` JSON object | `String` message (`200 OK`) |

## 🧪 API Testing with Postman

The endpoints can be fully tested using Postman. Since the application leverages optimized Data Transfer Objects (DTOs), ensure your request headers are set to `Content-Type: application/json` and use the following sample payloads:

### 1. Account Management Endpoints

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
### 2. Fetch Single Account details
* **HTTP Method:** GET
* **URL:** `http://localhost:8050/api/account/50501`
* **Path Variable:** Replace 50501 with the specific account number you want to look up.
* **Body:** None

### 3. Fetch All Accounts Details
* **HTTP Method:** GET
* **URL:** `http://localhost:8050/api/accounts`
* **Body:** None

### 4. Update Account Details
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/update/50501`
* **Path Variable:** Replace 50501 with the target account number.
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
### 5. Delete Account
* **HTTP Method:** DELETE
* **URL:** `http://localhost:8050/api/delete/50501/ABCDE1234F`
* **Path Variables:** `/close/{accNum}/{panNum}`
* **Body:** None

### 2. Transaction Endpoints

### 1. Deposit Funds
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/deposit`
* **Body (JSON):**
```json
{
  "accNum": 50501,
  "depositAmt": 5000.00
}
```
### 2. Withdraw Funds
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/withdrawn`
* **Body (JSON):**
```json
{
  "accNum": 50501,
  "withdrawAmt": 1500.00
}
```

### 3. Fund Transfer (Inter-Account)
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/transfer`
* **Body (JSON):**
```json
{
  "fromAccNum": 50502,
  "debitAmt": 2000.00,
  "toAccNum": 50503,
  "creditAmt": 2000.00
}
```
## 🔮 What's Next (Future Roadmap)

To bring this application closer to a production-ready enterprise system, the following features are planned for implementation:

* **Global Exception Handling**: Implementing a centralized @ControllerAdvice layer to cleanly intercept runtime exceptions (like insufficient funds or account mismatches) and return standardized, user-friendly error responses (e.g., 400 Bad Request or 404 Not Found) instead of raw stack traces.

