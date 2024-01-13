//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.example.fahrtenbuch;
import com.example.fahrtenbuch.business.CategoryFacade;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public class EditDriveController {
    private DriveFacade driveFacade;
    private ObservableList<Drive> fahrtListe = FXCollections.observableArrayList();
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private Alert alert;
    private Drive selectedDrive;
    @FXML
    private TextField datumTF;
    @FXML
    private TextField AbfahrtTF;
    @FXML
    private TextField ankunftTF;
    @FXML
    private TextField gefahreneKmTF;
    @FXML
    private TextField aktiveFahTF;
    @FXML
    private TextField kfzTF;

    @FXML
    public ComboBox<String> kategoriesTF;

    @FXML
    public void initialize() {
        initializeCategoryDropdown();
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


    public EditDriveController() {
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

    @FXML
    private void handleBtnOverview(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("Overview.fxml"));
        Parent root = (Parent)loader.load();
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void setDrive(Drive drive) {
        this.selectedDrive = drive;
        this.updateFields();
    }

    private void updateFields() {
        if (this.selectedDrive != null) {
            DriveFacade driveFacade = new DriveFacade();

            Date date = this.selectedDrive.getDate();
            Time departureTime = this.selectedDrive.getDepartureTime();
            Time arrivalTime = this.selectedDrive.getArrivalTime();
            Double drivenKilometres = this.selectedDrive.getDrivenKilometres();
            Integer waitingTime = this.selectedDrive.getWaitingTime();
            String currentCategory = driveFacade.getCategoryNameByDriveId(this.selectedDrive.getDriveId());
            this.kfzTF.setText(driveFacade.getLicensePlateByDriveId(this.selectedDrive.getDriveId()));
            this.AbfahrtTF.setText(departureTime != null ? departureTime.toString() : "");
            this.ankunftTF.setText(arrivalTime != null ? arrivalTime.toString() : "");
            this.gefahreneKmTF.setText(drivenKilometres != null ? drivenKilometres.toString() : "");
            this.aktiveFahTF.setText(waitingTime != null ? waitingTime.toString() : "");
            this.datumTF.setText(date != null ? date.toString() : "");
            this.kategoriesTF.setValue(currentCategory);
        }
    }

    @FXML
    private void deleteRide(ActionEvent event) throws IOException {

        DriveFacade driveFacade = new DriveFacade();
        driveFacade.deleteDriveById(this.selectedDrive.getDriveId());
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void saveChanges(ActionEvent event) throws IOException {

    }
}
