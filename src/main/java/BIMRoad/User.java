package BIMRoad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class User {
    /*
     * id name email password street postalcode city country creationDate isAdmin
     */

    // -1 as that doesn't exist as id so we can detect empty User
    private int id = -1;
    private String name;
    private String email;
    private String password;
    private String street;
    private String postalcode;
    private String city;
    private String country;
    private int creationDate;
    private int isAdmin;

    // all atributes
    public User(int id, String name, String email, String password, String street, String postalcode, String city,
                String country, int creationDate) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.street = street;
        this.postalcode = postalcode;
        this.city = city;
        this.country = country;
        this.creationDate = creationDate;
    }

    // some attributes for session
    public User(int id, String name, String email, int isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    // no attributes
    public User() {

    }

    // Get a user by its ID and return the info of that user
    public static User getUserById(int id) {

        // Create a new User object called "returnUser"
        User returnUser = new User();

        try {
            // Make a new database and connect the database
            database db = new database();
            Connection con = db.getConnection();

            // SQL Query where you select all users where the ID is equal to the id that is
            // passed
            PreparedStatement stmt = con.prepareStatement("Select * from Users WHERE id=" + id);

            // The outcome of the SQL query is loaded in the variable "rs" / result set
            ResultSet rs = stmt.executeQuery();
            {
                // If the id is in the database
                if (rs.next()) {

                    // Set the ID, name etc based on the information retrieved in the database
                    returnUser.setId(rs.getInt("id"));
                    returnUser.setName(rs.getString("name"));
                    returnUser.setEmail(rs.getString("email"));
                    returnUser.setPassword(rs.getString("password"));
                    returnUser.setStreet(rs.getString("street"));
                    returnUser.setPostalcode(rs.getString("postalcode"));
                    returnUser.setCity(rs.getString("city"));
                    returnUser.setCountry(rs.getString("country"));
                    returnUser.setCreationDate(rs.getInt("creationDate"));
                    returnUser.setIsAdmin(rs.getInt("isAdmin"));
                }
            }
            // Close everything
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnUser;
    }

    // Get a user by its email and password and return the info of that user
    public static User getUserByEmailPassword(String email, String password) {
        User returnUser = new User();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "Select * from Users WHERE email='" + email + "' AND password='" + password + "'");

            ResultSet rs = stmt.executeQuery();
            {
                // if we have a result
                if (rs.next()) {
                    returnUser.setId(rs.getInt("id"));
                    returnUser.setName(rs.getString("name"));
                    returnUser.setEmail(rs.getString("email"));
                    returnUser.setPassword(rs.getString("password"));
                    returnUser.setStreet(rs.getString("street"));
                    returnUser.setPostalcode(rs.getString("postalcode"));
                    returnUser.setCity(rs.getString("city"));
                    returnUser.setCountry(rs.getString("country"));
                    returnUser.setCreationDate(rs.getInt("creationDate"));
                    returnUser.setIsAdmin(rs.getInt("isAdmin"));
                }
            }
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnUser;
    }

    // Get a user by itemid and return the info of that user
    public static User getUserByItemId(int itemid) {
        User returnUser = new User();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement(
                    "SELECT * FROM Users WHERE id IN (SELECT userid FROM Items WHERE id=" + itemid + ")");

            ResultSet rs = stmt.executeQuery();
            {
                // if we have a result
                if (rs.next()) {
                    returnUser.setId(rs.getInt("id"));
                    returnUser.setName(rs.getString("name"));
                    returnUser.setEmail(rs.getString("email"));
                    returnUser.setPassword(rs.getString("password"));
                    returnUser.setStreet(rs.getString("street"));
                    returnUser.setPostalcode(rs.getString("postalcode"));
                    returnUser.setCity(rs.getString("city"));
                    returnUser.setCountry(rs.getString("country"));
                    returnUser.setCreationDate(rs.getInt("creationDate"));
                    returnUser.setIsAdmin(rs.getInt("isAdmin"));
                }
            }
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnUser;
    }

    // Get all the Users and return them in an ArrayList
    public static ArrayList<User> getUsers() {
        ArrayList<User> returnArray = new ArrayList<User>();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Users");

            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {

                    User tempUser = new User();

                    tempUser.setId(rs.getInt("id"));
                    tempUser.setName(rs.getString("name"));
                    tempUser.setEmail(rs.getString("email"));
                    tempUser.setPassword(rs.getString("password"));
                    tempUser.setStreet(rs.getString("street"));
                    tempUser.setPostalcode(rs.getString("postalcode"));
                    tempUser.setCity(rs.getString("city"));
                    tempUser.setCountry(rs.getString("country"));
                    tempUser.setCreationDate(rs.getInt("creationDate"));
                    tempUser.setIsAdmin(rs.getInt("isAdmin"));

                    returnArray.add(tempUser);
                }
            }
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnArray;
    }

    // Delete user with userid (admin)
    public static void deleteUser(int userid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            // check for both item id and user id to prevent users from deleting items that
            // are not theirs.
            PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE id=" + userid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Edit user details!
    public static void updateUser(String what, String value, int userid) {
        PreparedStatement ps = null;

        try {
            database db = new database();
            Connection con = db.getConnection();

            if ((what).equals("email"))
                ps = con.prepareStatement("UPDATE Users SET email = '" + value + "' WHERE id=" + userid);
            else if ((what).equals("street"))
                ps = con.prepareStatement("UPDATE Users SET street = '" + value + "' WHERE id=" + userid);
            else if ((what).equals("postalcode"))
                ps = con.prepareStatement("UPDATE Users SET postalcode = '" + value + "' WHERE id=" + userid);
            else if ((what).equals("city"))
                ps = con.prepareStatement("UPDATE Users SET city = '" + value + "' WHERE id=" + userid);
            else if ((what).equals("country"))
                ps = con.prepareStatement("UPDATE Users SET country = '" + value + "' WHERE id=" + userid);
            else if ((what).equals("password"))
                ps = con.prepareStatement("UPDATE Users SET password = '" + value + "' WHERE id=" + userid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = isAdmin;
    }
}
