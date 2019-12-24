package BIMRoad;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import org.apache.commons.io.IOUtils;

public class Item {

    /*
     * id itemname description price userid bid bidderid creationDate
     */

    private int id;
    private String itemname;
    private String description;
    private double price;
    private int userid;
    private double bid;
    private int bidderid;
    private int creationDate;

    public Item(int id, String itemname, String description, double price, int userid, double bid, int bidderid,
            int creationDate) {
        this.id = id;
        this.itemname = itemname;
        this.description = description;
        this.price = price;
        this.userid = userid;
        this.bid = bid;
        this.bidderid = bidderid;
        this.creationDate = creationDate;
    }

    public Item() {

    }

    // Get an item by its itemID and return the info of that item
    public static Item getItemById(int id) {
        Item returnItem = new Item();

        try {
            database db = new database();
            Connection con = db.getConnection();
            PreparedStatement stmt = con.prepareStatement("Select * from Items WHERE id=" + id);

            ResultSet rs = stmt.executeQuery();
            {
                // if we have a result
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

    // Get the items linked to a userID and return the info of the arraylist of
    // items of that userID
    public static ArrayList<Item> getItemsByUserid(int userid) {

        // Define an ArrayList object called "returnArray"
        ArrayList<Item> returnArray = new ArrayList<Item>();

        try {
            // Connect to the database
            database db = new database();
            Connection con = db.getConnection();

            // SQL Query that selects all items where the userID is the userID that is
            // passed
            PreparedStatement stmt = con
                    .prepareStatement("Select * from Items WHERE userid='" + userid + "' ORDER BY id DESC");

            // Create a resultset called "rs" and store the outcome of the query here
            ResultSet rs = stmt.executeQuery();
            {
                // If there are items found with that userID
                while (rs.next()) {

                    // Create a new item object called "tempItem"
                    Item tempItem = new Item();

                    // Define the variables of the item with the information found in the database
                    tempItem.setId(rs.getInt("id"));
                    tempItem.setItemname(rs.getString("itemname"));
                    tempItem.setDescription(rs.getString("description"));
                    tempItem.setPrice(rs.getDouble("price"));
                    tempItem.setUserid(rs.getInt("userid"));
                    tempItem.setBid(rs.getDouble("bid"));
                    tempItem.setBidderid(rs.getInt("bidderid"));
                    tempItem.setCreationDate(rs.getInt("creationdate"));

                    // Add "tempItem" to the ArrayList "returnArray"
                    returnArray.add(tempItem);
                }
            }

            // Close everything properly
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // Return the Arraylist "returnArray"
        return returnArray;
    }

    // Get the items that have "itemnames" like "searchTerm" and return the info of
    // those items in an ArrayList
    public static ArrayList<Item> getItemsBySearch(String searchTerm) {

        // Define an ArrayList object called "returnArray"
        ArrayList<Item> returnArray = new ArrayList<Item>();

        try {
            // Connect to the database
            database db = new database();
            Connection con = db.getConnection();

            // SQL Query that selects all items where the "itemname" is like the
            // "searchTerm" that is passed
            PreparedStatement stmt = con.prepareStatement(
                    "Select * from Items WHERE itemname LIKE '%" + searchTerm + "%' ORDER BY id DESC");

            // Create a resultset called "rs" and store the outcome of the query here
            ResultSet rs = stmt.executeQuery();
            {
                // If there are items with itemnames like "searchTerm"
                while (rs.next()) {

                    // Create a new item object called "tempItem"
                    Item tempItem = new Item();

                    // Define the variables of the item with the information found in the database
                    tempItem.setId(rs.getInt("id"));
                    tempItem.setItemname(rs.getString("itemname"));
                    tempItem.setDescription(rs.getString("description"));
                    tempItem.setPrice(rs.getDouble("price"));
                    tempItem.setUserid(rs.getInt("userid"));
                    tempItem.setBid(rs.getDouble("bid"));
                    tempItem.setBidderid(rs.getInt("bidderid"));
                    tempItem.setCreationDate(rs.getInt("creationdate"));

                    // add tempItem to the ArrayList "returnArray"
                    returnArray.add(tempItem);
                }
            }
            // Close everything properly"
            stmt.close();
            rs.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        // return the ArrayList "returnArray"
        return returnArray;
    }

    // Get all the items that are in the database and return them in an ArrayList
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

    // Add an item to the database
    public static boolean addItemToDatabase(String itemname, String description, double price, int userid,
            String imagename, InputStream image, int creationDate) {

        // Set the default of success on false.
        boolean success = false;
        PreparedStatement ps = null;

        try {
            // Connect to the database
            database db = new database();
            Connection con = db.getConnection();

            // Add the itemname in the first column of the database, the description in the
            // second, etc
            ps = con.prepareStatement(
                    "insert into Items(itemname, description, price, userid, imagename, image, creationDate) values (?,?,?,?,?,?,?)");
            ps.setString(1, itemname);
            ps.setString(2, description);
            ps.setDouble(3, price);
            ps.setInt(4, userid);
            ps.setString(5, imagename);
            ps.setBytes(6, IOUtils.toByteArray(image));
            ps.setInt(7, creationDate);

            ps.execute();

            // Set the success on true.
            success = true;

            // Print out in the consule: Item is added
            System.out.println("Item added itemname=" + itemname);

            // Close everything properly
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
        // Return the value of success
        return success;
    }

    // Update the HighestBid
    public static void updateHighestBid(Double bidAmount, int itemid, int userid) {
        try {
            // Connect to the database
            database db = new database();
            Connection con = db.getConnection();

            // SQL Query to update the bidAmount and the bidderID of the item that is bid on
            PreparedStatement ps = con.prepareStatement(
                    "UPDATE Items SET bid = " + bidAmount + ", bidderid = " + userid + " WHERE id='" + itemid + "'");

            // Execute query
            ps.execute();

            // Close everything properly
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete an item based on the itemid
    public static void deleteItemWithUserId(int itemid, int userid) {
        try {

            // Connect to database
            database db = new database();
            Connection con = db.getConnection();

            // check for both item id and user id to prevent users from deleting items that
            // are not theirs.
            PreparedStatement ps = con
                    .prepareStatement("DELETE FROM Items WHERE id=" + itemid + " AND userid=" + userid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete item with only itemID (admin)
    public static void deleteItem(int itemid) {
        try {
            database db = new database();
            Connection con = db.getConnection();

            // check for both item id and user id to prevent users from deleting items that
            // are not theirs.
            PreparedStatement ps = con.prepareStatement("DELETE FROM Items WHERE id=" + itemid);

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Edit item based on what is selected (what), with what (value), which item
    // (itemid) and of which user (userid)
    public static void updateItem(String what, String value, int itemid, int userid) {
        PreparedStatement ps = null;

        try {

            // Connect to database
            database db = new database();
            Connection con = db.getConnection();

            // If the user select description, change the item description with the passed
            // "value"
            if ((what).equals("description"))
                ps = con.prepareStatement(
                        "UPDATE Items SET description = '" + value + "' WHERE id=" + itemid + " AND userid=" + userid);

            // If the user select itemname, change the item name with the passed "itemname"
            else if ((what).equals("itemname"))
                ps = con.prepareStatement(
                        "UPDATE Items SET itemname = '" + value + "' WHERE id=" + itemid + " AND userid=" + userid);

            // If the user select price, change the item price with the passed "price"
            else if ((what).equals("price"))
                ps = con.prepareStatement(
                        "UPDATE Items SET price = '" + value + "' WHERE id=" + itemid + " AND userid=" + userid);

            ps.execute();

            ps.execute();

            // Close everything properly
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

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public double getBid() {
        return bid;
    }

    public void setBid(double bid) {
        this.bid = bid;
    }

    public int getBidderid() {
        return bidderid;
    }

    public void setBidderid(int bidderid) {
        this.bidderid = bidderid;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }

}
