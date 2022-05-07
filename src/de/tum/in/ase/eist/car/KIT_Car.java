package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class KIT_Car extends Car{

    private  String KIT_CAR_IMAGE_FILE = "KIT.png";

    public KIT_Car (Dimension2D gameBoardSize, boolean elon){
        super(gameBoardSize);
        if(elon){
            KIT_CAR_IMAGE_FILE = "elon.png";
            setSize(new Dimension2D(40, 31));
        } else {
            setSize(new Dimension2D(43 ,20));
        }
        randomStartingPosition();
        setIconLocation(KIT_CAR_IMAGE_FILE);
        setSpeed(7);
        setRandomDirection();
        setSize(new Dimension2D(43 ,20));
    }

    @Override
    public void drive(Dimension2D gameBoardSize){
        if (isCrunched()) {
            return;
        }
        double maxX = gameBoardSize.getWidth();
        double maxY = gameBoardSize.getHeight();
        // calculate delta between old coordinates and new ones based on speed and
        // direction
        double deltaX = getSpeed() * Math.sin(Math.toRadians(this.getDirection()));
        double deltaY = this.getSpeed() * Math.cos(Math.toRadians(this.getDirection()));
        double newX = this.getPosition().getX() + deltaX;
        double newY = this.getPosition().getY() + deltaY;

        if(newX < 0)
            newX = newX + gameBoardSize.getWidth();
        else if (newX > gameBoardSize.getWidth())
            newX = newX % gameBoardSize.getWidth();

        if(newY < 0)
            newY = newY + gameBoardSize.getHeight();
        else if (newY > gameBoardSize.getHeight())
            newY = newY % gameBoardSize.getHeight();

        setPosition(newX, newY);
    }
}
