package sample;

import java.util.Scanner;

//class for accommodation file handling

public class AccFileHandling {

    private  Scanner AccFile;

    public void openFile() {


        try {
            //opening the file
            AccFile = new Scanner(new java.io.File("Accommodation.txt"));

        } catch (Exception e) {

            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("File does not exist!");

        }

    }

    public Accommodation[] readRAccFile () {
        openFile();
        Accommodation[] AccommodationArray=new Accommodation[10];
        int accommodation_id;
        String type_of_accommodation;
        String owner;
        int telephone_number;
        double cost;
        String location;
        String booking_status;
        String date;

        int count = 0;

        // loop until current file has data to be read
        while (AccFile.hasNext()) {
            // get data & convert them into appropriate data
            accommodation_id = Integer.parseInt(AccFile.next());

            type_of_accommodation = AccFile.next();
            owner = AccFile.next();
            telephone_number = Integer.parseInt(AccFile.next());
            cost = Double.parseDouble(AccFile.next());
            location = AccFile.next();
            booking_status = AccFile.next();
            date = AccFile.next();

            // Load the data into the array
            AccommodationArray[count] = new Accommodation(accommodation_id, type_of_accommodation, owner, telephone_number, cost, location, booking_status, date);
            // Incrementation
            count++;
        }
        closeFile();
        //System.out.println(AccommodationArray.length);
        return  AccommodationArray;

    }

    // Close the file
    public void closeFile() {

        AccFile.close();

    }
}
