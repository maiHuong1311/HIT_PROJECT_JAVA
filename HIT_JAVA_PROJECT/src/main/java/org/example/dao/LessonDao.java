package dao;

import constant.ErrorContraint;
import constant.SuccessfullQuery;
import model.Exercise;
import model.Lesson;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LessonDao {
    public List<Lesson> findAll()  {
        Connection connection = DBConnection.getConnection();
        List<Lesson> list = new ArrayList<>();
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        try{

            String sql = "SELECT * FROM Lesson";
            preparedStatement = connection.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                Lesson lesson = new Lesson();
                lesson.setId(resultSet.getInt("id"));
                lesson.setTitle(resultSet.getString("Title"));
                lesson.setQuestionNumber(resultSet.getInt("QuestionNumber"));
                lesson.setExerciseNumber(resultSet.getInt("ExerciseNumber"));
                lesson.setSubjectId(resultSet.getString("SubjectId"));
                list.add(lesson);
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
    public void insert(Lesson lesson) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = "INSERT INTO Lesson(Title, QuestionNumber, ExerciseNumber, SubjectId) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lesson.getTitle());
            preparedStatement.setInt(2, lesson.getQuestionNumber());
            preparedStatement.setInt(3, lesson.getExerciseNumber());
            preparedStatement.setString(4, lesson.getSubjectId());

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
    public void update(Lesson lesson) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = "UPDATE Lesson " + "SET Title = ?, " + "    QuestionNumber = ?, " + "    ExerciseNumber = ?, " +
                    "    SubjectId = ?" +
                    "WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, lesson.getTitle());
            preparedStatement.setInt(2, lesson.getQuestionNumber());
            preparedStatement.setInt(3, lesson.getExerciseNumber());
            preparedStatement.setString(4, lesson.getSubjectId());
            preparedStatement.setInt(5, lesson.getId());
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
    public void delete(int id) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();

        try{
            String sql = "DELETE FROM Lesson WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);

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
