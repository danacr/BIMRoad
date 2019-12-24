package BIMRoad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class helpers {
    // strictest these can be is default instead of public

    // Checks if the e-mail consists of all the necessary elements of an email
    public static boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    // Creates and returns a timestamp on the current time
    public static int getUnixTimestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static String getDateFromTimestamp(int timestamp) {

        // Define an Date object called "date" with as constructor format "timestamp *
        // 1000L"
        Date date = new Date(timestamp * 1000L); // *1000 is to convert seconds to milliseconds

        // Define a new SimpleDateFormat object called "sdf" with as constructor format
        // "hours:minutes, day-month-year"
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM-yyyy");

        // Set the timezone of "sdf" on CET
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));

        // Define new variable that formats the sdf date into a formatted date
        String formattedDate = sdf.format(date);

        // return the formattedDate value
        return formattedDate;
    }

    // See if the input string is numeric
    public static boolean isNumeric(String str) {

        // Try to parse the string into a double, if it succeeds it returns true
        try {
            double d = Double.parseDouble(str);

            // if the string cannot be parsed into a double, it returns a false.
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    // Formats a double with 2 decimals.
    public static String doubleToTwoDecimals(double input) {

        // Creates a new object that defines a constructor with two decimals.
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(input);
    }

    // Checks if the email already is in the database
    public static boolean doesEmailExists(String email) {

        // Define a new variable "exists" and set the default on false
        boolean exists = false;

        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // Make a new database object called "db"
            database db = new database();

            // Connect to the database
            Connection con = db.getConnection();

            // Sending a SQL request to select all users where the email address is equal to
            // the email address that is passed
            ps = con.prepareStatement("select * from Users where email=?");
            ps.setString(1, email);

            // The outcome of the SQL query is loaded in the variable "rs" / result set
            rs = ps.executeQuery();

            // If the email exists already --> rs.next = true
            if (rs.next()) {
                // Print out the value of "rs" in the console
                System.out.println(rs.toString());
                return true;
            }

            // close the database
            con.close();
            db.connection.close();

        } catch (Exception e) {
            // Something is wrong, database returned error so couldn't find user + password
            return false;
        } finally {
            try {
                rs.close();
                ps.close();
            } catch (SQLException e) {
                System.out.println("SQLException in closing PreparedStatement or ResultSet");
            }
        }
        return exists;
    }

    // Check if a bid is higher than the current highest bid. Returns a boolean
    public static boolean bidIsHigherThanCurrentBid(double bidAmount, int itemid) {

        // Set default of "bidIsHigher" on false
        boolean bidIsHigher = false;

        try {
            // Connect to database
            database db = new database();
            Connection con = db.getConnection();

            // SQL Query to select the item with the same itemid that is passed.
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Items WHERE id=" + itemid);

            // Create a result set out of the query results
            ResultSet rs = stmt.executeQuery();
            {
                // If the item id exists in the database
                if (rs.next()) {

                    // Check if the bidAmount is higher than the current highest bid, if so, change
                    // bidIsHigher to true
                    if (bidAmount > rs.getDouble("bid")) {
                        bidIsHigher = true;
                    }
                }
            }

            // Close everything properly
            rs.close();
            stmt.close();
            con.close();
            db.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return whether the bid is higher (true/false)
        return bidIsHigher;
    }

}
