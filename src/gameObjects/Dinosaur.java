package gameObjects;

import gfx.Assets;

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by VikSUN on 4/11/2015.
 */

public class Dinosaur extends GameObject{
    private boolean toRight; // Enemy's moving direction
    private boolean attacksRight;
    private boolean attacksLeft;

    private static final int width = 142, height = 114, widthAttack = 134;

    // Chasing target
    private GameObject target;

    private int sprite = 1, hit = 1, death = 1;

    public Dinosaur(int x, int y, boolean startPos, GameObject target) {
        super(x, y, width, height);
        this.target = target;
        this.setVelX(2);
        this.toRight = startPos; // Starting position
        this.setHealth(40);
        this.setAttackDamage(20);
        this.setIsDead(false);
    }

    @Override
    public void tick() {
        if (this.getHealth() <= 0) {
            death++;
            this.setAttackDamage(0);
            if (death >= 79) {
                this.setIsDead(true);
            }
        } else if (attacksRight) {
            hit++;
            if (hit >= 19) {
                this.setX(this.getX() + getVelX());
                hit = 1;
                toRight = false; // Changes direction when hit.
                            }
        } else if (attacksLeft) {
            hit++;
            if (hit >= 19) {
                this.setX(this.getX() - getVelX());
                hit = 1;
                toRight = true; // Changes direction when hit.
            }
        } else if(toRight) {
            this.setX(this.getX() + getVelX());
            sprite++;
        } else {
            this.setX(this.getX() - getVelX());
            sprite++;
        }
        if(!this.getIsDead()){
            toRight = chase(toRight, target);
            attacksRight = attackOnRight(toRight, target);
            attacksLeft = attackOnLeft(toRight, target);
        }
        if (sprite >= 29) {
            sprite = 1;
        }

        this.getBoundingBox().setBounds(this.getX(), this.getY(), this.getWidth(), this.getHeight());
    }

    @Override
    public void render(Graphics graphics) {
        if (this.getHealth() <= 0) {
            if (toRight){
                graphics.drawImage(Assets.dinosaurDeath.crop(death / 40 * 129, 0, 129, 125), this.getX(), this.getY(), null);
            } else {
                graphics.drawImage(Assets.dinosaurDeathReversed.crop((79 - death) / 40 * 129, 0, 129, 125), this.getX(), this.getY(), null);
            }
        } else if (attacksRight) {
            graphics.drawImage(Assets.dinosaurAttack.crop(hit / 10 * widthAttack, 0, widthAttack, 115), this.getX(), this.getY(), null);
        } else if (attacksLeft) {
            graphics.drawImage(Assets.dinosaurAttackReversed.crop((19 - hit) / 10 * widthAttack, 0, widthAttack, 115), this.getX(), this.getY(), null);
        } else if (toRight){
            graphics.drawImage(Assets.dinosaur.crop(sprite / 10 * width, 0, width, height), this.getX(), this.getY(), null);
        } else {
            graphics.drawImage(Assets.dinosaurReversed.crop(sprite / 10 * width, 0, width, height), this.getX(), this.getY(), null);
        }
    }

    @Override
    public int getID() {
        return 3;
    }

    @Override
    public void Hit(int value) {
        this.setHealth(this.getHealth() - value);
    }

  //  @Override
    //public boolean Collision(LinkedList<GameObject> list) {
      //  for (GameObject obj : list) {
        //    if(this.intersects(obj) && obj.getID() != 3){
                //Must implement a Hit function in GameObject
                //obj.Hit();
       //     }
     //   }
   //     return false;
 //   }

    private int getWidthAttack() {
        return widthAttack;
    }

    private boolean chase(boolean currentDirectionIsRight, GameObject gameobject) {
        boolean moveToRight;

        // Enemy changes movement direction when passes by the player. Required distance for changing direction - player's width
        if (currentDirectionIsRight == true) {
            if (this.getX() >= gameobject.getX() + (gameobject.getWidth() * 3)) {
                moveToRight = false;
            } else {
                moveToRight = true;
            }
        } else {
            if (this.getX() + this.getWidth() + gameobject.getWidth() * 2 <= gameobject.getX()) {
                moveToRight = true;
            } else {
                moveToRight = false;
            }
        }

        return  moveToRight;
    }

    private boolean attackOnRight(boolean currentDirectionIsRight, GameObject player) {
        boolean attackOnRight = false;

        // Enemy attacks if it is on specific distance from the player
        if (currentDirectionIsRight == true) {
            if (player.getX() <= this.getX() + this.getWidthAttack() - 15 + this.getVelX() &&
                    player.getX() >= this.getX() + this.getWidthAttack() - 15 - this.getVelX() &&
                    player.getY() == 470 ) { // Attacks only when the player is on the ground. OVERLAPPING
                attackOnRight = true;
            }
        }

        return attackOnRight;
    }

    private boolean attackOnLeft(boolean currentDirectionIsRight, GameObject player) {
        boolean attackOnLeft = false;

        // Enemy attacks if it is on specific distance from the player
        if (currentDirectionIsRight == false) {
            if (player.getX() + player.getWidth()  <=  this.getX() + 15 + this.getVelX() &&
                    player.getX() + player.getWidth()  >=  this.getX() + 15 - this.getVelX() &&
                    player.getY() == 470) { // Attacks only when the player is on the ground. . OVERLAPPING
                attackOnLeft = true;
            }
        }

        return attackOnLeft;
    }
}
