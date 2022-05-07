package de.tum.in.ase.eist;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import de.tum.in.ase.eist.audio.AudioPlayerInterface;
import de.tum.in.ase.eist.car.*;
import de.tum.in.ase.eist.collision.Collision;
import de.tum.in.ase.eist.collision.DefaultCollision;
import de.tum.in.ase.eist.collision.PinguCollision;

/**
 * Creates all car objects, detects collisions, updates car positions, notifies
 * player about victory or defeat.
 */
public class GameBoard {

    protected LinkedList<Car> cars_apart_from_player = new LinkedList<Car>();
    private static final int NUMBER_OF_SLOW_CARS = 5;
    private static final int NUMBER_OF_TESLA_CARS = 2;

    /**
     * List of all active cars, does not contain player car.
     */
    private final List<Car> cars = new ArrayList<>();

    /**
     * The player object with player's car.
     */
    private final Player player;

    /**
     * AudioPlayer responsible for handling music and game sounds.
     */
    private AudioPlayerInterface audioPlayer;

    /**
     * Dimension of the GameBoard.
     */
    private final Dimension2D size;

    /**
     * true if game is running, false if game is stopped.
     */
    private boolean running;

    /**
     * List of all loser cars (needed for testing, DO NOT DELETE THIS)
     */
    private final List<Car> loserCars = new ArrayList<>();

    /**
     * The outcome of this game from the players perspective. The game's outcome is open at the beginning.
     */
    private GameOutcome gameOutcome = GameOutcome.OPEN;

    /**
     * Creates the game board based on the given size.
     *
     * @param size of the game board
     */
    public GameBoard(Dimension2D size) {
        this.size = size;
        TUM_Car playerCar = new TUM_Car(size);
        this.player = new Player(playerCar);
        this.player.setup();
        cars.add(new Pengu_Car(size, true));
        createCars();
    }

    /**
     * Creates as many cars as specified by {@link #NUMBER_OF_SLOW_CARS} and adds
     * them to the cars list.
     */
    protected void createCars() {
        for (int i = 0; i < NUMBER_OF_SLOW_CARS; i++) {
            this.cars.add(new SlowCar(this.size));
        }
        for (int i = 0; i < NUMBER_OF_TESLA_CARS; i++) {
            this.cars.add(new FastCar(this.size));
        }
    }

    public Dimension2D getSize() {
        return size;
    }

    /**
     * Returns if game is currently running.
     *
     * @return true if the game is currently running, false otherwise
     */
    public boolean isRunning() {
        return this.running;
    }

    /**
     * Sets whether the game should be currently running.
     * <p>
     * Also used for testing on Artemis.
     *
     * @param running true if the game should be running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }

    public GameOutcome getGameOutcome() {
        return gameOutcome;
    }

    /**
     * Returns all cars on the game board except the player's car as a list.
     *
     * @return the list of all non-player cars
     */
    public List<Car> getCars() {
        return this.cars;
    }

    public Car getPlayerCar() {
        return this.player.getCar();
    }

    public AudioPlayerInterface getAudioPlayer() {
        return this.audioPlayer;
    }

    public void setAudioPlayer(AudioPlayerInterface audioPlayer) {
        this.audioPlayer = audioPlayer;
    }

    /**
     * Updates the position of each car.
     */
    public void update() {
        moveCars();
    }

    /**
     * Starts the game. Cars start to move and background music starts to play.
     */
    public void startGame() {
        playMusic();
        this.running = true;
    }

    /**
     * Stops the game. Cars stop moving and background music stops playing.
     */
    public void stopGame() {
        stopMusic();
        this.running = false;
    }

    /**
     * Starts the background music.
     */
    public void playMusic() {
        this.audioPlayer.playBackgroundMusic();
    }

    /**
     * Stops the background music.
     */
    public void stopMusic() {
        this.audioPlayer.stopBackgroundMusic();
    }

    /**
     * @return list of loser cars
     */
    public List<Car> getLoserCars() {
        return this.loserCars;
    }

    /**
     * Moves all cars on this game board one step further.
     */
    public void moveCars() {
        // update the positions of the player car and the autonomous cars
        for (Car car : this.cars) {
            car.drive(size);
        }
        this.player.getCar().drive(size);

        // iterate through all cars (except player car) and check if it is crunched
        for (Car car : cars) {
            if (car.isCrunched()) {
                // because there is no need to check for a collision
                continue;
            }

            /*Collision c = new PinguCollision(getPlayerCar(), car);

            if (c.isCrash()) {
                Car winner = c.evaluate();
                if (winner instanceof Pengu_Car) {
                    gameOutcome = GameOutcome.NEXT_LEVEL;
                } else {
                    loserCars.add(getPlayerCar());
                    getPlayerCar().crunch();
                    gameOutcome = GameOutcome.LOST;
                }
            }
            */
            Collision collision = new DefaultCollision(player.getCar(), car);

            if (collision.isCrash()) {
                Car winner = collision.evaluate();
                Car loser = collision.evaluateLoser();
                printWinner(winner);
                loserCars.add(loser);
                loser.crunch();

                this.audioPlayer.playCrashSound();

                if (isWinner())
                    gameOutcome = GameOutcome.WON;
                else if (player.getCar().isCrunched())
                    gameOutcome = GameOutcome.LOST;
				else
					gameOutcome = GameOutcome.OPEN;
            }

            /*
             * Hint: you should set the attribute gameOutcome accordingly. Use 'isWinner()'
             * below for your implementation
             */


        }
    }

    /**
     * If all other cars are crunched, the player wins.
     *
     * @return true if the game is over and the player won, false otherwise
     */
    private boolean isWinner() {
        for (Car car : getCars()) {
            if (!car.isCrunched()) {
                return false;
            }
        }
        return true;
    }

    private void printWinner(Car winner) {
        if (winner == this.player.getCar()) {
            System.out.println("The player's car won the collision!");
        } else if (winner != null) {
            System.out.println(winner.getClass().getSimpleName() + " won the collision!");
        } else {
            System.err.println("Winner car was null!");
        }
    }

    public void reset() {
    }
}
