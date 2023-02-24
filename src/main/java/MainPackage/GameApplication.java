package MainPackage;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;


public class GameApplication extends Application {

    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        GridPane gameGrid = new GridPane();
        gameGrid.setPadding(new Insets(10));
        

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                Button button = new Button(" ");
                button.setStyle("-fx-background-radius: 0");
                button.setFont(Font.font("Arial", 12));
                gameGrid.add(button, i, j);
            }
        }

        Label topLabel = new Label("Turn: Player X");
        topLabel.setStyle("-fx-font: 30 arial;");
        
        pane.setTop(topLabel);
        pane.setCenter(gameGrid);
        BorderPane.setMargin(topLabel, new Insets(10, 0, 0, 0));
        BorderPane.setAlignment(topLabel, Pos.CENTER);
        BorderPane.setAlignment(gameGrid, Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setTitle("SuperTicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}