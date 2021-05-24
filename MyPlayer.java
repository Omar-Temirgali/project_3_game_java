import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class MyPlayer implements Player {
    private Circle ball = new Circle();
    private Map map;
    private Position pos;

    public MyPlayer(Map map) {
        this.map = map;
        pos = map.getStart();
        createBall(map.getBallColor()); //here we create Ball that is hunter
        map.add(ball, pos.getX(), pos.getY());
    }
    public void createBall(String ballColor) {
        switch (ballColor) {
            case "red": ball.setRadius(map.getUnit()/2.0 - 4.0);
                ball.setFill(Color.RED);
                ball.setStrokeWidth(2);
                ball.setStroke(Color.ORANGE); break;
            case "yellow": ball.setRadius(map.getUnit()/2.0 - 4.0);
                ball.setFill(Color.YELLOW);
                ball.setStrokeWidth(2);
                ball.setStroke(Color.YELLOWGREEN); break;
            case "pink": ball.setRadius(map.getUnit()/2.0 - 4.0);
                ball.setFill(Color.PINK);
                ball.setStrokeWidth(2);
                ball.setStroke(Color.HOTPINK); break;
            case "blue": ball.setRadius(map.getUnit()/2.0 - 4.0);
                ball.setFill(Color.LIGHTBLUE);
                ball.setStrokeWidth(2);
                ball.setStroke(Color.DARKBLUE);
        }
    }
    @Override
    public void moveRight() {
        map.getChildren().remove(ball);
        int x = pos.getX() + 1;
        int y = pos.getY();
        map.add(ball, x, y);
        pos.setX(x);
    }
    public void moveLeft() {
        map.getChildren().remove(ball);
        int x = pos.getX() - 1;
        int y = pos.getY();
        map.add(ball, x, y);
        pos.setX(x);
    }
    public void moveUp() {
        map.getChildren().remove(ball);
        int x = pos.getX();
        int y = pos.getY() - 1;
        map.add(ball, x, y);
        pos.setY(y);
    }
    public void moveDown() {
        map.getChildren().remove(ball);
        int x = pos.getX();
        int y = pos.getY() + 1;
        map.add(ball, x, y);
        pos.setY(y);
    }
    public Position getPosition() {
        return pos;
    }
}
