# Praktikum Software Engineering WS2023 - “Digital Driver’s Logbook”

Entwicklung eines Fahrtenbuches als Desktop-Applikation.

## Projektstruktur
### MYSQL Installation
#### Windows
https://dev.mysql.com/doc/refman/8.2/en/windows-installation.html
#### Mac
https://dev.mysql.com/doc/refman/8.0/en/macos-installation.html
### Download JDBC Connector
https://www.youtube.com/watch?v=whhSR0wlWQY
### Download JAVAFX SDK
https://gluonhq.com/products/javafx/
### Voraussetzung
JavaFX SDK; JDBC Connector; lokaler MySQL Server
### Installation
Unter Project Structure -> Libraries JAVAFX SDK hinzufügen und unter Project Structure -> Modules -> Dependencies -> Add jar den Mysql JDBC Connector hinzufügen. Anschließend kann das Programm mit der Main Klasse "LogbookApplication" im "fahrtenbuch" package mit Klick auf die Schaltfläche "Run" ausgeführt werden.


# Testdokumentation für die Klasse DriveFacadeTest

## Übersicht

Die Testklasse **DriveFacadeTest** enthält Tests für die Methoden der Klasse **DriveFacade**, die für die Verwaltung von Fahrten in der Datenbank verantwortlich ist. Die Tests überprüfen insbesondere die Funktionalität der Methode `getLicense_plateByDriveId()`.

## Umgebung

- **Sprache:** Java
- **Framework:** JUnit
- **Datenbank:** MySQL

## Testfälle

### 1. **testParseTextFieldToIntValidInput()**
   - **Beschreibung:** Überprüft die Konvertierung von Text zu Integer mit gültiger Eingabe.
   - **Schritte:**
     - Aufruf von `parseTextFieldToInt()` mit einer gültigen Zahl als Eingabe.
   - **Erwartetes Ergebnis:** Die zurückgegebene Zahl entspricht der erwarteten Zahl.

### 2. **testParseTextFieldToIntInvalidInput()**
   - **Beschreibung:** Überprüft die Konvertierung von Text zu Integer mit ungültiger Eingabe.
   - **Schritte:**
     - Aufruf von `parseTextFieldToInt()` mit einer ungültigen Eingabe.
   - **Erwartetes Ergebnis:** Es wird null zurückgegeben.

### 3. **testParseTextFieldToDoubleValidInput()**
   - **Beschreibung:** Überprüft die Konvertierung von Text zu Double mit gültiger Eingabe.
   - **Schritte:**
     - Aufruf von `parseTextFieldToDouble()` mit einer gültigen Zahl als Eingabe.
   - **Erwartetes Ergebnis:** Die zurückgegebene Zahl entspricht der erwarteten Zahl.

### 4. **testParseTextFieldToDoubleInvalidInput()**
   - **Beschreibung:** Überprüft die Konvertierung von Text zu Double mit ungültiger Eingabe.
   - **Schritte:**
     - Aufruf von `parseTextFieldToDouble()` mit einer ungültigen Eingabe.
   - **Erwartetes Ergebnis:** Es wird null zurückgegeben.

### 5. **testGetLicensePlateByDriveId()**
   - **Beschreibung:** Überprüft die Abfrage des Kennzeichens anhand der Fahrten-ID.
   - **Schritte:**
     - Aufruf von `getLicense_plateByDriveId()` mit einer existierenden Fahrten-ID.
   - **Erwartetes Ergebnis:** Das zurückgegebene Kennzeichen ist nicht null.

## Ausführung

Die Tests wurden mittels JUnit auf einer lokal eingerichteten Entwicklungsumgebung ausgeführt und erfolgreich validiert.


