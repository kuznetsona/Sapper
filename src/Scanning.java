class Scanning {
    private Field field;
    private boolean gameOver;
    private boolean win;
    private int width;
    private int height;
    private int minesCount;

    Scanning(int width, int height, int minesCount) {
        setGameParams(width, height, minesCount);
        field = new Field(width, height, minesCount);
    }

    Field getField() {
        return field;
    }

    boolean gameOver() {
        return gameOver;
    }

    boolean win() {
        return win;
    }

    void clickHexagon(int x, int y) {
        if (!field.clickHexagon(x, y)) gameOver = true;
        if (field.getClosed() == field.getMines()) win = true;
        if (gameOver || win) return;
        Renovation.update();
    }

    void changeFlag(int x, int y) {
        field.changeFlag(x, y);
        Renovation.update();
    }

    void restart() {
        field = new Field(width, height, minesCount);
        gameOver = false;
        win = false;
        Renovation.init(width, height);
        Renovation.update();
    }

    void setGameParams(int width, int height, int minesCount) {
        this.width = width;
        this.height = height;
        this.minesCount = minesCount;
    }

}