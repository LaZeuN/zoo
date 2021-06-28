package hu.zoo.controller;

import hu.zoo.App;
import hu.zoo.dao.*;
import hu.zoo.model.Adoption;
import hu.zoo.model.Animal;
import hu.zoo.model.Supporter;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class MainWindowController implements Initializable {


    private SupporterDAO supporterDAO = new SupporterDAOImpl();
    private List<Supporter> allSupporters;
    @FXML
    private TableView<Supporter> supporterTable;
    @FXML
    private TableColumn<Supporter, String> supporterNameColumn;
    @FXML
    private TableColumn<Supporter, String> supporterEmailColumn;
    @FXML
    private TableColumn<Supporter, Void> supporterActionsColumn;



    private AnimalDAO animalsDao = new AnimalDAOImpl();
    private List<Animal> allAnimals;
    @FXML
    private TableView<Animal> animalsTable;
    @FXML
    private TableColumn<Animal, String> animalNameColumn;
    @FXML
    private TableColumn<Animal, String> animalSpeciesColumn;
    @FXML
    private TableColumn<Animal, String> animalIntroductionColumn;
    @FXML
    private TableColumn<Animal, String> animalYearOfBirthColumn;
    @FXML
    private TableColumn<Animal, Void> animalActionsColumn;



    private AdoptionDAO adoptionDao = new AdoptionDAOImpl();
    private List<Adoption> allAdoptions;
    @FXML
    private TableView<Adoption> adoptionsTable;
    @FXML
    public TableColumn<Adoption, String> supporterColumn;
    @FXML
    public TableColumn<Adoption, String> supportedColumn;
    @FXML
    private TableColumn<Adoption, String> adoptionDateColumn;
    @FXML
    private TableColumn<Adoption, String> typeOfSupportColumn;
    @FXML
    private TableColumn<Adoption, String> amountOfSupportColumn;
    @FXML
    private TableColumn<Adoption, String> frequencyOfSupportColumn;


    @FXML
    private TextField supporterNameSearch;
    @FXML
    private TextField supporterEmailSearch;
    @FXML
    public void onSupporterSearch() {
        List<Supporter> filtered = allSupporters.stream().filter(supporter -> supporter.getName().contains(supporterNameSearch.getText())
                && supporter.getEmail().contains(supporterEmailSearch.getText())).collect(Collectors.toList());
        supporterTable.getItems().setAll(filtered);
    }


    @FXML
    private TextField animalNameSearch;
    @FXML
    private TextField animalSpeciesSearch;

    @FXML
    private CheckBox adoptedCheckBox;

    @FXML
    private TextField typeOfSupportSearch;

    @FXML
    private TextField supporterNameInAdoptionSearch;
    @FXML
    private TextField supportedNameInAdoptionSearch;
    @FXML
    private TextField yearOfAdoptionSearch;

    @FXML
    public void onAnimalSearch() {
        List<Animal> filtered = allAnimals.stream().filter(animal -> animal.getName().contains(animalNameSearch.getText()) && animal.getSpecies().contains(animalSpeciesSearch.getText())).collect(Collectors.toList());
        animalsTable.getItems().setAll(filtered);
    }

    @FXML
    public void onIsAdopted() {
        if (adoptedCheckBox.isSelected()) {
            List<Animal> filtered = allAnimals.stream().filter(animal -> animal.getIsAdopted().contains("1")).collect(Collectors.toList());
            animalsTable.getItems().setAll(filtered);
        } else {
            List<Animal> filtered = allAnimals.stream().filter(animal -> animal.getIsAdopted().contains("0")).collect(Collectors.toList());
            animalsTable.getItems().setAll(filtered);
        }
    }


    @FXML
    public void onAdoptionsSearch(){
        List<Adoption> filtered = allAdoptions.stream().filter(adoption -> adoption.getSupporter().contains(supporterNameInAdoptionSearch.getText())
                && adoption.getSupported().contains(supportedNameInAdoptionSearch.getText())
                && adoption.getTypeOfSupport().contains(typeOfSupportSearch.getText())
                && adoption.getAdoptionDate().toString().contains(yearOfAdoptionSearch.getText())).collect(Collectors.toList());
        adoptionsTable.getItems().setAll(filtered);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        refreshTable();
        onIsAdopted();
        supporterNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        supporterEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));


        animalNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        animalSpeciesColumn.setCellValueFactory(new PropertyValueFactory<>("species"));
        animalIntroductionColumn.setCellValueFactory(new PropertyValueFactory<>("introduction"));
        animalYearOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("yearOfBirth"));


        supporterColumn.setCellValueFactory(new PropertyValueFactory<>("supporter"));
        supportedColumn.setCellValueFactory(new PropertyValueFactory<>("supported"));
        adoptionDateColumn.setCellValueFactory(new PropertyValueFactory<>("adoptionDate"));
        typeOfSupportColumn.setCellValueFactory(new PropertyValueFactory<>("typeOfSupport"));
        amountOfSupportColumn.setCellValueFactory(new PropertyValueFactory<>("amountOfSupport"));
        frequencyOfSupportColumn.setCellValueFactory(new PropertyValueFactory<>("frequencyOfSupport"));


        supporterActionsColumn.setCellFactory(param -> new TableCell<>() {

            private final Button deleteSupporterBtn = new Button("Delete");
            private final Button editSupporterBtn = new Button("Edit");

            {
                deleteSupporterBtn.setOnAction(event -> {
                    Supporter s = getTableRow().getItem();
                    deleteSupporter(s);
                    refreshTable();
                });

                editSupporterBtn.setOnAction(event -> {
                    Supporter s = getTableRow().getItem();
                    editSupporter(s);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if ( empty ) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(editSupporterBtn, deleteSupporterBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });



        animalActionsColumn.setCellFactory(param -> new TableCell<>() {

            private final Button deleteAnimalBtn = new Button("Delete");
            private final Button editAnimalBtn = new Button("Edit");

            {
                deleteAnimalBtn.setOnAction(event -> {
                    Animal a = getTableRow().getItem();
                    deleteAnimal(a);
                    refreshTable();
                });

                editAnimalBtn.setOnAction(event -> {
                    Animal a = getTableRow().getItem();
                    editAnimal(a);
                    refreshTable();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if ( empty ) {
                    setGraphic(null);
                } else {
                    HBox container = new HBox();
                    container.getChildren().addAll(editAnimalBtn, deleteAnimalBtn);
                    container.setSpacing(10.0);
                    setGraphic(container);
                }
            }
        });

    }

    private void editSupporter(Supporter s) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/edit_supporter.fxml");
        EditSupporterController controller = fxmlLoader.getController();
        controller.setSupporter(s);
    }

    private void deleteSupporter(Supporter s) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete supporter: " + s.getName(), ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(buttonType -> {
            if ( buttonType == buttonType.YES ) {
                supporterDAO.delete(s);
            }
        });

    }

    private void editAnimal(Animal a) {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_animal.fxml");
        AddEditAnimalController controller = fxmlLoader.getController();
        controller.setAnimal(a);
    }

    private void deleteAnimal(Animal a) {
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete animal: " + a.getName(), ButtonType.YES, ButtonType.NO);

        confirm.showAndWait().ifPresent(buttonType -> {
            if ( buttonType == buttonType.YES ) {
                animalsDao.delete(a);
            }
        });

    }

    private void refreshTable() {
        allSupporters = supporterDAO.findAll();
        supporterTable.getItems().setAll(allSupporters);

        allAnimals = animalsDao.findAll();
        animalsTable.getItems().setAll(allAnimals);

        allAdoptions = adoptionDao.findAll();
        adoptionsTable.getItems().setAll(allAdoptions);
    }

    @FXML
    public void onRefreshAllTables(){
        refreshTable();
    }

    @FXML
    public void onExit() {
        Platform.exit();
    }

    @FXML
    public void onAddNewSupporter() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_supporter.fxml");
        AddEditSupporterController controller = fxmlLoader.getController();
        controller.setSupporter(new Supporter());
    }

    @FXML
    public void onAddNewAnimal() {
        FXMLLoader fxmlLoader = App.loadFXML("/fxml/add_edit_animal.fxml");
        AddEditAnimalController controller = fxmlLoader.getController();
        controller.setAnimal(new Animal());
    }

}
