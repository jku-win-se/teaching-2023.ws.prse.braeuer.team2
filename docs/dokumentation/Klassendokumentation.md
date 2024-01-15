# Klassendokumentation

# CategoryDriveFacade Klasse

Die Klasse `CategoryDriveFacade` bietet Methoden für die Interaktion mit der Datenbank in Bezug auf Fahrten (Drives) und Kategorien. Hier sind die wichtigsten Methoden:

### Konstruktor

Der Konstruktor stellt eine Verbindung zur Datenbank (`DatabaseConnection`) her und speichert die Verbindung (`Connection conn`) in der Klasse. Die Verbindung wird verwendet, um auf die Datenbank zuzugreifen.

### `getDrivesByCategoryId(Integer id)`

Diese Methode gibt eine Liste von Fahrten (`List<Drive>`) zurück, die zu einer bestimmten Kategorie gehören, identifiziert durch die übergebene `id`.
- Ein neues Drive-Objekt (`Drive drive`) wird initialisiert.
- Eine Liste von Fahrten (`List<Drive> drives`) wird erstellt, um die Ergebnisse zu speichern.
- Ein DriveFacade-Objekt wird instanziiert, um auf die Drive-Entitäten zuzugreifen.
- Es wird eine SQL-Abfrage vorbereitet, um alle Einträge in der Tabelle `category_drive` abzurufen, die zu einer bestimmten Kategorie gehören.
- Die vorbereitete Anweisung wird mit der Kategorie-ID versehen, und die Abfrage wird ausgeführt.
- Das Ergebnis (eine Menge von Datensätzen) wird durchlaufen, und für jeden Datensatz wird die zugehörige Fahrt durch Aufruf der `getDriveById`-Methode des DriveFacade-Objekts abgerufen und zur Liste hinzugefügt.

### `persistCategoryDrive(CategoryDrive cd)`

Diese Methode fügt einen Eintrag in die Tabelle `category_drive` hinzu, um die Zuordnung zwischen einer Kategorie und einer Fahrt zu speichern.
- Die übergebene CategoryDrive-Instanz (`cd`) enthält Informationen zur Zuordnung.
- Eine SQL-Abfrage wird vorbereitet, um Werte in die Tabelle einzufügen.
- Es wird überprüft, ob die `drive_id` in der CategoryDrive-Instanz nicht null ist. Andernfalls wird eine `IllegalArgumentException` ausgelöst.
- Die vorbereitete Anweisung wird mit den Werten aus der CategoryDrive-Instanz versehen, und die Abfrage wird ausgeführt, um die Zuordnung zu speichern. Etwaige auftretende SQL-Ausnahmen werden abgefangen und ausgedruckt.

# CategoryFacade Klasse

### Konstruktor

Der Konstruktor erstellt eine Verbindung zur Datenbank (`DatabaseConnection`) und speichert die Verbindung (`Connection conn`) in der Klasse. Die Verbindung wird verwendet, um auf die Datenbank zuzugreifen.

### `getCategoryById(Integer id)`

Diese Methode gibt eine Kategorie zurück, die durch die übergebene `id` identifiziert wird.
- Es wird eine SQL-Abfrage vorbereitet, um die Kategorie mit einer bestimmten ID aus der Tabelle `category` abzurufen.
- Die vorbereitete Anweisung wird mit der Kategorie-ID versehen, und die Abfrage wird ausgeführt.
- Wenn ein Ergebnis vorhanden ist, wird eine neue Category-Instanz erstellt, mit den Werten aus der Abfrage gefüllt und zurückgegeben.

### `getCategoryByName(String categoryName)`

Diese Methode gibt eine Kategorie zurück, die durch den übergebenen `categoryName` identifiziert wird.
- Es wird eine SQL-Abfrage vorbereitet, um die Kategorie mit einem bestimmten Namen aus der Tabelle `category` abzurufen.
- Die vorbereitete Anweisung wird mit dem Kategorienamen versehen, und die Abfrage wird ausgeführt.
- Wenn ein Ergebnis vorhanden ist, wird eine neue Category-Instanz erstellt, mit den Werten aus der Abfrage gefüllt und zurückgegeben.

### `getAllCategories()`

Diese Methode gibt eine Liste aller Kategorien aus der Tabelle `category` zurück.
- Es wird eine SQL-Abfrage vorbereitet, um alle Kategorien abzurufen.
- Die vorbereitete Anweisung wird ausgeführt, und für jedes Ergebnis wird eine neue Category-Instanz erstellt, mit den Werten aus der Abfrage gefüllt und der Liste hinzugefügt.

### `persistCategory(Category c)`

Diese Methode fügt eine neue Kategorie zur Tabelle `category` hinzu.
- Es wird eine SQL-Abfrage vorbereitet, um Werte in die Tabelle einzufügen.
- Die vorbereitete Anweisung wird mit dem Namen der Kategorie (`Category`-Instanz) versehen, und die Abfrage wird ausgeführt, um die Kategorie zu speichern.

