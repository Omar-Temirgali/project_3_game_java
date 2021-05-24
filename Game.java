import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class Game extends Application {
    private static Map map;
    private static Player player;
    private static Food foods;
    public static void main(String[] args) {
        try {
            if (!args[0].contains(".txt")) throw new IOException();
            Scanner scanner = new Scanner(new File(args[0]));
            map = new Map(args[0]);
        } catch (FileNotFoundException ex) {
            System.out.println("Such file does not exist!");
            System.exit(0);
        } catch (IOException e) {
            System.out.println("Incorrect format!");
            System.exit(1);
        }
        player = new MyPlayer(map);
        Application.launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        foods = new Food(map, player);
        map.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.RIGHT) {
                int y = player.getPosition().getY();
                int x = player.getPosition().getX();
                if (x != map.getSize() - 1) {
                    if (map.getValueAt(y, x + 1) != 1) player.moveRight();
                } else System.out.println("Invalid position!");
            } else if (event.getCode() == KeyCode.UP) {
                int y = player.getPosition().getY();
                int x = player.getPosition().getX();
                if (y != 0) {
                    if (map.getValueAt(y - 1, x) != 1) player.moveUp();
                } else System.out.println("Invalid position!");
            } else if (event.getCode() == KeyCode.DOWN) {
                int y = player.getPosition().getY();
                int x = player.getPosition().getX();
                if (y != map.getSize() - 1) {
                    if (map.getValueAt(y + 1, x) != 1) player.moveDown();
                } else System.out.println("Invalid position!");
            } else if (event.getCode() == KeyCode.LEFT){
                int y = player.getPosition().getY();
                int x = player.getPosition().getX();
                if (x != 0) {
                    if (map.getValueAt(y, x - 1) != 1) player.moveLeft();
                } else System.out.println("Invalid position!");
            } else System.out.println("Invalid key! Please use arrow keys!");
        });
        primaryStage.setScene(new Scene(map));
        primaryStage.setTitle("Game");
        primaryStage.show();
        map.requestFocus();
    }
}
