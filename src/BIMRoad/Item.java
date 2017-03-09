package BIMRoad;

public class Item {

    /*
    id
    itemname
    description
    price
    userid
    bid
    bidderid
    creationDate
     */

    private int id;
    private String itemname;
    private String description;
    private double price;
    private int userid;
    private double bid;
    private int bidderid;
    private int creationDate;

    public Item(int id, String itemname, String description, double price, int userid, double bid, int bidderid, int creationDate) {
        this.id = id;
        this.itemname = itemname;
        this.description = description;
        this.price = price;
        this.userid = userid;
        this.bid = bid;
        this.bidderid = bidderid;
        this.creationDate = creationDate;
    }

    public Item(){

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
