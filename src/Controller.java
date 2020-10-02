import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

class Controller {
    private static int width;
    private static int height;
    static Scanning play;
    static Pane zone;
    private static Polygon[][] square;

    static void init(int width, int height) {
        Controller.width = width;
        Controller.height = height;
        zone.getChildren().clear();
        square = new Polygon[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                final int column = j;
                final int row = i;
                int newJ;
                if (i % 2 != 0) { newJ = 37 + 45 * j;
                } else { newJ = 15 + 45 * j; }
                square[i][j] = draw(newJ, i);
                square[i][j].setStroke(Color.DARKVIOLET);
                square[i][j].setFill(Color.VIOLET);
                zone.getChildren().add(square[i][j]);
                square[i][j].setOnMouseClicked(event -> {
                    switch (event.getButton()) {
                        case PRIMARY:
                            play.clickHexagon(column, row);
                            break;
                        case SECONDARY:
                            play.changeFlag(column, row);
                            break;
                    }
                });
            }
        }
    }

    static void update() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Hexagon hexagon = play.getField().getZone()[i][j];
                if (hexagon.flag()) { square[i][j].setFill(Color.YELLOW);
                } else { square[i][j].setFill(Color.VIOLET);
                }
                if (hexagon.getVisibility()) { square[i][j].setFill(Color.LIGHTGRAY);
                    if (hexagon.getMine()) { square[i][j].setFill(Color.DEEPPINK);
                    } else if (hexagon.getMinesCount() != 0) {
                        ImagePattern number = new ImagePattern(new Image(
                                "Image/number_" + hexagon.getMinesCount() + ".png"));
                        square[i][j].setFill(number);
                    }
                }
            }
        }
        if (play.gameOver()) Controller.output("Game over(((");
        if (play.win()) Controller.output("Winning!");
    }
    private static Polygon draw(int x, int y) {
        int r = 15;
        int a = r / 2;
        double b = r * 0.80;
        y *= 12;
        y += 12;
        Polygon polygon = new Polygon(
                x - r, y,
                x - a, y - b,
                x + a, y - b,
                x + r, y,
                x + a, y + b,
                x - a, y + b);
        return polygon;
    }

    private static void output(String answer){
        Text text = new Text(zone.getWidth() * 0.23, zone.getHeight() * 0.5, answer);
        text.setFont(new Font(40));
        text.setTextAlignment(TextAlignment.LEFT);
        zone.getChildren().add(text);
    }
}
