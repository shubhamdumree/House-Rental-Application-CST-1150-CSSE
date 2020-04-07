package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

//class for customer inputting details

public class CustomerInput {


    static Stage CustomerWindow;

    private static TextField cusIDInput;
    private static TextField f_nameInput;
    private static TextField l_nameInput;
    private static TextField addressInput;
    private static TextField ccInput;
    private static TextField telInput;

    //variables
    private static int finalCusID;
    private static String finalFName;
    private static String finalSurname;
    private static String finalAddress;
    private static int finalCreditCard;
    private static int finalTelNumber;
    private static boolean cond1, cond2, cond3, cond4;
    private static boolean allCorrectInfoEntered = false;

    public static void input() throws IOException {

        CustomerWindow = new Stage();
        CustomerWindow.setTitle("Enter Customer Details");
        CustomerWindow.setResizable(false);
        CustomerWindow.initModality(Modality.APPLICATION_MODAL);

        //GridPane with 10px padding around edge
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        Label programTitle = new Label();
        programTitle.setText("Enter your details below");
        programTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        programTitle.setTranslateX(140);
        vbox.getChildren().add(programTitle);

        //cusID Label - constrains use (child, column, row)
        Label cusIDLabel = new Label("Customer ID");
        cusIDLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.setConstraints(cusIDLabel, 0, 2);

        //FName Input
        cusIDInput = new TextField();
        cusIDInput.setPromptText("Enter your customer ID");
        grid.setConstraints(cusIDInput, 1, 2);

        //FName Label - constrains use (child, column, row)
        Label f_nameLabel = new Label("First Name");
        f_nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.setConstraints(f_nameLabel, 0, 3);

        //FName Input
        f_nameInput = new TextField();
        f_nameInput.setPromptText("Enter your first name");
        grid.setConstraints(f_nameInput, 1, 3);

        //LName Label
        Label l_nameLabel = new Label("Last Name");
        l_nameLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.setConstraints(l_nameLabel, 0, 4);

        //LName Input
        l_nameInput = new TextField();
        l_nameInput.setPromptText("Enter your last name");
        grid.setConstraints(l_nameInput, 1, 4);

        //Address Label
        Label addressLabel = new Label("Address");
        addressLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.setConstraints(addressLabel, 0, 5);

        //Address Input
        addressInput = new TextField();
        addressInput.setPromptText("Enter your address");
        grid.setConstraints(addressInput, 1, 5);

        //credit_card Label
        Label ccLabel = new Label("Credit Card Information");
        ccLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.setConstraints(ccLabel, 0, 6);

        //credit_card Input
        ccInput = new TextField();
        ccInput.setPromptText("Enter your credit card information");
        grid.setConstraints(ccInput, 1, 6);

        //tel_number Label
        Label telLabel = new Label("Telephone Number");
        telLabel.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        grid.setConstraints(telLabel, 0, 7);

        //tel_number Input
        telInput = new TextField();
        telInput.setPromptText("Enter your telephone number");
        grid.setConstraints(telInput, 1, 7);

        //Submit details
        InputStream sub = Files.newInputStream(Paths.get("res/submit.png"));
        ImageView imgs = new ImageView(new Image(sub));
        imgs.setFitHeight(20);
        imgs.setFitWidth(20);
        Button submitButton = new Button("Submit Your Details", imgs);
        grid.setConstraints(submitButton, 0, 9);
        submitButton.setOnAction(e -> {
            validate_CusID();
            validate_Name_Address();
            validate_cc();
            validate_Phone();

            // Condition to make sure all correct data has been entered, then close this window
            if (cond1 == true && cond2 == true && cond3 == true && cond4 == true) {

                int cust_id = Integer.parseInt(cusIDInput.getText());
                String f_name = f_nameInput.getText();
                String l_name = l_nameInput.getText();
                String address = addressInput.getText();
                int cc = Integer.parseInt(ccInput.getText());
                int tel = Integer.parseInt(telInput.getText());

                CustomerDetails customer = new CustomerDetails(cust_id, f_name, l_name, address, cc, tel);
                CustomerFileHandling custFile=new CustomerFileHandling();
                try {
                    custFile.writeCustomerFile(customer);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                CustomerWindow.close();

            }
        });

        //Close window
        InputStream back = Files.newInputStream(Paths.get("res/back.png"));
        ImageView imgb = new ImageView(new Image(back));
        imgb.setFitHeight(20);
        imgb.setFitWidth(30);
        Button backButton = new Button("Back", imgb);
        grid.setConstraints(submitButton, 1, 8);
        backButton.setOnAction(e -> {
            CustomerWindow.close();
            allCorrectInfoEntered = false;
        });

        //Add everything to grid
        grid.getChildren().addAll(vbox, cusIDLabel, cusIDInput, f_nameLabel, f_nameInput, l_nameLabel, l_nameInput, addressLabel, addressInput, ccLabel, ccInput, telLabel, telInput, submitButton, backButton);

        BackgroundFill background_fill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene Customer_scene = new Scene(grid, 500, 600);

        CustomerWindow.setScene(Customer_scene);
        CustomerWindow.getIcons().add(new Image("file:res/cus1.png"));
        CustomerWindow.showAndWait();

    }

    //Validation of user input cusID method
    public static void validate_CusID(){
        cond1  =  true;

        try{

            // Convert data from text_field into Integer
            finalCusID = Integer.parseInt(cusIDInput.getText());

            if (finalCusID <= 0) {
                cond1 = false;
                MessageBox messageBox= new MessageBox();
                messageBox.ShowMessage("Invalid customer ID");

            }
            // Catch statement to prevent program from crashing
        }catch(Exception e){
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid customer ID");
            cusIDInput.clear();
            cond1 =false;
        }

    }

    //Validation of user input for Name, surname and address
    public static void validate_Name_Address(){
        cond2 = true;

        //Prevent user to enter invalid characters
        // range that accepts only alphabets
        if(!f_nameInput.getText().matches("^[a-zA-z]+$")) {
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid First Name!");
            f_nameInput.clear();
            cond2 = false;

        }

        else {

            finalFName = f_nameInput.getText();
        }

        // range that accepts only alphabets
        if(!l_nameInput.getText().matches("^[a-zA-z]+$")) {
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid Last Name!");
            l_nameInput.clear();
            cond2 = false;

        }

        else {

            finalSurname = l_nameInput.getText();
        }

        // range that accepts only alphabets
        if(!addressInput.getText().matches("^[a-zA-Z0-9]+$")) {
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid Address!");
            addressInput.clear();
            cond2 =  false;
        }

        else{

            finalAddress = addressInput.getText();

        }
    }

    // Validation of credit card
    public static void validate_cc(){
        cond3 = true;
        try{

            // Convert data from text_field into Integer
            finalCreditCard = Integer.parseInt(ccInput.getText());

            // Prevent invalid phoneNumber
            if (finalCreditCard <= 0) {
                cond3 = false;
                MessageBox messageBox= new MessageBox();
                messageBox.ShowMessage("Invalid Credit Card!");
            }

        }catch(Exception e){
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid Credit Card!");
            ccInput.clear();
            cond3 = false;
        }
    }

    // Validation of phone number
    public static void validate_Phone(){
        cond4 = true;
        try{

            // Convert data from text_field into Integer
            finalTelNumber = Integer.parseInt(telInput.getText());

            // Prevent invalid phoneNumber
            if (finalTelNumber <= 0) {
                cond4 = false;
                MessageBox messageBox= new MessageBox();
                messageBox.ShowMessage("Invalid Phone Number!");
            }

        }catch(Exception e){
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid Phone Number!");
            telInput.clear();
            cond4 = false;
        }
    }


}
