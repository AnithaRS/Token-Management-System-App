# 🎫 Token Management System

A full-stack **Queue / Token Management System** built for hospitals, service centers, and counters where customers need to wait in an organized queue instead of crowding around a desk. Customers generate a token, staff call the next token from an admin panel, and a live display screen shows what's currently being served — just like the systems you see in hospitals, banks, and mobile shops.

> 🎓 Final Year Project

---

## 📌 Problem Statement

When many people wait for service, businesses need a way to manage the queue in an organized manner. Without a token system:

- ❌ Confusion in the queue
- ❌ Crowding near the counter
- ❌ Unfair / unclear ordering

With a token system:

- ✅ Organized, first-come-first-served flow
- ✅ Faster service
- ✅ Clear, live display for customers

This pattern is used everywhere — **hospitals** ("Token 32 → Room 2"), **mobile shops** ("Token 14 → Counter 3"), **banks**, **government/passport offices**, and **billing counters**.

---

## 🧭 How It Works

```
Customer  →  Get Token (Patient Screen)
Staff     →  Call Next Token (Admin Panel)
Display   →  Shows currently-called tokens (TV screen)
```

```
      Patient
         │
         ▼
  Token Generator API
         │
         ▼
   MySQL Database
         │
         ▼
  Admin Panel (Call / Complete Token)
         │
         ▼
  Display Screen
```

---

## 🖥️ Screens

| Screen | Route | Purpose |
|---|---|---|
| **Patient Screen** | `/` | Patient taps **Get Token** and receives their token number |
| **Admin Panel** | `/admin` | Staff create tokens, call the next token for a counter, mark a token complete, and monitor live queue stats |
| **Display Screen** | `/display` | Public-facing screen (TV/monitor)  shows all currently-called tokens |

### Screenshots


**Customer Screen**
![Customer Screen](screenshots/customer.png)

**Token Generated**
![Token Generated](screenshots/customer-token.png)

**Admin Panel**
![Admin Panel](screenshots/admin.png)

**Display Screen**
![Display Screen](screenshots/display.png)

---

## 🏗️ Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 21, Spring Boot, Spring Data JPA, Spring Validation, Spring WebMVC |
| Database | MySQL |
| Frontend | Angular 22 (standalone components), TypeScript |
| Styling | Bootstrap, CSS |
| API Docs | springdoc-openapi (Swagger UI) |
| Build Tools | Maven (backend), Angular CLI / npm (frontend) |

---

## ✨ Features

- 🎟️ Generate a new sequential token for a customer
- 📋 View all currently waiting tokens
- 📢 Call the next waiting token to a specific counter
- ✅ Mark the currently-called token at a counter as completed
- 📺 Live display screen that auto-refreshes every 3 seconds
- 🧑‍💼 Admin dashboard with live stats: waiting count, currently-called count, selected counter
- ⚠️ Graceful error handling — e.g. can't call a next token at an inactive/busy counter
- 🔒 Backend uses pessimistic locking on token pickup to stay safe under concurrent "call next" requests

---

## 🔌 API Reference

Base URL: `http://localhost:9090/token-system/api`

| Method | Endpoint | Description |
|---|---|---|
| `POST` | `/tokens` | Create a new token (status: `WAITING`) |
| `GET` | `/tokens/waiting` | Get all tokens currently waiting |
| `GET` | `/display/current` | Get all tokens currently in `CALLED` status |
| `POST` | `/display/call-next` | Call the next waiting token to a counter — body: `{ "counterCode": "C1" }` |
| `POST` | `/display/complete` | Mark the current token at a counter as completed — body: `{ "counterCode": "C1" }` |
| `GET` | `/health` | Health check |

All responses are wrapped in a standard envelope:

```json
{
  "success": true,
  "message": "Token created successfully",
  "data": { "id": 4, "tokenNumber": 4, "status": "WAITING" }
}
```

Interactive Swagger docs are available at:
`http://localhost:9090/token-system/swagger-ui.html`

---

## 🗂️ Token Status Flow

```
WAITING  →  CALLED  →  COMPLETED
```

- **WAITING** — token created, sitting in the queue
- **CALLED** — staff called this token to a specific counter
- **COMPLETED** — service finished at the counter

---

## 📁 Project Structure

```
token-management-system/
├── backend/                      # Spring Boot API
│   └── src/main/java/com/hospital/
│       ├── Controller/           # REST controllers
│       ├── serviceImpl/          # Service layer
│       ├── repo/                 # JPA repositories
│       ├── entity/                # TokenQueue, TokenCounter
│       ├── enums/                # TokenStatus
│       ├── DtoRequest/           # Request DTOs
│       ├── DtoResponse/          # Response DTOs
│       ├── Mapper/               # Entity → DTO mapping
│       ├── Exception/            # Custom exceptions & handler
│       └── commom/                # ApiResponse wrapper, BaseEntity
│
└── frontend/                     # Angular application
    └── src/app/
        ├── Patient/             # Patient "Get Token" screen
        ├── admin/                # Admin panel
        ├── display/              # Live display screen
        └── services/             # TokenService (API calls)
```

---

## 🚀 Getting Started

### Prerequisites

- Java 21+
- Maven
- Node.js & npm
- MySQL running locally

### 1. Backend Setup

```bash
cd backend

# Create the database
mysql -u root -p -e "CREATE DATABASE token_display_db;"
```

Update `src/main/resources/application.yaml` with your MySQL credentials if needed:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/token_display_db?useSSL=false&serverTimezone=Asia/Kolkata&allowPublicKeyRetrieval=true
    username: root
    password: your_password
```

Run the backend:

```bash
./mvnw spring-boot:run
```

Backend runs at: `http://localhost:9090/token-system`

> ⚠️ Note: This project currently uses `ddl-auto: update`, so tables are auto-created on startup. Counters (`TokenCounter` rows, e.g. `C1`, `C2`, `C3`) need to be inserted manually into the database before "Call Next Token" will work, since there's no counter-creation endpoint yet.

### 2. Frontend Setup

```bash
cd frontend
npm install
ng serve
```

Frontend runs at: `http://localhost:4200`

- `/` — Patient screen
- `/admin` — Admin panel
- `/display` — Display screen

---

## 🔮 Future Enhancements

- [ ] Admin endpoint to create/manage counters (currently seeded manually in the DB)
- [ ] Reset all tokens (end-of-day reset)
- [ ] Authentication for the admin panel
- [ ] Replace 3-second polling with WebSocket-based live updates (`spring-boot-starter-websocket` is already included as a dependency)
- [ ] Audio/voice announcement on the display screen when a new token is called
- [ ] Multi-department / multi-branch support

--
🎓 Final Year Project
