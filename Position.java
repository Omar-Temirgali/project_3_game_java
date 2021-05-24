public class Position {
    private int x;
    private int y;

    public Position() {
        x = 0;
        y = 0;
    }
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public boolean equals(Position pos) {
        boolean b1 = false;
        boolean b2 = false;
        if (pos.getX() == getX()) b1 = true;
        if (pos.getY() == getY()) b2 = true;
        if (b1 && b2) return true;
        else if (!b1 && b2) return false;
        else if (b1 && !b2) return false;
        else return false;
    }
}
