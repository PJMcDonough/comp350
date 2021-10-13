package com.example.comp350;

import java.sql.*;

public class SpaReservationSQLRemove {

    private static Connection conn;

    public SpaReservationSQLRemove(Connection connection) {
        conn = connection;
    }

    private static void inputNumber(PreparedStatement ps,int number)
            throws SQLException {
        ps.setInt(0,number);
    }

    private static void inputFullName(PreparedStatement ps, String fName, String lName) throws SQLException
    {
        //first and last name is always the 1st two
        ps.setString(0, fName);
        ps.setString(1, lName);
    }

    private static void inputTime(PreparedStatement ps, int index, double time) throws SQLException {
        int hour = (int) time;
        double min = time - hour;
        ps.setTime(index, Time.valueOf(hour + ":" + min));
    }

   public void removeCustomer(String firstName, String lastName) throws SQLException {
        String query = "{CALL removeCustomerReservation(?,?)}";
       PreparedStatement preparedStmt = conn.prepareStatement(query);
       inputFullName(preparedStmt,firstName,lastName);

   }

   public void removeTime(double time) throws SQLException {
       String query = "{CALL removeCustomerTimeReservation(?)}";
       PreparedStatement preparedStmt = conn.prepareStatement(query);
       inputTime(preparedStmt,0,time);
   }

    public void removeCard(int cardId) throws SQLException {
        String query = "{CALL removeCreditCard(?)}";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        inputNumber(preparedStmt,cardId);
    }

    public void removeEmployee(String employeeFName,String employeeLName) throws SQLException {
        String query = "{CALL removeEmployeeByName(?,?)}";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        inputFullName(preparedStmt,employeeFName,employeeLName);
    }

}