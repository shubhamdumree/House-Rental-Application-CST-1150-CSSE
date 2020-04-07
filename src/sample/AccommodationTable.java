package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

//main accommodation table class

public class AccommodationTable {

    private static Stage  AccommodationWindow;
    private static TableView AccommodationTable;


    public static void Table(Accommodation[] AccommodationArray) throws Exception {

        //creating the accommodation table
        AccommodationWindow = new Stage();
        AccommodationWindow.getIcons().add(new Image("file:res/accommodation1.png"));
        AccommodationWindow.setResizable(false);
        AccommodationWindow.setTitle("Viewing All Accommodations");

        double screenWidth = Screen.getPrimary().getBounds().getWidth();
        double columnWidth = screenWidth / 7;

        //setting the style for the table background and columns
        String style = "-fx-background-color: orange; -fx-text-fill: black; -fx-border-color: black; -fx-border-radius: 5";

        //creating each column
        TableColumn<Accommodation, Integer> IDColumns = new TableColumn<>("Accommodation ID");
        IDColumns.setCellValueFactory(new PropertyValueFactory<>("accommodation_id"));
        IDColumns.setMinWidth(columnWidth);
        //prevent from resizing the table
        IDColumns.setResizable(false);
        IDColumns.setStyle(style);

        TableColumn<Accommodation, String> TAcc = new TableColumn<>("Type Of Accommodation");
        TAcc.setCellValueFactory(new PropertyValueFactory<>("type_of_accommodation"));
        TAcc.setMinWidth(153);
        TAcc.setResizable(false);
        TAcc.setStyle(style);

        TableColumn<Accommodation, String> OwnerColumns = new TableColumn<>("Owner");
        OwnerColumns.setCellValueFactory(new PropertyValueFactory<>("Owner"));
        OwnerColumns.setMinWidth(columnWidth);
        OwnerColumns.setSortable(false);
        OwnerColumns.setResizable(false);
        OwnerColumns.setStyle(style);

        TableColumn<Accommodation, String> TelColumns = new TableColumn<>("Telephone Number");
        TelColumns.setCellValueFactory(new PropertyValueFactory<>("telephone_number"));
        TelColumns.setMinWidth(columnWidth);
        TelColumns.setResizable(false);
        TelColumns.setStyle(style);

        TableColumn<Accommodation, String> CostColumns = new TableColumn<>("Cost");
        CostColumns.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        CostColumns.setMinWidth(columnWidth);
        CostColumns.setResizable(false);
        CostColumns.setStyle(style);

        TableColumn<Accommodation, String> LocationStatus = new TableColumn<>("Location");
        LocationStatus.setCellValueFactory(new PropertyValueFactory<>("Location"));
        LocationStatus.setMinWidth(columnWidth);
        LocationStatus.setResizable(false);
        LocationStatus.setStyle(style);

        TableColumn<Accommodation, String> BColumn = new TableColumn<>("Booking Status");
        BColumn.setCellValueFactory(new PropertyValueFactory<>("booking_status"));
        BColumn.setMinWidth(columnWidth);
        BColumn.setResizable(false);
        BColumn.setStyle(style);

        TableColumn<Accommodation, String> DateColumn = new TableColumn<>("Date Available");
        DateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        DateColumn.setMinWidth(columnWidth);
        DateColumn.setResizable(false);
        DateColumn.setStyle(style);

        AccommodationTable = new TableView<>();
        AccommodationTable.setFocusTraversable(false);
        AccommodationTable.setEditable(false);
        AccommodationTable.prefHeightProperty().bind(AccommodationWindow.heightProperty());
        AccommodationTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);


        //loop till the end of array to display accommodation values
        for (Accommodation AccommodationArray1 : AccommodationArray) {
            AccommodationTable.getItems().add(new Accommodation(AccommodationArray1.getAccommodation_id(), AccommodationArray1.getType_of_accommodation(), AccommodationArray1.getOwner(), AccommodationArray1.getTelephone_number(), AccommodationArray1.getCost(), AccommodationArray1.getLocation(), AccommodationArray1.getBooking_status(), AccommodationArray1.getDate()));
            }

            AccommodationTable.getColumns().addAll(IDColumns, TAcc, OwnerColumns, TelColumns, CostColumns, LocationStatus, BColumn, DateColumn);


        //adding a image on the back button
        InputStream back = Files.newInputStream(Paths.get("res/back.png"));
        ImageView imgb = new ImageView(new Image(back));
        imgb.setFitHeight(25);
        imgb.setFitWidth(40);
        Button btnBack = new Button("Back");
        btnBack.setPadding(new Insets(10, 10, 10, 10));
        btnBack.setMinWidth(200);
        btnBack.setFocusTraversable(false);
        btnBack.setOnAction(e -> AccommodationWindow.close());

        VBox vbox = new VBox();
        vbox.getChildren().addAll(AccommodationTable, btnBack);
        vbox.setAlignment(Pos.CENTER);

        //adding a background color to the table
        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        vbox.setBackground(background);

        BorderPane bp = new BorderPane();
        bp.setCenter(vbox);

        //setting the scene on the accommodation window and setting the size
        Scene scene = new Scene(bp, 1000, 700);
        AccommodationWindow.setScene(scene);
        AccommodationWindow.show();

    }
}


