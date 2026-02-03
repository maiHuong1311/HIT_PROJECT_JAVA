package dao;

import constant.ErrorContraint;
import constant.SuccessfullQuery;
import model.Exercise;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.sql.PreparedStatement;

public class ExDao {
    public List<Exercise> findAll()  {
        Connection connection = DBConnection.getConnection();
        List<Exercise> list = new ArrayList<>();
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        try{

            String sql = "SELECT * FROM Exercise";
            preparedStatement = connection.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                Exercise exercise = new Exercise();
                exercise.setId(resultSet.getString("id"));
                exercise.setContent(resultSet.getString("content"));
                exercise.setLessonId(resultSet.getInt("lessonId"));
                exercise.setSampleAnswer(resultSet.getString("sampleAnswer"));
                list.add(exercise);
            }
            System.out.println("Show successfully");
        }
        catch(SQLException e){
            System.err.println(ErrorContraint.FIND_ERROR);
        }
        finally{
            if (preparedStatement != null){
                try{
                    preparedStatement.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }

            }
            if(resultSet != null){
                try {
                    resultSet.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            if(connection != null){
                DBConnection.close(connection);
            }

        }
        return list;
    }
    public void insert(Exercise exercise) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = "INSERT INTO Exercise(id, content, lessonId, sampleAnswer)" +
                    "VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, exercise.getId());
            preparedStatement.setString(2, exercise.getContent());
            preparedStatement.setInt(3, exercise.getLessonId());
            preparedStatement.setString(4, exercise.getSampleAnswer());
            int result = preparedStatement.executeUpdate();
            if (result >0){
                System.out.println(SuccessfullQuery.INSERT);
            }

        }
        catch(SQLException e){
            System.err.println(ErrorContraint.INSERT_ERROR);
            e.printStackTrace();
        }
        finally{
            if (preparedStatement != null){
                try{
                    preparedStatement.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                DBConnection.close(connection);
            }
        }
    }
    public void update(Exercise exercise) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = "UPDATE Exercise" + "SET lessonId = ?, sampleAnswer = ?, content = ?" + "WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, exercise.getLessonId());
            preparedStatement.setString(2, exercise.getSampleAnswer());
            preparedStatement.setString(3, exercise.getContent());
            preparedStatement.setString(4, exercise.getId());
            preparedStatement.executeUpdate();
            System.out.println(SuccessfullQuery.UPDATE);

        }
        catch(SQLException e){
            System.err.println(ErrorContraint.UPDATE_ERROR);
        }
        finally{
            if (preparedStatement != null){
                try{
                    preparedStatement.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                DBConnection.close(connection);
            }
        }
    }
    public void delete(String id) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = "DELETE FROM Exercise WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, id);
            int result = preparedStatement.executeUpdate();
            if(result > 0){
                System.out.println(SuccessfullQuery.DELETE);
            }
        }
        catch(SQLException e){
            System.err.println(ErrorContraint.DELETE_ERROR);
            e.printStackTrace();
        }
        finally{
            if (preparedStatement != null){
                try{
                    preparedStatement.close();
                }
                catch(SQLException e){
                    e.printStackTrace();
                }
            }
            if(connection != null){
                DBConnection.close(connection);
            }
        }
    }

}
