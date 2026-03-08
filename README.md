# Voyago Backend API

A comprehensive Spring Boot backend for a bus ticket booking system with real-time seat management, payment processing, and WebSocket support.

## 🎯 Overview

Voyago is an industry-grade bus reservation platform providing secure booking, real-time seat synchronization, refund handling, and admin management capabilities.


## 🚀 Tech Stack

- **Framework**: Spring Boot 3.x
- **Database**: PostgreSQL
- **Real-time Communication**: WebSocket (Spring WebSocket + STOMP)
- **Payment Processing**: Razorpay, Stripe, PhonePe integration ready
- **Security**: Spring Security + JWT Authentication
- **ORM**: JPA/Hibernate
- **API Documentation**: REST API

## 📁 Project Structure




## 🔧 Setup & Installation

### Prerequisites
- Java 17+
- PostgreSQL 12+
- Maven 3.8+
- Git

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/advancejavajsp/Voyago-.git
cd Voyago-/backend

Configure Database Create application-dev.properties:



📡 API Endpoints
Authentication
POST /auth/register - Register new user
POST /auth/login - User login
POST /auth/refresh - Refresh JWT token
Buses & Routes
GET /buses - List all buses
POST /admin/bus - Create bus (Admin)
GET /routes - List all routes
POST /admin/route - Create route (Admin)
Trips
GET /trips/search - Search available trips
GET /trips/{tripId} - Get trip details
GET /buses/seats/trip/{tripId} - Get seat availability
POST /admin/trip - Create trip (Admin)
PUT /admin/trip/{tripId} - Update trip (Admin)
Bookings
POST /booking/create - Create new booking
GET /bookings/user/{userId} - Get user bookings
GET /booking/{bookingId} - Get booking details
PUT /booking/cancel/{bookingId} - Cancel booking
GET /booking/{bookingId}/pdf - Download ticket PDF
GET /booking/verify/{bookingId} - Verify QR code
Payments
POST /payment/initiate - Initiate payment
POST /payment/verify - Verify payment signature
GET /payment/{paymentId} - Get payment details
WebSocket (Real-time)
/ws/seats - Subscribe to seat availability updates
/ws/booking - Subscribe to booking status updates
🔐 Security Features
JWT-based authentication
Role-based access control (USER, ADMIN)
Encrypted passwords (BCrypt)
CORS configuration for frontend
Input validation and sanitization
SQL injection prevention


Features Implemented
✅ Bus & Route Management ✅ Trip Search & Filtering ✅ Seat Locking & Real-time Synchronization ✅ Booking Creation & Cancellation ✅ Refund Processing ✅ JWT Authentication ✅ Admin Panel APIs ✅ WebSocket Integration ✅ Email Notifications ✅ QR Code Ticket Verification

🗂️ Planned Features
 Payment Gateway Integration (Razorpay, Stripe, PhonePe)
 Redis for distributed seat locking
 Analytics Dashboard APIs
 SMS Notifications
 Dynamic Pricing Engine
 Microservices Architecture

