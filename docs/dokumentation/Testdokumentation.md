# Testdokumentation

## Übersicht
Die Testklasse `DriveFacadeTest` enthält Tests für die Methoden der `DriveFacade-Klasse`. Diese Tests prüfen die Funktionalitäten der `DriveFacade-Klasse`, die den Zugriff auf die Datenbank bezüglich Fahrten (Drives) ermöglicht.

## Umgebung
- Sprache: Java
- Framework: JUnit
- Datenbank: MySQL

## Testfälle

### `testParseTextFieldToIntValidInput()`
**Beschreibung:** Dieser Test prüft, ob die Methode `parseTextFieldToInt()` gültige Eingaben korrekt in Integer umwandelt.

**Schritte:**
- Aufruf der Methode `parseTextFieldToInt()` mit einer gültigen Eingabe (z.B., "123").

**Erwartetes Ergebnis:** Die Methode gibt den erwarteten Integer-Wert zurück.

### `testParseTextFieldToIntInvalidInput()`
**Beschreibung:** Dieser Test prüft, ob die Methode `parseTextFieldToInt()` ungültige Eingaben korrekt handhabt.

**Schritte:**
- Aufruf der Methode `parseTextFieldToInt()` mit einer ungültigen Eingabe (z.B., "abc").

**Erwartetes Ergebnis:** Die Methode gibt null zurück.

### `testParseTextFieldToDoubleValidInput()`
**Beschreibung:** Dieser Test prüft, ob die Methode `parseTextFieldToDouble()` gültige Eingaben korrekt in Double umwandelt.

**Schritte:**
- Aufruf der Methode `parseTextFieldToDouble()` mit einer gültigen Eingabe (z.B., "123.45").

**Erwartetes Ergebnis:** Die Methode gibt den erwarteten Double-Wert zurück.

### `testParseTextFieldToDoubleInvalidInput()`
**Beschreibung:** Dieser Test prüft, ob die Methode `parseTextFieldToDouble()` ungültige Eingaben korrekt handhabt.

**Schritte:**
- Aufruf der Methode `parseTextFieldToDouble()` mit einer ungültigen Eingabe (z.B., "abc").

**Erwartetes Ergebnis:** Die Methode gibt null zurück.

### `testGetLicensePlateByDriveId()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getLicensePlateByDriveId()` das erwartete Kennzeichen für eine gegebene Fahrt-ID zurückgibt.

**Schritte:**
- Aufruf der Methode `getLicensePlateByDriveId()` mit einer bekannten Fahrt-ID (z.B., 1).

**Erwartetes Ergebnis:** Die Methode gibt das erwartete Kennzeichen zurück.

### `testGetDriveById()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getDriveById()` das erwartete Drive-Objekt für eine gegebene Fahrt-ID zurückgibt.

**Schritte:**
- Aufruf der Methode `getDriveById()` mit einer bekannten Fahrt-ID (z.B., 1).

**Erwartetes Ergebnis:** Die Methode gibt das erwartete Drive-Objekt zurück.

### `testGetDrivesByLicensePlate()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getDrivesByLicensePlate()` eine Liste von Fahrten für ein bestimmtes Kennzeichen zurückgibt.

**Schritte:**
- Aufruf der Methode `getDrivesByLicensePlate()` mit einem bekannten Kennzeichen (z.B., "testlicense").

**Erwartetes Ergebnis:** Die Methode gibt eine nicht leere Liste von Drive-Objekten zurück.

### `testGetLastDriveId()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getLastDriveId()` die ID der letzten eingefügten Fahrt zurückgibt.

**Schritte:**
- Aufruf der Methode `getLastDriveId()`.

**Erwartetes Ergebnis:** Die Methode gibt die erwartete ID zurück.

### `testGetCategoryNameByDriveId()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getCategoryNameByDriveId()` den erwarteten Kategorienamen für eine gegebene Fahrt-ID zurückgibt.

**Schritte:**
- Aufruf der Methode `getCategoryNameByDriveId()` mit einer bekannten Fahrt-ID (z.B., 1).

**Erwartetes Ergebnis:** Die Methode gibt den erwarteten Kategorienamen zurück.

### `testFilterDrivesWithQuery()`
**Beschreibung:** Dieser Test prüft, ob die Methode `filterDrivesWithQuery()` Fahrten basierend auf einer dynamisch generierten SQL-Abfrage und einer optionalen Kategorie filtert.

**Schritte:**
- Aufruf der Methode `filterDrivesWithQuery()` mit einer dynamisch generierten SQL-Abfrage und einer optionalen Kategorie.

**Erwartetes Ergebnis:** Die Methode gibt eine nicht leere Liste von Drive-Objekten zurück.

### `testFilterByStatus()`
**Beschreibung:** Dieser Test prüft, ob die Methode `filterByStatus()` Fahrten basierend auf dem Status filtert.

**Schritte:**
- Aufruf der Methode `filterByStatus()` mit einem bekannten Status (z.B., "COMPLETED").

**Erwartetes Ergebnis:** Die Methode gibt eine nicht leere Liste von Drive-Objekten zurück.

### `testGetAverageSpeedByDriveId()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getAverageSpeedByDriveId()` die erwartete durchschnittliche Geschwindigkeit für eine Fahrt zurückgibt.

**Schritte:**
- Aufruf der Methode `getAverageSpeedByDriveId()` mit einer bekannten Fahrt-ID (z.B., 1).

**Erwartetes Ergebnis:** Die Methode gibt die erwartete durchschnittliche Geschwindigkeit zurück.

