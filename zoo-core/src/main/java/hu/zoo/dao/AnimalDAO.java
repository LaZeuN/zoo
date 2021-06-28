package hu.zoo.dao;

import hu.zoo.model.Animal;

import java.util.List;

public interface AnimalDAO {

    List<Animal> findAll();

    Animal save(Animal animal);

    void delete(Animal animal);

    Animal findById(int animalId);
}
