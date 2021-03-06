package gameObjects;
import game.Game;
import game.Handler;
import gfx.Assets;

import java.awt.*;
import java.util.LinkedList;

public class Player extends GameObject{
    private static double gravity = 20;
    private static final int width = 50, height = 60;
    private static long lastTimeHit = System.nanoTime();
    private static long now;
    private int i = 0;
    private int attackDamage;
    int lastDrawnPosition = 20;
    public static boolean
            isMovingRight= false,
            isMovingLeft = false,
            hasJumped = false,
            isIdle = true,
            isTurnedLeft = false,
            isTurnedRight = true,
            isShooting = false;


    public Player() {
        super(20, 470, width, height);

        this.setVelX(3);
        this.setVelY(2);

        this.setHealth(200);
        this.setLife(0);
        this.setIsDead(false);

        this.setAttackDamage(0);
    }

    @Override
    public void tick(){
       int tempX = this.getX();
       // int tempY = this.getY();
        if(this.getHealth() <= 0){
            this.setIsDead(true);
        }
        if(isMovingRight){

            if(this.getX() - lastDrawnPosition >= 10){
                i++;
                lastDrawnPosition = this.getX();
            }
            if(i>=9){
                i=0;
            }
            if(this.getX() >= 755){
                this.setX(755);
            } else {
                this.setX(this.getX() + this.getVelX());
            }
        } else if(isMovingLeft){
            if(lastDrawnPosition - this.getX() >= 10){
                i++;
                lastDrawnPosition = this.getX();
            }
            if(i>=9){
                i=0;
            }
            if(this.getX() <= 5){
                this.setX(5);
            } else {
                this.setX(this.getX() - this.getVelX());
            }

        }
        if(hasJumped){
            this.setY(this.getY()  - (int)gravity);
                gravity -= 0.5;
            if(this.getY() >= 470){
                hasJumped = false;
                gravity = 20;
                this.setY(470);
            }
        }

        if(isShooting){
            this.shoot();
            isShooting = false;
        }

        if(this.collision(Handler.objects)){
           this.setX(tempX);
       }
        this.getBoundingBox().setBounds(this.getX(), this.getY(), width, height);
    }

    @Override
    public void render(Graphics graphics) {
        //Just to see the player Rectangle
        //graphics.drawRect(this.getBoundingBox().x, this.getBoundingBox().y, this.getBoundingBox().width, this.getBoundingBox().height);

        if(hasJumped && isTurnedRight){
                graphics.drawImage(Assets.player.crop(0, 120, width, height), this.getX(), this.getY(), null);
        }
        else  if(hasJumped && isTurnedLeft){
                graphics.drawImage(Assets.reversedPlayer.crop(450, 120, width, height), this.getX(), this.getY(), null);
        }
        else if(isIdle && isTurnedRight ){
            graphics.drawImage(Assets.player.crop(0, 60, width, height), this.getX(), this.getY(), null);
        }
        else if(isIdle && isTurnedLeft){
            graphics.drawImage(Assets.reversedPlayer.crop(450, 60, width, height), this.getX(), this.getY(), null);
        }
        else if(isMovingRight){
            graphics.drawImage(Assets.player.crop(i * width, 0, width, height), this.getX(), this.getY(), null);
        }
        else if(isMovingLeft){
            graphics.drawImage(Assets.reversedPlayer.crop(500 - (i + 1) * width+3, 0, width, height), this.getX(), this.getY(), null);
        }
    }


    @Override
    public int getID() {
        return 1;
    }

    @Override
    public void Hit(int value) {
        this.setHealth(this.getHealth() - value);
        if (this.getHealth() <= 0) {
            this.setLife(this.getLife() - 1);
            this.setHealth(200);
        }
        if (this.getLife() < 0) {
            this.setIsDead(true);
        }
        System.out.print("Hit");
    }

    private boolean collision(LinkedList<GameObject> list) {
        for (GameObject obj : list) {
            if(this.intersects(obj) && obj.getID() != 2 && obj.getID() != 1) {
                if(obj.getAttackDamage() > 0){
                    now = System.nanoTime();
                    double delta = now - lastTimeHit;
                    double ns = 1_000_000_000.0;
                    if(Math.abs(delta / ns) > 0.8){
                        this.Hit(obj.getAttackDamage());
                        lastTimeHit = now;
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public void shoot(){
        if(isTurnedRight){
            Handler.objects.add(new Bullet(this.getX() + 51, this.getY() + 9, true));
        } else
            Handler.objects.add(new Bullet(this.getX() - 1, this.getY() + 9, false));
    }
}
