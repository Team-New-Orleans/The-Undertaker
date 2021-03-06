package gameObjects;

import game.Handler;
import gfx.Assets;

import java.awt.*;
import java.util.LinkedList;


public class Bullet extends GameObject{
    private boolean toRight;
    public Bullet(int x, int y, boolean toRight) {
        super(x, y, 20, 6);
        this.toRight = toRight;
        this.setVelX(5);
        setHealth(1);
        setAttackDamage(20);
    }

    @Override
    public void tick() {
        if(toRight) this.setX(this.getX() + getVelX());
        else this.setX(this.getX() - getVelX());

        this.getBoundingBox().setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        this.collision(Handler.objects);
    }

    @Override
    public void render(Graphics graphics) {
        graphics.drawImage(Assets.bullet.crop(0, 0, 20, 6), this.getX(), this.getY(), null);
    }


    @Override
    public int getID() {
        return 2;
    }


    private boolean collision(LinkedList<GameObject> list) {
        for (GameObject obj : list) {
            if(this.intersects(obj) && obj.getID() != 2 && obj.getID() != 1 && obj.getHealth() > 0){
                obj.Hit(this.getAttackDamage());
                this.setHealth(0);
                this.setIsDead(true);
            }
        }
        return false;
    }

    @Override
    public void Hit(int value) {
        this.setHealth(this.getHealth() - value);
    }
}
