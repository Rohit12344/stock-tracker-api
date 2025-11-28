📈 Stock Tracker API (Spring Boot)

A backend API built using Spring Boot that fetches Indian stock market data, including real-time quotes, overviews, historical prices, and support for saving favorite stocks.

🚀 Features

🔹 Fetch live stock data

🔹 Fetch detailed stock overviews

🔹 Get historical stock prices (default 30 days)

🔹 Save favorite stocks

🔹 Clean REST API structure

🔹 Integrates with external stock API using WebClient


🛠 Tech Stack

🔹 Java 17+

🔹 Spring Boot

🔹 Spring WebFlux (WebClient)

🔹 Maven

🔹 H2 Database (optional for favorites)


📡 API Endpoints
1. Get Stock Data
GET /api/v1/stocks/{stockSymbol}

2. Get Stock Overview
GET /api/v1/stocks/{stockSymbol}/overview

3. Get Stock History
GET /api/v1/stocks/{stockSymbol}/history?days=30

    Optional query param:
    days — number of days (default 30)

4. Add Favorite Stock
POST /api/v1/stocks/favourites

    Request body:
    {
      "symbol": "TCS"
    }

⚙️ Setup
1. Clone the repository
git clone https://github.com/<your-username>/stock-tracker-api.git
cd stock-tracker-api

2. Add environment variables

    Create application.properties:
    
    indian.stock.api.base.url=YOUR_API_URL
    indian.stock.api.key=YOUR_API_KEY
    server.port=8080

▶️ Run the Application
    Using Maven:
    mvn spring-boot:run

    Build JAR:
    mvn clean package
    java -jar target/stock-tracker-0.0.1-SNAPSHOT.jar
