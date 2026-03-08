# 🚌 Voyago — Bus Ticket Booking System

A full-stack bus ticket booking platform built with **Spring Boot** (backend) and **React + Vite** (frontend), featuring real-time seat locking, JWT-based authentication, payment processing, and admin management.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Tech Stack](#tech-stack)
- [Architecture](#architecture)
- [Database Schema](#database-schema)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [API Endpoints](#api-endpoints)
- [Features](#features)
- [Environment Variables](#environment-variables)
- [Deployment](#deployment)
- [License](#license)

---

## Overview

Voyago is a comprehensive bus booking system that allows users to search trips, select seats in real time, make payments, and manage bookings — while giving admins full control over buses, routes, and trips.

---

## Tech Stack

### Backend
| Layer | Technology |
|---|---|
| Framework | Spring Boot 3.x |
| Language | Java 17 |
| Database | PostgreSQL 12+ |
| ORM | Spring Data JPA / Hibernate |
| Security | Spring Security + JWT |
| Real-time | WebSocket (STOMP) |
| Build Tool | Maven 3.8+ |
| Scheduled Jobs | Spring `@Scheduled` |

### Frontend
| Layer | Technology |
|---|---|
| Framework | React 19 + Vite 7 |
| State Management | Redux Toolkit 2 |
| Styling | Tailwind CSS 4 + Material-UI |
| HTTP Client | Axios |
| Real-time | STOMP / SockJS WebSocket |
| Forms | React Hook Form |
| Routing | React Router 7 |
| PDF / QR | jsPDF + react-qr-code |
| Charts | Recharts |

---

## Architecture

The backend follows a classic **layered architecture** inside a single Spring Boot application:

```
Web/Mobile Client
      │
      ▼
Security Filter Chain (SecurityConfig + JwtFilter + JwtService)
      │
      ▼
┌─────────────────────────────────────────────┐
│               API Layer (Controllers)        │
│  TripController    RouteController           │
│  BusBookingController  BusController         │
│  UserController    AuthController            │
│  AnalyticsController   SeatController        │
└─────────────────────┬───────────────────────┘
                      │
                      ▼
┌─────────────────────────────────────────────┐
│             Business Layer (Services)        │
│  TripServiceImpl   RouteServiceImpl          │
│  BookingServiceImpl  BusServiceImpl          │
│  UserServiceImpl   AuthServiceImpl           │
│  AnalyticsService  SeatServiceImpl           │
└─────────────────────┬───────────────────────┘
                      │
          ┌───────────┴────────────┐
          ▼                        ▼
┌──────────────────┐    ┌──────────────────────┐
│  Data Access     │    │   Background Jobs     │
│  (JPA Repos)     │    │  RefundScheduler      │
│       │          │    │  Seat Lock Cleanup    │
│       ▼          │    └──────────────────────┘
│   PostgreSQL     │
└──────────────────┘
```

---

## Database Schema

The system uses **10 relational tables** in PostgreSQL:

| Table | Description |
|---|---|
| `users` | Registered users and admins |
| `bus` | Bus fleet with capacity and type |
| `route` | Source–destination routes |
| `trip` | Scheduled trips linking buses to routes |
| `seat` | Individual seats belonging to a bus |
| `seat_lock` | Temporary seat holds (with expiry) |
| `booking` | Confirmed bookings per user per trip |
| `passenger` | Passenger details for each booking |
| `booking_seat` | Junction table: booking ↔ seat ↔ passenger |
| `payment` | Payment records with refund tracking |

### Key Relationships
- A **trip** references one `bus` and one `route`
- A **booking** belongs to one `user` and one `trip`
- **Seats** are locked temporarily via `seat_lock` before payment
- **booking_seat** resolves the many-to-many between bookings, seats, and passengers
- **payment** has a 1-to-1 relationship with a booking (FK + UK)

---

## Project Structure

```
Voyago-/
├── backend/
│   └── src/main/java/com/voyago/
│       ├── controller/       # REST API endpoints
│       ├── service/          # Business logic
│       ├── repository/       # Spring Data JPA repositories
│       ├── entity/           # JPA entities
│       ├── dto/              # Data Transfer Objects
│       ├── security/         # JWT & Spring Security config
│       ├── websocket/        # WebSocket configuration
│       └── exception/        # Global exception handling
│
└── frontend/voyogo-frontend/
    └── src/
        ├── components/       # Reusable React components
        ├── pages/            # Page-level components
        ├── store/            # Redux store & slices
        ├── services/         # Axios API calls
        ├── hooks/            # Custom React hooks
        └── assets/           # Images, fonts, icons
```

---

## Getting Started

### Prerequisites
- Java 17+
- Node.js 18+
- PostgreSQL 12+
- Maven 3.8+

---

### Backend Setup

```bash
# 1. Clone the repository
git clone https://github.com/advancejavajsp/Voyago-.git
cd Voyago-/backend

# 2. Create your database config
# Create: src/main/resources/application-dev.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/voyago
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

# 3. Build and run
mvn clean install
mvn spring-boot:run
```

API available at: `http://localhost:8080`

---

### Frontend Setup

```bash
cd Voyago-/frontend/voyogo-frontend

# 1. Install dependencies
npm install

# 2. Configure environment
# Create .env.local:
VITE_API_BASE_URL=http://localhost:8080/api
VITE_WS_URL=ws://localhost:8080/ws
VITE_APP_ENV=development

# 3. Start dev server
npm run dev
```

App available at: `http://localhost:5173`

---

## API Endpoints

### Auth
| Method | Endpoint | Description |
|---|---|---|
| POST | `/auth/register` | Register a new user |
| POST | `/auth/login` | User login |
| POST | `/auth/refresh` | Refresh JWT token |

### Buses & Routes
| Method | Endpoint | Description |
|---|---|---|
| GET | `/buses` | List all buses |
| POST | `/admin/bus` | Create bus (Admin) |
| GET | `/routes` | List all routes |
| POST | `/admin/route` | Create route (Admin) |

### Trips
| Method | Endpoint | Description |
|---|---|---|
| GET | `/trips/search` | Search available trips |
| GET | `/trips/{tripId}` | Get trip details |
| GET | `/buses/seats/trip/{tripId}` | Get seat availability |
| POST | `/admin/trip` | Create trip (Admin) |
| PUT | `/admin/trip/{tripId}` | Update trip (Admin) |

### Bookings
| Method | Endpoint | Description |
|---|---|---|
| POST | `/booking/create` | Create new booking |
| GET | `/bookings/user/{userId}` | Get user's bookings |
| GET | `/booking/{bookingId}` | Get booking details |
| PUT | `/booking/cancel/{bookingId}` | Cancel a booking |
| GET | `/booking/{bookingId}/pdf` | Download ticket PDF |
| GET | `/booking/verify/{bookingId}` | Verify QR code |

### Payments
| Method | Endpoint | Description |
|---|---|---|
| POST | `/payment/initiate` | Initiate payment |
| POST | `/payment/verify` | Verify payment signature |
| GET | `/payment/{paymentId}` | Get payment details |

### WebSocket (Real-time)
| Topic | Description |
|---|---|
| `/ws/seats` | Subscribe to live seat availability |
| `/ws/booking` | Subscribe to booking status updates |

---

## Features

### ✅ Implemented
- 🔐 JWT authentication with role-based access (USER / ADMIN)
- 🚌 Bus and route management
- 🗓️ Trip scheduling and search
- 💺 Real-time seat locking and synchronization
- 🎟️ Booking creation, cancellation, and PDF ticket download
- 📦 QR code ticket verification
- 💳 Payment initiation and verification
- 🔄 Automated refund processing (`RefundScheduler`)
- ⏱️ Automatic seat lock expiry cleanup (`@Scheduled`)
- 📊 Analytics APIs
- 📧 Email notifications

### 🔜 Planned
- [ ] Razorpay / Stripe / PhonePe full integration
- [ ] Redis for distributed seat locking
- [ ] SMS notifications
- [ ] Dynamic pricing engine
- [ ] Analytics dashboard (admin)
- [ ] Microservices migration

---

## Environment Variables

### Backend (`.env` or `application.properties`)
```
DB_URL=jdbc:postgresql://localhost:5432/voyago
DB_USER=postgres
DB_PASSWORD=your_password
JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
EMAIL_USERNAME=your_email@gmail.com
EMAIL_PASSWORD=your_app_password
```

### Frontend (`.env.local`)
```
VITE_API_BASE_URL=http://localhost:8080/api
VITE_WS_URL=ws://localhost:8080/ws
VITE_APP_ENV=development
```

---

## Deployment

### Docker (Backend)
```bash
docker build -t voyago-backend .
docker run -p 8080:8080 voyago-backend
```

### Docker (Frontend)
```bash
docker build -t voyago-frontend .
docker run -p 3000:80 voyago-frontend
```

### Vercel (Frontend)
```bash
npm install -g vercel
vercel
```

### Netlify (Frontend)
```bash
npm run build
# Deploy the generated 'dist' folder
```

### AWS EC2 (Backend)
Deploy to EC2 with an RDS PostgreSQL instance. Ensure security groups allow port 8080 inbound.

---

## Contributing

1. Fork the repository
2. Create a feature branch: `git checkout -b feature/your-feature`
3. Commit your changes: `git commit -m "Add your feature"`
4. Push to the branch: `git push origin feature/your-feature`
5. Open a Pull Request

Please include a clear description, steps to reproduce any bugs, and screenshots where applicable.

---

## License

This project is licensed under the **MIT License**. See the [LICENSE](LICENSE) file for details.

---

## 👥 Contributors

- [@advancejavajsp](https://github.com/advancejavajsp)

---

*Last updated: March 2026*
