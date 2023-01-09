package application;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection connection = null;
        PreparedStatement pSt = null;

        try {
            connection = DB.getConnection();
            pSt = connection.prepareStatement(
                    "INSERT INTO seller "
                            + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                            + "Values "
                            + "(?, ?, ?, ?, ?)",
                            Statement.RETURN_GENERATED_KEYS);

            pSt.setString(1, "Carl Purple");
            pSt.setString(2, "carl@gmail.com");
            pSt.setDate(3, new Date(sdf.parse("22/04/1985").getTime()));
            pSt.setDouble(4, 3000.0);
            pSt.setInt(5, 4);

        // method 2:
//            pSt = connection.prepareStatement(
//                    "INSERT INTO department (Name) values ('D1'),('D2')",
//                    Statement.RETURN_GENERATED_KEYS);

            int rowsUpdated = pSt.executeUpdate();

            if (rowsUpdated > 0) {
                ResultSet resultSet = pSt.getGeneratedKeys();
                while (resultSet.next()){
                int id = resultSet.getInt(1);
                System.out.println("Done! Id = " + id);
                }
            }
        }
        catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        catch (ParseException parseException){
            parseException.printStackTrace();
        }
        finally {
            DB.closeStatement(pSt);
            DB.closeConnection();
        }
    }
}