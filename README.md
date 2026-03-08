Backend README (backend/README.md)
advancejavajsp / Voyago- / backend / README.md
v1
# Voyago Backend API

A comprehensive Spring Boot backend for a bus ticket booking system with real-time seat management, payment processing, and WebSocket support.

## 🎯 Overview

backend/ ├── src/main/java/com/voyago/ │ ├── controller/ # REST API endpoints │ ├── service/ # Business logic │ ├── repository/ # Data access layer │ ├── entity/ # JPA entities │ ├── dto/ # Data Transfer Objects │ ├── security/ # JWT & Spring Security config │ ├── websocket/ # WebSocket configuration │ └── exception/ # Global exception handling ├── src/main/resources/ │ ├── application.properties │ └── application-{profile}.properties └── pom.xml

Code

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
properties
spring.datasource.url=jdbc:postgresql://localhost:5432/voyago
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
Build the project
bash
mvn clean install
Run the application
bash
mvn spring-boot:run
The API will be available at http://localhost:8080

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
🛠️ Development
Run Tests
bash
mvn test
Code Style
Follow Google Java Style Guide. Use formatter:

bash
mvn spotless:apply
Database Migrations
Uses JPA auto-schema generation. Alternatively, use Flyway for migrations.

📚 Features Implemented
✅ Bus & Route Management ✅ Trip Search & Filtering ✅ Seat Locking & Real-time Synchronization ✅ Booking Creation & Cancellation ✅ Refund Processing ✅ JWT Authentication ✅ Admin Panel APIs ✅ WebSocket Integration ✅ Email Notifications ✅ QR Code Ticket Verification

🗂️ Planned Features
 Payment Gateway Integration (Razorpay, Stripe, PhonePe)
 Redis for distributed seat locking
 Analytics Dashboard APIs
 SMS Notifications
 Dynamic Pricing Engine
 Microservices Architecture
🐛 Environment Variables
Create .env file:

Code
DB_URL=jdbc:postgresql://localhost:5432/voyago
DB_USER=postgres
DB_PASSWORD=your_password
JWT_SECRET=your_secret_key
JWT_EXPIRATION=86400000
EMAIL_USERNAME=your_email@gmail.com
EMAIL_PASSWORD=your_app_password
📖 Documentation
API Documentation
Database Schema
Deployment Guide
Contributing Guidelines
🚀 Deployment
Docker
bash
docker build -t voyago-backend .
docker run -p 8080:8080 voyago-backend
AWS EC2
Deploy to EC2 with RDS PostgreSQL. See deployment guide.

📞 Support
For issues and feature requests, please create a GitHub issue.

📄 License
This project is licensed under the MIT License - see LICENSE file.

👥 Contributors
advancejavajsp
Last Updated: March 2026

Code

---

### 2. **Frontend README** (`frontend/voyogo-frontend/README.md`)
```markdown name=frontend/voyogo-frontend/README.md url=https://github.com/advancejavajsp/Voyago-/blob/master/frontend/voyogo-frontend/README.md
# Voyago Frontend

A modern, responsive React web application for bus ticket booking with real-time seat selection, payment integration, and user dashboard.

## 🎯 Overview

Voyago Frontend is a feature-rich React application built with Vite for optimal performance. It provides seamless bus booking experience with real-time seat management, secure payment processing, and comprehensive user management.

## 🚀 Tech Stack

- **Framework**: React 19.2
- **Build Tool**: Vite 7.3
- **State Management**: Redux Toolkit 2.11
- **Styling**: Tailwind CSS 4.1 + Material-UI
- **HTTP Client**: Axios 1.13
- **Real-time**: STOMP WebSocket 7.3
- **Forms**: React Hook Form 7.71
- **Routing**: React Router 7.13
- **PDF Generation**: jsPDF 4.1
- **UI Components**: Material-UI, React Icons

## 📁 Project Structure

frontend/voyogo-frontend/ ├── src/ │ ├── components/ # Reusable React components │ ├── pages/ # Page components │ ├── store/ # Redux store & slices │ ├── services/ # API calls & utilities │ ├── hooks/ # Custom React hooks │ ├── assets/ # Images, fonts, etc. │ ├── styles/ # Global styles │ ├── App.jsx │ └── main.jsx ├── public/ # Static files ├── package.json ├── vite.config.js ├── tailwind.config.js └── README.md

Code

## 🔧 Setup & Installation

### Prerequisites
- Node.js 18+
- npm or yarn
- Git

### Steps

1. **Clone the repository**
```bash
git clone https://github.com/advancejavajsp/Voyago-.git
cd Voyago-/frontend/voyogo-frontend
Install dependencies
bash
npm install
Environment Configuration Create .env.local:
env
VITE_API_BASE_URL=http://localhost:8080/api
VITE_WS_URL=ws://localhost:8080/ws
VITE_APP_ENV=development
Start development server
bash
npm run dev
Application will be available at http://localhost:5173

