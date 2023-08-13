package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class TicTacToe extends Application {
    private  Button buttons[][] = new Button[3][3];
    private  Label playerXScoreLabel , playerOScoreLabel;
    private  boolean playerXturn = true;
    private  int playerXscore = 0 , playerOScore = 0;
    private BorderPane ContentPane() {
        BorderPane root = new BorderPane();

//        Tittle
        Label titlelabel = new Label(" Tic Tac Toe");
        titlelabel.setStyle("-fx-font-size : 24pt ;-fx-font-weight :bold;");
        root.setTop(titlelabel);



//        game board
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        for (int i = 0; i <3 ; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = new Button();
                button.setPrefSize(100,100);
                button.setStyle("-fx-font-size : 24pt ;-fx-font-weight :bold;");
                button.setOnAction(event->buttonClicked(button));
               buttons[i][j] = button;
                gridPane.add(button , j , i);
            }

        }
root.setCenter(gridPane);
//        score
        HBox scoreBoard = new HBox(20);
        playerXScoreLabel = new Label("player  x : 0");
        playerXScoreLabel.setStyle("-fx-font-size : 16pt ;-fx-font-weight :bold;");
        playerOScoreLabel = new Label("player  O : 0");
        playerOScoreLabel.setStyle("-fx-font-size : 16pt ;-fx-font-weight :bold;");

        scoreBoard.getChildren().addAll(playerOScoreLabel , playerXScoreLabel);
        root.setBottom(scoreBoard);
        return root;

    }
    private  void buttonClicked(Button button) {
        if(button.getText().equals("")) {
            if (playerXturn) {
                button.setText("X");
            } else {
                button.setText("O");
            }
            playerXturn = !playerXturn;
            checkWinner();
        }
           return;
    }
    private  void  checkWinner() {
//        row
        for (int row = 0; row <3 ; row++) {
            if(buttons[row][0].getText().equals(buttons[row][1].getText())
                    &&buttons[row][1].getText().equals(buttons[row][2].getText())
                    &&!buttons[row][0].getText().isEmpty()
            ){
//                we have a
                System.out.println("winner");
                String winner = buttons[row][0].getText();
                showWinnerDialog(winner);
                updatScore(winner);
                resetBoard();
                return;

            }
        }

//        col

        for (int col = 0; col <3 ; col++) {
            if(buttons[0][col].getText().equals(buttons[1][col].getText())
                    &&buttons[1][col].getText().equals(buttons[2][col].getText())
                    &&!buttons[0][col].getText().isEmpty()
            ){
//                we have a
                System.out.println("winner");
                String winner = buttons[0][col].getText();
                showWinnerDialog(winner);
                updatScore(winner);
                resetBoard();
                return;
            }
        }


//     diagonal
        if(buttons[0][0].getText().equals(buttons[1][1].getText())
                &&buttons[1][1].getText().equals(buttons[2][2].getText())
                &&!buttons[0][0].getText().isEmpty()
        ){
//                we have a
            System.out.println("winner");
            String winner = buttons[0][0].getText();
            showWinnerDialog(winner);
            updatScore(winner);
            resetBoard();
            return;

        }
        if(buttons[2][0].getText().equals(buttons[1][1].getText())
                &&buttons[1][1].getText().equals(buttons[0][2].getText())
                &&!buttons[2][0].getText().isEmpty()
        ){
//                we have a
            System.out.println("winner");
            String winner = buttons[2][0].getText();
            showWinnerDialog(winner);
            updatScore(winner);
            resetBoard();
            return;

        }

//        tie
        boolean tie = true;
        for(Button row[] : buttons) {
            for (Button button : row) {
               if( button.getText().isEmpty()) {
                   tie =false;
                   break;
               }
            }
        }
        if(tie) {
            shoTieDialog();
            resetBoard();
        }
    }
    private  void  showWinnerDialog(String winner) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Congratulations " + winner + " ! you won the Game");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private  void  shoTieDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Winner");
        alert.setContentText("Game Over ! it 's a Tie.");
        alert.setHeaderText("");
        alert.showAndWait();
    }
    private  void  updatScore(String winner) {
        if (winner.equals("X")) {
            playerXscore++;
            playerXScoreLabel.setText("player  X : " + playerXscore);
        } else {
            playerOScore++;
            playerOScoreLabel.setText("player  O : " + playerOScoreLabel);
        }
    }
  private void resetBoard() {
          for(Button row[] : buttons) {
                 for(Button button: row) {
                     button.setText("");
          }
        }
    }
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(ContentPane());
        stage.setTitle("Hello!gopi");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}