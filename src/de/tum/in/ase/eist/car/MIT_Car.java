package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;

import java.time.LocalDateTime;

public class MIT_Car extends Car{

    int drive = 0;
    private LocalDateTime now = LocalDateTime.now();
    private static String MIT_CAR_IMAGE_FILE = "MIT.png";

    public MIT_Car(Dimension2D gameBoardSize, boolean elon) {
        super(gameBoardSize);
        if(elon){
            MIT_CAR_IMAGE_FILE = "elon.png";
            setSize(new Dimension2D(40, 31));
        } else {
            setSize(new Dimension2D(43,20));
        }
        randomStartingPosition();
        setIconLocation(MIT_CAR_IMAGE_FILE);
        setMinSpeed(8);
        setMaxSpeed(10);
        setRandomSpeed();
        setRandomDirection();
    }

    @Override
    public void drive(Dimension2D gameBoardSize){
        if(drive == 20 && !isCrunched()){
            setRandomPosition(gameBoardSize);
            setRandomSpeed();
            setRandomDirection();
            drive = 0;
        } else {
            super.drive(gameBoardSize);
        }
        drive++;
    }

    protected void setRandomStartingPosition(Dimension2D gameBoardSize) {
        double carX = calculateRandomDouble(60, gameBoardSize.getWidth() - getSize().getWidth());
        double carY = calculateRandomDouble(60, gameBoardSize.getHeight() - getSize().getHeight());
        this.setPosition(carX, carY);
    }

}
