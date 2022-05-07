package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class LMU_Car extends Car{
    private static String LMU_CAR_IMAGE_FILE = "LMU.png";

    public LMU_Car(Dimension2D gameBoardSize, int x, int y, int speed, int direction, boolean elon) {
        super(gameBoardSize);
        if(elon){
            LMU_CAR_IMAGE_FILE = "elon.png";
            setSize(new Dimension2D(40, 31));
        } else {
            setSize(new Dimension2D(37,37));

        }
        setPosition(x, y);
        setSpeed(speed);
        setIconLocation(LMU_CAR_IMAGE_FILE);
        setDirection(direction);
    }
}
