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

public class TableViewController {

    public TableColumn categoryColumn;
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
    @FXML
    List<Drive> drives = new ArrayList<>();
    @FXML
    public ComboBox<String> kategoriesTF;


    public TableViewController() {
        databaseConnection = new DatabaseConnection();
        databaseConnection.getConnection();
        alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Button-Klick");
        alert.setHeaderText(null);
        DialogPane dialogPane = alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        alert.setDialogPane(dialogPane);

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

    private void initializeCategoryDropdown() {
        CategoryFacade categoryFacade = new CategoryFacade();
        ObservableList<Category> categories = FXCollections.observableArrayList(categoryFacade.getAllCategories());

        ObservableList<String> categoryNames = FXCollections.observableArrayList();
        for (Category category : categories) {
            categoryNames.add(category.toString());
        }

        kategoriesTF.setItems(categoryNames);
    }


    @FXML
    private void initialize() {
        initializeCategoryDropdown();
    }



    @FXML
    private void filterBtnAct(ActionEvent event) {
        int selectedYear = Integer.parseInt(jahrTextField.getText());
        //int selectedMonth = Integer.parseInt(monatTextField.getText());
        int selectedMonth;

        try {
            selectedMonth = Integer.parseInt(monatTextField.getText());
        } catch (NumberFormatException e) {
            selectedMonth = 0;
        }

        if (selectedMonth == 0) {
            filterByYear(selectedYear);
        }else {
            final int finalSelectedMonth = selectedMonth;
            Predicate<Drive> filterPredicate = drive -> {
                LocalDate driveDate = drive.getDate().toLocalDate();
                return driveDate.getYear() == selectedYear && driveDate.getMonthValue() == finalSelectedMonth;
            };

            List<Drive> filteredDrives = driveFacade.getAllDrives().stream()
                    .filter(filterPredicate)
                    .collect(Collectors.toList());
            fahrtListe.setAll(filteredDrives);
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
        totalKilometres();
        totalKilometersColumn.setCellValueFactory(new PropertyValueFactory<>("drivenKilometres"));
    }

    public void totalKilometres(){
        Map<YearMonth, Double> monthlyKilometers = new HashMap<>();

        for (Drive drive : fahrtListe) {
            YearMonth yearMonth = YearMonth.from(drive.getDate().toLocalDate());
            double kilometers = monthlyKilometers.getOrDefault(yearMonth, 0.0);
            kilometers += drive.getDrivenKilometres();
            monthlyKilometers.put(yearMonth, kilometers);
        }

        ObservableList<Drive> list = monthlyKilometers.entrySet().stream()
                .map(entry -> {
                    Drive drive = new Drive();
                    drive.setDate(Date.valueOf(entry.getKey().atDay(1)));
                    drive.setDrivenKilometres(entry.getValue());
                    return drive;
                })
                .collect(Collectors.collectingAndThen(Collectors.toList(), FXCollections::observableArrayList));

        kilometerTable.setItems(list);
    }

    private void filterByYear(int selectedYear) {
        List<Drive> allDrives = driveFacade.getAllDrives();

        List<Drive> filteredDrives = allDrives.stream()
                .filter(drive -> drive.getDate().toLocalDate().getYear() == selectedYear)
                .collect(Collectors.toList());

        Map<Integer, List<Drive>> drivesByMonth = filteredDrives.stream()
                .collect(Collectors.groupingBy(drive -> drive.getDate().toLocalDate().getMonthValue()));

        List<Drive> resultDrives = new ArrayList<>();
        for (List<Drive> monthDrives : drivesByMonth.values()) {
            resultDrives.addAll(monthDrives);
        }
        fahrtListe.setAll(resultDrives);
    }

}
