package MainPackage;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MainMenu {
    
    public Parent createMenu(Scene scene, BorderPane pane) {
        BorderPane menu = new BorderPane();
        Label title = new Label("Game Menu");
        VBox optionsLayout = new VBox();
        Button resume = new Button("Resume");
        Button quitGame = new Button("Quit Game");
        
        menu.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        title.setFont(new Font("Arial", 50));
        title.setPadding(new Insets(100, 0, 0, 0));

        resume.setFont(new Font("Arial", 30));
        quitGame.setFont(new Font("Arial", 30));
        resume.setPrefWidth(200);
        quitGame.setPrefWidth(200);

        resume.setOnAction(event -> {
            scene.setRoot(pane);
        });
        
        quitGame.setOnAction(event -> {
            Platform.exit();
        });

        optionsLayout.getChildren().addAll(resume, quitGame);
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.setPadding(new Insets(-400, 20, 20, 20));
        optionsLayout.setSpacing(20);
        menu.setCenter(optionsLayout);

        
        return menu;
    }
}
