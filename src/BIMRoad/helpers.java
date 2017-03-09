package BIMRoad;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.TimeZone;

public class helpers {
    //strictest these can be is default instead of public

    public static boolean isValidEmail(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static int getUnixTimestamp() {
        return (int) (System.currentTimeMillis() / 1000L);
    }

    public static String getDateFromTimestamp(int timestamp) {
        Date date = new Date(timestamp * 1000L); // *1000 is to convert seconds to milliseconds
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm dd-MM-yyyy");
        sdf.setTimeZone(TimeZone.getTimeZone("CET"));
        String formattedDate = sdf.format(date);
        return formattedDate;
    }

    public static boolean isNumeric(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static String doubleToTwoDecimals(double input) {
        DecimalFormat df = new DecimalFormat("0.00");

        return df.format(input);
    }

    public static boolean doesEmailExists(String email) {
        boolean exists = false;

        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            database db = new database();
            Connection con = db.getConnection();
            ps = con.prepareStatement("select * from Users where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();

            if (rs.next()) {
                //email exists already
                System.out.println(rs.toString());
                return true;
            }
            con.close();
            db.connection.close();

        } catch (Exception e) {
            //Something is wrong, database returned error so couldn't find user + password
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

    public static User getUserById(int id) {
        User returnUser = new User();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Users WHERE id=" + id);

            ResultSet rs = stmt.executeQuery();
            {
                //if we have a result
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

    public static User getUserByEmailPassword(String email, String password) {
        User returnUser = new User();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Users WHERE email='" + email + "' AND password='" + password + "'");

            ResultSet rs = stmt.executeQuery();
            {
                //if we have a result
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

    public static User getUserByItemId(int itemid) {
        User returnUser = new User();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Users WHERE id IN (SELECT userid FROM Items WHERE id=" + itemid + ")");

            ResultSet rs = stmt.executeQuery();
            {
                //if we have a result
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

    public static Item getItemById(int id) {
        Item returnItem = new Item();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Items WHERE id=" + id);

            ResultSet rs = stmt.executeQuery();
            {
                //if we have a result
                if (rs.next()) {
                    returnItem.setId(rs.getInt("id"));
                    returnItem.setItemname(rs.getString("itemname"));
                    returnItem.setDescription(rs.getString("description"));
                    returnItem.setPrice(rs.getDouble("price"));
                    returnItem.setUserid(rs.getInt("userid"));
                    returnItem.setBid(rs.getDouble("bid"));
                    returnItem.setBidderid(rs.getInt("bidderid"));
                    returnItem.setCreationDate(rs.getInt("creationdate"));
                }
            }
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnItem;
    }

    public static ArrayList<Item> getItemsByUserid(int userid) {
        ArrayList<Item> returnArray = new ArrayList<Item>();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Items WHERE userid='" + userid + "' ORDER BY id DESC");

            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    Item tempItem = new Item();

                    tempItem.setId(rs.getInt("id"));
                    tempItem.setItemname(rs.getString("itemname"));
                    tempItem.setDescription(rs.getString("description"));
                    tempItem.setPrice(rs.getDouble("price"));
                    tempItem.setUserid(rs.getInt("userid"));
                    tempItem.setBid(rs.getDouble("bid"));
                    tempItem.setBidderid(rs.getInt("bidderid"));
                    tempItem.setCreationDate(rs.getInt("creationdate"));

                    returnArray.add(tempItem);
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

    public static ArrayList<Item> getItemsBySearch(String searchTerm) {
        ArrayList<Item> returnArray = new ArrayList<Item>();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Items WHERE itemname LIKE '%" + searchTerm + "%' ORDER BY id DESC");

            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    Item tempItem = new Item();

                    tempItem.setId(rs.getInt("id"));
                    tempItem.setItemname(rs.getString("itemname"));
                    tempItem.setDescription(rs.getString("description"));
                    tempItem.setPrice(rs.getDouble("price"));
                    tempItem.setUserid(rs.getInt("userid"));
                    tempItem.setBid(rs.getDouble("bid"));
                    tempItem.setBidderid(rs.getInt("bidderid"));
                    tempItem.setCreationDate(rs.getInt("creationdate"));

                    returnArray.add(tempItem);
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

    public static ArrayList<Item> getItems() {
        ArrayList<Item> returnArray = new ArrayList<Item>();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Items ORDER BY id DESC");

            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {

                    Item tempItem = new Item();

                    tempItem.setId(rs.getInt("id"));
                    tempItem.setItemname(rs.getString("itemname"));
                    tempItem.setDescription(rs.getString("description"));
                    tempItem.setPrice(rs.getDouble("price"));
                    tempItem.setUserid(rs.getInt("userid"));
                    tempItem.setBid(rs.getDouble("bid"));
                    tempItem.setBidderid(rs.getInt("bidderid"));
                    tempItem.setCreationDate(rs.getInt("creationdate"));

                    returnArray.add(tempItem);
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

    public static ArrayList<Message> getMessages() {
        ArrayList<Message> returnArray = new ArrayList<Message>();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Messages");

            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {

                    Message tempMessage = new Message();

                    tempMessage.setId(rs.getInt("id"));
                    tempMessage.setSender(rs.getString("sender"));
                    tempMessage.setSenderEmail(rs.getString("senderemail"));
                    tempMessage.setContent(rs.getString("content"));
                    tempMessage.setReceiver(rs.getString("receiver"));
                    tempMessage.setCreationDate(rs.getInt("creationdate"));

                    returnArray.add(tempMessage);
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

    public static ArrayList<Message> getMessagesByUsername(String username) {
        ArrayList<Message> returnArray = new ArrayList<Message>();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Messages WHERE receiver='" + username + "' ORDER BY id DESC");

            ResultSet rs = stmt.executeQuery();
            {
                while (rs.next()) {
                    Message tempMessage = new Message();

                    tempMessage.setId(rs.getInt("id"));
                    tempMessage.setSender(rs.getString("sender"));
                    tempMessage.setSenderEmail(rs.getString("senderemail"));
                    tempMessage.setContent(rs.getString("content"));
                    tempMessage.setReceiver(rs.getString("receiver"));
                    tempMessage.setCreationDate(rs.getInt("creationdate"));

                    returnArray.add(tempMessage);
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

    public static boolean addItemToDatabase(String itemname, String description, double price, int userid, String imagename, InputStream image, int creationDate) {
        boolean success = false;
        PreparedStatement ps = null;

        try {
            database db = new database();
            Connection con = db.getConnection();

            ps = con.prepareStatement("insert into Items(itemname, description, price, userid, imagename, image, creationDate) values (?,?,?,?,?,?,?)");
            ps.setString(1, itemname);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setInt(4, userid);
            ps.setString(5, imagename);
            ps.setBlob(6, image);
            ps.setInt(7, creationDate);

            ps.execute();

            success = true;
            System.out.println("Item added itemname=" + itemname);
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("database connection problem");
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("SQLException in closing PreparedStatement");
            }
        }

        return success;
    }

    public static boolean addMessageToDatabase(String sender, String senderemail, String content, String receiver, int creationDate) {
        boolean success = false;
        PreparedStatement ps = null;

        try {
            database db = new database();
            Connection con = db.getConnection();

            ps = con.prepareStatement("insert into Messages(sender,senderemail,content, receiver) values (?,?,?,?)");
            ps.setString(1, sender);
            ps.setString(2, senderemail);
            ps.setString(3, content);
            ps.setString(4, receiver);

            ps.execute();
            System.out.println("Message sent=" + content);
            success = true;
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("database connection problem");
        } finally {
            try {
                ps.close();
            } catch (SQLException e) {
                System.out.println("SQLException in closing PreparedStatement");
            }
        }

        return success;
    }

    public static boolean bidIsHigherThanCurrentBid(double bidAmount, int itemid) {
        boolean bidIsHigher = false;

        try {
            database db = new database();
            Connection con = db.getConnection();

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM Items WHERE id=" + itemid);
            ResultSet rs = stmt.executeQuery();
            {
                if (rs.next()) {
                    if (bidAmount > rs.getDouble("bid")) {
                        bidIsHigher = true;
                    }
                }
            }
            rs.close();
            stmt.close();
            con.close();
            db.connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        return bidIsHigher;
    }

    public static void updateHighestBid(Double bidAmount, int itemid, int userid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            PreparedStatement ps = con.prepareStatement("UPDATE Items SET bid = " + bidAmount + ", bidderid = " + userid + " WHERE id='" + itemid + "'");

            ps.execute();

            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteItemWithUserId(int itemid, int userid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            //check for both item id and user id to prevent users from deleting items that are not theirs.
            PreparedStatement ps = con.prepareStatement("DELETE FROM Items WHERE id=" + itemid + "AND userid=" + userid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteMessageWithUserName(int messageid, String userName) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            //check for both id and username to prevent users from deleting messages that are not theirs
            PreparedStatement ps = con.prepareStatement("DELETE FROM Messages WHERE id=" + messageid + "AND receiver='" + userName + "'");

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteItem(int itemid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            //check for both item id and user id to prevent users from deleting items that are not theirs.
            PreparedStatement ps = con.prepareStatement("DELETE FROM Items WHERE id=" + itemid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteMessage(int messageid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            //check for both id and username to prevent users from deleting messages that are not theirs
            PreparedStatement ps = con.prepareStatement("DELETE FROM Messages WHERE id=" + messageid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteUser(int userid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            //check for both item id and user id to prevent users from deleting items that are not theirs.
            PreparedStatement ps = con.prepareStatement("DELETE FROM Users WHERE id=" + userid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateItem(String what, String value, int itemid, int userid) {
        PreparedStatement ps = null;

        try {
            database db = new database();
            Connection con = db.getConnection();


            if ((what).equals("description"))
                ps = con.prepareStatement("UPDATE Items SET description = '" + value + "' WHERE id=" + itemid + " AND userid=" + userid);
            else if ((what).equals("itemname"))
                ps = con.prepareStatement("UPDATE Items SET itemname = '" + value + "' WHERE id=" + itemid + " AND userid=" + userid);
            else if ((what).equals("price"))
                ps = con.prepareStatement("UPDATE Items SET price = '" + value + "' WHERE id=" + itemid + " AND userid=" + userid);

            ps.execute();

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

}
