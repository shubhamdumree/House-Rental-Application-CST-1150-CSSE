package sample;

public class Accommodation {

    //Data Members
    //instance variables

    private int accommodation_id;
    private String type_of_accommodation;
    private String owner;
    private int telephone_number;
    private double cost;
    private String location;
    private String booking_status;
    private String date;

    public Accommodation() {   //default constructor

        accommodation_id = 0;
        type_of_accommodation = "";
        owner = "";
        telephone_number = 0;
        cost = 0;
        location = "";
        booking_status = "";
        date = "";

    }

    public Accommodation(int acc_id, String acc, String own, int tel, double ct, String loc, String book, String dt){       //constructor 2

        accommodation_id = acc_id;
        type_of_accommodation = acc;
        owner = own;
        telephone_number = tel;
        cost = ct;
        location = loc;
        booking_status = book;
        date = dt;

    }

    // use of setters and getters
    //inheritance

    public int getAccommodation_id() {

        return accommodation_id;

    }

    public void setAccommodation_id(int accommodation_id) {

        this.accommodation_id = accommodation_id;

    }

    public String getType_of_accommodation() {

        return type_of_accommodation;

    }

    public void setType_of_accommodation(String accommodation_id) {

        this.type_of_accommodation = type_of_accommodation;

    }

    public String getOwner() {

        return owner;

    }

    public void setOwner(String owner) {

        this.owner = owner;

    }

    public int getTelephone_number() {

        return telephone_number;

    }

    public void setTelephone_number(int telephone_number) {

        this.telephone_number = telephone_number;

    }

    public double getCost() {

        return cost;

    }

    public void setCost(double cost) {

        this.cost = cost;

    }

    public String getLocation() {

        return location;

    }

    public void setLocation(String location) {

        this.location = location;

    }

    public String getBooking_status() {

        return booking_status;

    }

    public void setBooking_status(String booking_status) {

        this.booking_status = booking_status;

    }

    public String getDate() {

        return date;

    }

    public void setDate(String date) {

        this.date = date;

    }

} // end of accommodation class
