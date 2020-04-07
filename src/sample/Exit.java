package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

//class for quit function

public class Exit {

    static Stage windowExit;

    //exception in case file fails
    public static void Quit() throws Exception {

        //main window
        windowExit = new Stage();
        windowExit.setTitle("Exit");
        windowExit.getIcons().add(new Image("file:res/Logo.jpg"));
        windowExit.setResizable(false);
        windowExit.initModality(Modality.APPLICATION_MODAL);

        GridPane exitPane = new GridPane();
        exitPane.setAlignment(Pos.CENTER);
        exitPane.setVgap(35);

        //label text
        Label exitMessage = new Label();
        exitMessage.setText("Are you sure you want to exit the application?");
        exitMessage.setTranslateX(60);
        exitMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        exitMessage.setAlignment(Pos.CENTER);

        //set an icon on this button
        InputStream sub = Files.newInputStream(Paths.get("res/submit.png"));
        ImageView imgs = new ImageView(new Image(sub));
        imgs.setFitHeight(20);
        imgs.setFitWidth(20);
        Button btnYes = new Button("Yes", imgs);
        btnYes.setMinWidth(150);
        btnYes.setFocusTraversable(false);
        //set an action for this button - application closes
        btnYes.setOnAction(e -> System.exit(0));

        //set an icon on this button
        InputStream cl = Files.newInputStream(Paths.get("res/exit2.png"));
        ImageView imgx = new ImageView(new Image(cl));
        imgx.setFitHeight(20);
        imgx.setFitWidth(20);
        Button btnNo = new Button("No", imgx);
        btnNo.setMinWidth(150);
        btnNo.setFocusTraversable(false);
        //set an action for this button - window exit closes
        btnNo.setOnAction(e -> windowExit.close());

        exitPane.add(exitMessage, 0, 0);
        exitPane.add(btnYes, 0, 1);
        exitPane.add(btnNo, 1, 1);

        BackgroundFill background_fill = new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        exitPane.setBackground(background);

        //creating the scene
        Scene scene = new Scene(exitPane, 500, 150);

        //setting the scene on the window
        windowExit.setScene(scene);
        windowExit.getIcons().add(new Image("file:res/exit2.png"));
        windowExit.showAndWait();

    }
}

