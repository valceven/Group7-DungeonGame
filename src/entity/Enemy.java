package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public abstract class Enemy extends Entity {
    GamePanel gamePanel;
    AIState state = AIState.IDLE;
    public final int screenX;
    public final int screenY;
    private int cooldown = 0;
    int attackSpeed;

    public Enemy(int x, int y, String dir, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);

        worldX = gamePanel.tileSize * x;
        worldY = gamePanel.tileSize * y;
        direction = dir;
        speed = 4;

        maxHp = 1;
        hp = 1;
    }

    public abstract void update(Player p);

    public abstract void draw(Graphics2D graphics);
    void detectPlayer(Player p) {
        if ((state == AIState.IDLE) && 5 <= Math.sqrt(Math.pow(worldX - p.worldX, 2) + Math.pow(worldY - p.worldY, 2))) {
            state = AIState.COMBAT;
        }
    }
    void moveTowardsPlayer(Player p) {
        if (state == AIState.COMBAT) {
            worldX += (int) (speed * Math.cos(Math.atan2(worldY - p.worldY, worldX - p.worldX)));
            worldY += (int) (speed * Math.sin(Math.atan2(worldY - p.worldY, worldX - p.worldX)));
        }
    }
    void strafeOnPlayer(Player p) {
        if (state == AIState.COMBAT) {
            if (worldX == p.worldX) {
                if (p.worldY > worldY) {
                    shootPlayer(p, 'r');
                } else {
                    shootPlayer(p, 'l');
                }
            } else if (worldY == p.worldY) {
                if (p.worldX > worldX) {
                    shootPlayer(p, 'u');
                } else {
                    shootPlayer(p, 'd');
                }
            }
            if (direction.equals("up") || direction.equals("down")) {
                worldX += (int) (speed * Math.cos(Math.atan2(worldY - p.worldY, worldX - p.worldX)));
            } else {
                worldY += (int) (speed * Math.sin(Math.atan2(worldY - p.worldY, worldX - p.worldX)));
            }
        }
    }
    void shootPlayer(Player p, char c) {
        if (cooldown < 0) {
            if (c == 'l') {
                gamePanel.entityHandler.spawnProjectile(screenX, screenY, "left", gamePanel, "enemy");
            } else if (c == 'r') {
                gamePanel.entityHandler.spawnProjectile(screenX, screenY, "right", gamePanel, "enemy");
            } else if (c == 'u') {
                gamePanel.entityHandler.spawnProjectile(screenX, screenY, "up", gamePanel, "enemy");
            } else {
                gamePanel.entityHandler.spawnProjectile(screenX, screenY, "down", gamePanel, "enemy");
            }
            cooldown += attackSpeed;
        } else {
            cooldown--;
        }
    }

    public int getCooldown() {
        return cooldown;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }
}



