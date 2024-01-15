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

## Code Qualität
