package BIMRoad;

public class User {
    /*
    id
    name
    email
    password
    street
    postalcode
    city
    country
    creationDate
    isAdmin
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

    //all atributes
    public User(int id, String name, String email, String password, String street, String postalcode, String city, String country, int creationDate) {
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

    //some attributes for session
    public User(int id, String name, String email, int isAdmin) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.isAdmin = isAdmin;
    }

    //no attributes
    public User() {

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
