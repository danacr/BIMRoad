package BIMRoad;

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
