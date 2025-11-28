📈 Stock Tracker API

A Spring Boot backend service for fetching Indian stock market data, stock history, overviews, and managing user favorites.

🧭 Table of Contents

Overview

Features

Architecture

Tech Stack

Project Structure

API Endpoints

Setup & Installation

Build & Run

Environment Variables

Future Enhancements

📘 Overview

This project is a Spring Boot–based stock tracking backend that integrates with an external Indian stock market API.
It provides:

Live stock data

Stock overviews

Historical price charts

Favorite stock management

Clean, RESTful endpoints

⭐ Features
✔ Fetch real-time stock details
✔ View stock overview & fundamentals
✔ Fetch historical stock prices (daily OHLC)
✔ Add user-favorite stocks
✔ Built on reactive WebClient for external API calls
✔ Clean Layered Architecture (Controller → Service → Client → DTOs)
🏗 Architecture
                       ┌──────────────────────────┐
                       │    External Stock API     │
                       └──────────────┬───────────┘
                                      │
                               WebClient Calls
                                      │
                   ┌──────────────────▼──────────────────┐
                   │          StockClient                 │
                   │  (Handles all external API calls)    │
                   └──────────────────┬──────────────────┘
                                      │
                               Business Logic
                                      │
                   ┌──────────────────▼──────────────────┐
                   │            StockService              │
                   │  (Processing, mapping, validation)   │
                   └──────────────────┬──────────────────┘
                                      │
                                 REST API Layer
                                      │
       ┌──────────────────────────────▼───────────────────────────────┐
       │                        StockController                        │
       └──────────────────────────────┬───────────────────────────────┘
                                      │
                                JSON Responses
                                      │
                    ┌─────────────────▼─────────────────┐
                    │       Frontend / Mobile App       │
                    └───────────────────────────────────┘

🧰 Tech Stack
Layer	Technology
Language	Java 17+
Framework	Spring Boot
HTTP Client	Spring WebClient
DB	(Optional) JPA/Hibernate if used for favorites
Build Tool	Maven
Deployment	Docker / AWS (optional)
📂 Project Structure
src/main/java/com/rohit/stock_tracker/
│
├── controller/
│   └── StockController.java
│
├── service/
│   └── StockService.java
│
├── client/
│   └── StockClient.java
│
├── dto/
│   ├── StockResponse.java
│   ├── StockOverviewResponse.java
│   ├── DailyStockResponse.java
│   └── FavoriteStockRequest.java
│
├── entity/
│   └── FavoriteStock.java
│
└── config/
    └── WebClientConfig.java

📡 API Endpoints
1. Get Stock Data
GET /api/v1/stocks/{stockSymbol}


Response: StockResponse

2. Get Stock Overview
GET /api/v1/stocks/{stockSymbol}/overview


Response: StockOverviewResponse

3. Get Stock History
GET /api/v1/stocks/{stockSymbol}/history?days=30


Response: List<DailyStockResponse>

4. Add Favorite Stock
POST /api/v1/stocks/favourites


Request Body:

{
  "symbol": "TCS"
}

⚙ Setup & Installation
Prerequisites

Java 17+

Maven 3.8+

Internet access (for external stock API)

1️⃣ Clone the repository
git clone https://github.com/your-username/stock-tracker.git
cd stock-tracker

2️⃣ Add environment variables

Create application.properties or application.yml:

indian.stock.api.base.url=<API_BASE_URL>
indian.stock.api.key=<YOUR_API_KEY>
server.port=8080

🏃 Build & Run
Run using Maven
mvn spring-boot:run

Build JAR
mvn clean package


Run it:

java -jar target/stock-tracker-0.0.1-SNAPSHOT.jar
