package de.tum.in.ase.eist;

import de.tum.in.ase.eist.gameview.GameBoardUI;
import de.tum.in.ase.eist.gameview.GameToolBar;
import de.tum.in.ase.eist.gameview.LevelButton;
import de.tum.in.ase.eist.gameview.LevelText;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Starts the Bumpers Application, loads the GameToolBar and GameBoardUI. This
 * class is the root of the JavaFX Application.
 *
 * @see Application
 */
public class BumpersApplication extends Application {

    private static final int GRID_LAYOUT_PADDING = 5;
    private static final int GRID_LAYOUT_PREF_HEIGHT = 350;
    private static final int GRID_LAYOUT_PREF_WIDTH = 505;

    /**
     * Starts the Bumpers Window by setting up a new tool bar, a new user interface
     * and adding them to the stage.
     *
     * @param primaryStage the primary stage for this application, onto which the
     *                     application scene can be set.
     */
    @Override
    public void start(Stage primaryStage) {
        // the tool bar object with start and stop buttons
        GameToolBar toolBar = new GameToolBar();
        GameBoardUI gameBoardUI = new GameBoardUI(toolBar);
        toolBar.initializeActions(gameBoardUI);

        Button level0Button = LevelButton.createLevelButton("Yes");
        Label level0Text = LevelText.createLevelText(
                "Even though I'm not a world-renowned philosopher I do believe there is truth to the following quote:\"You desire what you don't have\". Over the first semester, every TUM Computer Science student has formed a deep connection with penguins. We love and care for them. \n" +
                        "Unfortunately, other universities have gotten envious, including prestigious colleges such as Stanford, MIT, and Harvard! Who can blame them? After all, we win Nobel prizes with the help of the penguins. They want to collaborate with the penguins themselves! They managed to kidnap our beloved penguins! With tears in their eyes, all of the TUM freshmen come together and try to help. Can you help?");

        VBox layout0 = new VBox(40);
        layout0.setAlignment(Pos.TOP_CENTER);

        Button level1Button = LevelButton.createLevelButton("Next");
        Label level1Text = LevelText.createLevelText("Instructions:\nThe game consists of 7 + 1 (Bonus) levels. In each level you need to reach the penguin on the screen without being caught by other colleges. Otherwise you lose. You move your player (TUM) with the mouse. Click on the canvas and you're going to steer in that direction \n" +
                "In my first semester I realized how innovative TUM is. Therefore, it's no surprise, that they brought a snowball-machine to the fight! In each level they can throw a total of 3 snowballs. You can throw the snowballs with 'x' or 'X'. Be careful! If you hit the penguin you lose. If you hit another college that college will be frozen for that level.");
        VBox layout1 = new VBox(40);
        layout1.setAlignment(Pos.TOP_CENTER);

        Button level2Button = LevelButton.createLevelButton("Start!");
        Label level2Text = LevelText.createLevelText("Level 1: Let's start very easy. The University that we TUM students despise the most is only guarding the penguin. This should be fairly easy.\n\nHave fun! Any feedback is welcome!\n-ge28lop");
        VBox layout2 = new VBox(40);
        layout2.setAlignment(Pos.TOP_CENTER);

        Pane gridLayout = createLayout(gameBoardUI, toolBar);


        layout0.getChildren().addAll(level0Text, level0Button);
        layout1.getChildren().addAll(level1Text, level1Button);
        layout2.getChildren().addAll(level2Text, level2Button);
        Scene scene1 = new Scene(layout0, GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
        Scene scene2 = new Scene(layout1, GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
        Scene scene3 = new Scene(layout2, GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
        Scene scene4 = new Scene(gridLayout);

        level0Button.setOnAction(e -> {
            primaryStage.setScene(scene2);
        });
        level1Button.setOnAction(e -> {
            primaryStage.setScene(scene3);
        });
        level2Button.setOnAction(e -> {
            primaryStage.setScene(scene4);
        });

        primaryStage.setTitle("Bumpers");
        primaryStage.setScene(scene1);
        primaryStage.setOnCloseRequest(closeEvent -> gameBoardUI.stopGame());


        // scene and stages
        primaryStage.show();
    }

    /**
     * Creates a new {@link Pane} that arranges the game's UI elements.
     */
    private static Pane createLayout(GameBoardUI gameBoardUI, GameToolBar toolBar) {
        // GridPanes are divided into columns and rows, like a table
        GridPane gridLayout = new GridPane();
        gridLayout.setPrefSize(GRID_LAYOUT_PREF_WIDTH, GRID_LAYOUT_PREF_HEIGHT);
        gridLayout.setVgap(GRID_LAYOUT_PADDING);
        gridLayout.setPadding(new Insets(GRID_LAYOUT_PADDING));

        // add all components to the gridLayout
        // second parameter is column index, second parameter is row index of grid
        gridLayout.add(gameBoardUI, 0, 1);
        gridLayout.add(toolBar, 0, 0);
        return gridLayout;
    }

    /**
     * The whole game will be executed through the launch() method.
     * <p>
     * Use {@link Bumpers#main(String[])} to run the Java application.
     */
    public static void startApp(String[] args) {
        launch(args);
    }
}
