package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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

//class for sorting price and accommodationID and displaying on a table

public class SortingData {

    static Stage sortWindow;
    static int sortId;


    public static void Sort(Accommodation[] AccommodationArray) throws IOException {

        //creating a sorting window
        sortWindow = new Stage();
        sortWindow.setTitle("Sorting Choice");
        sortWindow.setResizable(false);
        sortWindow.initModality(Modality.APPLICATION_MODAL);

        //defining the gridpane
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(15, 15, 15, 15));
        grid.setVgap(10);
        grid.setHgap(12);

        Label sortingOption = new Label("Choose the type of sorting you want:");
        //setting the font on the label
        sortingOption.setFont(Font.font("Verdana", FontWeight.BOLD, 12));
        GridPane.setConstraints(sortingOption, 1, 2);

        //creating a toggle
        ToggleGroup choice = new ToggleGroup();

        RadioButton op1 = new RadioButton("Accommodation ID(Ascending)");
        op1.setToggleGroup(choice);
        op1.setSelected(true);

        RadioButton op2 = new RadioButton("Accommodation Price(Min. To Max.)");
        op2.setToggleGroup(choice);

        VBox radioBox = new VBox(10, op1, op2);
        GridPane.setConstraints(radioBox,1,4);

        InputStream sub = Files.newInputStream(Paths.get("res/submit.png"));
        ImageView imgs = new ImageView(new Image(sub));
        imgs.setFitHeight(20);
        imgs.setFitWidth(20);
        Button submit = new Button("Sort", imgs);
        submit.setOnAction(e -> {

            if (op1.isSelected()) {

                sortId = 1;

            }

            else  {

                sortId = 2;

            }

            sortWindow.close();


        });

        InputStream cl = Files.newInputStream(Paths.get("res/exit2.png"));
        ImageView imgx = new ImageView(new Image(cl));
        imgx.setFitHeight(20);
        imgx.setFitWidth(20);
        Button cancel = new Button("Cancel", imgx);
        cancel.setOnAction(e -> sortWindow.close());


        HBox buttonBox = new HBox(10, submit, cancel);
        GridPane.setConstraints(buttonBox,1,14);

        grid.getChildren().addAll(sortingOption,radioBox,buttonBox);

        BackgroundFill background_fill = new BackgroundFill(Color.ORANGE, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        grid.setBackground(background);

        Scene s1 = new Scene(grid, 500, 500);
        sortWindow.setScene(s1);
        sortWindow.getIcons().add(new Image("file:res/sort.png"));
        sortWindow.showAndWait();


    }

    public static int returnSortID(){

        return sortId;

    }


}
