package employees2;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesDao {

    private DataSource dataSource;

    public EmployeesDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void insertEmployee(String name) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees(emp_name) VALUES (?);");) {
            stmt.setString(1, name);
            stmt.executeUpdate();
        } catch (
                SQLException se) {
            throw new IllegalStateException("Error by insert", se);
        }
    }

    public long insertEmployeeAndGetKey(String name) {
        try (
                Connection conn = dataSource.getConnection();
                PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees(emp_name) VALUES (?);", Statement.RETURN_GENERATED_KEYS);) {
            stmt.setString(1, name);
            stmt.executeUpdate();
            return executeAndGenerateKey(stmt);
        } catch (
                SQLException se) {
            throw new IllegalStateException("Error by insert", se);
        }
    }

    private long executeAndGenerateKey(PreparedStatement stmt) {
        try (ResultSet rs = stmt.getGeneratedKeys()) {
            if (rs.next()) {
                return rs.getLong("id");
            } else {
                throw new SQLException("No key has generated");
            }
        } catch (SQLException se) {
            throw new IllegalStateException("Error by insert", se);
        }
    }

    public List<String> selectAllEmployees() {
        List<String> names = new ArrayList<>();
        try (Connection conn = dataSource.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT emp_name FROM`employees`;");) {
            while (rs.next()) {
                String name = rs.getString("emp_name");
                names.add(name);

            }
            return names;
        } catch (SQLException se) {
            throw new IllegalStateException("Error by select", se);
        }
    }

    public String selectEmployeeById(long id) {

        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT `emp_name` FROM`employees` WHERE id=?;");) {
            stmt.setLong(1,id);

             return getStringByPreparedStatement(stmt);

        } catch (SQLException se) {
            throw new IllegalStateException("Error by select", se);
        }

    }

    private String getStringByPreparedStatement(PreparedStatement stmt) {
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                String name = rs.getString("emp_name");
                return name;
            }
            throw new IllegalArgumentException("No results");
        } catch (SQLException se) {
            throw new IllegalStateException("Error by select", se);

        }
    }
    public void createEmployees(List<String> names){
       try( Connection conn = dataSource.getConnection()){
           conn.setAutoCommit(false);

           try(PreparedStatement stmt = conn.prepareStatement("INSERT INTO employees(emp_name) VALUES (?);");){
               for(String item : names){
                   if(item.startsWith("x")){
                       throw new IllegalArgumentException("Invalid name");
                   }
                   stmt.setString(1, item);
                   stmt.executeUpdate();
               }
               conn.commit();
           }
           catch (IllegalArgumentException iae) {
               conn.rollback();
           }
       } catch (SQLException se) {
           throw new IllegalStateException("Can not insert", se);
       }
    }

}

