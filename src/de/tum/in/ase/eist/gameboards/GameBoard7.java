package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.*;

public class GameBoard7 extends GameBoard {

    public GameBoard7(Dimension2D size){
        super(size);
    }

    @Override
    public void createCars(){
        for(int i = 0; i < 2; i++){
            getCars().add(new KIT_Car(getSize(), false));
            getCars().add(new Stanford_Car(getSize(), false));
        }
        getCars().add(new MIT_Car(getSize(), false));
        getCars().add(new LMU_Car(super.getSize(), 60, 100, 6, 0, false));
        getCars().add(new LMU_Car(super.getSize(), 175, 200, 4, 180, false));
    }
}
