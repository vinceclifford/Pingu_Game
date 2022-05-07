package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class Pengu_Car extends Car {

    private static final String PENGU_CAR_IMAGE_FILE = "Pengu.png";

    public Pengu_Car(Dimension2D gameBoardSize, boolean movingPengu) {
        super(gameBoardSize);
        if (!movingPengu) {
            setPosition(400, 260);
            setSpeed(0);
        }
        else{
            setRandomPosition(gameBoardSize);
            setPosition(400, 260);
            setSpeed(5);
        }
        setIconLocation(PENGU_CAR_IMAGE_FILE);
        setSize(new Dimension2D(28, 39));
    }

}
