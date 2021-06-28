package hu.zoo.dao;

import hu.zoo.config.ZooConfiguration;
import hu.zoo.model.*;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AdoptionDAOImpl implements AdoptionDAO{

    private static final String SELECT_ALL_ADOPTIONS_BY_ID = "SELECT * FROM ADOPTION WHERE supporter_id=?";
    private static final String SELECT_ALL_ADOPTIONS = "SELECT * FROM ADOPTION";
    private static final String INSERT_ADOPTION = "INSERT INTO ADOPTION (adoptionDate, typeOfSupport, amountOfSupport, frequencyOfSupport, supporter_id, animal_id, Supporter, Supported) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_ADOPTION = "UPDATE ADOPTION SET adoptionDate=?, typeOfSupport=?, amountOfSupport=?, frequencyOfSupport=?, supporter_id=?, animal_id=?, Supporter=?, Supported=? WHERE id=?";
    private String connectionURL;
    private static AdoptionDAOImpl instance;

    public AdoptionDAOImpl() {
        this.connectionURL = ZooConfiguration.getValue("db.url");
    }

    public static AdoptionDAO getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new AdoptionDAOImpl();
        }
        return instance;
    }

    @Override
    public List<Adoption> findAll() {
        List<Adoption> result = new ArrayList<>();


        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ADOPTIONS)
        ) {

            while(rs.next()) {
                Adoption adoption = new Adoption();
                adoption.setId(rs.getInt("id"));
                adoption.setSupporter(rs.getString("supporter"));
                adoption.setSupported(rs.getString("supported"));
                Date date = Date.valueOf(rs.getString("adoptionDate"));
                adoption.setAdoptionDate(date == null ? LocalDate.now() : date.toLocalDate());
                adoption.setTypeOfSupport(rs.getString("typeOfSupport"));
                adoption.setAmountOfSupport(rs.getString("amountOfSupport"));
                adoption.setFrequencyOfSupport(rs.getString("frequencyOfSupport"));
                adoption.setSupporterId(rs.getInt("supporter_id"));
                adoption.setAnimalId(rs.getInt("animal_id"));

                result.add(adoption);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public Adoption save(Adoption adoption) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = adoption.getId() <= 0 ? c.prepareStatement(INSERT_ADOPTION, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_ADOPTION);
        ) {

            if (adoption.getId() > 0) {//UPDATE
                stmt.setInt(9, adoption.getId());
            }

            stmt.setString(1, adoption.getAdoptionDate().toString());
            stmt.setString(2, adoption.getTypeOfSupport());
            stmt.setString(3, adoption.getAmountOfSupport());
            stmt.setString(4, adoption.getFrequencyOfSupport());
            stmt.setInt(5, adoption.getSupporterId());
            stmt.setInt(6, adoption.getAnimalId());
            stmt.setString(7, adoption.getSupporter());
            stmt.setString(8, adoption.getSupported());

            int affectedRows = stmt.executeUpdate();
            if ( affectedRows == 0 ) {
                return null;
            }

            if ( adoption.getId() <= 0 ) { //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if ( genKeys.next() ) {
                    adoption.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return adoption;
    }

    @Override
    public List<Adoption> findAllBySupporterIdAndAnimalId(Supporter supporter, Animal animal) {
        return findAllBySupporterIdAndAnimalId(supporter.getId(), animal.getId());
    }

    @Override
    public List<Adoption> findAllBySupporterIdAndAnimalId(int supporterId, int animalId) {
        List<Adoption> result = new ArrayList<>();


        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement stmt = c.prepareStatement(SELECT_ALL_ADOPTIONS);
        ) {
            stmt.setInt(1, supporterId);
            stmt.setInt(2, animalId);
            ResultSet rs = stmt.executeQuery(SELECT_ALL_ADOPTIONS_BY_ID);

            while(rs.next()) {
                Adoption adoption = new Adoption();
                adoption.setId(rs.getInt("id"));
                Date date = Date.valueOf(rs.getString("adoptionDate"));
                adoption.setAdoptionDate(date == null ? LocalDate.now() : date.toLocalDate());
                adoption.setTypeOfSupport(rs.getString("typeOfSupport"));
                adoption.setAmountOfSupport(rs.getString("amountOfSupport"));
                adoption.setFrequencyOfSupport(rs.getString("frequencyOfSupport"));
                adoption.setSupporter(rs.getString("supporter"));
                adoption.setSupported(rs.getString("supported"));


                result.add(adoption);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public List<Adoption> findAllBySupporterId(int supporterId) {
        List<Adoption> result = new ArrayList<>();

        try(Connection c = DriverManager.getConnection(connectionURL);
            PreparedStatement pstmt = c.prepareStatement(SELECT_ALL_ADOPTIONS_BY_ID)
        ) {
            pstmt.setInt(1,supporterId);
            ResultSet rs = pstmt.executeQuery();
            while(rs.next()) {
                Adoption adoption = new Adoption();
                adoption.setId(rs.getInt("id"));
                adoption.setSupporter(rs.getString("supporter"));
                adoption.setSupported(rs.getString("supported"));
                Date date = Date.valueOf(rs.getString("adoptionDate"));
                adoption.setAdoptionDate(date == null ? LocalDate.now() : date.toLocalDate());
                adoption.setTypeOfSupport(rs.getString("typeOfSupport"));
                adoption.setAmountOfSupport(rs.getString("amountOfSupport"));
                adoption.setFrequencyOfSupport(rs.getString("frequencyOfSupport"));
                adoption.setSupporterId(rs.getInt("supporter_id"));
                adoption.setAnimalId(rs.getInt("animal_id"));

                result.add(adoption);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public List<Adoption> findAllBySupporterId(Adoption adoption) {
        return findAllBySupporterId(adoption.getId());
    }

}