### `deleteCategoryById(Integer id)`

Diese Methode löscht eine Kategorie aus der Tabelle `category` basierend auf der übergebenen `id`.
- Es wird eine SQL-Abfrage vorbereitet, um die Kategorie mit einer bestimmten ID zu löschen.
- Die vorbereitete Anweisung wird mit der Kategorie-ID versehen, und die Abfrage wird ausgeführt, um die Kategorie zu löschen.

# DatabaseConnection Klasse

Die Klasse `DatabaseConnection` ist verantwortlich für die Datenbankverbindung und enthält Methoden zum Initialisieren der Datenbank, Exportieren/Importieren von Daten in CSV-Dateien und zum Hochladen von Dateien in die Cloud. Hier sind die einzelnen Methoden erklärt:

### `initiateConnection()`

Diese Methode initialisiert die Datenbankverbindung, indem sie die `getConnection()`-Methode aufruft.

### `getConnection()`

Erstellt und gibt eine Verbindung zur MySQL-Datenbank zurück.
Die Verbindungsparameter (URL, Benutzername, Passwort) sind hartcodiert.

### `initiateDB()`

Diese Methode initialisiert die Datenbank, indem sie Tabellen erstellt und Daten einfügt.
Es werden SQL-Anweisungen zum Löschen und Erstellen von Tabellen (`vehicle`, `drive`, `category`, `category_drive`) definiert.
Danach werden einige Beispiel-Daten über Fassadenklassen (`VehicleFacade`, `DriveFacade`, `CategoryFacade`, `CategoryDriveFacade`) in die Tabellen eingefügt.
Schließlich werden die Daten in CSV-Dateien exportiert und dann wieder importiert.

### `closeConnection()`

Schließt die Datenbankverbindung.

### `exportDataToCSV()`

Exportiert die Daten aus den Tabellen (`vehicle`, `drive`, `category`, `category_drive`) in CSV-Dateien.
Ruft die Methode `exportTableToCSV(tableName, filePath)` für jede Tabelle auf.

### `exportDataToCloud()`

Exportiert die Daten in CSV-Dateien und lädt sie in die Cloud hoch.
Verwendet die Methode `uploadFile(filePath)` für den Dateiupload.
Gibt die Links zu den hochgeladenen Dateien zurück.

### `uploadFile(String filePath)`

Lädt eine Datei mithilfe von Curl in die Cloud hoch.
Verwendet den Dienst file.io für den Dateiupload.
Gibt den Link zur hochgeladenen Datei zurück.

### `exportTableToCSV(String tableName, String filePath)`

Exportiert Daten aus der angegebenen Tabelle in eine CSV-Datei.
Die Daten werden mit einem Semikolon als Trennzeichen in die Datei geschrieben.

### `importDataFromCSV()`

Löscht die vorhandenen Daten in den Tabellen (`vehicle`, `drive`, `category`, `category_drive`).
Liest die Daten aus CSV-Dateien ein und fügt sie in die entsprechenden Tabellen ein.
Ruft die Methode `readCSV(tableName, filePath)` für jede Tabelle auf.

### `readCSV(String tableName, String filePath)`

Liest Daten aus einer CSV-Datei und fügt sie in die angegebene Tabelle ein.
Die Methode `insertRow(tableName, data)` wird für jeden Datensatz aufgerufen.

### `insertRow(String tableName, String[] data)`

Fügt eine Zeile (Datensatz) in die angegebene Tabelle ein.
Verwendet Prepared Statements und setzt die Werte entsprechend dem Datentyp.

Die Klasse kombiniert Datenbankoperationen, CSV-Datei-Handling und Cloud-Uploads, um eine Vielzahl von Aufgaben im Zusammenhang mit einem Fahrtenbuchsystem zu erfüllen.

# DriveFacade Klasse

Die Klasse `DriveFacade` ist für den Zugriff auf die Datenbank bezüglich Fahrten (Drives) verantwortlich. Hier sind die einzelnen Methoden erklärt:

### `getDriveById(Integer id)`

Gibt eine Fahrt (Drive) basierend auf der angegebenen ID zurück.
Die Methode führt eine SQL-Abfrage aus, um alle Details einer Fahrt aus der Datenbank zu erhalten und erstellt dann ein entsprechendes Drive-Objekt.

### `getDrivesByLicensePlate(String licensePlate)`

Gibt eine Liste von Fahrten zurück, die mit einem bestimmten Kennzeichen (`licensePlate`) verknüpft sind.
Die Methode führt eine SQL-Abfrage aus, die Join-Operationen verwendet, um Fahrten mit einem bestimmten Kennzeichen zu erhalten, und erstellt dann eine Liste von Drive-Objekten.

### `getAllDrives()`

Gibt eine Liste aller Fahrten in der Datenbank zurück.
Die Methode führt eine einfache SQL-Abfrage aus, um alle Fahrten zu erhalten, und erstellt dann eine Liste von Drive-Objekten.

### `persistDrive(Drive v)`

