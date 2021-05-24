import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class Map extends GridPane {
    private int UNIT = 0;
    private int size;
    private int[][] map;
    private Position start;
    private Rectangle[][] cells = new Rectangle[0][0];
    private String theme = "";
    private String ballColor = "";

    public Map(String file) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a size of map (preferably 40 - 80): ");
        do {
            UNIT = input.nextInt();
            if (UNIT < 20) System.out.print("Please, write valid number (bigger than 20): ");
        } while (UNIT < 20);
        System.out.print("Choose theme (light/dark): ");
        while (!(theme.equals("light") || theme.equals("dark"))) {
            theme = input.next().toLowerCase();
            if (!(theme.equals("light") || theme.equals("dark"))) System.out.print("Invalid choice. Choose theme (light/dark): ");
        }
        System.out.print("Choose ball color (red/yellow/blue/pink): ");
        while (!(ballColor.equals("red") || ballColor.equals("yellow") || ballColor.equals("blue") || ballColor.equals("pink"))) {
            ballColor = input.next().toLowerCase();
            if (!(ballColor.equals("red") || ballColor.equals("yellow") || ballColor.equals("blue") || ballColor.equals("pink")))
                System.out.print("Invalid choice. Choose given color: ");
        }
        mapMatrix(file);
        if (theme.equals("light")) mapGrid();
        else if (theme.equals("dark")) mapGridDark();

        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                if (map[x][y] == 0) add(cells[x][y], y, x);
                else if (map[x][y] == 1) add(cells[x][y], y, x);
                else if (map[x][y] == 2) add(cells[x][y], y, x);
            }
        }
        start = getStartPosition();
    }
    public Position getStart() {
        return start;
    }
    public int getUnit() {
        return UNIT;
    }
    public int getSize() {
        return size;
    }
    public void mapMatrix(String file) {
        try {
            Scanner scanner = new Scanner(new File(file));
            size = scanner.nextInt();
            scanner.nextLine();
            map = new int[getSize()][getSize()];
            LinkedList<String> bitmap = new LinkedList<>();
            while (scanner.hasNext()) {
                String numbers = scanner.next();
                bitmap.add(numbers);
            }
            for (int x = 0; x < size; x++)
                for (int y = 0; y < size; y++) map[x][y] = Integer.parseInt(bitmap.removeFirst());
        } catch (FileNotFoundException e) {
            System.out.println("File doesn't exist!");
        }
    }
    public void mapGridDark() { //dark theme
        cells = new Rectangle[getSize()][getSize()];
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                cells[x][y] = new Rectangle();
                cells[x][y].setHeight(getUnit());
                cells[x][y].setWidth(getUnit());
                switch (map[x][y]) {
                    case 0:
                    case 2:
                        cells[x][y].setFill(Color.DARKGREY);
                            cells[x][y].setStrokeType(StrokeType.INSIDE);
                            cells[x][y].setStroke(Color.BLACK);
                            cells[x][y].setStrokeWidth(0.3); break;
                    case 1: cells[x][y].setFill(Color.BLACK); break;
                }
            }
        }
    }
    public void mapGrid() { //light theme
        cells = new Rectangle[getSize()][getSize()];
        for (int x = 0; x < getSize(); x++) {
            for (int y = 0; y < getSize(); y++) {
                cells[x][y] = new Rectangle();
                cells[x][y].setHeight(getUnit());
                cells[x][y].setWidth(getUnit());
                switch (map[x][y]) {
                    case 0:
                    case 2: cells[x][y].setFill(Color.WHITE);
                            cells[x][y].setStrokeType(StrokeType.INSIDE);
                            cells[x][y].setStroke(Color.BLACK);
                            cells[x][y].setStrokeWidth(0.3); break;
                    case 1: cells[x][y].setFill(Color.BLACK);
                }
            }
        }
    }
    public int getValueAt(int x, int y) {
        return map[x][y];
    }
    public Position getStartPosition() {
        int x = 0, y;
        Position startFromMap = new Position();
        while (x < getSize()) {
            y = 0;
            while (y < getSize()) {
                if (map[x][y] == 2) {
                    startFromMap.setX(y);
                    startFromMap.setY(x);
                }
                y++;
            }
            x++;
        }
        return startFromMap;
    }
    public String getBallColor() {
        return ballColor;
    }
}
