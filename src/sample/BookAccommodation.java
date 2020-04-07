package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

//class for booking the accommodation chosen by the user

public class BookAccommodation {

    private static Stage AccWindow;
    private static ComboBox<Integer> comboBox;
    private static TextField days_booked;

    // Private variables
    private static boolean info = false;
    private static int DurationBooked, AccId = 0;

    public static void BookAcc(Accommodation[] AccommodationArray) throws IOException {

        // Declares window as stage and sets the title
        AccWindow = new Stage();
        AccWindow.setTitle("Book Your Accommodation");
        AccWindow.setResizable(false);
        AccWindow.initModality(Modality.APPLICATION_MODAL);

        // Defines GridPane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        //label and set location on the GridPane
        Label acc = new Label("Please choose Accommodation ID:");
        acc.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        GridPane.setConstraints(acc, 1, 1);

        //create a comboBox
        comboBox = new ComboBox<>();

        // Loop through roomArray to retrieve roomID and adds them to the comboBox
        for (int i = 0; i < AccommodationArray.length; i++) {

            if (!(AccommodationArray[i].getAccommodation_id() == 0) && AccommodationArray[i].getBooking_status().equals("Available")) {

                comboBox.getItems().add(AccommodationArray[i].getAccommodation_id());

            }

        }

        // Set comboBox default value to 0
        comboBox.setValue(0);
        GridPane.setConstraints(comboBox, 3, 1);

        // Label and set it's location on the GridPane
        Label Duration_Booked = new Label("Duration:");
        Duration_Booked.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        GridPane.setConstraints(Duration_Booked, 1, 2);

        // Text_field and set it's location on the gridPane
        days_booked = new TextField();
        days_booked.setPromptText("Enter number of days you want to book");
        GridPane.setConstraints(days_booked, 3, 2);

        // Submit button
        InputStream sub = Files.newInputStream(Paths.get("res/submit.png"));
        ImageView imgs = new ImageView(new Image(sub));
        imgs.setFitHeight(20);
        imgs.setFitWidth(20);
        Button submit = new Button("Book", imgs);
        submit.setOnAction(e -> {

            try {
                valid_data(comboBox.getValue(), days_booked.getText(), AccommodationArray);
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        });

        //cancel button and putting an image on it
        InputStream cl = Files.newInputStream(Paths.get("res/exit2.png"));
        ImageView imgx = new ImageView(new Image(cl));
        imgx.setFitHeight(20);
        imgx.setFitWidth(20);
        Button cancel = new Button("Cancel", imgx);
        cancel.setOnAction(e -> {
            AccWindow.close();
            info = false;
        });

        // HBox to arrange the buttons horizontally
        HBox button = new HBox(10, submit, cancel);
        GridPane.setConstraints(button, 1, 20);

        // Add the window features to the gridPane
        grid.getChildren().addAll(acc, comboBox, days_booked, Duration_Booked, button);

        BackgroundFill background_fill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        // Create scene to be display on the window
        // Add scene to window, link scene to css file
        Scene bookSc = new Scene(grid, 500, 350);
        AccWindow.setScene(bookSc);
        AccWindow.getIcons().add(new Image("file:res/book.png"));
        AccWindow.showAndWait();

    }

    // Validation method
    private static void valid_data(int value, String d, Accommodation[] AccommodationArray) throws Exception {

        int day = 0;
        boolean cond1 = true;
        boolean cond2 = true;


        // Checks if user selected a accID from the comboBox
        if (value == 0) {
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Please select an Accommodation ID!");
            cond1 = false;
        }

        else {
            AccId = value;

        }

        boolean found = false;

        // validation for number of days
        try {

            day = Integer.parseInt(d);

            if (day <= 0) {

                MessageBox messageBox= new MessageBox();
                messageBox.ShowMessage("Please enter a valid number of days!");
                days_booked.clear();
                cond2 = false;
            } else {

                DurationBooked = day;

            }

        } catch (NumberFormatException e) {

            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Please enter a valid number of days!");
            days_booked.clear();
            cond2 = false;
        }

        // Condition to check if all correct data has been entered correctly
        if (cond1 == true && cond2 == true) {

            info = true;
            AccWindow.close();
            //message box for displaying the user that has booked an accommodation and confirming its booking
            CustomerFileHandling customerFileHandling=new CustomerFileHandling();
            customerFileHandling.openFile();
            CustomerDetails[] customer= new CustomerDetails[0];
            customer=customerFileHandling.readCustomerFile();
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Congratulation!  "+ customer[0].getFirstName() + " " + customer[0].getLastName() + " You have successfully booked this accommodation\n Paid using credit card: " + customer[0].getCredit_card());

        }

    }

    // getters for the info
    public static boolean AddAll() {
        return info;

    }

    public static int returnAccId(){
        return AccId;

    }

    public static int Days_Booked() {

        return DurationBooked;

    }


}
