package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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
import java.text.*;
import java.util.Date;

//class for searching data

public class SearchWindow {

    static Stage searchWindow;
    static TextField input_price, input_available, input_date;
    static String finalAvailable, finalDate;
    static int finalPrice;
    static boolean cond1, cond2, cond3;

    SimpleDateFormat sd_format = new SimpleDateFormat("dd-MM-yyyyy");

    public void search() throws IOException {

        //creating a search window
        searchWindow = new Stage();
        searchWindow.setTitle("Search Accommodation");
        searchWindow.getIcons().add(new Image("file:res/search1.png"));
        searchWindow.setResizable(false);
        searchWindow.initModality(Modality.APPLICATION_MODAL);

        GridPane viewAll = new GridPane();
        viewAll.setAlignment(Pos.CENTER);
        viewAll.setVgap(30);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        //setting the title of the search window
        Label programTitle = new Label();
        programTitle.setText("Search Options:-");
        programTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        vbox.getChildren().add(programTitle);

        input_price = new TextField();
        input_price.setPromptText("Enter max. price here:");
        input_price.setFocusTraversable(false);

        InputStream price1 = Files.newInputStream(Paths.get("res/price.png"));
        ImageView img1 = new ImageView(new Image(price1));
        img1.setFitHeight(24);
        img1.setFitWidth(25);
        Button price = new Button("Search by price", img1);
        price.setAlignment(Pos.CENTER);
        price.setMaxWidth(250);
        price.setFocusTraversable(false);
        price.setOnAction(event -> {
            int inputPrice=0;

            //converting inputPrice from a string to an integer
            try{
               inputPrice= Integer.parseInt(input_price.getText());
            }
            catch (NumberFormatException e){

            }

            AccFileHandling fileHandling = new AccFileHandling();
            Accommodation[] AccommodationArray = fileHandling.readRAccFile();
            AccommodationArray = searchPrice(inputPrice, AccommodationArray);
            validate_Price();

            if (cond1) {
                AccommodationTable viewAllPrice = new AccommodationTable();
                try {
                    viewAllPrice.Table(AccommodationArray);
                } catch (Exception e) {
                e.printStackTrace();
                }
            }

        });

        input_available = new TextField();
        input_available.setPromptText("Enter Available or Not-Available");
        input_available.setFocusTraversable(false);

        InputStream avl1 = Files.newInputStream(Paths.get("res/available.png"));
        ImageView img2 = new ImageView(new Image(avl1));
        img2.setFitHeight(22);
        img2.setFitWidth(22);
        Button avl = new Button("Search by availability", img2);
        avl.setAlignment(Pos.CENTER);
        avl.setMaxWidth(250);
        avl.setFocusTraversable(false);
        avl.setOnAction(event -> {

            String inputAvailable = input_available.getText();
            AccFileHandling fileHandling = new AccFileHandling();
            Accommodation[] AccommodationArray = fileHandling.readRAccFile();
            AccommodationArray = searchAvailability(inputAvailable, AccommodationArray);
            validate_Availability();

            if(cond2){
                AccommodationTable viewAllAvailability = new AccommodationTable();
                try {
                    viewAllAvailability.Table(AccommodationArray);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        });

        input_date = new TextField();
        input_date.setPromptText("DD-MM-YYYY");
        input_date.setFocusTraversable(false);


        //inputting an image on the back button
        InputStream date1 = Files.newInputStream(Paths.get("res/date.png"));
        ImageView img3 = new ImageView(new Image(date1));
        img3.setFitHeight(22);
        img3.setFitWidth(22);
        Button date = new Button("Search by date", img3);
        date.setAlignment(Pos.CENTER);
        date.setMaxWidth(250);
        date.setFocusTraversable(false);
        date.setOnAction(event -> {

            String inputDate = input_date.getText();
            validate_Date();
            AccFileHandling fileHandling = new AccFileHandling();
            Accommodation[] AccommodationArray = fileHandling.readRAccFile();
                    if(cond3) {
                        try {

                            AccommodationArray = searchDate(inputDate, AccommodationArray);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }


                        AccommodationTable viewAllDate = new AccommodationTable();
                        try {
                            viewAllDate.Table(AccommodationArray);
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
        });

        //inputting an image on the back button
        InputStream back = Files.newInputStream(Paths.get("res/back.png"));
        ImageView imgb = new ImageView(new Image(back));
        imgb.setFitHeight(25);
        imgb.setFitWidth(40);
        Button btnBack = new Button("Back", imgb);
        btnBack.setText("Back");
        btnBack.setAlignment(Pos.CENTER);
        btnBack.setMaxWidth(250);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> searchWindow.close());

        viewAll.getChildren().addAll(vbox);

        BackgroundFill background_fill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        viewAll.setBackground(background);

        viewAll.add(input_price, 0, 1);
        viewAll.add(price, 0, 2);
        viewAll.add(input_available, 0, 3);
        viewAll.add(avl, 0, 4);
        viewAll.add(input_date, 0, 5);
        viewAll.add(date, 0, 6);
        viewAll.add(btnBack, 0, 8);

        Scene scene = new Scene(viewAll, 700, 700);

        searchWindow.setScene(scene);
        searchWindow.getIcons().add(new Image("file:res/search1.png"));
        searchWindow.showAndWait();

    }

    //searching method for price of accommodation
    public static Accommodation[] searchPrice(double price, Accommodation[] AccommodationArray) {


        int n = AccommodationArray.length;
        int count = 0;
        //for looping for going through accommodation prices
        for (int i = 0; i < n; i++) {
            if (AccommodationArray[i].getCost() <= price) {
                count++;
            }
        }


        Accommodation[] result = new Accommodation[count];
        int q = 0;
        for (int i = 0; i < n; i++) {
            if (AccommodationArray[i].getCost() <= price) {
                result[q] = AccommodationArray[i];
                q++;
            }


        }
        return result;

    }

    //searching method for availability of accommodation
    public static Accommodation[] searchAvailability(String criteria, Accommodation[] AccommodationArray) {


        int n = AccommodationArray.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (AccommodationArray[i].getBooking_status().equalsIgnoreCase(criteria)) {
                count++;
            }
        }
        Accommodation[] result = new Accommodation[count];
        int q = 0;
        for (int i = 0; i < n; i++) {
            if (AccommodationArray[i].getBooking_status().equalsIgnoreCase(criteria)) {
                result[q] = AccommodationArray[i];
                q++;
            }


        }
        return result;

    }

    //searching method for date
    public  Accommodation[] searchDate(String date1, Accommodation[] AccommodationArray) throws ParseException {

        Date d1 = sd_format.parse(date1);
        int n = AccommodationArray.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            //comparing dates
            Date d2 = sd_format.parse(AccommodationArray[i].getDate());
            if(d1.compareTo(d2) < 0) {
                count++;
            }
        }

        Accommodation[] result = new Accommodation[count];
        int q = 0;
        for (int i = 0; i < n; i++) {
            //comparing dates
            Date d2 = sd_format.parse(AccommodationArray[i].getDate());
            if(d1.compareTo(d2) < 0) {
                result[q] = AccommodationArray[i];
                q++;
            }

        }

        return result;

    }


    //Validation of user input of price
    public static void validate_Price() {

        cond1  =  true;

            try {

                // Convert data from text_field into integer
                finalPrice = Integer.parseInt(input_price.getText());

                if (finalPrice <= 0) {
                    MessageBox messageBox= new MessageBox();
                    messageBox.ShowMessage("Invalid Input");
                }
                // Catch statement to prevent program from crashing
                } catch(NumberFormatException e) {

                    MessageBox messageBox= new MessageBox();
                    messageBox.ShowMessage("Invalid Input");
                    input_price.clear();
                    cond1 = false;

            }

    }


    //validate user input availability
    public static void validate_Availability() {

        cond2 = true;

        //Prevent user to enter invalid input
        if (!input_available.getText().equalsIgnoreCase("Available") || !input_available.getText().equalsIgnoreCase("Not-Available")) {
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid Input. Please Enter Available or Not-Available");
            input_available.clear();
            cond2 = false;

        }

        else {

            finalAvailable = input_available.getText();

        }
        
    }

    //Validation of user input for date
    public static void validate_Date() {

        cond3 = true;

        //Prevent user to enter invalid characters

        if(!input_date.getText().matches("[0-9]{1,2}(/|-)[0-9]{1,2}(/|-)[0-9]{4}")) {
            MessageBox messageBox= new MessageBox();
            messageBox.ShowMessage("Invalid Date!");
            input_date.clear();
            cond3 = false;

        }

        else {

            finalDate = input_date.getText();
        }
        

    }



} //end of search window

