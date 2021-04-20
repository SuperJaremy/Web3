import com.edu.Web3.Database;
import com.edu.Web3.Point;
import com.edu.Web3.TableBean;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseTest {

    private static Database database;
    private static final String username = "";
    private static final String password = "";
    private static Connection connection;

    @BeforeAll
    @Test
    public static void setUp() throws SQLException {
        database = new Database();
        database.setUsername(username);
        database.setPassword(password);
        database.init();
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orbis",
                username, password);
    }

    @Test
    public void checkAddition() throws SQLException{
        Point point = new Point(BigDecimal.ZERO, BigDecimal.ZERO, BigDecimal.ZERO);
        TableBean.Entity entity = new TableBean.Entity(point,false);
        database.addEntity("TEST",entity);
        List<TableBean.Entity> entities = new ArrayList<>();

        Statement statement = connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM \"Points\" WHERE ID = 'TEST'");
        while(result.next()){
            Point point1 = new Point(result.getBigDecimal("X"),
                    result.getBigDecimal("Y"),result.getBigDecimal("R"));
            boolean hit = result.getInt("RESULT")==1;
            entities.add(new TableBean.Entity(point1, hit));
        }
        statement.executeQuery("DELETE FROM\"Points\" WHERE ID = 'TEST' ");
        Assertions.assertEquals(1,entities.size(),"Лишние объекты в базе");
        Assertions.assertEquals(entities.get(0),entity);
    }

    @Test
    public void checkSelection() throws SQLException{
        List<TableBean.Entity> entityListViaMethod = database.getEntities("");
        List<TableBean.Entity> entityListActual = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet set = statement.executeQuery("SELECT * FROM \"Points\"");
        while(set.next()){
            Point point1 = new Point(set.getBigDecimal("X"),
                    set.getBigDecimal("Y"),set.getBigDecimal("R"));
            boolean hit = set.getInt("RESULT")==1;
            entityListActual.add(new TableBean.Entity(point1, hit));
        }
        Assertions.assertArrayEquals(entityListViaMethod.toArray(),entityListActual.toArray(),
                "Полученные из базы данные не совпадают");
    }

    @AfterAll
    public static void closeUp() throws SQLException{
        connection.close();
        database.destroy();
    }
}
