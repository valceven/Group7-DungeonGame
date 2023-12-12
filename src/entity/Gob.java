package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Gob extends Enemy {
    gobClass gc;
    gobSize gs;
    private BufferedImage[] enemyUp = new BufferedImage[8];
    private BufferedImage[] enemyDown = new BufferedImage[8];
    private BufferedImage[] enemyLeft = new BufferedImage[8];
    private BufferedImage[] enemyRight = new BufferedImage[8];
    // ArrayList<Enemy> enemies;
    // enemies.add(new Gob(new Fighter, new Mini, gp));
    public Gob(int x, int y, String dir, gobClass gc, gobSize gs, GamePanel gamePanel) {
        super(x, y, dir, gamePanel);
        this.gc = gc;
        this.gs = gs;
        speed = 2*this.gc.speedMult();
        attackSpeed = this.gc.attackSpeed();
        try {
            for (int i = 0; i < 8; i++) {
                enemyLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_left" + i + ".png"));
                enemyRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_right" + i + ".png"));
                enemyUp[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_up" + i + ".png"));
                enemyDown[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_down" + i + ".png"));
            }
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("no goblin sprite lol");
        }
    }

    @Override
    public void update(Player p) {
        detectPlayer(p);
        if (gc.getClassText().equals("Archer")){
            strafeOnPlayer(p);
        } else {
            moveTowardsPlayer(p);
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
        BufferedImage image = switch (direction) {
            case "left" -> enemyLeft[spriteNum];
            case "right" -> enemyRight[spriteNum];
            case "up" -> enemyUp[spriteNum];
            case "down" -> enemyDown[spriteNum];
            default -> null;
        };
        int ascreenX = worldX - gamePanel.entityHandler.getPlayer().worldX + gamePanel.entityHandler.getPlayer().screenX;
        int ascreenY = worldY - gamePanel.entityHandler.getPlayer().worldY + gamePanel.entityHandler.getPlayer().screenY;
        if (worldX + gamePanel.tileSize > gamePanel.entityHandler.getPlayer().worldX - gamePanel.entityHandler.getPlayer().screenX &&
                worldX - gamePanel.tileSize < gamePanel.entityHandler.getPlayer().worldX + gamePanel.entityHandler.getPlayer().screenX &&
                worldY + gamePanel.tileSize > gamePanel.entityHandler.getPlayer().worldY - gamePanel.entityHandler.getPlayer().screenY &&
                worldY - gamePanel.tileSize < gamePanel.entityHandler.getPlayer().worldY + gamePanel.entityHandler.getPlayer().screenY) {
            if (image != null) {
                graphics.drawImage(image, ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            } else {
                graphics.setColor(Color.GREEN);
                graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
            }
        }
    }
}
