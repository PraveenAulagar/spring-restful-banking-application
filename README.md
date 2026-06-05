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
    "name":"pravin",
    "address":"Navi mumbai",
    "mobileNo":1122334455,
    "email":"pn@gmail.com",
    "dob":"1988/01/01",
    "aadharNum":"123409876432",
    "panNum":"ABC1234GH",
    "accOpeningDate":"2026/04/01",
    "balance":5000
}
```
![image alt](https://github.com/PraveenAulagar/spring-restful-banking-application/blob/d334c3a8844950957ee25160eb1bf7a2a742101d/spring_restFul_bankingApplication/screenshots/Screenshot%202026-06-05%20184212.png)
### 2. Fetch Single Account details
* **HTTP Method:** GET
* **URL:** `http://localhost:8050/api/account/50501`
* **Path Variable:** Replace 50501 with the specific account number you want to look up.
* **Body:** None
![image alt](https://github.com/PraveenAulagar/spring-restful-banking-application/blob/d715c0a6a31cafa9e0918ad9b04af45910c944d4/spring_restFul_bankingApplication/screenshots/Screenshot%202026-06-03%20185937.png)
### 3. Fetch All Accounts Details
* **HTTP Method:** GET
* **URL:** `http://localhost:8050/api/accounts`
* **Body:** None
![image alt](https://github.com/PraveenAulagar/spring-restful-banking-application/blob/524f14bd9618c35ab74cccf4b02eec22a712d398/spring_restFul_bankingApplication/screenshots/Screenshot%202026-06-05%20182947.png)
### 4. Update Account Details
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/update/50501`
* **Path Variable:** Replace 50501 with the target account number.
* **Body Type:** JSON (raw)
* **Sample Request Body:**
```json
{   "accNum":50501,
    "name":"praveen",
    "address":"nashik",
    "mobileNo":1234567890,
    "email":"p@gmail.com",
    "dob":"1988/09/09",
    "aadharNum":"123409876521",
    "panNum":"ABC1234XY",
    "accOpeningDate":"2026/01/01",
    "balance":5000
}
```
### name updated from pravin to praveen
![image alt](https://github.com/PraveenAulagar/spring-restful-banking-application/blob/5ab961a5e974fc2697b5731992b08f6e0bce1fa1/spring_restFul_bankingApplication/screenshots/Screenshot%202026-06-05%20181243.png)
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
![image alt](https://github.com/PraveenAulagar/spring-restful-banking-application/blob/99e862ec75ab3fdc103d264b10713f7352af2484/spring_restFul_bankingApplication/screenshots/Screenshot%202026-06-05%20173425.png)
### 2. Withdraw Funds
* **HTTP Method:** PUT
* **URL:** `http://localhost:8050/api/withdrawn`
* **Body (JSON):**
```json
{
  "accNum": 50501,
  "withdrawAmt": 2200.00
}
```
![image alt](https://github.com/PraveenAulagar/spring-restful-banking-application/blob/e6e669d222baf1884da498a7f083de97d003f0d7/spring_restFul_bankingApplication/screenshots/Screenshot%202026-06-05%20173806.png)
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

