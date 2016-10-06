package com.courses.db.dao;



import com.courses.db.DBConnection;
import com.courses.db.DaoException;
import com.courses.db.dto.Mark;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class MarkDao {
    private Connection connection;
    private PreparedStatement preparedStatementGetAllMark;
    private PreparedStatement preparedStatementInsertMark;
    private PreparedStatement preparedStatementDelateMark;
    private PreparedStatement preparedStatementUpdateMark;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatementGetMarkById;
    static private  final String SQL_QUERY_GET_MARK = "select id,student_id,subject_id,mark from mark where id = ?";
    static private final String SQL_QUERY_GET_ALL_MARK = "select id,mark,student_id,subject_id from mark";
    static private final String SQL_QUERY_INSERT_MARK = "insert into mark (student_id,subject_id,mark) values (?,?,?)";
    static private final String SQL_QUERY_DELETE_MARK = "delete from mark where id = ?";
    static private final String SQL_QUERY_UPDATE_MARK = "update mark set student_id = ?, subject_id = ?, mark = ?  where id = ?";

    public MarkDao() throws DaoException {
        DBConnection dbConnection = new DBConnection();
        connection = dbConnection.getConnection();
    }

    public void close() throws DaoException {
        try {
            if (preparedStatementDelateMark != null) {
                preparedStatementDelateMark.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementGetAllMark != null) {
                preparedStatementGetAllMark.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementInsertMark != null) {
                preparedStatementInsertMark.close();
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        try {
            if (preparedStatementUpdateMark != null) {
                preparedStatementUpdateMark.close();
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

    public Mark getMarktById(int key) {

        Mark mark = null;

        try {
            preparedStatement = getPreparedStatementGetMarkById();
        } catch (SQLException e) {
            throw new DaoException(e);
        }

        ResultSet resultSet = null;
        try {
            preparedStatement.setInt(1, key);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int student_id = resultSet.getInt("student_id");
                int subject_id = resultSet.getInt("subject_id");
                int mark_name = resultSet.getInt("mark");
                mark = new Mark(id,student_id,subject_id, mark_name);

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

        return mark;
    }
    private PreparedStatement getPreparedStatementGetMarkById() throws SQLException {
        if (preparedStatementGetMarkById == null) {
            preparedStatementGetMarkById = connection.prepareStatement(SQL_QUERY_GET_MARK);
        }
        return preparedStatementGetMarkById;
    }

    public List<Mark> getAllMark() {

        List<Mark> result = new ArrayList<>();

        preparedStatement = getPreparedStatementGetAllMark();

        ResultSet resultSet = null;
        try {

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int mark = resultSet.getInt("mark");
                int student_id = resultSet.getInt("student_id");
                int subject_id = resultSet.getInt("subject_id");
                result.add(new Mark(id, student_id, subject_id, mark));
            }
        } catch (Exception e) {
            throw new DaoException(e);
        }
        try {
            if (resultSet != null) {
                resultSet.close();
            }
        } catch (Exception se) {
            throw new DaoException(se);
        }
        return result;
    }

    private PreparedStatement getPreparedStatementGetAllMark() throws DaoException {
        try {
            if (preparedStatementGetAllMark == null) {
                preparedStatementGetAllMark = connection.prepareStatement(SQL_QUERY_GET_ALL_MARK);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementGetAllMark;
    }

    public void insertMark(Mark mark) {

        preparedStatement = getPreparedStatementInsertMark();

        try {

            preparedStatement.setInt(1, mark.getStudent_id());
            preparedStatement.setInt(2, mark.getSubject_id());
            preparedStatement.setInt(3, mark.getMark());
            preparedStatement.execute();

        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    private PreparedStatement getPreparedStatementInsertMark() throws DaoException {
        try {
            if (preparedStatementInsertMark == null) {
                preparedStatementInsertMark = connection.prepareStatement(SQL_QUERY_INSERT_MARK);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementInsertMark;
    }

    public void delateMark(int id) {
        preparedStatement = getPreparedStatementDelateMark();
        try {

            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (Exception e) {
            throw new DaoException(e);
        }

    }

    private PreparedStatement getPreparedStatementDelateMark() {
        try {
            if (preparedStatementDelateMark == null) {
                preparedStatementDelateMark = connection.prepareStatement(SQL_QUERY_DELETE_MARK);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementDelateMark;
    }

    public void updateMark(Mark markt, int id) {

        preparedStatement = getPreparedStatementUpdateMark();
        try {

            preparedStatement.setInt(1, markt.getStudent_id());
            preparedStatement.setInt(2, markt.getSubject_id());
            preparedStatement.setInt(3, markt.getMark());
            preparedStatement.setInt(4, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private PreparedStatement getPreparedStatementUpdateMark() throws DaoException {
        try {
            if (preparedStatementUpdateMark == null) {
                preparedStatementUpdateMark = connection.prepareStatement(SQL_QUERY_UPDATE_MARK);
            }
        } catch (SQLException se) {
            throw new DaoException(se);
        }
        return preparedStatementUpdateMark;
    }


}
