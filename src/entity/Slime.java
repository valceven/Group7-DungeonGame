package entity;

import main.Collision;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

import static entity.EntityHandler.error_image;

class Slime extends Enemy {
    private final BufferedImage[] enemySprL = new BufferedImage[8];
    private final BufferedImage[] enemySprR = new BufferedImage[8];
    public Slime(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
        speed = 1;
        hp = 5;
        try {
            for (int i = 0; i < 8; i++) {
                enemySprL[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/boss_sprite/sir_jay" + i + ".png")));
                enemySprR[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/boss_sprite/sir_jay" + i + ".png")));
            }
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("missing slime sprites");
        }
    }
    @Override
    public void update(Player p) {
        if (hp <= 0) {
            EntityHandler.getInstance(gamePanel).spawnBlood(worldX, worldY, gamePanel);
            markForRemoval();
            gamePanel.win();
            return;
        }
        detectPlayer(p);

        collide = false;
        Collision.getInstance(gamePanel).checkTile(this);
        //gamePanel.collision.checkPlayer(this);

        if (getCooldown() > 0) {
            setCooldown(getCooldown()-1);
            collide = false;
            Collision.getInstance(gamePanel).checkTile(this);
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
        } else {
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if (i <= 20) {
                moveTowardsPlayer(p);
            } else if (i <= 40) {
                direction = "up";
            } else if (i <= 60) {
                direction = "down";
            } else if (i <= 80) {
                direction = "left";
            } else {
                direction = "right";
            }
            setCooldown(20);
        }
        spriteCounter += 7.5;
        if (spriteCounter == 60) {
            spriteNum++;
            if (spriteNum > 7) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    @Override
    public void draw(Graphics2D graphics) {
        BufferedImage image = switch (direction) {
        case "right", "up" -> enemySprR[spriteNum];
        default -> enemySprL[spriteNum];
        };

        int ascreenX = worldX - EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX;
        int ascreenY = worldY - EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY;
        if (worldX + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldX - EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
                worldX - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
                worldY + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldY - EntityHandler.getInstance(gamePanel).getPlayer().screenY &&
                worldY - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY) {
            if (image != null) {
                graphics.drawImage(image, ascreenX-(gamePanel.tileSize/2), ascreenY-(gamePanel.tileSize/2), gamePanel.tileSize*2, gamePanel.tileSize*2, null);
            } else {
                graphics.setColor(Color.MAGENTA);
                graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                graphics.drawImage(error_image, ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            }
        }
    }
}