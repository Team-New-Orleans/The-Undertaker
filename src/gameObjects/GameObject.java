package gameObjects;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {
    private int x, y;
    private int velX, velY;
    protected int width;
    protected int height;

    private Rectangle boundingBox;

    public GameObject(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.boundingBox = new Rectangle(this.x,this.y,width, height);
    }
    public abstract void tick();
    public abstract void render(Graphics graphics);
    //getID will be unique for the different types of object: Player: 1, Bullet: 2, Enemy: 3
    public abstract int getID();
    //Checks if the current object is colliding with something
    public abstract boolean Collision(LinkedList<GameObject> list);

    public boolean intersects(GameObject other) {
        return this.getBoundingBox().contains(other.getBoundingBox());
    }

    public int getVelX() {
        return velX;
    }

    public void setVelX(int velX) {
        this.velX = velX;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getVelY() {
        return velY;
    }

    public void setVelY(int velY) {
        this.velY = velY;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }
<<<<<<< HEAD

=======
>>>>>>> parent of 4757575... Dinosaur - new enemy added
}
