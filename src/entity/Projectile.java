package entity;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

import static entity.EntityHandler.error_image;


public class Projectile extends Entity {
    GamePanel gamePanel;
    public final int screenX;
    public final int screenY;
    String side;
    ProjType pt;
    boolean sprMissing = false;
    private final BufferedImage[] projSpr = new BufferedImage[4];
    private boolean markedForRemoval = false;
    private int lifespan;
    public Projectile(int x, int y, ProjType pt, String dir, String side, GamePanel gp) {
        this.pt = pt;
        this.gamePanel = gp;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        this.side = side;
        lifespan = pt.timer();

        worldX = gamePanel.tileSize * x;
        worldY = gamePanel.tileSize * y;
        direction = dir;
        speed = 4;
        try {
            for (int i = 0; i < this.pt.frames(); i++) {
                projSpr[i] =  ImageIO.read(getClass().getResourceAsStream("/proj/" + this.pt.getProjName() + i + ".png"));
            }
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("missing projectile sprites");
            sprMissing = true;
        }

    }

    public void update(Player p) {
        if (lifespan <= 0) {
            markForRemoval();
            return;
        }
        //lifespan--;

        switch (direction) {
            case "up":
                worldY -= speed;
                break;
            case "down":
                worldY += speed;
                break;
            case "left":
                worldX -= speed;
                break;
            case "right":
                worldX += speed;
                break;
        }

        spriteCounter += 7.5;
        if (spriteCounter == 60) {
            spriteNum++;
            if (spriteNum > pt.frames()-1) {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
        attackArea.setBounds(worldX, worldY, 24, 24);
        if (side.equals("enemy")) {
            p.attackArea.setBounds(p.worldX,p.worldY, 20, 20);
            if (attackArea.intersects(p.attackArea)) {
                p.hit();
            }
        }
    }
    public void draw(Graphics2D graphics) {
        int ascreenX = worldX - EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX;
        int ascreenY = worldY - EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY;
//        if (worldX + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldX - EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
//                worldX - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
//                worldY + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldY - EntityHandler.getInstance(gamePanel).getPlayer().screenY &&
//                worldY - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY) {
            if (!sprMissing) {
                graphics.drawImage(projSpr[spriteNum], ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            } else {
                graphics.setColor(Color.MAGENTA);
                graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                graphics.drawImage(error_image, ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
//        }
    }

    public void markForRemoval() {
        markedForRemoval = true;
    }
    public boolean isMarkedForRemoval() {
        return markedForRemoval;
    }
}
