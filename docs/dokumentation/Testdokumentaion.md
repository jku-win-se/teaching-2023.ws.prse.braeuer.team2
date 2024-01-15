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

### `testPersistRecurringDrive()`
**Beschreibung:** Dieser Test prüft, ob die Methode `persistRecurringDrive()` wiederkehrende Fahrten erfolgreich in die Datenbank einfügt.

**Schritte:**
- Aufruf der Methode `persistRecurringDrive()` mit den erforderlichen Parametern.
- Überprüfung, ob die wiederkehrenden Fahrten in der Datenbank vorhanden sind.

**Erwartetes Ergebnis:** Die Methode fügt die wiederkehrenden Fahrten erfolgreich in die Datenbank ein.

### `testGetAverageSpeedByDriveId()`
**Beschreibung:** Dieser Test prüft, ob die Methode `getAverageSpeedByDriveId()` die erwartete durchschnittliche Geschwindigkeit für eine Fahrt zurückgibt.

**Schritte:**
- Aufruf der Methode `getAverageSpeedByDriveId()` mit einer bekannten Fahrt-ID (z.B., 1).

**Erwartetes Ergebnis:** Die Methode gibt die erwartete durchschnittliche Geschwindigkeit zurück.
