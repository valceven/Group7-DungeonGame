package objects;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import entity.EntityHandler;

public class objectParent {

    public BufferedImage image;
    public BufferedImage[] imageArr = new BufferedImage[12];
    public String name;
    public boolean collision = false;
    public int worldX,worldY;

    public void draw(Graphics2D graphics, GamePanel gamePanel){

        int screenX = worldX - EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX;
        int screenY = worldY - EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY;

        if(worldX + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldX - EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
                worldX - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
                worldY + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldY - EntityHandler.getInstance(gamePanel).getPlayer().screenY &&
                worldY - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY){
            graphics.drawImage(image,screenX,screenY,gamePanel.tileSize*5, gamePanel.tileSize*5 ,null);
        }
    }
}
