package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class Stanford_Car extends Car {

    private int counterInvisibility = 0;
    private int counterSpawn = 0;
    private String STANFORD_CAR_IMAGE_FILE = "Stanford.png";
    private final String TOTAL_WHITE_CAR_FILE = "White.png";

    public Stanford_Car(Dimension2D gameBoardSize, boolean elon) {
        super(gameBoardSize);
        if (elon){
            STANFORD_CAR_IMAGE_FILE = "elon.png";
            setSize(new Dimension2D(40, 31));
        } else{
            setSize(new Dimension2D(28, 40));
        }
        randomStartingPosition();
        setIconLocation(STANFORD_CAR_IMAGE_FILE);

        setSpeed(10);
        setRandomDirection();
    }

    @Override
    public void drive(Dimension2D gameBoardSize) {
        if (counterInvisibility == 30)
            setIconLocation(TOTAL_WHITE_CAR_FILE);
        else if (counterInvisibility == 45) {
            setIconLocation(STANFORD_CAR_IMAGE_FILE);
            counterInvisibility = 0;
        }

        if (counterSpawn == 20) {
            setRandomPosition(gameBoardSize);
            setRandomDirection();
            counterSpawn = 0;
        } else {
            super.drive(gameBoardSize);
        }
        counterSpawn++;
        counterInvisibility++;
    }

}