## Überischt
Die Testklasse TableViewControllerTest enthält Tests für die Methoden der Klasse TableViewController, die die das Filtern nach Jahr, Monat und/oder Kategorie ermöglicht und die Einträge mit den jeweiligen gesamt gefahrenen km tabellarisch ausgibt.

## Umgebung
- Sprache: Java
- Framework: JUnit
- Datenbank: MySQL

## Testfälle

### `testFilterByYear()`
**Beschreibung:** Überprüft die Filterung von Fahrten nach einem bestimmten Jahr.

**Schritte:**
- Ruft `filterByYear()` mit dem ausgewählten Jahr auf.

**Erwartetes Ergebnis:** Nur die Fahrten des ausgewählten Jahres sind in der Liste enthalten.


### `testFilterByYearNoMatch()`
**Beschreibung:** Überprüft die Filterung, wenn keine Fahrten im ausgewählten Jahr vorhanden sind.

**Schritte:**
- Ruft `filterByMonth()` mit dem ausgewählten Monat auf.

**Erwartetes Ergebnis:** Die gefilterte Liste ist leer.
  

### `testFilterByMonth()`
**Beschreibung:** Überprüft die Filterung von Fahrten nach Monat.

**Schritte:**
- Ruft `filterByMonth()` mit dem ausgewählten Monat auf.
  
**Erwartetes Ergebnis:** Nur die Fahrten des ausgewählten Jahres sind in der Liste enthalten.


### `testFilterByMonthNoMatch()`
**Beschreibung:** Überprüft die Filterung, wenn keine Fahrten im ausgewählten Monat vorhanden sind.

**Schritte:**
- Ruft `filterByMonth()` mit einem Monat auf, für den keine Fahrten vorhanden sind.
  
**Erwartetes Ergebnis:** Die gefilterte Liste ist leer.


### `testFilterByYearAndMonth()`
**Beschreibung:** Überprüft die Filterung von Fahrten nach Jahr und Monat.

**Schritte:**
- Ruft `filterByYearAndMonth()` mit dem ausgewählten Jahr und Monat auf.
  
**Erwartetes Ergebnis:** Nur die Fahrten des ausgewählten Jahres und Monats sind in der Liste enthalten.


### `testFilterByYearAndMonthNoMatch()`
**Beschreibung:** Überprüft die Filterung, wenn keine Fahrten im ausgewählten Jahr und Monat vorhanden sind.

**Schritte:**
- Ruft `filterByYearAndMonth()` mit einem Jahr und Monat auf, für die keine Fahrten vorhanden sind.
  
**Erwartetes Ergebnis:** Nur die Fahrten des ausgewählten Jahres und Monats sind in der Liste enthalten.


### `testFilterByCategory()`
**Beschreibung:** Überprüft die Filterung von Fahrten nach Kategorie.

**Schritte:**
- Ruft `filterByCategory()` mit der ausgewählten Kategorie auf.
  
**Erwartetes Ergebnis:** Nur die Fahrten mit der ausgewählten Kategorie sind in der Liste enthalten.


### `testFilterByCategoryNoMatch()`
**Beschreibung:** Überprüft die Filterung, wenn keine Fahrten in der ausgewählten Kategorie vorhanden sind.

**Schritte:**
- Ruft `filterByCategory()` mit einer Kategorie auf, für die keine Fahrten vorhanden sind.
  
**Erwartetes Ergebnis:** Die gefilterte Liste ist leer.

## Ausführung
Die Tests wurden mittels JUnit auf einer lokal eingerichteten Entwicklungsumgebung ausgeführt und erfolgreich validiert.

## Übersicht

Die Testklasse `TestBackup` enthält Tests für die Methoden zum Import und Export von Daten in der Datenbank durch die `DatabaseConnection`-Klasse. Diese Tests prüfen die Funktionalität der Methoden `exportDataToCSV()` und `importDataFromCSV()`.

## Testfälle

### testExportData()

**Beschreibung:**
Dieser Test prüft, ob die Methode `exportDataToCSV()` Daten aus der Datenbank in CSV-Dateien exportiert.

**Schritte:**
1. Ausführung der Methode `exportDataToCSV()`.
2. Überprüfung, ob die erzeugten CSV-Dateien für Fahrzeuge (`vehicle.csv`), Kategorien (`category.csv`), Fahrten (`drive.csv`) und Kategorie-Fahrten-Beziehungen (`category_drive.csv`) existieren.

**Erwartetes Ergebnis:**
Alle CSV-Dateien werden erfolgreich erstellt.

### testImportData()

**Beschreibung:**
Dieser Test prüft, ob die Methode `importDataFromCSV()` Daten aus CSV-Dateien in die Datenbank importiert.

**Schritte:**
1. Erzeugung von Beispiel-CSV-Dateien (`vehicle.csv`, `category.csv`, `drive.csv`, `category_drive.csv`).
2. Löschen bereits vorhandener Backup-Dateien, falls vorhanden.
3. Import der Daten aus den CSV-Dateien in die Datenbank.
4. Zählen der Zeilen in den entsprechenden Datenbanktabellen (`vehicle`, `drive`, `category`, `category_drive`).

**Erwartetes Ergebnis:**
- Die Anzahl der Zeilen in den Tabellen `vehicle`, `drive`, `category` und `category_drive` entspricht den erwarteten Werten nach dem Import.

### Ausführung

Die Tests wurden mittels JUnit auf einer lokal eingerichteten Entwicklungsumgebung ausgeführt und erfolgreich validiert.


