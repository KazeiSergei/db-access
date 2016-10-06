package com.courses.db.dao;

import com.courses.db.DBConnection;
import com.courses.db.DaoException;
import com.courses.db.dto.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class StudentDao {


    private Connection connection;
    private PreparedStatement preparedStatementGetAll;
    private PreparedStatement preparedStatementGetStudentWithMark;
    private PreparedStatement preparedStatementInsertStudent;
    private PreparedStatement preparedStatementDelateStudent;
    private PreparedStatement preparedStatementUpdateStudent;
    private PreparedStatement preparedStatementGetStudentById;
    private PreparedStatement preparedStatement;
    private static final String SQL_QUERY_INSERT_STUDENT = "insert into student (first_name,second_name) values (?,?)";
    private static final String SQL_QUERY_GET_STUDENT_BY_ID = "select id,first_name,second_name from student where id = ?";
    private static final String SQL_QUERY_UDATE_STUDENT = "update student set first_name = ?,second_name = ? where id = ?";
    private static final String SQL_QUERY_DELATE_STUDENT = "delete from student where id = ?";
    private static final String SQL_QUERY_GET_STUDENT_WITH_MARKS = "select student.id,student.first_name,student.second_name,subject.subject_name,mark.mark,mark.id from student inner join mark on student.id = mark.student_id inner join subject on mark.subject_id = subject.id where student.id = ?";
    private static final String SQL_QUERY_GET_ALL_STUDENT = "select id,first_name,second_name from student";
    public StudentDao() {
        DBConnection dbConnection = new DBConnection();
        try {
            connection = dbConnection.getConnection();
        } catch (DaoException e) {
            throw new RuntimeException(e);
        }

    }

    public void close() throws DaoException {
        try {
            if (preparedStatementDelateStudent != null) {
                preparedStatementDelateStudent.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementGetAll != null) {
                preparedStatementGetAll.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementGetStudentWithMark != null) {
                preparedStatementGetStudentWithMark.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementInsertStudent != null) {
                preparedStatementInsertStudent.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementUpdateStudent != null) {
                preparedStatementUpdateStudent.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
    }


    public List<Student> getAll() {

        List<Student> result = new ArrayList<>();

        try {
            preparedStatement = getPreparedStatementGetAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultSet = null;
        try {


            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                result.add(new Student(id, first_name, second_name));

            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }

        return result;
    }

    public Student getStudentById(int key) {

        Student student = null;

        try {
            preparedStatement = getPreparedStatementGetStudentById();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                student = new Student(id, first_name, second_name);

            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }

        return student;
    }

    public List<Student> getStudentWithMark(int key) {

        List<Student> result = new ArrayList<>();
        try {
            preparedStatement = getPreparedStatementGetStudentWithMark();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {

                int id = resultSet.getInt("id");
                String first_name = resultSet.getString("first_name");
                String second_name = resultSet.getString("second_name");
                String subject = resultSet.getString("subject_name");
                int mark = resultSet.getInt("mark");
                int markId = resultSet.getInt("id");
                result.add(new Student(id, first_name, second_name, subject, mark,markId));
            }

        } catch (Exception e) {
            throw new DaoException(e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
            } catch (SQLException se) {
                throw new DaoException(se);
            }
        }
        return result;
    }


    public void insertStudent(Student student) {

        try {
            preparedStatement = getPreparedStatementInsertStudent();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        try {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getSecondName());
            preparedStatement.execute();

        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    public void delateStudent(int id) {
        try {
            preparedStatement = getPreparedStatementDelateStudent();
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        try {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    public void updateStudent(Student student, int id) {

        try {
            preparedStatement = getPreparedStatementUpdateStudent();
        } catch (SQLException e) {
            throw new DaoException();
        }
        try {

            preparedStatement.setString(1, student.getFirstName());
            preparedStatement.setString(2, student.getSecondName());
            preparedStatement.setInt(3, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private PreparedStatement getPreparedStatementGetStudentById() throws SQLException {
        if (preparedStatementGetStudentById == null) {
            preparedStatementGetStudentById = connection.prepareStatement(SQL_QUERY_GET_STUDENT_BY_ID);
        }
        return preparedStatementGetStudentById;
    }

    private PreparedStatement getPreparedStatementInsertStudent() throws SQLException {
        if (preparedStatementInsertStudent == null) {
            preparedStatementInsertStudent = connection.prepareStatement(SQL_QUERY_INSERT_STUDENT);
        }

        return preparedStatementInsertStudent;
    }

    private PreparedStatement getPreparedStatementUpdateStudent() throws SQLException {
        if (preparedStatementUpdateStudent == null) {
            preparedStatementUpdateStudent = connection.prepareStatement(SQL_QUERY_UDATE_STUDENT);
        }

        return preparedStatementUpdateStudent;
    }

    private PreparedStatement getPreparedStatementDelateStudent() throws SQLException {
        if (preparedStatementDelateStudent == null) {
            preparedStatementDelateStudent = connection.prepareStatement(SQL_QUERY_DELATE_STUDENT);
        }

        return preparedStatementDelateStudent;
    }

    private PreparedStatement getPreparedStatementGetStudentWithMark() throws SQLException {
        if (preparedStatementGetStudentWithMark == null) {
            preparedStatementGetStudentWithMark = connection.prepareStatement(SQL_QUERY_GET_STUDENT_WITH_MARKS);
        }

        return preparedStatementGetStudentWithMark;
    }

    private PreparedStatement getPreparedStatementGetAll() throws SQLException {
        if (preparedStatementGetAll == null) {
            preparedStatementGetAll = connection.prepareStatement(SQL_QUERY_GET_ALL_STUDENT);
        }

        return preparedStatementGetAll;
    }

}

