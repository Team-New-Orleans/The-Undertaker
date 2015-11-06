package display;

import game.Game;

import java.awt.*;

/**
 * Created by Simooo on 6.11.2015 �..
 */
public class HUD {

    private int greenValue = 255;
    public void tick(){
        if(Game.player.getHealth() < 200){
            greenValue = Game.player.getHealth();
        }
    }
    public void render(Graphics graphics){
        graphics.setColor(Color.gray);
        graphics.fillRect(15, 15, 200, 32);
        graphics.setColor(new Color(100, greenValue, 0));
        graphics.fillRect(15, 15, Game.player.getHealth(), 32);
        graphics.setColor(Color.white);
        graphics.drawRect(15, 15, 200, 32);
    }
}