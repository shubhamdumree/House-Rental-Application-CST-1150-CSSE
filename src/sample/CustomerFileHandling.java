package sample;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class CustomerFileHandling {

    private Scanner CusFile;

    public void openFile() {


        try {
            //opening the file
            CusFile = new Scanner(new java.io.File("Customer.txt"));

        } catch (Exception e) {
            //if file could not be opened, displaying error message
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("File does not exist!");

        }

    }

    public void writeCustomerFile(CustomerDetails customer) throws IOException {

        //define file to be overwritten
        File myFile = new File("Customer.txt");
        PrintStream theWriter = new PrintStream(myFile);


        //load data from array into files

            if (!(customer.getCustomerID() == 0)) {

                theWriter.print(customer.getCustomerID());
                theWriter.print(" ");
                theWriter.print(customer.getFirstName());
                theWriter.print(" ");
                theWriter.print(customer.getLastName());
                theWriter.print(" ");
                theWriter.print(customer.getAddress());
                theWriter.print(" ");
                theWriter.print(customer.getCredit_card());
                theWriter.print(" ");
                theWriter.print(customer.getTelephone_number());
            }


        //Close current file
        theWriter.close();
    }

    public CustomerDetails[] readCustomerFile() {
        openFile();
        CustomerDetails [] customerDetails=new CustomerDetails[1];

        int CustomerID;
        String FirstName;
        String LastName;
        String Address;
        int Credit_card;
        int Telephone_number;

        // loop until current file has data to be read
        while (CusFile.hasNext()) {

            CustomerID = Integer.parseInt(CusFile.next());
            FirstName = CusFile.next();
            LastName = CusFile.next();
            Address = CusFile.next();
            Credit_card = Integer.parseInt(CusFile.next());
            Telephone_number = Integer.parseInt(CusFile.next());

            // convert them into appropriate data
            customerDetails[0] = new CustomerDetails(CustomerID, FirstName, LastName, Address, Credit_card, Telephone_number);

            // Increment counter

        }
        return customerDetails;
    }

}
