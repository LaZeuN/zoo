package hu.zoo.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Adoption {


    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty supporter = new SimpleStringProperty(this, "supporter");
    private StringProperty supported = new SimpleStringProperty(this, "supported");
    private ObjectProperty<LocalDate> adoptionDate = new SimpleObjectProperty<>(this, "adoptionDate");
    private StringProperty typeOfSupport = new SimpleStringProperty(this, "typeOfSupport");
    private StringProperty amountOfSupport = new SimpleStringProperty(this, "amountOfSupport");
    private StringProperty frequencyOfSupport = new SimpleStringProperty(this, "frequencyOfSupport");
    private IntegerProperty supporterId = new SimpleIntegerProperty(this, "supporter_id");
    private IntegerProperty animalId = new SimpleIntegerProperty(this, "animal_id");

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getSupporter() {
        return supporter.get();
    }

    public StringProperty supporterProperty() {
        return supporter;
    }

    public void setSupporter(String supporter) {
        this.supporter.set(supporter);
    }

    public String getSupported() {
        return supported.get();
    }

    public StringProperty supportedProperty() {
        return supported;
    }

    public void setSupported(String supported) {
        this.supported.set(supported);
    }

    public LocalDate getAdoptionDate() {
        return adoptionDate.get();
    }

    public ObjectProperty<LocalDate> adoptionDateProperty() {
        return adoptionDate;
    }

    public void setAdoptionDate(LocalDate adoptionDate) {
        this.adoptionDate.set(adoptionDate);
    }

    public String getTypeOfSupport() {
        return typeOfSupport.get();
    }

    public StringProperty typeOfSupportProperty() {
        return typeOfSupport;
    }

    public void setTypeOfSupport(String typeOfSupport) {
        this.typeOfSupport.set(typeOfSupport);
    }

    public String getFrequencyOfSupport() {
        return frequencyOfSupport.get();
    }

    public StringProperty frequencyOfSupportProperty() {
        return frequencyOfSupport;
    }

    public void setFrequencyOfSupport(String frequencyOfSupport) {
        this.frequencyOfSupport.set(frequencyOfSupport);
    }

    public String getAmountOfSupport() {
        return amountOfSupport.get();
    }

    public StringProperty amountOfSupportProperty() {
        return amountOfSupport;
    }

    public void setAmountOfSupport(String amountOfSupport) {
        this.amountOfSupport.set(amountOfSupport);
    }

    public int getSupporterId() {
        return supporterId.get();
    }

    public IntegerProperty supporterIdProperty() {
        return supporterId;
    }

    public void setSupporterId(int supporterId) {
        this.supporterId.set(supporterId);
    }

    public int getAnimalId() {
        return animalId.get();
    }

    public IntegerProperty animalIdProperty() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId.set(animalId);
    }
}
