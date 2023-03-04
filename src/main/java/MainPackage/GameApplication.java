package MainPackage;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
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
    private MainMenu mainMenu;

    public GameApplication() {
        this.xTurn = true;
        this.gameOver = false;
        this.buttons = new HashMap<>();
        this.topLabel = new Label("Turn: Player X");
        this.mainMenu = new MainMenu();
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

        Parent mainMenuLayout = this.mainMenu.createMenu(scene, pane);

        scene.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ESCAPE) {
                if (scene.getRoot().equals(pane)) {
                    scene.setRoot(mainMenuLayout);
                } else if (scene.getRoot().equals(mainMenuLayout)) {
                    scene.setRoot(pane);                    
                }
            }
        });

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
        return (checkButtonInVerticalWinningPattern(rowColumn) || 
                checkButtonInHorizontalWinningPattern(rowColumn) || 
                checkButtonInLTRDiagonalWinningPattern(rowColumn) ||
                checkButtonInRTLDiagonalWinningPattern(rowColumn));
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

            if (sum == 5 || sum == -5) {
                return true;
            }
        }

        return false;
    }

    private boolean checkButtonInVerticalWinningPattern(String rowColumn) {
        if (this.buttons.get(rowColumn).getText().equals(" ")) {
            return false;
        }
        
        String[] rowAndColumn = rowColumn.split(" ");
        
        int row = Integer.valueOf(rowAndColumn[0]);
        int column = Integer.valueOf(rowAndColumn[1]);

        int start = row - 4;
        if (start < 0) {
            start = 0;
        }

        int end = row + 4;
        if (end > 29) {
            end = 29;
        }

        ArrayList<Button> buttonsToCheck = new ArrayList<>();
        for (int i = start; i <= end; i++) {
            buttonsToCheck.add(this.buttons.get("" + i + " " + column));
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

            if (sum == 5 || sum == -5) {
                return true;
            }
        }

        return false;
    }

    private boolean checkButtonInLTRDiagonalWinningPattern(String rowColumn) {
        if (this.buttons.get(rowColumn).getText().equals(" ")) {
            return false;
        }
        
        String[] rowAndColumn = rowColumn.split(" ");
        
        int row = Integer.valueOf(rowAndColumn[0]);
        int column = Integer.valueOf(rowAndColumn[1]);
        int rowStart = 0;
        int columnStart = 0;
        int rowEnd = 0;
        int columnEnd = 0;

        if (row <= column) {
            rowStart = row - 4;
            columnStart = column - 4;
            if (rowStart < 0) {
                columnStart = columnStart + (-1 * rowStart);
                rowStart = 0;
            }
        } else if (row > column) {
            rowStart = row - 4;
            columnStart = column - 4;
            if (columnStart < 0) {
                rowStart = rowStart + (-1 * columnStart);
                columnStart = 0;
            }
        }

        if (row >= column) {
            rowEnd = row + 4;
            columnEnd = column + 4;
            if (rowEnd > 29) {
                columnEnd = columnEnd - (rowEnd - 29);
                rowEnd = 29;
            }
        } else if (row < column) {
            rowEnd = row + 4;
            columnEnd = column + 4;
            if (columnEnd > 29) {
                rowEnd = rowEnd - (columnEnd - 29);
                columnEnd = 29;
            }
        }

        int numberOfButtonsToCheck = rowEnd - rowStart;
        ArrayList<Button> buttonsToCheck = new ArrayList<>();
        for (int i = 0; i <= numberOfButtonsToCheck; i++) {
            buttonsToCheck.add(this.buttons.get("" + (rowStart + i) + " " + (columnStart + i)));
        }

        for (int i = 0; i <= rowEnd - rowStart - 4; i++) {
            List<Button> buttonsToCheckSublist = buttonsToCheck.subList(i, i + 5);
            int sum = 0;

            for (Button button : buttonsToCheckSublist) {
                if (button.getText().equals("X")) {
                    sum++;
                } else if (button.getText().equals("O")) {
                    sum--;
                }
            }

            if (sum == 5 || sum == -5) {
                return true;
            }
        }
        
        return false;
    }

    private boolean checkButtonInRTLDiagonalWinningPattern(String rowColumn) {
        if (this.buttons.get(rowColumn).getText().equals(" ")) {
            return false;
        }
        
        String[] rowAndColumn = rowColumn.split(" ");
        
        int row = Integer.valueOf(rowAndColumn[0]);
        int column = Integer.valueOf(rowAndColumn[1]);
        int rowStart = 0;
        int columnStart = 0;
        int rowEnd = 0;
        int columnEnd = 0;   
        
        if (row <= 29 - column) {
            rowStart = row - 4;
            columnStart = column + 4;
            if (rowStart < 0) {
                columnStart = columnStart + rowStart;
                rowStart = 0;
            }
        } else if (row > 29 - column) {
            rowStart = row - 4;
            columnStart = column + 4;
            if (columnStart > 29) {
                rowStart = rowStart + (columnStart - 29);
                columnStart = 29;
            }
        }

        if (29 - row <= column) {
            rowEnd = row + 4;
            columnEnd = column - 4;
            if (rowEnd > 29) {
                columnEnd = columnEnd + (rowEnd - 29);
                rowEnd = 29;
            }
        } else if (29 - row > column) {
            rowEnd = row + 4;
            columnEnd = column - 4;
            if (columnEnd < 0) {
                rowEnd = rowEnd + columnEnd;
                columnEnd = 0;
            }
        }

        int numberOfButtonsToCheck = rowEnd - rowStart;
        ArrayList<Button> buttonsToCheck = new ArrayList<>();
        for (int i = 0; i <= numberOfButtonsToCheck; i++) {
            buttonsToCheck.add(this.buttons.get("" + (rowStart + i) + " " + (columnStart - i)));
        }

        for (int i = 0; i <= rowEnd - rowStart - 4; i++) {
            List<Button> buttonsToCheckSublist = buttonsToCheck.subList(i, i + 5);
            int sum = 0;

            for (Button button : buttonsToCheckSublist) {
                if (button.getText().equals("X")) {
                    sum++;
                } else if (button.getText().equals("O")) {
                    sum--;
                }
            }

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