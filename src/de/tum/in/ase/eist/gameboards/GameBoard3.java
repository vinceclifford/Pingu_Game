package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.KIT_Car;
import de.tum.in.ase.eist.car.LMU_Car;
import de.tum.in.ase.eist.car.Stanford_Car;

public class GameBoard3 extends GameBoard {

    public GameBoard3(Dimension2D size) {
        super(size);
    }

    @Override
    protected void createCars() {
        for(int i = 0; i < 4; i++)
            getCars().add(new KIT_Car(super.getSize(), false));

        getCars().add(new LMU_Car(super.getSize(), 175, 200, 4, 0, false));
        getCars().add(new LMU_Car(super.getSize(), 250, 200, 7, 0, false));
        getCars().add(new LMU_Car(super.getSize(), 175, 150, 4, 90, false));

    }
}
