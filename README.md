# Getting Started

Dieses Dokument beschreibt die notwendigen Schritte, um das E-Commerce Microservices Projekt lokal zu starten.

## Voraussetzungen

- Docker & Docker Compose
- Java 17
- Node.js & npm
- Angular CLI

## Backend starten

### 1. Datenbank starten

Im Root-Verzeichnis des Backends befindet sich ein Docker Compose File. Führen Sie folgenden Befehl aus:

```bash
docker-compose up -d
```

## 2. Microservices starten

### 1. Config Server
```bash
cd config-server
./gradlew bootRun
```

### 2. Discovery Server (Eureka)
```bash
cd discovery-service
./gradlew bootRun
```

### 3. Restliche Services (können parallel gestartet werden)
```bash
cd api-gateway
./gradlew bootRun

cd auth-service
./gradlew bootRun

cd product-service
./gradlew bootRun

cd cart-service
./gradlew bootRun

cd order-service
./gradlew bootRun
```

## Frontend starten
### 1. Dependencies installieren
Im Frontend-Verzeichnis:
```bash
cd frontend
npm install
```
### 2. Development Server starten
```bash
ng serve
```
Die Anwendung ist nun unter http://localhost:4200 erreichbar.

## Hinweise

- Stellen Sie sicher, dass alle erforderlichen Ports verfügbar sind
- Die Datenbank läuft auf Port 3306
- Der Discovery Service (Eureka) ist unter `http://localhost:8761` erreichbar
- Das API Gateway läuft auf Port 8080

## Troubleshooting

- Falls Verbindungsprobleme auftreten, überprüfen Sie ob alle Services erfolgreich gestartet wurden
- Der Eureka Server sollte die registrierten Services unter `http://localhost:8761` anzeigen
- Überprüfen Sie die Service-Logs für detaillierte Fehlermeldungen
