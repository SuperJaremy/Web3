package com.edu.Web3;

import lombok.Data;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import java.sql.*;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

@ManagedBean(eager = true)
@ApplicationScoped
@Data
public class Database {
    BlockingQueue<Connection> connections;
    private final int CONNECTIONS_NUMBER = 4;

    @ManagedProperty(value = "#{data.username}")
    private String username;

    @ManagedProperty(value = "#{data.password}")
    private String password;

    @PostConstruct
    public void init() throws SQLException {
        connections = new ArrayBlockingQueue<>(CONNECTIONS_NUMBER);
        for (int i = 0; i < CONNECTIONS_NUMBER; i++)
            connections.add(DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orbis", username, password));
    }

    @PreDestroy
    public void destroy() throws SQLException {
        for (Connection i : connections) {
            i.close();
        }
    }

    public boolean addEntity(String id, TableBean.Entity entity) {
        try {
            Connection connection = connections.take();
            try {
                PreparedStatement addStatement = prepareAddStatements(connection);
                addStatement.setString(1, id);
                addStatement.setDouble(2, entity.getPoint().getX().doubleValue());
                addStatement.setDouble(3, entity.getPoint().getY().doubleValue());
                addStatement.setDouble(4, entity.getPoint().getR().doubleValue());
                addStatement.setInt(5, entity.getHit() ? 1 : 0);
                addStatement.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            } finally {
                connections.put(connection);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean getEntities(String id, TableBean bean) {
        try {
            Connection connection = connections.take();
            try {
                PreparedStatement getStatement = prepareGetStatement(connection);
                List<TableBean.Entity> list = bean.getEntities();
                ResultSet resultSet = getStatement.executeQuery();
                while (resultSet.next()) {
                    list.add(new TableBean.Entity(new Point(resultSet.getBigDecimal("X"), resultSet.getBigDecimal("Y"), resultSet.getBigDecimal("R")), resultSet.getInt("RESULT") == 1));
                }
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            } finally {
                connections.put(connection);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean removeEntities(String id) {
        try {
            Connection connection = connections.take();
            try {
                PreparedStatement removeStatement = prepareRemoveStatement(connection);
                removeStatement.setString(1, id);
                removeStatement.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                return false;
            } finally {
                connections.put(connection);
            }
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private PreparedStatement prepareAddStatements(Connection connection) throws SQLException {
        return connection.prepareStatement("INSERT INTO \"Points\" VALUES (?,?,?,?,?)");
    }

    private PreparedStatement prepareRemoveStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("DELETE FROM \"Points\" where ID=?");
    }

    private PreparedStatement prepareGetStatement(Connection connection) throws SQLException {
        return connection.prepareStatement("SELECT * FROM \"Points\"");
    }

}
