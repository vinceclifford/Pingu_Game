package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.*;

public class GameBoard8 extends GameBoard {

    public GameBoard8(Dimension2D size) {
        super(size);
    }

    @Override
    protected void createCars() {
        for (int i = 0; i < 2; i++) {
            getCars().add(new KIT_Car(super.getSize(),true));
            getCars().add(new Harvard_Car(super.getSize(), true));
            getCars().add(new MIT_Car(super.getSize(), true));
            getCars().add(new Stanford_Car(super.getSize(), true));
        }
        getCars().add(new LMU_Car(super.getSize(), 60, 100, 6, 0, true) );
        getCars().add(new LMU_Car(super.getSize(), 175, 200, 4, 0, true) );
    }
}
