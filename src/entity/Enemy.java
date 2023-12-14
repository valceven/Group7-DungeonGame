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
    private int attackSpeed = 30;
    private boolean inMeleeRange = false;
    private boolean markedForRemoval = false;

    public Enemy(int x, int y, GamePanel gp) {
        this.gamePanel = gp;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);

        worldX = gamePanel.tileSize * x;
        worldY = gamePanel.tileSize * y;
        speed = 4;

        maxHp = 1;
        hp = 1;
    }

    public abstract void update(Player p);

    public abstract void draw(Graphics2D graphics);
    void detectPlayer(Player p) {
        if ((state == AIState.IDLE) &&  256 > Math.sqrt(Math.pow(worldX - p.worldX, 2) + Math.pow(worldY - p.worldY, 2))) {
            state = AIState.COMBAT;
        } else {
            switch (Integer.compare(worldX, p.worldX)) {
                case 1 -> direction = "left";
                default -> direction = "right";
            }
        }
    }
    void moveTowardsPlayer(Player p) {
        if (state == AIState.COMBAT) {
            worldX -= (int) (speed * Math.cos(Math.atan2(worldY - p.worldY, worldX - p.worldX)));
            worldY -= (int) (speed * Math.sin(Math.atan2(worldY - p.worldY, worldX - p.worldX)));

        }
    }
    void strafeOnPlayer(Player p, char axis, ProjType pt) {
        if (state == AIState.COMBAT) {
            if (axis == 'y' && worldX == p.worldX) {
                shootPlayer(p, 'y', pt);
            } else if (axis == 'x' && worldY == p.worldY) {
                shootPlayer(p, 'x', pt);
            }
            if (axis == 'y') {
                worldY -= (speed * Integer.compare(worldY, p.worldY));
            } else {
                worldX -= (speed * Integer.compare(worldX, p.worldX));
            }
        }
    }
    void shootPlayer(Player p, char axis, ProjType pt) {
        int t;
        if (cooldown < 0) {
            if (axis == 'y') {
                t = Integer.compare(worldX, p.worldX);
                if (t == 1) {
                    EntityHandler.getInstance(gamePanel).spawnProjectile(screenX, screenY, pt, "left", "enemy", gamePanel);
                } else {
                    EntityHandler.getInstance(gamePanel).spawnProjectile(screenX, screenY, pt, "right", "enemy", gamePanel);
                }
            } else {
                t = Integer.compare(worldY, p.worldY);
                if (t == 1) {
                    EntityHandler.getInstance(gamePanel).spawnProjectile(screenX, screenY, pt, "up", "enemy", gamePanel);
                } else {
                    EntityHandler.getInstance(gamePanel).spawnProjectile(screenX, screenY, pt, "down", "enemy", gamePanel);
                }
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

    public boolean isInMeleeRange() {
        return inMeleeRange;
    }

    public void setInMeleeRange(boolean inMeleeRange) {
        this.inMeleeRange = inMeleeRange;
    }
    public void markForRemoval() {
        markedForRemoval = true;
    }
    public boolean isMarkedForRemoval() {
        return markedForRemoval;
    }

    public int getAttackSpeed() {
        return attackSpeed;
    }

    public void setAttackSpeed(int attackSpeed) {
        this.attackSpeed = attackSpeed;
    }
}



