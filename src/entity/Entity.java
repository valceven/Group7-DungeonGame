package entity;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldX;
    public int worldY;
    public int speed;

    public String direction;
    public double spriteCounter = 0;
    public int spriteNum = 1;
    public Rectangle solidArea = new Rectangle(8,12,32,32);
    public boolean collide = false;
    // char status;
    public int maxHp;
    public int hp;

}
