package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.Car;
import de.tum.in.ase.eist.car.LMU_Car;
import de.tum.in.ase.eist.car.TUM_Car;

import java.util.LinkedList;

public class GameBoard1 extends GameBoard {
    /**
     * Creates the game board based on the given size.
     *
     * @param size of the game board
     */
    public GameBoard1(Dimension2D size) {
        super(size);
    }

    @Override
    protected void createCars() {
        getCars().add(new LMU_Car(super.getSize(), 100, 80, 6, 90, false) );
        getCars().add(new LMU_Car(super.getSize(), 60, 260, 5, 0, false) );
        getCars().add(new LMU_Car(super.getSize(), 175, 200, 4, 0, false) );
        getCars().add(new LMU_Car(super.getSize(), 250, 200, 7, 0, false) );
        getCars().add(new LMU_Car(super.getSize(), 175, 150, 4, 90, false) );
    }
}
