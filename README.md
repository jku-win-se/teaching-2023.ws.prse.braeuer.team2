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


## API-Dokumentation

Die API bietet Methoden zur Interaktion mit den Entitäten in einer Fahrtenbuchanwendung. Diese Methoden ermöglichen es Benutzern, Operationen wie das Abrufen, Erstellen und Löschen von Entitäten durchzuführen.

### DatabaseConnection

#### Methoden

1. **`getConnection(): Connection`**

   Stellt die Verbindung zur Datenbank her und gibt die `Connection` zurück.

2. **`initiateDB(): void`**

   Initialisiert die Datenbank mit den notwendigen Tabellen und Beispieldaten.

3. **`closeConnection(): void`**

   Schließt die Verbindung zur Datenbank.

4. **`exportDataToCSV(): void`**

   Exportiert Daten aus den Datenbanktabellen in separate CSV-Dateien.

5. **`importDataFromCSV(): void`**

   Importiert Daten aus vorhandenen CSV-Dateien in die entsprechenden Datenbanktabellen.

### CategoryFacade

#### Methoden

1. **`getCategoryById(Integer id): Category`**

   Ruft eine Kategorie anhand ihrer eindeutigen Kennung ab.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung der Kategorie.
   - Rückgabe:
     - `Category`: Das Kategorieobjekt, wenn gefunden, oder null, wenn nicht gefunden.

2. **`getCategoryByName(String categoryName): Category`**

   Ruft eine Kategorie anhand ihres Namens ab.

   - Parameter:
     - `categoryName` (String): Der Name der Kategorie.
   - Rückgabe:
     - `Category`: Das Kategorieobjekt, wenn gefunden, oder null, wenn nicht gefunden.

3. **`getAllCategories(): List<Category>`**

   Ruft eine Liste aller Kategorien ab.

   - Rückgabe:
     - `List<Category>`: Eine Liste, die alle Kategorieobjekte enthält.

4. **`persistCategory(Category c): void`**

   Persistiert eine neue Kategorie in der Datenbank.

   - Parameter:
     - `c` (Category): Das zu speichernde Kategorieobjekt.

5. **`deleteCategoryById(Integer id): void`**

   Löscht eine Kategorie anhand ihrer eindeutigen Kennung.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung der zu löschenden Kategorie.

### Category_Drive_Facade

#### Methoden

1. **`getDrivesByCategoryId(Integer id): List<Drive>`**

   Ruft eine Liste von Fahrten ab, die mit einer bestimmten Kategorie verknüpft sind.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung der Kategorie.
   - Rückgabe:
     - `List<Drive>`: Eine Liste von Fahrten, die mit der angegebenen Kategorie verknüpft sind.

2. **`persistCategoryDrive(Category_Drive cd): void`**

   Persistiert eine Kategorie-Fahrten-Beziehung in der Datenbank.

   - Parameter:

     - `cd` (Category_Drive): Das Objekt, das die Beziehung zwischen Kategorie und Fahrt repräsentiert.

       

### DriveFacade

#### Methoden

1. **`getDriveById(Integer id): Drive`**

   Ruft eine Fahrt anhand ihrer eindeutigen Kennung ab.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung der Fahrt.
   - Rückgabe:
     - `Drive`: Das Fahrtobjekt, wenn gefunden, oder null, wenn nicht gefunden.

2. **`getDrivesByLicensePlate(String licensePlate): List<Drive>`**

   Ruft eine Liste von Fahrten anhand des Fahrzeugkennzeichens ab.

   - Parameter:
     - `licensePlate` (String): Das Kennzeichen des Fahrzeugs.
   - Rückgabe:
     - `List<Drive>`: Eine Liste von Fahrtobjekten, die mit dem Fahrzeug verknüpft sind.

3. **`getAllDrives(): List<Drive>`**

   Ruft eine Liste aller Fahrten ab.

   - Rückgabe:
     - `List<Drive>`: Eine Liste, die alle Fahrtobjekte enthält.

4. **`persistDrive(Drive v): void`**

   Speichert eine neue Fahrt in der Datenbank.

   - Parameter:
     - `v` (Drive): Das zu speichernde Fahrtobjekt.

5. **`persistRecurringDrive(Integer vehicleId, Date startDate, Date endDate, int interval): void`**

   Speichert wiederkehrende Fahrten basierend auf dem angegebenen Fahrzeug, Startdatum, Enddatum und Intervall.

   - Parameter:
     - `vehicleId` (Integer): Die eindeutige Kennung des Fahrzeugs.
     - `startDate` (Date): Das Startdatum für wiederkehrende Fahrten.
     - `endDate` (Date): Das Enddatum für wiederkehrende Fahrten.
     - `interval` (int): Das Intervall in Tagen zwischen den wiederkehrenden Fahrten.

6. **`deleteDriveById(Integer id): void`**

   Löscht eine Fahrt anhand ihrer eindeutigen Kennung.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung der zu löschenden Fahrt.

7. **`getLastDriveId(): Integer`**

   Ruft die eindeutige Kennung der zuletzt erstellten Fahrt ab.

   - Rückgabe:

     - `Integer`: Die eindeutige Kennung der zuletzt erstellten Fahrt.

       

### VehicleFacade

#### Methoden

1. **`getVehicleById(Integer id): Vehicle`**

   Ruft ein Fahrzeug anhand seiner eindeutigen Kennung ab.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung des Fahrzeugs.
   - Rückgabe:
     - `Vehicle`: Das Fahrzeugobjekt, wenn gefunden, oder null, wenn nicht gefunden.

2. **`getAllVehicles(): List<Vehicle>`**

   Ruft eine Liste aller Fahrzeuge ab.

   - Rückgabe:
     - `List<Vehicle>`: Eine Liste, die alle Fahrzeugobjekte enthält.

3. **`persistVehicle(Vehicle v): void`**

   Speichert ein neues Fahrzeug in der Datenbank.

   - Parameter:
     - `v` (Vehicle): Das zu speichernde Fahrzeugobjekt.

4. **`deleteVehicleById(Integer id): void`**

   Löscht ein Fahrzeug anhand seiner eindeutigen Kennung.

   - Parameter:
     - `id` (Integer): Die eindeutige Kennung des zu löschenden Fahrzeugs.

5. **`getVehicleByLicensePlate(String licensePlate): Vehicle`**

   Ruft ein Fahrzeug anhand seines Kennzeichens ab.

   - Parameter:
     - `licensePlate` (String): Das Kennzeichen des Fahrzeugs.
   - Rückgabe:
     - `Vehicle`: Das Fahrzeugobjekt, wenn gefunden, oder null, wenn nicht gefunden.

6. **`getVehicleIdByLicensePlate(String licensePlate): Integer`**

   Ruft die eindeutige Kennung eines Fahrzeugs anhand seines Kennzeichens ab.

   - Parameter:
     - `licensePlate` (String): Das Kennzeichen des Fahrzeugs.
   - Rückgabe:
     - `Integer`: Die eindeutige Kennung des Fahrzeugs oder null, wenn nicht gefunden.



