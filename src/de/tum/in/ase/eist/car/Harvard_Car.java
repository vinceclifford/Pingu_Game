package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class Harvard_Car extends Car {
    private int drive = 0;
    private String HARVARD_CAR_IMAGE_FILE = "Harvard.png";

    public Harvard_Car(Dimension2D gameBoardSize, boolean elon) {
        super(gameBoardSize);
        if (elon) {
            HARVARD_CAR_IMAGE_FILE = "elon.png";
            setSize(new Dimension2D(40, 31));
        } else {
            setSize(new Dimension2D(32, 38));
        }
        randomStartingPosition();
        setIconLocation(HARVARD_CAR_IMAGE_FILE);
        setSpeed(9);
        setRandomDirection();
    }

    @Override
    public void drive(Dimension2D gameBoardSize) {

        if (drive == 40) {
            drive = 0;
        }
        super.drive(gameBoardSize);
        drive++;
    }

    public boolean isInvisible() {
        return drive >= 30 && drive <= 39;
    }
}
