package hu.zoo.dao;

import hu.zoo.model.Adoption;
import hu.zoo.model.Animal;
import hu.zoo.model.Supporter;

import java.util.List;

public interface AdoptionDAO {

    List<Adoption> findAllBySupporterIdAndAnimalId(Supporter supporter, Animal animal);
    List<Adoption> findAllBySupporterIdAndAnimalId(int supporterId, int animalId);
    List<Adoption> findAllBySupporterId(int supporterId);
    List<Adoption> findAll();

    Adoption save(Adoption adoption);
}
