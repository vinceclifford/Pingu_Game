package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.Harvard_Car;
import de.tum.in.ase.eist.car.KIT_Car;
import de.tum.in.ase.eist.car.MIT_Car;

public class GameBoard5 extends GameBoard {

    public GameBoard5(Dimension2D size){
        super(size);
    }

    @Override
    public void createCars(){
        for(int i = 0; i < 6; i++)
            getCars().add(new MIT_Car(getSize(), false)) ;
        getCars().add(new KIT_Car(getSize(),false));
    }
}
