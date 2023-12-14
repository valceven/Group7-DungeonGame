package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;
    public String direction = "down";
    public double spriteCounter = 0;
    public int spriteNum = 1;
    public int attackSpriteNum = 1;
    public Rectangle solidArea = new Rectangle(8,16,32,32);
    public boolean collide = false;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    // char status;
    public int maxHp;
    public int hp;

}
