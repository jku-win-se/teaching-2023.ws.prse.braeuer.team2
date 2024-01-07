//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import com.example.fahrtenbuch.entities.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Year;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class OverviewController implements Initializable{
    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Button btnDataAction;

    @FXML
    private Button btnLogBook;

    @FXML
    private Button btnNewRide;

    @FXML
    private Button btnStart;

    @FXML
    private TextField durchschnittlicheGeschTextField;

    @FXML
    private TextField fahrtzeitTextField;

    @FXML
    private TextField gefahreneKmTextField;

    @FXML
    private TextField jahrTextField;

    @FXML
    private ComboBox<String> kategoryTF;

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private ImageView logoIcon;

    @FXML
    private TextField monatTextField;

    @FXML
    private TextField tagTextField;

    private DriveFacade driveFacade;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Alert alert;
    private List<Drive> driveList;

    public OverviewController() {
        this.databaseConnection.getConnection();
        this.alert = new Alert(AlertType.INFORMATION);
        this.alert.setTitle("Button-Klick");
        this.alert.setHeaderText((String)null);
        DialogPane dialogPane = this.alert.getDialogPane();
        dialogPane.setStyle("-fx-font-family: 'Arial'; -fx-font-size: 14;");
        this.alert.setDialogPane(dialogPane);
        this.driveFacade = new DriveFacade();
    }

    @FXML
    private void returnToStartBtn(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("hello-view.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void handleBtnDataAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("DataAction.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleNewRide(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Index.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void handleFahrtenbucherPage(ActionEvent event) throws IOException {
        Drive drive = new Drive(1, Date.valueOf(LocalDate.now()), Time.valueOf(LocalTime.now()), Time.valueOf(LocalTime.now()), 3, 3.0);
        List<Drive> drives = this.driveFacade.getAllDrives();
        drives.add(drive);
        this.fahrtListe = FXCollections.observableArrayList(this.driveFacade.getAllDrives());
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("FahrtenbucherPage.fxml"));
        Parent overviewPage = (Parent)loader.load();
        FahrtenbucherController fahrtenbucherController = (FahrtenbucherController)loader.getController();
        fahrtenbucherController.setTableLogbook(this.fahrtListe);
        Scene scene = new Scene(overviewPage);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }


    //new method for these next edition

    @FXML
    void filterBtnAct(ActionEvent event) {
        StringBuilder queryBuilder = new StringBuilder("SELECT * FROM drive WHERE 1=1");

        Double avgSpeed = parseTextFieldToDouble(durchschnittlicheGeschTextField.getText());
        Integer waitingTime = parseTextFieldToInt(fahrtzeitTextField.getText());
        Double drivenKm = parseTextFieldToDouble(gefahreneKmTextField.getText());
        Integer year = parseTextFieldToInt(jahrTextField.getText());
        Integer month = parseTextFieldToInt(monatTextField.getText());
        Integer day = parseTextFieldToInt(tagTextField.getText());
        String category = kategoryTF.getValue();

        if (year != null) {
            queryBuilder.append(" AND YEAR(drive_date) = ").append(year);
        }

        if (drivenKm != null) {
            queryBuilder.append(" AND driven_kilometres BETWEEN ").append(drivenKm - 0.1 * drivenKm)
                    .append(" AND ").append(drivenKm + 0.1 * drivenKm);
        }

        if (category != null && !category.isEmpty()) {
            queryBuilder.append(" AND drive_id IN (SELECT drive_id FROM category_drive cd JOIN category c ON cd.category_id = c.category_id WHERE c.category_name = ?)");
        }

        if (month != null) {
            queryBuilder.append(" AND MONTH(drive_date) = ").append(month);
        }

        if (avgSpeed != null) {
            queryBuilder.append(" AND ABS((driven_kilometres / (TIMESTAMPDIFF(SECOND, departure_time, arrival_time + INTERVAL waiting_time SECOND) / 3600)) - ")
                    .append(avgSpeed).append(") <= ").append(avgSpeed * 0.10);
        }

        if (waitingTime != null) {
            queryBuilder.append(" AND waiting_time = ").append(waitingTime);
        }

        //new addition
        if (day != null) {
            queryBuilder.append(" AND DAY(drive_date) = ").append(day);
        }

        driveList = driveFacade.filterDrivesWithQuery(queryBuilder.toString(), category);

        updateCheart(year);
    }

    private Integer parseTextFieldToInt(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private Double parseTextFieldToDouble(String text) {
        try {
            return Double.parseDouble(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }


    private void updateCheart(int year) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        List<Drive> filteredDrives = filterDrivesByYear(driveList, year);

        Map<String, Double> monthlyTotalKm = new LinkedHashMap<>();

        for (Drive drive : filteredDrives) {
            int month = drive.getDate().toLocalDate().getMonthValue();
            String label = getMonthLabel(month);

            monthlyTotalKm.merge(label, drive.getDrivenKilometres(), Double::sum);
        }

        for (Map.Entry<String, Double> entry : monthlyTotalKm.entrySet()) {
            series.getData().add(new XYChart.Data<>(entry.getKey(), entry.getValue()));
        }

        //set label
        lineChart.getXAxis().setLabel("Month");
        lineChart.getYAxis().setLabel("Total Driven Kilometers");


        lineChart.getData().clear();
        lineChart.getData().add(series);
    }

    private List<Drive> filterDrivesByYear(List<Drive> drives, int year) {
        return drives.stream()
                .filter(drive -> drive.getDate().toLocalDate().getYear() == year)
                .collect(Collectors.toList());
    }

    public String getMonthLabel(int month) {
        return switch (month) {
            case 1 -> "Jan";
            case 2 -> "Feb";
            case 3 -> "Mar";
            case 4 -> "Apr";
            case 5 -> "May";
            case 6 -> "Jun";
            case 7 -> "Jul";
            case 8 -> "Aug";
            case 9 -> "Sep";
            case 10 -> "Oct";
            case 11 -> "Nov";
            case 12 -> "Dec";
            default -> "Unknown";
        };
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<String> categoryNames = driveFacade.getAllCategoryNames();
        categoryNames.add(0, null);
        kategoryTF.getItems().addAll(categoryNames);

        driveList = driveFacade.getAllDrives();

        updateCheart(Year.now().getValue());
    }



}
