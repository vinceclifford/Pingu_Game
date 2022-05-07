package de.tum.in.ase.eist.gameview;

import java.net.URL;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.GameOutcome;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.audio.AudioPlayer;
import de.tum.in.ase.eist.car.Car;
import de.tum.in.ase.eist.car.Harvard_Car;
import de.tum.in.ase.eist.car.Stanford_Car;
import de.tum.in.ase.eist.gameboards.*;
import de.tum.in.ase.eist.usercontrol.KeyboardSteering;
import de.tum.in.ase.eist.usercontrol.MouseSteering;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCharacterCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;

/**
 * This class implements the user interface for steering the player car. The
 * user interface is implemented as a Thread that is started by clicking the
 * start button on the tool bar and stops by the stop button.
 */
public class GameBoardUI extends Canvas {

    private static final Color BACKGROUND_COLOR = Color.WHITE;
    private int gameAt = 0;
    /**
     * The update period of the game in ms, this gives us 25 fps.
     */
    private static final int UPDATE_PERIOD = 1000 / 25;
    private static final int DEFAULT_WIDTH = 500;
    private static final int DEFAULT_HEIGHT = 300;
    private static final Dimension2D DEFAULT_SIZE = new Dimension2D(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private boolean first = true;

    public static Dimension2D getPreferredSize() {
        return DEFAULT_SIZE;
    }

    /**
     * Timer responsible for updating the game every frame that runs in a separate
     * thread.
     */
    private Timer gameTimer;

    private GameBoard gameBoard;

    private final GameToolBar gameToolBar;

    private MouseSteering mouseSteering;

    private KeyboardSteering keyboardSteering;

    private HashMap<String, Image> imageCache;

    public GameBoardUI(GameToolBar gameToolBar) {
        this.gameToolBar = gameToolBar;
        setup();
    }

    public GameBoard getGameBoard() {
        return gameBoard;
    }

    public MouseSteering getMouseSteering() {
        return mouseSteering;
    }

    /**
     * Removes all existing cars from the game board and re-adds them. Player car is
     * reset to default starting position. Renders graphics.
     */
    public void setup() {
        setupGameBoard();
        setupImageCache();
        this.gameToolBar.updateToolBarStatus(false);
        paint();
    }

    public GameBoard returnCurrentGame(int index) {
        switch (index) {
            case 0 -> {
                return new GameBoard1(DEFAULT_SIZE);
            }
            case 1 -> {
                return new GameBoard2(DEFAULT_SIZE);
            }
            case 2 -> {
                return new GameBoard3(DEFAULT_SIZE);
            }
            case 3 -> {
                return new GameBoard4(DEFAULT_SIZE);
            }
            case 4 -> {
                return new GameBoard5(DEFAULT_SIZE);
            }
            case 5 -> {
                return new GameBoard6(DEFAULT_SIZE);
            }
            case 6 -> {
                return new GameBoard7(DEFAULT_SIZE);
            }
            case 7 -> {
                return new GameBoard8(DEFAULT_SIZE);
            }
        }
        return null;
    }

    private void setupGameBoard() {
        Dimension2D size = getPreferredSize();
        this.gameBoard = returnCurrentGame(gameAt);
        this.gameBoard.setAudioPlayer(new AudioPlayer());
        widthProperty().set(size.getWidth());
        heightProperty().set(size.getHeight());
        this.mouseSteering = new MouseSteering(this.gameBoard.getPlayerCar());
        this.keyboardSteering = new KeyboardSteering(this.gameBoard);

        addEventFilter(MouseEvent.MOUSE_PRESSED, (e) -> requestFocus());

        if (first) {
            this.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent clickEvent) -> {
                this.mouseSteering.mousePressed(clickEvent);
            });

            this.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
                if (e.getText().equals("x") || e.getText().equals("X")) {
                    keyboardSteering.xPressed();
                }
            });
            first = false;
        }

    }


    private void setupImageCache() {
        this.imageCache = new HashMap<>();
        for (Car car : this.gameBoard.getCars()) {
            // Loads the image into the cache
            getImage(car.getIconLocation());
        }
        for (Car car : this.gameBoard.getBullets())
            getImage(car.getIconLocation());

        String playerImageLocation = this.gameBoard.getPlayerCar().getIconLocation();
        getImage(playerImageLocation);
    }

    /**
     * Returns the car's image. If no image is present in the cache, a new image is created.
     *
     * @param carImageFilePath an image file path that needs to be available in the
     *                         resources folder of the project
     */
    private Image getImage(String carImageFilePath) {
        return this.imageCache.computeIfAbsent(carImageFilePath, this::createImage);
    }

    /**
     * Loads the car's image.
     *
     * @param carImageFilePath an image file path that needs to be available in the
     *                         resources folder of the project
     */
    private Image createImage(String carImageFilePath) {
        URL carImageUrl = getClass().getClassLoader().getResource(carImageFilePath);
        if (carImageUrl == null) {
            throw new IllegalArgumentException(
                    "Please ensure that your resources folder contains the appropriate files for this exercise.");
        }
        return new Image(carImageUrl.toExternalForm());
    }

    /**
     * Starts the GameBoardUI Thread, if it wasn't running. Starts the game board,
     * which causes the cars to change their positions (i.e. move). Renders graphics
     * and updates tool bar status.
     */
    public void startGame() {
        if (!this.gameBoard.isRunning()) {
            this.gameBoard.startGame();
            this.gameToolBar.updateToolBarStatus(true);
            startTimer();
            paint();
        }
    }

    private void startTimer() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                updateGame();
            }
        };
        if (this.gameTimer != null) {
            this.gameTimer.cancel();
        }
        this.gameTimer = new Timer();
        this.gameTimer.scheduleAtFixedRate(timerTask, UPDATE_PERIOD, UPDATE_PERIOD);
    }

    private void updateGame() {
        if (gameBoard.isRunning()) {
            // updates car positions and re-renders graphics
            this.gameBoard.update();
            // when this.gameBoard.getOutcome() is OPEN, do nothing
            if (this.gameBoard.getGameOutcome() == GameOutcome.LOST) {
                getGameBoard().getAudioPlayer().playCrashSound();
                showAsyncAlert("You lost. Try this level again");
                this.stopGame();
                setupGameBoard();
            } else if (this.gameBoard.getGameOutcome() == GameOutcome.NEXT_LEVEL && gameAt != 7) {
                this.stopGame();
                getGameBoard().getAudioPlayer().playLevelUp();
                showAsyncAlert(determineMessage());
                gameAt++;
                setupGameBoard();
            } else if (this.gameBoard.getGameOutcome() == GameOutcome.NEXT_LEVEL && gameAt == 7) {
                showAsyncAlert("Congratulations! You won!!");
                this.stopGame();
            }
            paint();
        }
    }

    private String determineMessage() {
        switch (gameAt) {
            case 0 -> {
                return "Congratulations! You passed level 1. It was not that hard, was it?\n\n" +
                        "Through constant innovation KIT found a technique on how to walk through walls.\n " +
                        "Hopefully, you're still able to save the penguins!";
            }
            case 1 -> {
                return "Congratulations! You passed level 2. \n" +
                        "Let's see if you can still rescue the penguins even if KIT and LMU team up...";
            }
            case 2 -> {
                return " Congratulations! You passed level 3.\n\n" +
                        "Harvard's number of former and current professors that won a Nobel prize totals 50. \n" +
                        "So assuming that they would not innovate with the help of our beloved penguins is \n" +
                        "wrong. Unfortunately for us, they invented, with the help of the penguins, an elixir \n" +
                        "that makes them invisible every couple of seconds. Can you still free the penguins?";
            }
            case 3 -> {
                return "Congratulations on passing level 4!\n" +
                        "MIT is located in Harvard's backyard. They're not even seperated by a mile.\n" +
                        "It's no surprise that the world's best Computer Science university heard about our penguins! \n" +
                        "Unlike Harvard they conducted research in Quantum teleportation. Every couple of seconds they\n" +
                        "teleport to a new location with different speed and direction. Please bring the Penguins back to \n" +
                        "TUM";
            }
            case 4 -> {
                return "Congratulations! You passed level 5!\n" +
                        "The west coast of the United States know of our penguins as well! Stanford combined the research \n" +
                        "of Havard and MIT and created an elixir with which makes one teleport and vanishe at the same time! \n" +
                        "Can you free the penguins?";
            }

            case 5 -> {
                return "Congratulations on passing level 6!\n" +
                        "Now you only need to fight off the end boss!";
            }
            case 6 -> {
                return "Congratulation on completing the Bumper game of ge28lop! I hope you had fun!\n" +
                        "If you still want to waste time / procrastinate you can continue playing the bonus level. The only caveat\n" +
                        "is that you can't predict if a player  can vanish, teleport, move through walls etc. You'll see\n" +
                        "what I mean in a second";
            }
        }
        return null;
    }

    /**
     * Stops the game board and set the tool bar to default values.
     */
    public void stopGame() {
        if (this.gameBoard.isRunning()) {
            this.gameBoard.stopGame();
            this.gameToolBar.updateToolBarStatus(false);
            this.gameTimer.cancel();
        }
    }

    /**
     * Render the graphics of the whole game by iterating through the cars of the
     * game board at render each of them individually.
     */
    private void paint() {

        Image img = new Image("Background.png");
        getGraphicsContext2D().setFill(new ImagePattern(img));
        getGraphicsContext2D().fillRect(0, 0, getWidth(), getHeight());

        for (Car car : this.gameBoard.getCars()) {
            if (car instanceof Harvard_Car h) {
                if (!h.isInvisible()) {
                    paintCar(car);
                }
            } else if (car instanceof Stanford_Car s) {
                if (!s.isInvisible()) {
                    paintCar(car);
                }
            } else {
                paintCar(car);
            }
        }
        for (Car car : this.gameBoard.getBullets())
            paintCar(car);
        // render player car
        paintCar(this.gameBoard.getPlayerCar());
    }

    /**
     * Show image of a car at the current position of the car.
     *
     * @param car to be drawn
     */
    private void paintCar(Car car) {
        Point2D carPosition = car.getPosition();

        getGraphicsContext2D().drawImage(getImage(car.getIconLocation()), carPosition.getX(),
                carPosition.getY(), car.getSize().getWidth(), car.getSize().getHeight());
    }

    /**
     * Method used to display alerts in moveCars().
     *
     * @param message you want to display as a String
     */
    private void showAsyncAlert(String message) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(message);
            alert.showAndWait();
            this.setup();
        });
    }
}
