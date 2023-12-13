package entity;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import static main.GamePanel.error_image;

public class Projectile extends Entity {
    GamePanel gamePanel;
    public final int screenX;
    public final int screenY;
    String side;
    ProjType pt;
    boolean sprMissing = false;
    private final BufferedImage[] projSpr = new BufferedImage[8];
    public Projectile(int x, int y, ProjType pt, String dir, GamePanel gamePanel, String side) {
        this.gamePanel = gamePanel;
        this.pt = pt;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        this.side = side;

        worldX = gamePanel.tileSize * x;
        worldY = gamePanel.tileSize * y;
        direction = dir;
        speed = 4;
            for (int i = 0; i < pt.frames(); i++) {
                try {
                    projSpr[i] =  ImageIO.read(getClass().getResourceAsStream("/proj/" + this.pt.getProjName() + i + ".png"));
                } catch (IOException | IllegalArgumentException e) {
                    System.err.println("missing projectile sprites");
                    sprMissing = true;
                    break;
                }
            }

    }

    public void update(Player p) {
        //System.out.println(pt.getProjName() + " exists");
        spriteCounter += 7.5;
        if (spriteCounter == 60) {
            spriteNum++;
            if (spriteNum >= pt.frames()) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics) {
        int ascreenX = worldX - gamePanel.entityHandler.getPlayer().worldX + gamePanel.entityHandler.getPlayer().screenX;
        int ascreenY = worldY - gamePanel.entityHandler.getPlayer().worldY + gamePanel.entityHandler.getPlayer().screenY;
//        if (worldX + gamePanel.tileSize > gamePanel.entityHandler.getPlayer().worldX - gamePanel.entityHandler.getPlayer().screenX &&
//                worldX - gamePanel.tileSize < gamePanel.entityHandler.getPlayer().worldX + gamePanel.entityHandler.getPlayer().screenX &&
//                worldY + gamePanel.tileSize > gamePanel.entityHandler.getPlayer().worldY - gamePanel.entityHandler.getPlayer().screenY &&
//                worldY - gamePanel.tileSize < gamePanel.entityHandler.getPlayer().worldY + gamePanel.entityHandler.getPlayer().screenY) {
            if (!sprMissing) {
                graphics.drawImage(projSpr[spriteNum], ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            } else {
                graphics.setColor(Color.MAGENTA);
                graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                graphics.drawImage(error_image, ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
//        }
    }
}
