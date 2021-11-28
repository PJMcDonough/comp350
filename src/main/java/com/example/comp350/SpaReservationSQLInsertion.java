package com.example.comp350;

import java.sql.*;

public class SpaReservationSQLInsertion {

    private static Statement statement;
    private static Connection conn;

    public SpaReservationSQLInsertion(Statement st, Connection connection) {

        statement = st;
        conn = connection;
    }
    private static void inputTime(PreparedStatement ps, int index, double time) throws SQLException {
        int hour = (int) time;
        double min = time - hour;
        ps.setTime(index, Time.valueOf(hour + ":" + min));
    }

    private static void inputFullName(PreparedStatement ps, String fName, String lName) throws SQLException
    {
        //first and last name is always the 1st two
        ps.setString(0, fName);
        ps.setString(1, lName);

    }

    public void insertSpaReservationSQL(String firstName, String lastName, double startTime, double endTime)
            throws SQLException {

        String query = "{CALL makeReservation(?, ?, ?, ?)}";
        statement.executeUpdate(query);

        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        inputFullName(preparedStmt, firstName, lastName);

        inputTime(preparedStmt, 3, startTime);
        inputTime(preparedStmt, 5, endTime);

        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void insertCardToCustomer(int customerId,int cardId, int cardNum,int expDate,
                                            short cvv,String provider,float amount) throws SQLException {
        String query = "{CALL addCardToCustomer(?, ?, ?, ?, ?, ?, ?)}";
        statement.executeUpdate(query);

        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(query);

        preparedStmt.setInt(0, customerId);
        preparedStmt.setInt(1, cardId);
        preparedStmt.setInt(2, cardNum);
        preparedStmt.setInt(3, expDate);
        preparedStmt.setInt(4, cvv);
        preparedStmt.setString( 5, provider);
        preparedStmt.setFloat(6, amount);

        // execute the preparedstatement
        preparedStmt.execute();
    }

    public void insertNewEmployee(String firstName, String lastName, String job, int payroll,int loginNum) throws SQLException {
        String query = "{CALL addNewEmployee(?, ?, ?, ?, ?)}";
        statement.executeUpdate(query);

        // create the mysql insert prepared statement
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        inputFullName(preparedStmt, firstName, lastName);
        preparedStmt.setString(2, job);
        preparedStmt.setInt(3, payroll);
        preparedStmt.setInt( 4, loginNum);

        // execute the prepared statement
        preparedStmt.execute();
    }
}
