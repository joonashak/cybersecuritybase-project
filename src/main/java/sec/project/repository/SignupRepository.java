package sec.project.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import sec.project.domain.Signup;

public class SignupRepository {
    
    public SignupRepository() {
        
        initializeDatabase();
    }

    private Connection connect() {
        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:h2:file:./database");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        return conn;
    }
    
    private void disconnect(Connection conn) {
        try {
            conn.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    private void initializeDatabase() {
        Connection conn = connect();
        
        try {
            String sql = "CREATE TABLE signups(name varchar(255),"
                    + "address varchar(255));";
            conn.createStatement().execute(sql);
            
            sql = "INSERT INTO signups SET name = 'Spock', address = 'Vulcan';";
            conn.createStatement().execute(sql);
            sql = "INSERT INTO signups SET name = 'James Kirk',"
                    + "address = 'U.S.S. Enterprise';";
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        disconnect(conn);
    }
    
    public List<Signup> findAll() {
        List<Signup> signups = new ArrayList<>();
        Connection conn = connect();
        
        try {
            ResultSet res = conn.createStatement()
                    .executeQuery("SELECT * FROM signups;");
            
            while (res.next()) {
                Signup signup = new Signup(res.getString("name"), 
                        res.getString("address"));
                signups.add(signup);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        disconnect(conn);
        
        return signups;
    }
    
    public List<Signup> findByName(String name) {
        List<Signup> signups = new ArrayList<>();
        Connection conn = connect();
        
        try {
            ResultSet res = conn.createStatement()
                    .executeQuery("SELECT * FROM signups WHERE name = '"
                            + name + "';");
            
            while (res.next()) {
                Signup signup = new Signup(res.getString("name"), 
                        res.getString("address"));
                signups.add(signup);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        
        disconnect(conn);
        
        return signups;
    }
    
    public void save(Signup signup) {
        Connection conn = connect();
        
        try {
            String sql = "INSERT INTO signups(name,address) VALUES('"
                    + signup.getName() + "','" + signup.getAddress() + "');";
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        disconnect(conn);
    }
    
    public void delete(String name) {
        Connection conn = connect();
        
        try {
            String sql = "DELETE FROM signups WHERE name = '"
                    + name + "' LIMIT 1;";
            conn.createStatement().execute(sql);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        disconnect(conn);
    }
}
