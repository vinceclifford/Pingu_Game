package de.tum.in.ase.eist.usercontrol;

import de.tum.in.ase.eist.GameBoard;
import de.tum.in.ase.eist.car.Bullet_Car;
import de.tum.in.ase.eist.car.Car;

public class KeyboardSteering {

    private int amount = 0;

    private final GameBoard gb;

    public KeyboardSteering(GameBoard gb) {
        this.gb = gb;
    }

    public void xPressed() {
        if (amount++ < 3) {
            int direction = gb.getPlayerCar().getDirection();
            double x = gb.getPlayerCar().getPosition().getX();
            double y = gb.getPlayerCar().getPosition().getY();
            gb.getBullets().add(new Bullet_Car(gb.getSize(), direction, x, y));
            System.out.println("Remaining bullets: " + (3 - amount));
        }
    }
}
