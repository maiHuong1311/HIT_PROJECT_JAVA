package dao;

import constant.ErrorContraint;
import constant.SuccessfullQuery;
import model.Subject;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {
    public List<Subject> findAll() {
        List<Subject> list = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM Subject";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Subject subject = new Subject();
                subject.setId(resultSet.getString("id"));
                subject.setName(resultSet.getString("name"));
                subject.setTotalQuestions(resultSet.getInt("totalQuestions"));
                subject.setTotalExercises(resultSet.getInt("totalExercises"));
                list.add(subject);
            }
            System.out.println("Show successfully");

        } catch (SQLException e) {
            System.err.println(ErrorContraint.FIND_ERROR);
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    public void insert(Subject subject) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Subject(id, name, totalQuestions, totalExercises) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, subject.getId());
            preparedStatement.setString(2, subject.getName());
            preparedStatement.setInt(3, subject.getTotalQuestions());
            preparedStatement.setInt(4, subject.getTotalExercises());

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println(SuccessfullQuery.INSERT);
            }

        } catch (SQLException e) {
            System.err.println(ErrorContraint.INSERT_ERROR);
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Subject subject) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Subject SET name = ?, totalQuestions = ?, totalExercises = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, subject.getTotalQuestions());
            preparedStatement.setInt(3, subject.getTotalExercises());
            preparedStatement.setString(4, subject.getId());

            preparedStatement.executeUpdate();
            System.out.println(SuccessfullQuery.UPDATE);

        } catch (SQLException e) {
            System.err.println(ErrorContraint.UPDATE_ERROR);
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(String id) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "DELETE FROM Subject WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);

            int result = preparedStatement.executeUpdate();
            if (result > 0) {
                System.out.println(SuccessfullQuery.DELETE);
            }

        } catch (SQLException e) {
            System.err.println(ErrorContraint.DELETE_ERROR);
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) DBConnection.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
