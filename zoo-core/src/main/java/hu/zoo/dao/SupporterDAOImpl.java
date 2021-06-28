package hu.zoo.dao;

import hu.zoo.config.ZooConfiguration;
import hu.zoo.model.Supporter;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import at.favre.lib.crypto.bcrypt.BCrypt;

public class SupporterDAOImpl implements SupporterDAO{

    private static final String SELECT_ALL_SUPPORTER = "SELECT * FROM SUPPORTER";
    private static final String INSERT_SUPPORTER = "INSERT INTO SUPPORTER (name, email, password) VALUES (?,?,?)";
    private static final String UPDATE_SUPPORTER = "UPDATE SUPPORTER SET name=?, email=?, password=? WHERE id=?";
    private static final String DELETE_SUPPORTER = "DELETE FROM SUPPORTER WHERE id = ?";
    private Properties props = new Properties();
    private static String connectionURL;
    private static SupporterDAOImpl instance;

    public SupporterDAOImpl() {
        connectionURL = ZooConfiguration.getValue("db.url");
    }

    public static SupporterDAOImpl getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.sqlite.JDBC");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            instance = new SupporterDAOImpl();
        }
        return instance;
    }

    @Override
    public Supporter getSupporterById(int id) {
        try (Connection conn = DriverManager.getConnection(connectionURL);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM SUPPORTER WHERE id = ?")
        ) {
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                Supporter u = new Supporter();
                u.setId(rs.getInt(1));
                u.setName(rs.getString(2));
                u.setEmail(rs.getString(3));
                u.setPassword(rs.getString(4));
                return u;
            }

        } catch (SQLException e) {

            e.printStackTrace();
        }

        return null;
    }

    @Override
    public Supporter login(String email, String password) {

        try (Connection conn = DriverManager.getConnection(connectionURL);
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM SUPPORTER WHERE email = ?")
        ) {
            pst.setString(1, email);

            ResultSet rs = pst.executeQuery();
            if (rs.next()) {

                String dbPass = rs.getString("password");

                BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), dbPass);
                if(result.verified){
                    Supporter supporter = new Supporter();
                    supporter.setEmail(rs.getString("email"));
                    supporter.setPassword(rs.getString("password"));
                    supporter.setName(rs.getString("name"));
                    supporter.setId(rs.getInt("id"));
                    return supporter;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<Supporter> findAll() {
        List<Supporter> result = new ArrayList<>();


        try(Connection c = DriverManager.getConnection(connectionURL);
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(SELECT_ALL_SUPPORTER)
        ) {

            while(rs.next()) {
                Supporter supporter = new Supporter();
                supporter.setId(rs.getInt("id"));
                supporter.setName(rs.getString("name"));
                supporter.setEmail(rs.getString("email"));
                supporter.setPassword(rs.getString("password"));

                result.add(supporter);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    @Override
    public Supporter save(Supporter supporter) {

        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = supporter.getId() <= 0 ? c.prepareStatement(INSERT_SUPPORTER, Statement.RETURN_GENERATED_KEYS) : c.prepareStatement(UPDATE_SUPPORTER);
        ) {

            if (supporter.getId() > 0) {//UPDATE
                stmt.setInt(4, supporter.getId());
                stmt.setString(3, supporter.getPassword());
            } else { // INSERT

                    String hashedPwd = BCrypt.withDefaults().hashToString(12, supporter.getPassword().toCharArray());
                    stmt.setString(3, hashedPwd);
            }

            stmt.setString(1, supporter.getName());
            stmt.setString(2, supporter.getEmail());

            int affectedRows = stmt.executeUpdate();
            if ( affectedRows == 0 ) {
                return null;
            }

            if ( supporter.getId() <= 0 ) { //INSERT
                ResultSet genKeys = stmt.getGeneratedKeys();
                if ( genKeys.next() ) {
                    supporter.setId(genKeys.getInt(1));
                }
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
            return null;
        }

        return supporter;
    }

    @Override
    public void delete(Supporter supporter) {
        try (Connection c = DriverManager.getConnection(connectionURL);
             PreparedStatement stmt = c.prepareStatement(DELETE_SUPPORTER);
        ) {

            stmt.setInt(1, supporter.getId());
            stmt.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
