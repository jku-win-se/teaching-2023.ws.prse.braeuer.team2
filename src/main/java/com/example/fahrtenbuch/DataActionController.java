//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.fahrtenbuch;

import com.example.fahrtenbuch.entities.*;

import com.example.fahrtenbuch.business.DatabaseConnection;
import com.example.fahrtenbuch.business.DriveFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DialogPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class DataActionController {
    private DriveFacade driveFacade;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Alert alert;
    List<Drive> drives = new ArrayList();


    public DataActionController() {
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
    private void handleDataExport() throws IOException {
        this.databaseConnection.exportDataToCSV();
        this.alert.setContentText("Daten wurden exportiert");
        this.alert.showAndWait();
    }

    @FXML
    private void handleDataImport() throws IOException {
        this.databaseConnection.importDataFromCSV();
        this.alert.setContentText("Daten wurden importiert");
        this.alert.showAndWait();
    }

    @FXML
    private void handleDataCloudExport() throws IOException, InterruptedException {
        this.alert.setContentText(this.databaseConnection.exportDataToCloud());
        this.alert.showAndWait();
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
    private void handleBtnOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Overview.fxml"));
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
}
