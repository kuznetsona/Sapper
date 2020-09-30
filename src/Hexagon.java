class Hexagon {

    private int x;
    private int y;
    private boolean visible;
    private boolean mine;
    private byte nearestMines;
    private boolean flag;

    Hexagon(int x, int y) {
        this.x = x;
        this.y = y;
        flag = false;
        visible = false;
        nearestMines = 0;
    }

    void changeFlag() { flag = !flag; }
    boolean flag() { return flag; }
    //
    int getX() { return x; }
    int getY() { return y; }

    void setVisible() { visible = true; }
    boolean getVisibility() { return visible; }

    boolean getMine() { return mine; }
    void setMine() { mine = true; }

    byte getMinesCount() { return nearestMines; }
    void setMinesCount(byte mines) { nearestMines = mines; }
}