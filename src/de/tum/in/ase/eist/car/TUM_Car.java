package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class TUM_Car extends Car{

    private static final String TUM_CAR_IMAGE_FILE = "TUM.png";

    private static final int MIN_SPEED_FAST_CAR = 5;
    private static final int MAX_SPEED_FAST_CAR = 12;

    public TUM_Car(Dimension2D gameBoardSize) {
        super(gameBoardSize);
        setSpeed(6);
        setIconLocation(TUM_CAR_IMAGE_FILE);
        setPosition(0 , 0);
        setSize(new Dimension2D(43,20));
    }

    public void reset(){
        setCrunched(false);
        setSpeed(7);
        setPosition(0, 0);
    }
}
