package de.tum.in.ase.eist.car;

import de.tum.in.ase.eist.Dimension2D;

public class Bullet_Car extends Car{

    private  String BULLET_CAR_IMAGE_FILE = "Snowball.png";

    public Bullet_Car(Dimension2D gameBoardSize, int direction, double x, double y){
        super(gameBoardSize);
        setSpeed(11);
        setDirection(direction);
        setPosition(x + 20, y  + 10);
        setIconLocation(BULLET_CAR_IMAGE_FILE);
        setSize(new Dimension2D(13,13));
    }

    @Override
    public void drive(Dimension2D gameBoardSize) {
        if (this.getCrunshed()) {
            return;
        }
        double maxX = gameBoardSize.getWidth();
        double maxY = gameBoardSize.getHeight();
        // calculate delta between old coordinates and new ones based on speed and
        // direction
        double deltaX = this.getSpeed() * Math.sin(Math.toRadians(this.getDirection()));
        double deltaY = this.getSpeed() * Math.cos(Math.toRadians(this.getDirection()));
        double newX = this.getPosition().getX() + deltaX;
        double newY = this.getPosition().getY() + deltaY;

        setPosition(newX, newY);
    }
}
