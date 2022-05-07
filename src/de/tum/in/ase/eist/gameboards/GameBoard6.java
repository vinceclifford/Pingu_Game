package de.tum.in.ase.eist.gameboards;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.Harvard_Car;
import de.tum.in.ase.eist.car.Stanford_Car;

public class GameBoard6 extends GameBoard {

    public GameBoard6(Dimension2D size){
        super(size);
    }

    @Override
    public void createCars(){
        for(int i = 0; i < 5; i++)
            getCars().add(new Stanford_Car(getSize(), false));
    }
}
