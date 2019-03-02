package topdown;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * Class used to create the player GameObject and alter its properties.
 *
 * @author Isaac Jimenez
 * @author Nicholas English
 * @author Suman Gurung
 * @version 1.0
 */
public class Player extends GameObject {

    /**
     * Instance of Handler class.
     */
    private Handler handle;

    /**
     * Used to store amount of health player has.
     */
    private int health;

    /**
     * Used to store how much money the player has.
     */
    private int money;

    /**
     * User to store how much shield the player has.
     */
    private int overShield;

    /**
     * Instance of the Game class.
     */
    private Game game;

    /**
     * Instance of SecondTimer class.
     */
    private SecondTimer timer;

    /**
     * Constructor creates a player object by accepting in various
     * parameters needed to determine the properties of the player.
     *
     * @param x      represents the x coordinate at which the player will
     *               spawn.
     * @param y      represents the y coordinate at which the player will
     *               spawn.
     * @param type   represents the type of object.
     * @param handle represents the instance of the Handler class being
     *               passed down to this constructor.
     * @param game   represents the instance of the Game class being
     *               passed down to this constructor.
     */
    public Player(final int x, final int y, final Type type,
                  final Handler handle, final Game game) {
        super(x, y, type, handle);
        health = 100;
        money = 100;
        overShield = 0;
        this.handle = handle;
        this.game = game;
        timer = new SecondTimer();
    }

    /**
     * Continuously updates the player's position, and checks to see if
     * the player has collided with an enemy.
     */
    @Override
    public void tick() {
    	setX((int) (getX() + getVelX()));
        setY((int) (getY() + getVelY()));

        //Movement
        if (handle.isUp() && getY() > 0) {
        	setVelY(-3);
        } else if (!handle.isDown()) {
            setVelY(0);
        }

        if (handle.isDown() && getY() < game.getHEIGHT() - 40) {
        	setVelY(3);
        } else if (!handle.isUp()) {
        	setVelY(0);
        }

        if (handle.isRight() && getX() < game.getWIDTH() - 40) {
            setVelX(3);
        } else if (!handle.isLeft()) {
            setVelX(0);
        }

        if (handle.isLeft() && getX() > 0) {
        	setVelX(-3);
        } else if (!handle.isRight()) {
        	setVelX(0);
        }

        if (handle.isLeft() && handle.isRight()) {
        	setVelX(0);
        }

        if (handle.isUp() && handle.isDown()) {
        	setVelY(0);
        }

        for (int i = 0; i < handle.getList().size(); i++) {
            GameObject tempObject = handle.getList().get(i);
            if (tempObject.getType() == Type.smallEnemy) {
                if (getBounds().intersects(tempObject.getBounds())) {
                    if (timer.isTimeUp()) {
                        health -= 10;
                        timer.setTime(System.currentTimeMillis());
                        if (health == 0) {
                            handle.removeObject(this);
                            game.setState(State.GameOver);
                        }
                    }
                }
            }
        }
    }

    /**
     * Draws the player with the given bounds.
     *
     * @param g visual display for the 2D graphics.
     */
    @Override
    public void render(final Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(getX(), getY(), 40, 40);
    }

    /**
     * Returns the boundaries of the player.
     *
     * @return The boundaries of the player as a rectangle.
     */
    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), getY(), 40, 40);
    }

    /**
     * Returns an int that represents the current health of the player.
     *
     * @return The health of the current player.
     */
    public int getHealth() {
        return health;
    }

    /**
     * Sets the health for the player object to the amount passed.
     *
     * @param health The health to be set to the player object.
     */
    public void setHealth(final int health) {
        this.health = health;
    }

    /**
     * Returns the amount of money the player currently has.
     *
     * @return the amount of money the player has.
     */
    public int getMoney() {
        return money;
    }

    /**
     * Sets the amount of money the player has.
     *
     * @param money the amount of in-game currency the player has.
     */
    public void setMoney(final int money) {
        this.money = money;
    }

    /**
     * Returns the amount of shield the player currently has.
     *
     * @return the amount of shield the player has.
     */
    public int getOverShield() {
        return overShield;
    }

    /**
     * Sets the amount of shield the player has.
     * @param overShield the amount of extra health the player has.
     */
    public void setOverShield(final int overShield) {
        this.overShield = overShield;
    }
}
