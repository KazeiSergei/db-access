package com.courses.db.dao;


import com.courses.db.DBConnection;
import com.courses.db.DaoException;
import com.courses.db.dto.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao {


    private Connection connection;
    private PreparedStatement preparedStatementGetAllSubject;
    private PreparedStatement preparedStatementInsertSubject;
    private PreparedStatement preparedStatementDelateSubject;
    private PreparedStatement preparedStatementUpdateSubject;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatementGetSubjectById;
    static private  final String SQL_QUERY_GET_ALL_SUBJECT = "select id,subject_name from subject";
    static private  final String SQL_QUERY_GET_SUBJECT = "select id,subject_name from subject where id = ?";
    static private final String SQL_QUERY_INSERT_SUBJECT = "insert into subject (subject_name) values (?)";
    static private final String SQL_QUERY_DELATE_SUBJECT = "delete from subject where id = ?";
    static private final String SQL_QUERY_UPDATE_SUBject = "update subject set subject_name = ? where id = ?";


    public SubjectDao() throws DaoException {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public void close() throws DaoException {
        try {
            if (preparedStatementDelateSubject != null) {
                preparedStatementDelateSubject.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementGetAllSubject != null) {
                preparedStatementGetAllSubject.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementInsertSubject != null) {
                preparedStatementInsertSubject.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementUpdateSubject != null) {
                preparedStatementUpdateSubject.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }

        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception se) {
            throw new DaoException(se);
        }
    }

    public Subject getSubjectById(int key) {

        Subject subject = null;

        try {
            preparedStatement = getPreparedStatementGetSubjectById();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String subject_name = resultSet.getString("subject_name");
                subject = new Subject(id, subject_name);

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

        return subject;
    }
    private PreparedStatement getPreparedStatementGetSubjectById() throws SQLException {
        if (preparedStatementGetSubjectById == null) {
            preparedStatementGetSubjectById = connection.prepareStatement(SQL_QUERY_GET_SUBJECT);
        }
        return preparedStatementGetSubjectById;
    }

    public List<Subject> getAllSudject() {

        List<Subject> result = new ArrayList<>();

        preparedStatement = getPreparedStatementGetAllSubject();

        ResultSet resultSet = null;
        try {

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("subject_name");
                result.add(new Subject(id, name));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        return result;
    }
    private PreparedStatement getPreparedStatementGetAllSubject() throws DaoException {
        try {
            if (preparedStatementGetAllSubject == null) {
                preparedStatementGetAllSubject = connection.prepareStatement(SQL_QUERY_GET_ALL_SUBJECT);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementGetAllSubject;
    }

    public void insertSubject(Subject subject) {

        preparedStatement = getPreparedStatementInsertSubject();

        try {

            preparedStatement.setString(1, subject.getName());
            preparedStatement.execute();

        } catch (Exception e) {
            throw new DaoException(e);
        }

    }
    private PreparedStatement getPreparedStatementInsertSubject() {
        try {
            if (preparedStatementInsertSubject == null) {
                preparedStatementInsertSubject = connection.prepareStatement(SQL_QUERY_INSERT_SUBJECT);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementInsertSubject;
    }

    public void delateSubject(int id)  {
        preparedStatement = getPreparedStatementDelateSubject();
        try {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }
    private PreparedStatement getPreparedStatementDelateSubject() throws DaoException {
        try {
            if (preparedStatementDelateSubject == null) {
                preparedStatementDelateSubject = connection.prepareStatement(SQL_QUERY_DELATE_SUBJECT);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementDelateSubject;
    }

    public void updateSubject(Subject subject, int id)  {

        preparedStatement = getPreparedStatementUpdateSubject();
        try {

            preparedStatement.setString(1, subject.getName());
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private PreparedStatement getPreparedStatementUpdateSubject() throws DaoException {
        try {
            if (preparedStatementUpdateSubject == null) {
                preparedStatementUpdateSubject = connection.prepareStatement(SQL_QUERY_UPDATE_SUBject);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementUpdateSubject;
    }
}
