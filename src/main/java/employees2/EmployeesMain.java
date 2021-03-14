package employees2;

import org.mariadb.jdbc.MariaDbDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeesMain {

    public static void main(String[] args) {
        MariaDbDataSource dataSource = new MariaDbDataSource();
        try {
            dataSource.setUrl("jdbc:mariadb://localhost:3306/employees2?useUnicode=true");
            dataSource.setUser("employees2");
            dataSource.setPassword("employees2");
        } catch (SQLException se) {
            throw new IllegalStateException("Can not connect to db", se);
        }

        EmployeesDao eDao = new EmployeesDao(dataSource);
//        eDao.insertEmployee("Jimmy Hendrix");
//        System.out.println(eDao.insertEmployeeAndGetKey("Bob Marley"));
//        System.out.println(eDao.selectAllEmployees());
        System.out.println(eDao.selectEmployeeById(10));
        List<String> goodList = List.of("Ancsa","Atilla");
        List<String> badList = List.of("Valaki","Józsi", "x-man");
        eDao.createEmployees(goodList);
        eDao.createEmployees(badList);




    }

}
