package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.Car;
import de.tum.in.ase.eist.car.KIT_Car;
import de.tum.in.ase.eist.car.LMU_Car;

public class GameBoard2 extends GameBoard {

    public GameBoard2(Dimension2D size) {
        super(size);
    }

    @Override
    protected void createCars() {
        for(int i = 0; i < 5; i++)
            getCars().add(new KIT_Car(super.getSize(), false));
    }

}
