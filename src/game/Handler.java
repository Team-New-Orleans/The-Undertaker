package game;

import display.Display;
import gameObjects.Bullet;
import gameObjects.GameObject;

import java.awt.*;
import java.util.LinkedList;

public class Handler {

    public static LinkedList<GameObject> objects = new LinkedList<>();

    public void tick(){
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            if(tempObject.getIsDead()) objects.remove(i);
            tempObject.tick();

            // Removes a game object which has left the canvas.
            if ((tempObject.getX() + tempObject.getWidth() * 2  <= 0 ) || (tempObject.getX() >= 800 + tempObject.getWidth() * 2)) {
                objects.remove(i);
            }

        }
    }
    public void render(Graphics graphics){
        for (int i = 0; i < objects.size(); i++) {
            GameObject tempObject = objects.get(i);
            tempObject.render(graphics);
        }
    }

}
