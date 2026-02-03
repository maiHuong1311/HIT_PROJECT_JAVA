package dao;

import constant.ErrorContraint;
import constant.SuccessfullQuery;
import model.Exercise;
import model.MultipleChoiceQuestion;
import utils.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MCQDao {
    public List<MultipleChoiceQuestion> findAll()  {
        Connection connection = DBConnection.getConnection();
        List<MultipleChoiceQuestion> list = new ArrayList<>();
        PreparedStatement preparedStatement= null;
        ResultSet resultSet = null;
        try{

            String sql = "SELECT * FROM Exicrie";
            preparedStatement = connection.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                MultipleChoiceQuestion MCQ = new MultipleChoiceQuestion();
                MCQ.setOptionA(resultSet.getString("A"));
                MCQ.setOptionB(resultSet.getString("B"));
                MCQ.setOptionC(resultSet.getString("C"));
                MCQ.setOptionD(resultSet.getString("D"));
                MCQ.setCorrectAnswer(resultSet.getString("CorrecAW"));
                list.add(MCQ);
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
    public void insert(MultipleChoiceQuestion MCQ) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = "INSERT INTO question\n" +
                    "(content, option_a, option_b, option_c, option_d, correct_answer)\n" +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MCQ.getOptionA());
            preparedStatement.setString(2, MCQ.getOptionB());
            preparedStatement.setString(3, MCQ.getOptionC());
            preparedStatement.setString(4, MCQ.getOptionD());
            preparedStatement.setString(5, MCQ.getCorrectAnswer());

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
    public void update(MultipleChoiceQuestion MCQ) {
        PreparedStatement preparedStatement= null;
        Connection connection = DBConnection.getConnection();
        try{
            String sql = """
            UPDATE question
            SET content = ?,
                option_a = ?,
                option_b = ?,
                option_c = ?,
                option_d = ?,
                correct_answer = ?
            WHERE id = ?
        """;
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, MCQ.getOptionA());
            preparedStatement.setString(2, MCQ.getOptionB());
            preparedStatement.setString(3, MCQ.getOptionC());
            preparedStatement.setString(4, MCQ.getOptionD());
            preparedStatement.setString(5, MCQ.getCorrectAnswer());
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

}
