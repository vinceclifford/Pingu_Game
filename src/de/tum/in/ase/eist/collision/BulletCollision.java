package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Bullet_Car;
import de.tum.in.ase.eist.car.Car;

public class BulletCollision extends Collision {

    public BulletCollision(Car car1, Car car2) {
        super(car1, car2);
    }

    @Override
    public boolean detectCollision() {
        return detectCollisionHelper();
    }

    @Override
    public Car evaluate() {
        Point2D p1 = getCar1().getPosition();
        Point2D p2 = getCar2().getPosition();
        // Both bullet and car will always be crunshed!


        Car winnerCar = getCar2();

        return winnerCar;
    }

}
