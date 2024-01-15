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



## Code Qualität