📜 Available Scripts
bash
# Start development server
npm run dev

# Build for production
npm run build

# Preview production build
npm run preview

# Run ESLint
npm run lint
🎨 Key Features
User Features
✅ Search & Filter Buses

Filter by date, route, AC/Non-AC, sleeper/seater
Real-time seat availability
Dynamic pricing display
✅ Seat Selection

Interactive seat map
Real-time seat locking
Multiple passenger info entry
Seat preferences
✅ Booking Management

View booking history
Cancel bookings
Download tickets (PDF)
QR code verification
✅ Payment Integration

Razorpay integration
Secure payment gateway
Multiple payment methods
Refund tracking
✅ User Dashboard

Profile management
Booking history
Save preferences
Download tickets
Technical Features
Responsive design (mobile, tablet, desktop)
Real-time seat synchronization via WebSocket
Redux state management
Form validation with React Hook Form
Error handling & notifications (Toastify)
Authentication with JWT
Protected routes
🔐 Authentication & Authorization
JWT token-based authentication
Automatic token refresh
Role-based access control
Secure API communication
🌐 API Integration
All API calls are centralized in src/services/:

bash
src/services/
├── api.js           # Axios instance
├── authService.js   # Authentication
├── busService.js    # Bus & route APIs
├── bookingService.js # Booking operations
└── paymentService.js # Payment integration
🔌 WebSocket Integration
Real-time seat updates using STOMP protocol:

JavaScript
// Subscribe to seat availability
client.subscribe('/topic/seats/{tripId}', (message) => {
  // Handle seat updates
});

// Subscribe to booking status
client.subscribe('/topic/booking/{bookingId}', (message) => {
  // Handle booking updates
});
📦 Dependencies Overview
UI & Styling
@mui/material - Material-UI components
tailwindcss - Utility-first CSS
react-icons - Icon library
State Management
@reduxjs/toolkit - Redux with utilities
react-redux - React bindings
Forms & Validation
react-hook-form - Efficient form handling
Built-in validation
HTTP & WebSocket
axios - HTTP client
@stomp/stompjs - STOMP client
sockjs-client - WebSocket fallback
Utilities
react-router-dom - Client-side routing
jwt-decode - JWT parsing
jspdf - PDF generation
html2canvas - Screenshot to canvas
react-qr-code - QR code generation
recharts - Data visualization
🎯 Component Architecture
Code
App/
├── Layout
│   ├── Header
│   ├── Navigation
│   └── Footer
├── Pages
│   ├── Home
│   ├── Search
│   ├── SeatSelection
│   ├── Booking
│   ├── MyBookings
│   └── Profile
└── Protected Routes
    └── User Dashboard
🧪 Development Guidelines
Code Style
Use functional components with hooks
Follow React best practices
Keep components small & focused
Use custom hooks for logic reuse
State Management
Use Redux for global state
Use local state for component-level data
Use Context API for theme/auth
Component Standards
jsx
// Use named exports
export const ComponentName = () => {
  return <div>Component</div>;
};

// Use PropTypes or TypeScript for props
ComponentName.propTypes = {
  // Define props
};
🚀 Performance Optimization
Code splitting with React.lazy
Image optimization
Bundle analysis
Lazy loading routes
Memoization for expensive operations
🔍 Testing
bash
# Unit & Integration tests coming soon
npm run test
🐛 Common Issues & Solutions
WebSocket Connection Failed
Ensure backend is running on correct port
Check VITE_WS_URL environment variable
Verify CORS configuration
API 404 Errors
Confirm backend API base URL
Check network tab in DevTools
Verify backend server status
Token Expired
Application auto-refreshes JWT
Manual re-login if needed
Check token expiration settings
📱 Browser Support
Chrome 90+
Firefox 88+
Safari 14+
Edge 90+
🌍 Deployment
Vercel
bash
npm install -g vercel
vercel
Netlify
bash
npm run build
# Deploy 'dist' folder to Netlify
Docker
bash
docker build -t voyago-frontend .
docker run -p 3000:80 voyago-frontend
📚 Additional Resources
Vite Documentation
React Documentation
Redux Toolkit Guide
Tailwind CSS Docs
Material-UI Docs
📖 Related Documentation
Backend README
API Documentation
Deployment Guide
Contributing Guidelines
🐛 Bug Reports & Features
Found a bug or want to suggest a feature? Create an issue with:

Clear description
Steps to reproduce
Expected vs actual behavior
Screenshots (if applicable)
📄 License
This project is licensed under the MIT License - see LICENSE file.

👥 Contributors
advancejavajsp
