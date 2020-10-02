import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
        }

    @Override
    public void start(Stage primaryStage) {
        Group group = new Group();
        //контейнер. Цель Group это сгруппировать
        Scene scene = new Scene(group, 455, 524);
        primaryStage.setScene(scene);

        primaryStage.setTitle("Minesweeper");
        scene.setFill(Color.LIGHTGRAY);

        VBox vBox = new VBox();
        Pane gameArea = new Pane();
        MenuBar menuBar = new MenuBar();
        Menu game = new Menu("Меню");
        MenuItem menuItem = new MenuItem("Задать размер поля");
        MenuItem restart = new MenuItem("Restart");
        menuBar.getMenus().add(game);
        menuBar.setMinSize(445, 30);
        game.getItems().add(menuItem);
        game.getItems().add(restart);
        vBox.getChildren().addAll(menuBar, gameArea);
        group.getChildren().add(vBox);
        Scanning scanning = new Scanning(10, 40, 60);
        restart.setOnAction(event -> {
            scanning.restart();
        });
        game.setOnAction(event -> {
            List<Integer> params = getFieldSize();
            if (params == null) return;
            scanning.setGameParams(params.get(0), params.get(1), params.get(2));
            scanning.restart();
            primaryStage.setWidth(10 + params.get(0) * 45);
            primaryStage.setHeight(72 + 13 * params.get(1));
            menuBar.setMinWidth(70 + params.get(0) * 45);
        });
        Controller.play = scanning;
        Controller.zone = gameArea;
        Controller.init(10, 40);
        primaryStage.setResizable(true);
        primaryStage.show();
    }

    private List<Integer> getFieldSize() {
        Dialog<List<Integer>> dialog = new Dialog<>();
        dialog.setTitle("Задать размер поля");
        dialog.setResizable(false);

        Label width = new Label("Ширина:");
        Label height = new Label("Высота:");
        Label mines = new Label("Количество мин:");

        TextField widthTxt = new TextField();
        TextField heightTxt = new TextField();
        TextField minesTxt = new TextField();

        GridPane grid = new GridPane();
        grid.add(width, 1, 1);
        grid.add(height, 1, 2);
        grid.add(mines, 1, 3);
        grid.add(widthTxt, 2, 1);
        grid.add(heightTxt, 2, 2);
        grid.add(minesTxt, 2, 3);

        dialog.getDialogPane().setContent(grid);

        ButtonType buttonOk = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().add(buttonOk);

        dialog.setResultConverter(param -> {
            if (param == buttonOk) {
                List<Integer> result = new ArrayList<>();
                if (widthTxt.getText() == null || heightTxt.getText() == null || minesTxt.getText() == null ||
                        Integer.parseInt(minesTxt.getText()) >=
                                Integer.parseInt(widthTxt.getText()) * Integer.parseInt(heightTxt.getText()))
                    return null;
                if (Integer.parseInt(widthTxt.getText()) < 8 || Integer.parseInt(heightTxt.getText()) < 8)
                    return null;

                result.add(Integer.valueOf(widthTxt.getText()));
                result.add(Integer.valueOf(heightTxt.getText()));
                result.add(Integer.valueOf(minesTxt.getText()));
                return result;
            }
            return null;
        });
        Optional<List<Integer>> result = dialog.showAndWait();
        return result.orElse(null);
    }
}