Fügt eine neue Fahrt in die Datenbank ein.
Überprüft, ob die Abfahrtszeit vor der Ankunftszeit liegt, wirft andernfalls eine `InvalidDriveException`.
Die Methode verwendet Prepared Statements, um sicherzustellen, dass Daten sicher in die Datenbank eingefügt werden.

### `persistRecurringDrive(Integer vehicleId, Date startDate, Date endDate, int interval)`

Fügt wiederkehrende Fahrten in die Datenbank ein.
Erstellt eine Fahrt für das Startdatum und fügt dann Fahrten in regelmäßigen Intervallen bis zum Enddatum ein.

### `deleteDriveById(Integer id)`

Löscht eine Fahrt basierend auf der angegebenen ID aus der Datenbank.

### `getLastDriveId()`

Gibt die ID der letzten in die Datenbank eingefügten Fahrt zurück.

### `getLicensePlateByDriveId(int dID)`

Gibt das Kennzeichen des Fahrzeugs für die angegebene Fahrt-ID zurück.

### `getCategoryNameByDriveId(int driveId)`

Gibt den Kategorienamen für die angegebene Fahrt-ID zurück.

### `filterDrivesWithQuery(String query, String category)`

Filtert Fahrten basierend auf einer dynamisch generierten SQL-Abfrage und einer optionalen Kategorie.
Wird für die Implementierung einer Filterfunktion auf einer Benutzeroberfläche verwendet.

### `getAllCategoryNames()`

Gibt eine Liste aller Kategorienamen aus der Datenbank zurück.

### `filterByStatus(String status)`

Filtert Fahrten basierend auf dem Status ("all" für alle Fahrten oder ein bestimmter Status).
Wird für die Implementierung einer Filterfunktion auf einer Benutzeroberfläche verwendet.

### `getAverageSpeedByDriveId(int driveId)`

Berechnet die durchschnittliche Geschwindigkeit für eine Fahrt basierend auf den gefahrenen Kilometern, Abfahrts- und Ankunftszeit.
Wird für die Anzeige der durchschnittlichen Geschwindigkeit in der Benutzeroberfläche verwendet.

### `calculateTimeDifferenceInSeconds(Time departureTime, Time arrivalTime, int waitingTime)`

Hilfsmethode zur Berechnung der Zeitdifferenz in Sekunden zwischen Abfahrt, Ankunft und Wartezeit.

Die Klasse bietet somit Methoden, um Fahrten abzurufen, zu persistieren, zu löschen und zu filtern, sowie zusätzliche Funktionen wie das Berechnen der durchschnittlichen Geschwindigkeit und das Abrufen von Informationen zu Kategorien und Kennzeichen.

# VehicleFacade Klasse

Die Klasse `VehicleFacade` ist für den Datenbankzugriff in Bezug auf Fahrzeuge (Vehicles) verantwortlich. Hier sind die einzelnen Methoden erklärt:

### `getVehicleById(Integer id)`

Gibt ein Fahrzeug (Vehicle) basierend auf der angegebenen ID zurück.
Die Methode führt eine SQL-Abfrage aus, um alle Details eines Fahrzeugs aus der Datenbank zu erhalten, und erstellt dann ein entsprechendes Vehicle-Objekt.

### `getAllVehicles()`

Gibt eine Liste aller Fahrzeuge in der Datenbank zurück.
Die Methode führt eine einfache SQL-Abfrage aus, um alle Fahrzeuge zu erhalten, und erstellt dann eine Liste von Vehicle-Objekten.

### `persistVehicle(Vehicle v)`

Fügt ein neues Fahrzeug in die Datenbank ein.
Die Methode verwendet Prepared Statements, um sicherzustellen, dass die Daten sicher in die Datenbank eingefügt werden.

### `deleteVehicleById(Integer id)`

Löscht ein Fahrzeug basierend auf der angegebenen ID aus der Datenbank.

### `getVehicleByLicensePlate(String licensePlate)`

Gibt ein Fahrzeug basierend auf dem angegebenen Kennzeichen (`licensePlate`) zurück.
Die Methode führt eine SQL-Abfrage aus, um ein Fahrzeug mit einem bestimmten Kennzeichen zu erhalten, und erstellt dann ein entsprechendes Vehicle-Objekt.

### `getVehicleIdByLicensePlate(String licensePlate)`

Gibt die ID eines Fahrzeugs basierend auf dem angegebenen Kennzeichen zurück.
Die Methode führt eine SQL-Abfrage aus, um die ID eines Fahrzeugs mit einem bestimmten Kennzeichen zu erhalten.

### `VehicleFacade()`

Der Konstruktor der Klasse erstellt eine Verbindung zur Datenbank (`DatabaseConnection` wird verwendet), um spätere Datenbankoperationen durchzuführen.
Die Klasse bietet somit Methoden, um Fahrzeuge abzurufen, zu persistieren, zu löschen und basierend auf dem Kennzeichen oder der ID zu suchen.
