package MainPackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class GameApplication extends Application {

    private boolean xTurn;
    private boolean gameOver;
    private Map<String, Button> buttons;
    private Label topLabel;

    public GameApplication() {
        this.xTurn = true;
        this.gameOver = false;
        this.buttons = new HashMap<>();
        this.topLabel = new Label("Turn: Player X");
    }

    public void start(Stage stage) {
        BorderPane pane = new BorderPane();
        GridPane gameGrid = new GridPane();
        gameGrid.setPadding(new Insets(10));

        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {  
                Button button = createButton();  
                this.buttons.put("" + i + " " + j, button);            
                gameGrid.add(button, j, i);
            }
        }

        this.topLabel.setStyle("-fx-font: 30 arial;");
        
        pane.setTop(this.topLabel);
        pane.setCenter(gameGrid);
        BorderPane.setMargin(topLabel, new Insets(10, 0, 0, 0));
        BorderPane.setAlignment(topLabel, Pos.CENTER);
        BorderPane.setAlignment(gameGrid, Pos.CENTER);
        Scene scene = new Scene(pane);
        stage.setTitle("SuperTicTacToe");
        stage.setScene(scene);
        stage.show();
    }

    private Button createButton() {
        Button button = new Button(" ");
        button.setFont(Font.font("Monospaced", 12));

        button.setOnAction((event) -> {
            if (this.gameOver) {
                return;
            }

            if (!button.getText().equals(" ")) {
                return;
            }

            if (this.xTurn) {
                button.setText("X");
                if (checkAllButtons()) {
                    this.gameOver = true;
                    this.topLabel.setText("X Won!");
                } else {
                    this.xTurn = false;
                    this.topLabel.setText("Turn: Player O");
                }

            } else {
                button.setText("O");
                if (checkAllButtons()) {
                    this.gameOver = true;
                    this.topLabel.setText("O Won!");
                } else {
                    this.xTurn = true;
                    this.topLabel.setText("Turn: Player X");
                }
            }
        });

        return button;
    }

    private boolean checkAllButtons() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                if (checkButtonInWinningPattern("" + i + " " + j)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkButtonInWinningPattern(String rowColumn) {
        return checkButtonInHorizontalWinningPattern(rowColumn);
    }

    private boolean checkButtonInHorizontalWinningPattern(String rowColumn) {
        if (this.buttons.get(rowColumn).getText().equals(" ")) {
            return false;
        }
        
        String[] rowAndColumn = rowColumn.split(" ");
        
        int row = Integer.valueOf(rowAndColumn[0]);
        int column = Integer.valueOf(rowAndColumn[1]);

        int start = column - 4;
        if (start < 0) {
            start = 0;
        }

        int end = column + 4;
        if (end > 29) {
            end = 29;
        }

        ArrayList<Button> buttonsToCheck = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            buttonsToCheck.add(this.buttons.get("" + row + " " + i));
        }

        for (int i = 0; i <= end - start - 4; i++) {
            List<Button> buttonsToCheckSublist = buttonsToCheck.subList(i, i + 5);
            int sum = 0;

            for (Button button : buttonsToCheckSublist) {
                if (button.getText().equals("X")) {
                    sum++;
                } else if (button.getText().equals("O")) {
                    sum--;
                }
            }

            System.out.println(sum);

            if (sum == 5 || sum == -5) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        launch();
    }

}