package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

//main class

public class HouseRental extends Application {
    private Stage window;

    //method that launches the application
    public static void main(String[] args) {

        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;

        Pane root = new Pane();
        StackPane lo = new StackPane();
        root.setPrefSize(1200, 700);

        //try statement for displaying yellow background
        try (InputStream is = Files.newInputStream(Paths.get("res/Background1.jpg"))){
            ImageView img = new ImageView(new Image(is));
            img.setFitHeight(700);
            img.setFitWidth(750);

            root.getChildren().add(img);

        }catch (IOException e) {
            System.out.println("Background Not Found");
        }

        //try statement for displaying the house logo at the right of the scene
        try (InputStream in = Files.newInputStream(Paths.get("res/Logo1.png"))){
            ImageView img = new ImageView(new Image(in));
            img.setFitHeight(200);
            img.setFitWidth(250);
            img.setTranslateX(850);
            img.setTranslateY(150);

            lo.getChildren().add(img);

        }catch (IOException e) {
            System.out.println("Logo Not Found");
        }

        GridPane mainMenu = new GridPane();
        mainMenu.setAlignment(Pos.CENTER);
        mainMenu.setPadding(new Insets(0, 0, 0, 15));
        mainMenu.setVgap(35);

        VBox vbox = new VBox();
        vbox.setAlignment(Pos.CENTER);

        //setting the title of the application window
        Label programTitle = new Label();
        programTitle.setText("Welcome to House Rental System");
        //setting the font for the title
        programTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        //positioning the title
        programTitle.setTranslateX(240);
        programTitle.setTranslateY(35);
        vbox.getChildren().add(programTitle);

        //customer details menu
        InputStream cus = Files.newInputStream(Paths.get("res/cus1.png"));
        ImageView img0 = new ImageView(new Image(cus));
        img0.setFitHeight(25);
        img0.setFitWidth(25);
        Button btnAdd = new Button("Enter Customer Details", img0);
        btnAdd.setAlignment(Pos.CENTER);
        btnAdd.setMaxWidth(250);
        btnAdd.setFocusTraversable(false);
        btnAdd.setOnAction(e -> {

            try {
                CustomerInput.input();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        });

        //viewing accommodation table menu
        InputStream view = Files.newInputStream(Paths.get("res/accommodation1.png"));
        ImageView img1 = new ImageView(new Image(view));
        img1.setFitHeight(24);
        img1.setFitWidth(25);
        Button btnAcc = new Button("View All Accommodations", img1);
        btnAcc.setAlignment(Pos.CENTER);
        btnAcc.setMaxWidth(250);
        btnAcc.setFocusTraversable(false);
        btnAcc.setOnAction(e -> {
            try {

                AccFileHandling fileHandling = new AccFileHandling();
                Accommodation [] AccommodationArray = fileHandling.readRAccFile();
                AccommodationTable viewAll = new AccommodationTable();
                viewAll.Table(AccommodationArray);

            } catch (Exception ex) {
            }

        });

        //search menu
        InputStream search = Files.newInputStream(Paths.get("res/search1.png"));
        ImageView img2 = new ImageView(new Image(search));
        img2.setFitHeight(23);
        img2.setFitWidth(20);
        Button btnSearch = new Button("Search", img2);
        btnSearch.setAlignment(Pos.CENTER);
        btnSearch.setMaxWidth(250);
        btnSearch.setFocusTraversable(false);
        btnSearch.setOnAction(e -> {
            try {
                SearchWindow Search = new SearchWindow();
                Search.search();
            } catch (Exception ex) {
            }

        });

        //booking accommodation menu
        InputStream book = Files.newInputStream(Paths.get("res/book.png"));
        ImageView img3 = new ImageView(new Image(book));
        img3.setFitHeight(25);
        img3.setFitWidth(25);
        Button btnBook = new Button("Book Your Accommodation", img3);
        btnBook.setAlignment(Pos.CENTER);
        btnBook.setMaxWidth(250);
        btnBook.setFocusTraversable(false);
        btnBook.setOnAction(event -> {
            AccFileHandling fileHandling = new AccFileHandling();
            Accommodation [] AccommodationArray = fileHandling.readRAccFile();
            BookAccommodation bookAccommodation = new BookAccommodation();
            try {
                bookAccommodation.BookAcc(AccommodationArray);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //sorting menu
        InputStream sor = Files.newInputStream(Paths.get("res/sort.png"));
        ImageView img5 = new ImageView(new Image(sor));
        img5.setFitHeight(25);
        img5.setFitWidth(25);
        Button btnSort = new Button("Sort By", img5);
        btnSort.setAlignment(Pos.CENTER);
        btnSort.setMaxWidth(250);
        btnSort.setFocusTraversable(false);
        btnSort.setOnAction(e -> {
            AccFileHandling fileHandling = new AccFileHandling();
            Accommodation [] AccommodationArray = fileHandling.readRAccFile();
            try {
                sorting(AccommodationArray);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        //exiting menu
        InputStream exit = Files.newInputStream(Paths.get("res/exit1.png"));
        ImageView img4 = new ImageView(new Image(exit));
        img4.setFitHeight(25);
        img4.setFitWidth(25);
        Button btnExit = new Button("Exit", img4);
        btnExit.setAlignment(Pos.CENTER);
        btnExit.setMaxWidth(250);
        btnExit.setFocusTraversable(false);
        btnExit.setOnAction(e -> {
            try {

                Exit.Quit();
            } catch (Exception ex) {
            }
        });

        //adding all the menu button to the vBox
        mainMenu.getChildren().add(vbox);
        mainMenu.add(btnAdd, 0, 9);
        mainMenu.add(btnAcc, 0, 10);
        mainMenu.add(btnSort, 0, 11);
        mainMenu.add(btnSearch, 1, 9);
        mainMenu.add(btnBook, 1, 10);
        mainMenu.add(btnExit, 1, 11);
        mainMenu.setHgap(60);

        StackPane st = new StackPane();

        root.getChildren().addAll(mainMenu, st, lo);

        Scene scene = new Scene(root, 1200, 700);
        

        window.setTitle("House Rental App");
        window.setScene(scene);
        window.setOnCloseRequest(e -> {
            try {
                e.consume();
                Exit.Quit();

            } catch (Exception ex) {
            }

        });
        window.getIcons().add(new Image("file:res/Logo1.png"));
        window.show();

    }


    //sorting method for accID & price
    public static void sorting(Accommodation[] AccommodationArray) throws Exception {

        //allow user to choose Sorting type
        SortingData.Sort(AccommodationArray);

        //variables
        int tempId, tempTel;
        String tempOwner, tempLoc, tempAvailability, tempDate, tempType;
        double tempCost;

        // Sort by sortingID
        switch (SortingData.returnSortID()) {

            //bubble sorting algorithm
            // Single sort by roomID (Ascending)
            case 1:

                for (int i = 0; i < AccommodationArray.length; i++) {

                    for (int j = i + 1; j < AccommodationArray.length; j++) {

                        if (AccommodationArray[i].getAccommodation_id() > AccommodationArray[j].getAccommodation_id()) {

                            // Swapping process
                            tempId = AccommodationArray[i].getAccommodation_id();
                            tempType = AccommodationArray[i].getType_of_accommodation();
                            tempOwner = AccommodationArray[i].getOwner();
                            tempTel = AccommodationArray[i].getTelephone_number();
                            tempCost = AccommodationArray[i].getCost();
                            tempLoc = AccommodationArray[i].getLocation();
                            tempAvailability = AccommodationArray[i].getBooking_status();
                            tempDate = AccommodationArray[i].getDate();


                            AccommodationArray[i].setAccommodation_id(AccommodationArray[j].getAccommodation_id());
                            AccommodationArray[i].setType_of_accommodation(AccommodationArray[j].getType_of_accommodation());
                            AccommodationArray[i].setOwner(AccommodationArray[j].getOwner());
                            AccommodationArray[i].setTelephone_number(AccommodationArray[j].getTelephone_number());
                            AccommodationArray[i].setCost(AccommodationArray[j].getCost());
                            AccommodationArray[i].setLocation(AccommodationArray[j].getLocation());
                            AccommodationArray[i].setBooking_status(AccommodationArray[j].getBooking_status());
                            AccommodationArray[i].setDate(AccommodationArray[j].getDate());

                            AccommodationArray[j].setAccommodation_id(tempId);
                            AccommodationArray[j].setType_of_accommodation(tempType);
                            AccommodationArray[j].setOwner(tempOwner);
                            AccommodationArray[j].setTelephone_number(tempTel);
                            AccommodationArray[j].setCost(tempCost);
                            AccommodationArray[j].setLocation(tempLoc);
                            AccommodationArray[j].setBooking_status(tempAvailability);
                            AccommodationArray[j].setDate(tempDate);

                        }

                    }

                }
                // Display the new sorted table
                AccommodationTable.Table(AccommodationArray);

                break;

            case 2:

                for (int i = 0; i < AccommodationArray.length; i++) {

                    for (int j = i + 1; j < AccommodationArray.length; j++) {

                        // Checks pricing
                        if (AccommodationArray[i].getCost() > AccommodationArray[j].getCost()) {

                            //swapping process
                            tempId = AccommodationArray[i].getAccommodation_id();
                            tempType = AccommodationArray[i].getType_of_accommodation();
                            tempOwner = AccommodationArray[i].getOwner();
                            tempTel = AccommodationArray[i].getTelephone_number();
                            tempCost = AccommodationArray[i].getCost();
                            tempLoc = AccommodationArray[i].getLocation();
                            tempAvailability = AccommodationArray[i].getBooking_status();
                            tempDate = AccommodationArray[i].getDate();

                            AccommodationArray[i].setAccommodation_id(AccommodationArray[j].getAccommodation_id());
                            AccommodationArray[i].setType_of_accommodation(AccommodationArray[j].getType_of_accommodation());
                            AccommodationArray[i].setOwner(AccommodationArray[j].getOwner());
                            AccommodationArray[i].setTelephone_number(AccommodationArray[j].getTelephone_number());
                            AccommodationArray[i].setCost(AccommodationArray[j].getCost());
                            AccommodationArray[i].setLocation(AccommodationArray[j].getLocation());
                            AccommodationArray[i].setBooking_status(AccommodationArray[j].getBooking_status());
                            AccommodationArray[i].setDate(AccommodationArray[j].getDate());

                            AccommodationArray[j].setAccommodation_id(tempId);
                            AccommodationArray[j].setType_of_accommodation(tempType);
                            AccommodationArray[j].setOwner(tempOwner);
                            AccommodationArray[j].setTelephone_number(tempTel);
                            AccommodationArray[j].setCost(tempCost);
                            AccommodationArray[j].setLocation(tempLoc);
                            AccommodationArray[j].setBooking_status(tempAvailability);
                            AccommodationArray[j].setDate(tempDate);

                        }

                    }

                }

                // Display the new sorted table
                AccommodationTable.Table(AccommodationArray);
                break;

        }

    } // end of sorting method

}



