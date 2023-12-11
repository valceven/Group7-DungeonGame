package objects;

import main.GamePanel;
import java.awt.*;
import java.awt.image.BufferedImage;

public class objectParent {

    public BufferedImage image;
    public BufferedImage[] imageArr = new BufferedImage[12];
    public String name;
    public boolean collision = false;
    public int worldX,worldY;

    public void draw(Graphics2D graphics, GamePanel gamePanel){

        int screenX = worldX - gamePanel.entityHandler.getPlayer().worldX + gamePanel.entityHandler.getPlayer().screenX;
        int screenY = worldY - gamePanel.entityHandler.getPlayer().worldY + gamePanel.entityHandler.getPlayer().screenY;

        if(worldX + gamePanel.tileSize > gamePanel.entityHandler.getPlayer().worldX - gamePanel.entityHandler.getPlayer().screenX &&
                worldX - gamePanel.tileSize < gamePanel.entityHandler.getPlayer().worldX + gamePanel.entityHandler.getPlayer().screenX &&
                worldY + gamePanel.tileSize > gamePanel.entityHandler.getPlayer().worldY - gamePanel.entityHandler.getPlayer().screenY &&
                worldY - gamePanel.tileSize < gamePanel.entityHandler.getPlayer().worldY + gamePanel.entityHandler.getPlayer().screenY){
            graphics.drawImage(image,screenX,screenY,gamePanel.tileSize*5, gamePanel.tileSize*5 ,null);
        }
    }
}
