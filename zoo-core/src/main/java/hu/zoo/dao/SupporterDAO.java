package hu.zoo.dao;

import hu.zoo.model.Supporter;

import java.util.List;

public interface SupporterDAO {

    Supporter getSupporterById(int id);

    Supporter login(String email, String password);

    List<Supporter> findAll();

    Supporter save(Supporter supporter);

    void delete(Supporter supporter);
}
