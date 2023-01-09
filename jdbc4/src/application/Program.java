package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        Connection connection = null;
        PreparedStatement pSt = null;

        try {
            connection = DB.getConnection();
            pSt = connection.prepareStatement(
                    "UPDATE seller "
                            + "SET BaseSalary = BaseSalary + ? "
                            + "WHERE "
                            + "(DepartmentId = ?)");

            pSt.setDouble(1, 200.0);
            pSt.setInt(2, 2);

            int rowsAffected = pSt.executeUpdate();

            System.out.println("Done! Rows affected: " + rowsAffected);
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        finally {
            DB.closeStatement(pSt);
            DB.closeConnection();
        }
    }
}