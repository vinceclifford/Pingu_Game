package de.tum.in.ase.eist.collision;

import de.tum.in.ase.eist.Dimension2D;
import de.tum.in.ase.eist.Point2D;
import de.tum.in.ase.eist.car.Car;

/**
 * This class defines the behavior when two cars collide.
 */
public abstract class Collision {

    private final Car car1;
    private final Car car2;
    private final boolean crash;

    public Collision(Car car1, Car car2) {
        this.car1 = car1;
        this.car2 = car2;
        this.crash = detectCollision();
    }

    public boolean isCrash() {
        return crash;
    }

	protected Car getCar1() {
		return car1;
	}

	protected Car getCar2() {
		return car2;
	}

    public abstract boolean detectCollision();

    /**
     * Evaluates winner of the collision.
     *
     * @return winner Car
     */
    public abstract Car evaluate();

    /**
     * Evaluates loser of the collision.
     *
     * @return loser Car
     */
    public Car evaluateLoser() {
        Car winner = evaluate();
        if (this.car1 == winner) {
            return this.car2;
        }
        return this.car1;
    }

    boolean detectCollisionHelper(){
        Point2D p1 = getCar1().getPosition();
        Dimension2D d1 = getCar1().getSize();

        Point2D p2 = getCar2().getPosition();
        Dimension2D d2 = getCar2().getSize();

        boolean above = p1.getY() + d1.getHeight() < p2.getY();
        boolean below = p1.getY() > p2.getY() + d2.getHeight();
        boolean right = p1.getX() + d1.getWidth() < p2.getX();
        boolean left = p1.getX() > p2.getX() + d2.getWidth();

        return !above && !below && !right && !left;
    }

}
