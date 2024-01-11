package com.example.fahrtenbuch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class PopupRemoveCategoryController {

    private String selectedCategoryName;
    @FXML
    private Label selectedCategory;

    public void setRemoveCategoryName(String selectedCategoryName) {
        selectedCategory.setText('"' + selectedCategoryName + '"');
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void removeCategory(ActionEvent event) {
    }
}