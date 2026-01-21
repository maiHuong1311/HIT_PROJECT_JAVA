package dao;

import constant.ErrorContraint;
import constant.SuccessfullQuery;
import model.Question;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao {

    public List<Question> findAll() {
        List<Question> list = new ArrayList<>();
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            String sql = "SELECT * FROM Question";
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Question q = new Question();
                q.setId(resultSet.getString("id"));
                q.setContent(resultSet.getString("content"));
                q.setLessonId(resultSet.getInt("lessonId"));
                list.add(q);
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

    public void insert(Question question) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "INSERT INTO Question(id, content, lessonId) VALUES (?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, question.getId());
            preparedStatement.setString(2, question.getContent());
            preparedStatement.setInt(3, question.getLessonId());

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

    public void update(Question question) {
        Connection connection = DBConnection.getConnection();
        PreparedStatement preparedStatement = null;

        try {
            String sql = "UPDATE Question SET content = ?, lessonId = ? WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, question.getContent());
            preparedStatement.setInt(2, question.getLessonId());
            preparedStatement.setString(3, question.getId());

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
            String sql = "DELETE FROM Question WHERE id = ?";
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
