package com.example.fahrtenbuch;

import com.example.fahrtenbuch.business.CategoryFacade;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class PopupEditCategoryController {
    public TextField editCategoryField;

    private String oldName;

    public void setEditCategoryField(String categoryField) {
        editCategoryField.setText(categoryField);
        oldName = categoryField;
    }

    @FXML
    public void closePopup(ActionEvent actionEvent) {
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    public void editCategory(ActionEvent event) {
        String newName = editCategoryField.getText();

        if (!newName.isEmpty()) {
            CategoryFacade categoryFacade = new CategoryFacade();
            categoryFacade.updateCategoryName(oldName, newName);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        } else {
            showAlert(Alert.AlertType.ERROR, "Fehler", "Felder dürfen nicht leer sein.", "Bitte füllen Sie alle Felder aus.");
        }
    }

    private void showAlert(Alert.AlertType alertType, String title, String headerText, String contentText) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }
}
