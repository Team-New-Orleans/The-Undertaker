package gameObjects;

import java.awt.*;
import java.util.LinkedList;

public abstract class GameObject {
    private int x, y;
    private int velX, velY;
    protected int width;
    protected int height;
    private int health;
    private int life;
    private int attackDamage;
    private boolean isDead;

    private Rectangle boundingBox;

    public GameObject(int x, int y, int width, int height) {
        this.setX(x);
        this.setY(y);
        this.setWidth(width);
        this.setHeight(height);
        this.boundingBox = new Rectangle(this.x, this.y, width, height);
    }

    public abstract void tick();

    public abstract void render(Graphics graphics);

    //getID will be unique for the different types of object: Player: 1, Bullet: 2, Enemy: 3
    public abstract int getID();

    //Checks if the current object is colliding with something
   // public abstract boolean Collision(LinkedList<GameObject> list);

    //Hits the object if a bullet is intersecting it
    public abstract void Hit(int value);

    public boolean intersects(GameObject other) {
        return this.getBoundingBox().intersects(other.getBoundingBox());
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

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttackDamage() {
        return attackDamage;
    }

    public void setAttackDamage(int attackDamage) {
        this.attackDamage = attackDamage;
    }

    public Rectangle getBoundingBox() {
        return boundingBox;
    }

    public boolean getIsDead() {return isDead ;}

    public void setIsDead (boolean isDead) {
        this.isDead = isDead;
    }

    public int getLife() {return this.life; }

    public void setLife (int life) {this.life = life; }
}
