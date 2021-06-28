package hu.zoo.model;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Animal {

    private IntegerProperty id = new SimpleIntegerProperty(this, "id");
    private StringProperty name = new SimpleStringProperty(this, "name");
    private StringProperty species = new SimpleStringProperty(this, "species");
    private StringProperty picture = new SimpleStringProperty(this, "picture");
    private StringProperty introduction = new SimpleStringProperty(this, "introduction");
    private ObjectProperty<LocalDate> yearOfBirth = new SimpleObjectProperty<>(this, "yearOfBirth");
    private StringProperty isAdopted = new SimpleStringProperty(this, "isAdopted");


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getSpecies() {
        return species.get();
    }

    public StringProperty speciesProperty() {
        return species;
    }

    public void setSpecies(String species) {
        this.species.set(species);
    }

    public String getPicture() {
        return picture.get();
    }

    public StringProperty pictureProperty() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture.set(picture);
    }

    public String getIntroduction() {
        return introduction.get();
    }

    public StringProperty introductionProperty() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction.set(introduction);
    }

    public LocalDate getYearOfBirth() {
        return yearOfBirth.get();
    }

    public ObjectProperty<LocalDate> yearOfBirthProperty() {
        return yearOfBirth;
    }

    public void setYearOfBirth(LocalDate yearOfBirth) {
        this.yearOfBirth.set(yearOfBirth);
    }

    public String getIsAdopted() {
        return isAdopted.get();
    }

    public StringProperty isAdoptedProperty() {
        return isAdopted;
    }

    public void setIsAdopted(String isAdopted) {
        this.isAdopted.set(isAdopted);
    }
}
