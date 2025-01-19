# Projektname - Backend und Frontend Setup

Dieses Repository enthält die Konfiguration und die notwendigen Schritte, um das Backend und Frontend für das Projekt mit Docker und Docker Compose zu starten.

## Voraussetzungen

Stelle sicher, dass Docker und Docker Compose auf deinem System installiert sind. Wenn sie noch nicht installiert sind, kannst du sie hier herunterladen:

- [Docker installieren](https://docs.docker.com/get-docker/)
- [Docker Compose installieren](https://docs.docker.com/compose/install/)

## Backend und Frontend Setup

Um das Backend und Frontend mit Docker Compose zu starten, folge diesen Schritten:

### 1. Repository klonen

Klonen Sie das Repository auf deinem lokalen System:

```bash
git clone https://github.com/dein-repository.git
cd dein-repository
```
### 2. Docker Compose starten
Führe den folgenden Befehl aus, um die grundlegenden Docker-Container (Services) hochzufahren:

```bash
Kopieren
docker-compose up -d
cd dein-repository
```
Dieser Befehl startet alle Services im Hintergrund. Der -d-Flag sorgt dafür, dass die Container im "Detached Mode" laufen (d.h., sie laufen im Hintergrund).

Services starten
Die Services sollten in einer bestimmten Reihenfolge gestartet werden. Hier ist die empfohlene Reihenfolge:

1. Config Server starten
Der Config Server wird benötigt, um die Konfiguration der verschiedenen Services zentral zu verwalten. Starte diesen Service zuerst:

```bash
docker-compose up config-server
```
### 2. Discovery Service starten
Der Discovery Service ist erforderlich, damit die verschiedenen Microservices miteinander kommunizieren können. Starte diesen Service nach dem Config Server:

```bash
docker-compose up discovery-service
```
### 3. Weitere Backend Services starten
Nachdem der Config Server und der Discovery Service laufen, kannst du die restlichen Backend-Services starten. Du kannst auch einzelne Services je nach Bedarf starten:

```bash
docker-compose up api
docker-compose up db
docker-compose up cache
```
Falls du alle Backend-Services auf einmal starten möchtest, kannst du den folgenden Befehl verwenden:

```bash

docker-compose up backend
```
### 4. Frontend (Angular) starten
Das Frontend wurde mit Angular umgesetzt und benötigt einen Web-Server. Starte diesen Service, um das Frontend verfügbar zu machen:

```bash
docker-compose up frontend
```
Sobald der Service hochgefahren ist, kannst du die Anwendung im Browser unter http://localhost:4200 aufrufen.

### Services
Die folgenden Services sind Teil des Projekts und können individuell oder zusammen gestartet werden:

config-server: Der Config Server, der die zentrale Verwaltung der Konfigurationen übernimmt.
discovery-service: Der Service zur Verwaltung der Registrierung und Kommunikation zwischen Microservices.
api: Der API-Server, der die Geschäftslogik verarbeitet.
db: Die Datenbank, die das Backend benötigt.
cache: Der Caching-Service für schnelleren Datenzugriff.
frontend: Das Angular-basierte Frontend, das die Benutzeroberfläche bereitstellt.
Container stoppen
Um die Container zu stoppen, ohne sie zu entfernen, benutze:

```bash
docker-compose stop
```
Falls du die Container vollständig stoppen und entfernen möchtest, inklusive Netzwerke und Volumes, kannst du folgendes ausführen:

```bash
docker-compose down
```
