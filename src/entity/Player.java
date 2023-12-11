package entity;

import entity.Entity;
import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    private BufferedImage mageLeft,mageRight;
    private BufferedImage[] knightLeft = new BufferedImage[8];
    private BufferedImage[] knightRight = new BufferedImage[8];
    private BufferedImage[] knightUp = new BufferedImage[8];
    private BufferedImage[] knightDown = new BufferedImage[8];
    private String characterType;

    public Player(GamePanel gamePanel, KeyHandler keyH){
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        setDefaultValues();
        getPlayerImage();
    }


    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 16;
        worldY = gamePanel.tileSize * 18;
        speed = 4;
        direction = "down";

        maxHp = 11;
        hp = 1;
    }

    public void getPlayerImage() {
        try {
            for (int i = 1; i <= 8; i++) {
                knightLeft[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_left" + i + ".png"));
                knightRight[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_right" + i + ".png"));
                knightUp[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_up" + i + ".png"));
                knightDown[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/player_down" + i + ".png"));
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.up || keyH.down || keyH.left || keyH.right) {
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else if (keyH.right) {
                direction = "right";
            }

            spriteCounter += 7.5;
            if (spriteCounter == 60) {
                spriteNum++;
                if (spriteNum >= 8) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
            collide = false;
            gamePanel.collision.checkTile(this);
      //      gamePanel.collision.checkEnemy(this, gamePanel.entityHandler.enemyList);

     //       int monsterIndex = gamePanel.collision.checkEntity(this, gamePanel.monster);

            if (!collide) {
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                }
            }
        }
    }
    public void draw(Graphics2D graphics) {
        BufferedImage image = switch (direction) {
            case "left" -> knightLeft[spriteNum];
            case "right" -> knightRight[spriteNum];
            case "up" -> knightUp[spriteNum];
            default -> knightDown[spriteNum];
        };
        if (image != null) {
            graphics.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }
}