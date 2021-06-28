package hu.zoo.controller;

import hu.zoo.App;
import hu.zoo.dao.SupporterDAO;
import hu.zoo.dao.SupporterDAOImpl;
import hu.zoo.model.Supporter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AddEditSupporterController implements Initializable {

    private Supporter supporter;
    private SupporterDAO supporterDAO = new SupporterDAOImpl();

    @FXML
    private TextField name;
    @FXML
    private TextField email;
    @FXML
    private TextField password;

    @FXML
    private Button saveSupporterBtn;

    @FXML
    public Label nameError;
    @FXML
    public Label emailError;
    @FXML
    public Label passwordError;

    public void setSupporter(Supporter s) {
        this.supporter = s;

        name.textProperty().bindBidirectional(supporter.nameProperty());
        email.textProperty().bindBidirectional(supporter.emailProperty());
        password.textProperty().bindBidirectional(supporter.passwordProperty());
    }

    @FXML
    public void onSupporterCancel() {
        App.loadFXML("/fxml/main_window.fxml");
    }

    @FXML
    public void onSupporterSave() {
        supporter = supporterDAO.save(supporter);
        App.loadFXML("/fxml/main_window.fxml");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        saveSupporterBtn.disableProperty().bind(name.textProperty().isEmpty().or(email.textProperty().isNull().or(password.textProperty().isEmpty())));

        name.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isEmpty()) {
                nameError.setText("Name is required");
            } else {
                nameError.setText("");
            }
        });

        email.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isEmpty()) {
                emailError.setText("Email is required");
            } else if (newValue != null && !newValue.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")) {
                emailError.setText("Invalid email");
            }else {
                emailError.setText("");
            }
        });

        password.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isEmpty()) {
                passwordError.setText("Password is required");
            } else {
                passwordError.setText("");
            }
        });
    }
}
