package BIMRoad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Message {
    /*
    id
    sender
    senderEmail
    content
    receiver
    creatonDate
     */

    private int id;
    private String sender;
    private String senderEmail;
    private String content;
    private String receiver;
    private int creationDate;

    public Message(int id, String sender, String senderEmail, String content, String receiver, int creationDate) {
        this.id = id;
        this.sender = sender;
        this.senderEmail = senderEmail;
        this.receiver = receiver;
        this.creationDate = creationDate;
    }

    public Message() {

    }

    //Get all the messages and return them in an ArrayList
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

    //Get all the messages of a user and return them in an ArrayList
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

    //Add a message to the database and return if it is succeeded
    public static boolean addMessageToDatabase(String sender, String senderemail, String content, String receiver, int creationDate) {

        //Set default success value on false
        boolean success = false;
        PreparedStatement ps = null;

        try {

            //Connect to database
            database db = new database();
            Connection con = db.getConnection();

            //Add the values into the database: in column one, sender, column two: sender email etc.
            ps = con.prepareStatement("insert into Messages(sender,senderemail,content, receiver) values (?,?,?,?)");
            ps.setString(1, sender);
            ps.setString(2, senderemail);
            ps.setString(3, content);
            ps.setString(4, receiver);

            ps.execute();
            System.out.println("Message sent=" + content);

            //Set success on true
            success = true;

            //Close everything properly
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
        //Return the value of success
        return success;
    }

    //Delete a message with messageid and username
    public static void deleteMessageWithUserName(int messageid, String userName) {
        try {

            //Connect to database
            database db = new database();
            Connection con = db.getConnection();

            //check for both id and username to prevent users from deleting messages that are not theirs
            PreparedStatement ps = con.prepareStatement("DELETE FROM Messages WHERE id=" + messageid + " AND receiver='" + userName + "'");

            ps.execute();
            ps.close();
            con.close();
            db.connection.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Delete message with only messageid (admin)
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public int getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(int creationDate) {
        this.creationDate = creationDate;
    }
}
