package MainPackage;

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
        Button restart = new Button("Restart");
        
        menu.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        title.setFont(new Font("Arial", 50));
        title.setPadding(new Insets(100, 0, 0, 0));

        resume.setFont(new Font("Arial", 30));
        restart.setFont(new Font("Arial", 30));
        resume.setPrefWidth(200);
        restart.setPrefWidth(200);

        resume.setOnAction(event -> {
            scene.setRoot(pane);
        });
        
        restart.setOnAction(event -> {

        });

        optionsLayout.getChildren().addAll(resume, restart);
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.setPadding(new Insets(-400, 20, 20, 20));
        optionsLayout.setSpacing(20);
        menu.setCenter(optionsLayout);

        
        return menu;
    }
}
