package sample;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

//this class was not used in
public class BookingConfirmationGUI {

    static Stage windowBookGui;

    public static void BookFx() throws Exception {

        windowBookGui = new Stage();
        windowBookGui.setTitle("Booking Confirmation");
        windowBookGui.getIcons().add(new Image("file:res/submit.jpg"));
        windowBookGui.setResizable(false);
        windowBookGui.initModality(Modality.APPLICATION_MODAL);

        GridPane confirmPane = new GridPane();
        confirmPane.setAlignment(Pos.CENTER);
        confirmPane.setVgap(35);

        Label confirmMessage = new Label();
        confirmMessage.setText("Congratulations you have successfully booked this accommodation");
        confirmMessage.setTranslateX(60);
        confirmMessage.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
        confirmMessage.setAlignment(Pos.CENTER);

        Button btnNo = new Button("Close");
        btnNo.setMinWidth(150);
        btnNo.setFocusTraversable(false);
        btnNo.setOnAction(e -> windowBookGui.close());

        confirmPane.add(confirmMessage, 0, 0);
        confirmPane.add(btnNo, 1, 1);

        Scene scene = new Scene(confirmMessage, 500, 150);

        windowBookGui.setScene(scene);
        windowBookGui.getIcons().add(new Image("file:res/exit2.png"));
        windowBookGui.showAndWait();

    }

}
