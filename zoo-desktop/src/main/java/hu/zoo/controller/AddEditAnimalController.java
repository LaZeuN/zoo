package hu.zoo.controller;

import hu.zoo.App;
import hu.zoo.dao.AnimalDAO;
import hu.zoo.dao.AnimalDAOImpl;
import hu.zoo.model.Animal;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


import java.io.FileInputStream;
import java.net.URL;
import java.util.ResourceBundle;

public class AddEditAnimalController implements Initializable {


    private Animal animal;
    private AnimalDAO animalDAO= new AnimalDAOImpl();

    @FXML
    public TextField name;
    @FXML
    public TextField species;
    @FXML
    public TextField picture;
    @FXML
    public TextField introduction;
    @FXML
    public DatePicker yearOfBirth;
    @FXML
    public Button saveAnimalBtn;
    @FXML
    public Label speciesError;
    @FXML

    public void setAnimal(Animal a) {
        this.animal = a;

        name.textProperty().bindBidirectional(animal.nameProperty());
        species.textProperty().bindBidirectional(animal.speciesProperty());
        picture.textProperty().bindBidirectional(animal.pictureProperty());
        introduction.textProperty().bindBidirectional(animal.introductionProperty());
        yearOfBirth.valueProperty().bindBidirectional(animal.yearOfBirthProperty());
    }

    public void onAnimalSave() {
        animal = animalDAO.save(animal);
        App.loadFXML("/fxml/main_window.fxml");
    }

    public void onAnimalCancel() { App.loadFXML("/fxml/main_window.fxml"); }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        saveAnimalBtn.disableProperty().bind(species.textProperty().isEmpty().or(yearOfBirth.valueProperty().isNull()));

        species.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null && newValue.isEmpty()) {
                speciesError.setText("Species is required");
            } else {
                speciesError.setText("");
            }
        });
    }

}
