# Architekturdokumetaion

## Prototype 
https://www.figma.com/file/uS6ZQE29Ug4cUVFpfFBYfK/Prototyp-Fahrtenbuch?type=design&node-id=118%3A644&mode=design&t=eRd3X0ueWxJAVFvE-1

## UML

### UML Diagramm SQL-Tabellen

![alt text](https://github.com/jku-win-se/teaching-2023.ws.prse.braeuer.team2/blob/main/docs/UML_Tabellen.jpg?raw=true)



### UML Diagramm Klassen

![alt text](https://github.com/jku-win-se/teaching-2023.ws.prse.braeuer.team2/blob/main/docs/UML_Java_Klassen.jpg?raw=true)

## Implementierung

### Projektstruktur-Dokumentation

#### Struktur

1. **com.example.fahrtenbuch.business**:
   - Beinhaltet die Geschäftslogik der Anwendung.
   - Enthält Klassen für die Datenbankinteraktion und die Backup Funktion.
2. **com.example.fahrtenbuch.entities**:
   - Enthält die Entitätsklassen

#### Wichtige Klassen und ihre Funktionen

- **DatabaseConnection**:
  - Verwaltet die Datenbankverbindung.
  - Verantwortlich für die Initialisierung und den Aufbau der Datenbankverbindung.
  - Handhabt den Export und Import von Daten.
- **VehicleFacade**:
  - Bietet Methoden zur Verwaltung von Fahrzeugdaten.
  - Ermöglicht das Abrufen, Hinzufügen und Löschen von Fahrzeugen in der Datenbank.
- **DriveFacade**:
  - Zuständig für die Verwaltung von Fahrten.
  - Umfasst Erstellung, Löschung und Abfrage von Fahrten.
- **CategoryFacade**:
  - Verwaltet Kategorien.
  - Ermöglicht das Abrufen, Hinzufügen und Löschen von Kategorien.
- **CategoryDriveFacade**:
  - Stellt Methoden zum Abfragen von Fahrten pro Kategorie und zur Persistierung von der Verknüpfung Kategorie und Fahrt.

#### Datenfluss und Interaktion

- Die **Facade-Klassen** interagieren direkt mit der **DatabaseConnection**-Klasse, um CRUD-Operationen (Create, Read, Update, Delete) auf den Daten durchzuführen.
- **Entitätsklassen** werden von den Facade-Klassen genutzt, um Datenstrukturen zu definieren, die in Datenbankoperationen verwendet werden.

#### Datei- und Verzeichnisstruktur

Die Dateistruktur spiegelt die Paketstruktur wider. Jede Klasse befindet sich in einem eigenen Datei, und alle Dateien sind in entsprechenden Verzeichnissen nach ihren Paketen organisiert.

#### Zusammenfassung

Die Projektstruktur ist so gestaltet, dass sie die Prinzipien des sauberen Designs und der einfachen Wartbarkeit unterstützt. Die klare Trennung von Zuständigkeiten zwischen den verschiedenen Klassen und Paketen ermöglicht es, Änderungen effizient durchzuführen und die Erweiterbarkeit der Anwendung zu gewährleisten.

### Implementierung Import/Export/Cloud-Export

1. **Datenbankverbindung (DatabaseConnection)**
   - Die Klasse `DatabaseConnection` im Paket `com.example.fahrtenbuch.business` ist zentral für die Verbindung zur MySQL-Datenbank.
   - Verwendet JDBC (`java.sql.*`) für Datenbankoperationen.
2. **Export von Daten in CSV-Dateien (exportDataToCSV)**
   - Die Methode `exportDataToCSV` exportiert Daten aus den Tabellen (z.B. `vehicle`, `category`, `drive`, `category_drive`) in separate CSV-Dateien.
   - Verwendet Java I/O-Klassen (`java.io.*`) zur Dateihandhabung.
3. **Cloud-Export-Funktion (exportDataToCloud)**
   - `exportDataToCloud` lädt die CSV-Dateien in die Cloud hoch und gibt Links zu diesen Dateien zurück.
   - Nutzt den `ProcessBuilder` für den Datei-Upload mittels Curl, wobei auf den Dienst file.io zugegriffen wird.
   - Verarbeitet die HTTP-Antwort mit `org.json.JSONObject` zur Extraktion des Links aus dem response.
4. **Import von CSV-Daten in die Datenbank (importDataFromCSV)**
   - Die Methode `importDataFromCSV` liest CSV-Dateien und importiert die Daten zurück in die Datenbank.
   - Nutzt Java I/O-Klassen zur Dateileseoperation und JDBC für Datenbankeinsätze.
  
### MySQL-Datenbank in der Cloud

Die MySQL-Datenbank wurde auf einer Virtuellen Maschine auf DigitalOcean installiert und bringt einen entscheidenden Vorteil für die Bereitstellung und den Betrieb der Anwendung: Es entfällt die Notwendigkeit, eine Datenbank lokal zu installieren und zu konfigurieren. Diese zentrale Datenbanklösung ist die Voraussetzung zur Verteilung unserer JAR-Datei, die als finale Anwendungsabgabe dient. Benutzer der Anwendung müssen keine lokale Datenbanksetup durchführen, was die Installation und Inbetriebnahme deutlich vereinfacht.

Dieser Ansatz bietet mehrere praktische Vorteile:

1. **Einfachere Konfiguration und Deployment**: Da die Datenbank bereits in der Cloud gehostet wird, können Entwickler und Benutzer die Anwendung direkt starten, ohne sich um die Datenbankinstallation kümmern zu müssen. Dies reduziert die Komplexität und mögliche Fehlerquellen bei der Einrichtung.
2. **Geringere lokale Ressourcenanforderungen**: Da die Datenbank auf einer VM bei DigitalOcean läuft, werden lokale Ressourcen wie Speicherplatz und Rechenkapazität geschont.
3. **Zentralisierte Datenverwaltung**: Alle Daten werden zentral verwaltet und (Test-)Daten müssen nicht je Client manuell auf einem Rechner gespeichert werden.

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


## Code Qualität

### PMD Integration

PMD ist ein statisches Analysetool, das im Projekt-Workflow eingebunden ist, um Code-Qualitätsstandards zu gewährleisten. Es dient der frühzeitigen Erkennung von potenziellen Fehlern, schlechten Praktiken und Optimierungsmöglichkeiten im Code.

- **Einrichtung**: In der GitHub Actions Workflow-Datei wird PMD als Schritt definiert. Die Checks der veränderten Dateien werden automatisch bei jedem Push- oder Pull-Request auf jeder Branch ausgeführt.
- **Konfiguration**: PMD wird so konfiguriert, dass es die Java-Quelldateien im Verzeichnis `src/main/java` analysiert. Die Regeln für die Code-Analyse werden in einer zur Verfügung gestellten `ruleset.xml`-Datei definiert.
- **Ausführung**: Bei der Ausführung scannt PMD den Code gemäß den festgelegten Regeln und identifiziert Code-Muster, die verbessert werden können, um die Code-Qualität zu erhöhen.

### SonarQube Integration

SonarQube ist ein fortschrittliches Tool zur statischen Code-Analyse, das in den Workflow integriert ist, um umfassende Code-Reviews zu ermöglichen. Es deckt ein breiteres Spektrum an Code-Qualitätsaspekten ab, einschließlich Bugs, Code-Smells, Sicherheitslücken und Testabdeckung.

- **Einrichtung**: SonarQube wird als Schritt in der GitHub Actions Workflow-Datei eingebunden. Es ist konfiguriert, um bei jedem Push- oder Pull-Request auf den allen Branches ausgeführt zu werden.
- **Build und Analyse**: Der Schritt `Build and analyze` führt Maven-Befehle aus, um das Projekt zu bauen und gleichzeitig eine SonarQube-Analyse durchzuführen. Hierfür werden sowohl das `GITHUB_TOKEN` als auch das `SONAR_TOKEN` verwendet.
- **Häufige Bugs**: Ein häufig identifizierter Bug durch SonarQube ist das Fehlen eines `finally`-Blocks in `try-catch`-Konstrukten, was notwendig ist, um Ressourcen wie Datenquellen korrekt zu schließen und Ressourcenlecks zu vermeiden.

### Wichtigkeit dieser Integrationen

- **Frühzeitige Fehlererkennung**: Beide Tools helfen dabei, Fehler und potenzielle Probleme im Code zu identifizieren, bevor sie in die main branch gelangen.
- **Automatisierte Code-Reviews**: Die Integration ermöglicht kontinuierliche und automatisierte Code-Reviews, die die Codequalität und die Effizienz des Entwicklungsprozesses verbessern.
