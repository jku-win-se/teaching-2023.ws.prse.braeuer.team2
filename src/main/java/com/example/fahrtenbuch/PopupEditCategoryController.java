package com.example.fahrtenbuch;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupEditCategoryController {
    public TextField editCategoryField;

    public void setEditCategoryField(String categoryField) {
        editCategoryField.setText(categoryField);
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    public void editCategory(ActionEvent event) {
    }
}
