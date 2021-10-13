package com.example.comp350;

import java.sql.*;

public class SpaReservationSQL {
    private static Connection conn;
    private static Statement statement;

    public SpaReservationSQL() throws SQLException {
        String url = "http://54.145.12.150";
        conn = DriverManager.getConnection(url, "root", "cicomp420");
        statement = conn.createStatement();
    }

    private static void getInsertionOperation(String op, String[]input) throws SQLException {
        SpaReservationSQLInsertion insertionSQL = new SpaReservationSQLInsertion(statement,conn);

        switch (op.toUpperCase())
        {
            case "EMPLOYEE":
                insertionSQL.insertNewEmployee(input[0],input[1],input[2],Integer.parseInt(input[3]),Integer.parseInt(input[4]));
                break;
            case "CARD":
                insertionSQL.insertCardToCustomer(Integer.parseInt(input[0]),Integer.parseInt(input[1]),
                        Integer.parseInt(input[2]),Integer.parseInt(input[3]),Short.parseShort(input[4]),input[5], Integer.parseInt(input[6]));
        }
    }

    /*public void getInsertionEmployeeOp(String fName, String lName, String jobType,int payroll)
    {
        new SpaReservationSQLInsertion(statement,conn).insertNewEmployee();
    }*/

    public void getInsertionCustomerOp(String fName, String lName, double sTime, double eTime) throws SQLException {
        new SpaReservationSQLInsertion(statement,conn).insertSpaReservationSQL(fName,lName,sTime,eTime);
    }

    public void getViewOperation(String op) throws SQLException {
        SpaReservationSQLDisplay displaySQL = new SpaReservationSQLDisplay(statement,conn);

        //Asks which operation to complete and executes with its specific procedures
        switch (op.toUpperCase())
        {
            case "NAME":
                displaySQL.displayNames();
                break;

            case "TIME":
                displaySQL.displayTimes();
                break;

            case "TAKEN":
                displaySQL.displayCustomerUnavailable();
                break;

            default:
                throw new IllegalStateException("Unexpected value: " + op.toUpperCase());
        }
    }

    public void getOperationName(String op, String FirstName, String LastName) throws SQLException {
        SpaReservationSQLRemove removeSQL = new SpaReservationSQLRemove(conn);

        switch (op.toUpperCase())
        {
            case "REMOVE CUSTOMER":
                removeSQL.removeCustomer(FirstName,LastName);
                break;
            case "VIEW CUSTOMER":
                new SpaReservationSQLDisplay(statement,conn).displayCustomer(FirstName,LastName);
            break;
            case "EMPLOYEE":
                removeSQL.removeEmployee(FirstName,LastName);
            break;
        }
    }

    public static void main(String[] args) {
        getInsertionOperation();
    }

    public void getOperationTime(String op, double time) throws SQLException
    {
        if (op.toUpperCase().equals("REMOVE"))
            new SpaReservationSQLRemove(conn).removeTime(time);

        if(op.toUpperCase().equals("VIEW"))
            new SpaReservationSQLDisplay(statement,conn).displayCustomerTime(time);
    }

    public void getOperationNumber(String op, int number) throws SQLException {
        if (op.toUpperCase().equals("REMOVE"))
            new SpaReservationSQLRemove(conn).removeTime(number);

        if (op.toUpperCase().equals("VIEW"))
            new SpaReservationSQLDisplay(statement,conn).displayCustomerSpa(number);
    }
}