package employees2;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeesDaoTest {

    EmployeesDao dao;
    MariaDbDataSource dataSource;

    @BeforeEach
    public void init(){
        MariaDbDataSource dataSource;
        try {
            dataSource = new MariaDbDataSource();
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees2?useUnicode=true");
            dataSource.setUser("employees2");
            dataSource.setPassword("employees2");
        } catch (SQLException se) {
            throw new IllegalStateException("Can not connect to database", se);
        }

        Flyway flyway = Flyway.configure().locations("/db/migration/employees2").dataSource(dataSource).load();
        flyway.clean();

        flyway.migrate();


        dao = new EmployeesDao(dataSource);
    }

    @Test
    void insertEmployee() {
        dao.insertEmployee("John Doe");
    }

    @Test
    void insertEmployeeAndGetKey() {
        assertEquals(5,dao.insertEmployeeAndGetKey("Bob Marley"));
    }

    @Test
    void selectAllEmployees() {
        List<String> names = dao.selectAllEmployees();
        assertEquals(4, names.size());
    }

    @Test
    void selectEmployeeById() {
        assertEquals("Jimmy Doe", dao.selectEmployeeById(3));
    }

    @Test
    void createEmployeesRight() {
        List<String> goodList = List.of("Ancsa","Atilla");
        dao.createEmployees(goodList);
        List<String> names = dao.selectAllEmployees();
        assertEquals(6, names.size());
    }

    @Test
    void createEmployeesBad() {
        List<String> badList = List.of("Valaki","Józsi", "x-man");
        dao.createEmployees(badList);
        List<String> names = dao.selectAllEmployees();
        assertEquals(4, names.size());
    }
}