package com.example.comp350;

import java.sql.*;

public class SpaReservationSQLDisplay {

    private static Statement statement;
    private static Connection conn;

    public SpaReservationSQLDisplay(Statement st,Connection connection) {

        statement = st;
        conn = connection;
    }

    private static void printName(ResultSet rs) throws SQLException
    {
        System.out.println("Customer: " + rs.getInt(1));
        System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
        getSpaSQL(rs);
        System.out.println("Starting Time: " + rs.getString(4));
        System.out.println("Duration: " + (Integer.parseInt(rs.getString(4)) - Integer.parseInt(rs.getString(6))));
    }

    private static void printTime(ResultSet rs) throws SQLException
    {
        System.out.printf("Starting Time: %s\tEnding Time: %s\n", rs.getString(4),rs.getString(6));
        System.out.println("Name: " + rs.getString(2) + " " + rs.getString(3));
        System.out.println("Duration: " + (Integer.parseInt(rs.getString(4)) - Integer.parseInt(rs.getString(6))));

    }

    private static void getSpaSQL(ResultSet rs) throws SQLException {
        String query = "{CALL displayCustomerSpa(?)}";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        preparedStmt.setString(1, String.valueOf(rs.getInt("SPA_ID")));

        //Gets all the attributes from the spa table
        System.out.println("Spa: " + rs.getString("SPA_Type"));
        System.out.println("Special: " + rs.getString("SPA_Type"));
        System.out.println("Price: " + rs.getFloat("SPA_Cost"));
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

    public void displayNames() throws SQLException {
        String query = "{CALL displayReservationByName()}";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next())
            printName(rs);
    }

    public void displayTimes() throws SQLException {
        String query = "{CALL displayReservationsBySTime()}";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next())
            printName(rs);

    }

    public void displayCustomerUnavailable()throws SQLException {

        String query = "{CALL displayUnavailableTime()}";
        ResultSet rs = statement.executeQuery(query);
        while(rs.next())
            printTime(rs);
    }

   public void displayCustomer(String firstName, String lastName) throws SQLException {
        String query = "{CALL displayCustomerReservation(?,?)}";
       PreparedStatement preparedStmt = conn.prepareStatement(query);
       inputFullName(preparedStmt,firstName,lastName);

       ResultSet rs = statement.executeQuery(query);
       printName(rs);

   }
   public void displayCustomerTime(double time) throws SQLException {
        String query = "{CALL displayCustomerTimeReservation(?)}";
        PreparedStatement preparedStmt = conn.prepareStatement(query);
        inputTime(preparedStmt,0,time);

       ResultSet rs = statement.executeQuery(query);
       printTime(rs);
    }

     public void displayCustomerSpa(int customerId)throws SQLException {

        String query = "{CALL displayCustomerSpa(?)}";
         PreparedStatement preparedStmt = conn.prepareStatement(query);
         inputNumber(preparedStmt,customerId);

        getSpaSQL(statement.executeQuery(query));

    }
}