package sample;

public class CustomerDetails {

    //Data Members
    //instance variables

    private int CustomerID;
    private String FirstName;
    private String LastName;
    private String Address;
    private int Credit_card;
    private int Telephone_number;

    public CustomerDetails() {

        CustomerID = 0;               // default constructor //
        FirstName = "Enter your First Name: ";
        LastName = "Enter your Surname: ";
        Address = "Enter your Address: ";
        Credit_card = 0;
        Telephone_number = 0;

    }

    public CustomerDetails (int user_input_customer_id, String user_input_first_name, String user_input_surname,
                     String user_input_address, int user_input_credit_card, int user_input_telephone_number) {

        CustomerID = user_input_customer_id;               // constructor 2 //
        FirstName = user_input_first_name;
        LastName = user_input_surname;
        Address = user_input_address;
        Credit_card = user_input_credit_card;
        Telephone_number = user_input_telephone_number;

    }

    // use of setters and getters
    //inheritance

    public int getCustomerID() {

        return CustomerID;

    }

    public void setCustomerID(int CustomerID) {

        this.CustomerID = CustomerID;

    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String FirstName) {

        this.FirstName = FirstName;

    }

    public String getLastName() {

        return LastName;

    }

    public void setLastName(String LastName) {

        this.LastName = LastName;

    }

    public String getAddress() {

        return Address;

    }

    public void setAddress(String Address) {

        this.Address = Address;

    }

    public int getCredit_card() {

        return Credit_card;

    }

    public void setCredit_card(int Credit_card) {

        this.Credit_card = Credit_card;

    }

    public int getTelephone_number() {

        return Telephone_number;

    }

    public void setTelephone_number(int Telephone_number) {

        this.Telephone_number = Telephone_number;

    }


} //end of customer details class
