package entity;

import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;
class Slime extends Enemy {
    private BufferedImage[] enemySpr = new BufferedImage[8];
    public Slime(int x, int y, String dir, GamePanel gamePanel) {
        super(x, y, dir, gamePanel);
        speed = 1;
        try {
            for (int i = 0; i < 1; i++) {
                enemySpr[i] = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/enemy/slime" + i + ".png")));
            }
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("missing slime sprites");
        }
    }
    @Override
    public void update(Player p) {
        //detectPlayer(p);
        //moveTowardsPlayer(p);
        collide = false;
        gamePanel.collision.checkTile(this);
       // gamePanel.collision.checkPlayer(this);

        if (getCooldown() > 0) {
            setCooldown(getCooldown()-1);
            collide = false;
            gamePanel.collision.checkTile(this);

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
            if (i <= 25) {
                direction = "up";
            } if (i > 25 && i <= 50) {
                direction = "down";
            } if (i > 50 && i <= 75) {
                direction = "left";
            } if (i > 75) {
                direction = "right";
            }
            setCooldown(10);
        }
        spriteCounter += 7.5;
        if (spriteCounter == 60) {
            spriteNum++;
            if (spriteNum >= 8) {
                spriteNum = 1;
            }
            spriteCounter = 0;
        }

    }

    @Override
    public void draw(Graphics2D graphics) {
        //BufferedImage image = enemySpr[spriteNum];
        BufferedImage image = enemySpr[0];
        if (image != null) {
            graphics.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        } else {
            graphics.setColor(Color.MAGENTA);
            graphics.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
            graphics.drawRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }
}