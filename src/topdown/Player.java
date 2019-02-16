package topdown;

import java.awt.*;

public class Player extends GameObject {

    private Handler handle;
    private int health;
    private Game game;

    public Player(int x, int y, Type type, Handler handle, Game game) {
        super(x, y, type);
        this.handle = handle;
        health = 100;
        this.game = game;
    }

    @Override
    public void tick() {
        x += velX;
        y += velY;

        //Movement
        if (handle.isUp() && y > 10)
            velY = -3;
        else if (!handle.isDown())
            velY = 0;

        if (handle.isDown() && y < game.getHEIGHT() - 50)
            velY = 3;
        else if (!handle.isUp())
            velY = 0;

        if (handle.isRight() && x < game.getWIDTH() - 50) {
            velX = 3;
        }
        else if (!handle.isLeft())
            velX = 0;

        if (handle.isLeft() && x > 10)
            velX = -3;
        else if (!handle.isRight())
            velX = 0;

        if (handle.isLeft() && handle.isRight())
            velX = 0;

        if (handle.isUp() && handle.isDown())
            velY = 0;
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillRect(x, y, 40, 40);
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 40, 40);
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }
}