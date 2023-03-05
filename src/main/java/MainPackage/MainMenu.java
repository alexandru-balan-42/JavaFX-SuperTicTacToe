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
    
    public Parent createMenu(Scene scene, BorderPane pane, Button restartButton, Button scoreResetButton) {
        BorderPane menu = new BorderPane();
        Label title = new Label("Game Menu");
        VBox optionsLayout = new VBox();
        Button resume = new Button("Resume");
        Button quitGame = new Button("Quit Game");
        
        menu.setTop(title);
        BorderPane.setAlignment(title, Pos.CENTER);
        title.setFont(new Font("Arial", 50));
        title.setPadding(new Insets(100, 0, 0, 0));

        styleButton(resume);
        styleButton(restartButton);
        styleButton(scoreResetButton);
        styleButton(quitGame);

        resume.setOnAction(event -> {
            scene.setRoot(pane);
        });
        
        quitGame.setOnAction(event -> {
            Platform.exit();
        });

        optionsLayout.getChildren().addAll(resume, restartButton, scoreResetButton, quitGame);
        optionsLayout.setAlignment(Pos.CENTER);
        optionsLayout.setPadding(new Insets(-270, 20, 20, 20));
        optionsLayout.setSpacing(20);
        menu.setCenter(optionsLayout);

        
        return menu;
    }

    private void styleButton(Button button) {
        button.setFont(new Font("Arial", 30));
        button.setPrefWidth(220);
    }
}
