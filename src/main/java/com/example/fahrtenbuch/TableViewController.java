package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.CategoryFacade;
import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.Category;
import com.example.fahrtenbuch.entities.Drive;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TableViewController{
    public TableColumn<Drive, String> categoryColumn;
    public TextField sumTextField;
    @FXML
    private TableColumn<Drive, Integer> yearColumn;
    @FXML
    private TableColumn<Drive, Integer> monthColumn;
    @FXML
    private TableColumn<Drive, Double> totalKilometersColumn;
    @FXML
    public TableView<Drive> kilometerTable;
    public TextField monatTextField;
    public TextField jahrTextField;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();
    private DriveFacade driveFacade;
    private DatabaseConnection databaseConnection;
    private Alert alert;

    private Category category;
    @FXML
    List<Drive> drives = new ArrayList<>();
    @FXML
    public ComboBox<String> kategoriesTF;


    public TableViewController() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();

        driveFacade = new DriveFacade();
    }
    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Index.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleFahrtenbucherPage(ActionEvent event) throws IOException {
        Drive drive = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()),Time.valueOf(LocalTime.now()), 3, 3.0);
        drives = driveFacade.getAllDrives();
        drives.add(drive);

        fahrtListe = FXCollections.observableArrayList(driveFacade.getAllDrives());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FahrtenbucherPage.fxml"));
        Parent overviewPage = loader.load();

        FahrtenbucherController fahrtenbucherController = loader.getController();
        fahrtenbucherController.setTableLogbook(fahrtListe);

        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleBtnDataAction (ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("DataAction.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Overview.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void initialize() {
        List<String> categoryNames = driveFacade.getAllCategoryNames();
        categoryNames.add(0, null);
        kategoriesTF.getItems().addAll(categoryNames);
    }


    @FXML
    private void filterBtnAct(ActionEvent event) {
        String yearText = jahrTextField.getText();
        String monthText = monatTextField.getText();
        String selectedCategory = kategoriesTF.getValue();

        int selectedYear = 0;
        int selectedMonth = 0;

        try {
            if (!yearText.isEmpty()) {
                selectedYear = Integer.parseInt(yearText);

                if (!yearText.matches("\\d{4}")) {
                    showAlert(Alert.AlertType.ERROR, "Ungültige Eingabe", "Bitte geben Sie ein gültiges Jahr im Format JJJJ ein.");
                    return;
                }
            }

            if (!monthText.isEmpty()) {
                selectedMonth = Integer.parseInt(monthText);

                if (selectedMonth < 1 || selectedMonth > 12) {
                    showAlert(Alert.AlertType.ERROR, "Ungültige Eingabe", "Bitte geben Sie einen Monat zwischen 1 und 12 ein.");
                    return;
                }
            }

            if (yearText.isEmpty() && monthText.isEmpty() && selectedCategory == null){
                showAlert(Alert.AlertType.ERROR, "Ungültige Eingabe", "Bitte geben Sie Werte ein, nach denen gefiltert werden soll.");
                return;
            }
        } catch (NumberFormatException e) {
            showAlert(Alert.AlertType.ERROR, "Ungültige Eingabe", "Bitte geben Sie gültige Zahlen für Jahr und Monat ein.");
            return;
        }

        if (selectedYear > 0 && monthText.isEmpty()){
            filterByYear(selectedYear);
            if (fahrtListe.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Keine Einträge gefunden", "Für die eingegebenen Daten wurden keine Einträge gefunden");
            }
        }

        if (selectedMonth > 0 && yearText.isEmpty()){
            filterByMonth(selectedMonth);
            if (fahrtListe.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Keine Einträge gefunden", "Für die eingegebenen Daten wurden keine Einträge gefunden");
            }
        }

        if (selectedYear > 0 && selectedMonth > 0){
            filterByYearAndMonth(selectedYear, selectedMonth);
            if (fahrtListe.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Keine Einträge gefunden", "Für die eingegebenen Daten wurden keine Einträge gefunden");
            }
        }

        if(selectedMonth > 0 && selectedYear > 0 && selectedCategory != null && !selectedCategory.isEmpty()){
            filterByYearMonthCategory(selectedYear, selectedMonth, selectedCategory);
        }

        if (selectedYear > 0 && monthText.isEmpty() && selectedCategory != null && !selectedCategory.isEmpty()) {
            filterByYearAndCategory(selectedYear, selectedCategory);
        }

        if (selectedMonth > 0 && yearText.isEmpty() && selectedCategory != null && !selectedCategory.isEmpty()) {
            filterByMonthAndCategory(selectedMonth, selectedCategory);
        }

        if (selectedCategory != null && !selectedCategory.isEmpty() && yearText.isEmpty() && monthText.isEmpty()) {
            filterByCategory(selectedCategory);
            if (fahrtListe.isEmpty()) {
                showAlert(Alert.AlertType.INFORMATION, "Keine Einträge gefunden", "Für die eingegebene Kategorie wurden keine Einträge gefunden");
            }
        }
        updateTable();

    }

    private void updateTable() {
        yearColumn.setCellValueFactory(cellData -> {
            Date driveDate = cellData.getValue().getDate();
            int year = driveDate.toLocalDate().getYear();
            return new SimpleObjectProperty<>(year);
        });

        monthColumn.setCellValueFactory(cellData -> {
            Date driveDate = cellData.getValue().getDate();
            int month = driveDate.toLocalDate().getMonthValue();
            return new SimpleObjectProperty<>(month);
        });

        categoryColumn.setCellValueFactory(cellData -> {
            StringProperty categoryValue = new SimpleStringProperty();
            Integer driveId = cellData.getValue().getDriveId();
            if (driveId != null) {
                String categoryName = driveFacade.getCategoryNameByDriveId(driveId);
                categoryValue.set(categoryName);
            }
            return categoryValue;
        });

        totalKilometres();
        totalKilometersColumn.setCellValueFactory(new PropertyValueFactory<>("drivenKilometres"));
    }

    public void totalKilometres(){
        Map<YearMonth, List<Drive>> drivesByMonth = fahrtListe.stream()
                .collect(Collectors.groupingBy(drive -> YearMonth.from(drive.getDate().toLocalDate())));

        List<Drive> resultDrives = drivesByMonth.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());

        kilometerTable.setItems(FXCollections.observableArrayList(resultDrives));

        double totalKm = resultDrives.stream()
                .filter(drive -> drive.getDate() != null)
                .mapToDouble(Drive::getDrivenKilometres)
                .sum();

        sumTextField.setText(String.valueOf(totalKm));
    }

    public List<Drive> filterByYear(int selectedYear) {
        Predicate<Drive> filterPredicate = drive -> {
            LocalDate driveDate = drive.getDate().toLocalDate();
            return driveDate.getYear() == selectedYear;
        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
        return fahrtListe;
    }

    public List<Drive> filterByMonth(int selectedMonth) {
        Predicate<Drive> filterPredicate = drive -> {
            LocalDate driveDate = drive.getDate().toLocalDate();
            return driveDate.getMonthValue() == selectedMonth;
        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
        return fahrtListe;
    }

    public List<Drive> filterByYearAndMonth(int selectedYear, int selectedMonth) {
        Predicate<Drive> filterPredicate = drive -> {
            LocalDate driveDate = drive.getDate().toLocalDate();
            return driveDate.getYear() == selectedYear && driveDate.getMonthValue() == selectedMonth;

        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
        return fahrtListe;
    }

    private void filterByYearAndCategory(int selectedYear, String selectedCategory) {
        Predicate<Drive> filterPredicate = drive -> {
            LocalDate driveDate = drive.getDate().toLocalDate();
            String category = driveFacade.getCategoryNameByDriveId(drive.getDriveId());
            return driveDate.getYear() == selectedYear && selectedCategory.equals(category);
        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
    }

    public List<Drive> filterByMonthAndCategory(int selectedMonth, String selectedCategory) {
        Predicate<Drive> filterPredicate = drive -> {
            LocalDate driveDate = drive.getDate().toLocalDate();
            String category = driveFacade.getCategoryNameByDriveId(drive.getDriveId());
            return driveDate.getMonthValue() == selectedMonth && selectedCategory.equals(category);
        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
        return fahrtListe;
    }

    public List<Drive> filterByYearMonthCategory(int selectedYear, int selectedMonth, String selectedCategory) {
        Predicate<Drive> filterPredicate = drive -> {
            LocalDate driveDate = drive.getDate().toLocalDate();
            String category = driveFacade.getCategoryNameByDriveId(drive.getDriveId());
            return driveDate.getYear() == selectedYear && driveDate.getMonthValue() == selectedMonth && selectedCategory.equals(category);
        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
        return fahrtListe;
    }

    public List<Drive> filterByCategory(String selectedCategory) {
        Predicate<Drive> filterPredicate = drive -> {
            String category = driveFacade.getCategoryNameByDriveId(drive.getDriveId());
            return selectedCategory.equals(category);
        };

        List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                .filter(filterPredicate)
                .collect(Collectors.toList());

        fahrtListe.setAll(filteredDrives);
        return fahrtListe;
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.show();
    }

}