package hu.zoo.dao;

import hu.zoo.config.ZooConfiguration;
import hu.zoo.model.Animal;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AnimalDAOImpl implements AnimalDAO{

    private static final String SELECT_ALL_ANIMALS = "SELECT * FROM ANIMAL";
    private static final String INSERT_ANIMAL = "INSERT INTO ANIMAL (name, species, picture, introduction, yearOfBirth, isAdopted) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_ANIMAL = "UPDATE ANIMAL SET name=?, species=?, picture=?, introduction=?, yearOfBirth=?, isAdopted=? WHERE id=?";
    private static final String DELETE_ANIMAL = "DELETE FROM ANIMAL WHERE id = ?";
    private Properties props = new Properties();
    private static String connectionURL;
    private static AnimalDAOImpl instance;

    public AnimalDAOImpl() {
        connectionURL = ZooConfiguration.getValue("db.url");
    }

    public static AnimalDAO getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new AnimalDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Animal> findAll() {
        List<Animal> result = new ArrayList<>();


        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ANIMALS)
        ) {

            while(rs.next()) {
                Animal animal = new Animal();
                animal.setId(rs.getInt("id"));
                animal.setName(rs.getString("name"));
                animal.setSpecies(rs.getString("species"));
                animal.setIntroduction(rs.getString("introduction"));
                Date date = Date.valueOf(rs.getString("yearOfBirth"));
                animal.setYearOfBirth(date == null ? LocalDate.now() : date.toLocalDate());
                animal.setIsAdopted(rs.getString("isAdopted"));

                result.add(animal);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public Animal save(Animal animal) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = animal.getId() <= 0 ? c.prepareStatement(INSERT_ANIMAL, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_ANIMAL);
        ) {

            if (animal.getId() > 0) {//UPDATE
                stmt.setInt(7, animal.getId());
            }

            if ( animal.getId() <= 0 ) { animal.setIsAdopted("0"); }
            stmt.setString(1, animal.getName());
            stmt.setString(2, animal.getSpecies());
            stmt.setString(3, animal.getPicture());
            stmt.setString(4, animal.getIntroduction());
            stmt.setString(5, animal.getYearOfBirth().toString());
            stmt.setString(6, animal.getIsAdopted());

            int affectedRows = stmt.executeUpdate();
            if ( affectedRows == 0 ) {
                return null;
            }

            if ( animal.getId() <= 0 ) { //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if ( genKeys.next() ) {
                    animal.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return animal;
    }

    @Override
    public void delete(Animal animal) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_ANIMAL);
        ) {

            stmt.setInt(1, animal.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public Animal findById(int animalId) {
        return null;
    }
}
