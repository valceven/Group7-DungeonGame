package entity;

import main.Collision;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

class Gob extends Enemy {
    gobClass gc;
    gobSize gs;
    private BufferedImage[] enemyLeft = new BufferedImage[4];
    private BufferedImage[] enemyRight = new BufferedImage[4];
    private BufferedImage[] enemyAttackLeft = new BufferedImage[4];
    private BufferedImage[] enemyAttackRight = new BufferedImage[4];
    // ArrayList<Enemy> enemies;
    // enemies.add(new Gob(new Fighter, new Mini, gp));
    public Gob(int x, int y, gobClass gc, gobSize gs, GamePanel gamePanel) {
        super(x, y, gamePanel);
        this.gc = gc;
        this.gs = gs;
        speed = this.gc.speedMult();
        setAttackSpeed(this.gc.attackSpeed());
        try {
            for (int i = 0; i < 4; i++) {
                enemyLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_left" + i + ".png"));
                enemyRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_right" + i + ".png"));
                enemyAttackLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_attack_left" + i + ".png"));
                enemyAttackRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/" + gs.getSizeText() + gc.getClassText() + "_attack_right" + i + ".png"));
            }
        } catch (IOException | IllegalArgumentException | NullPointerException e) {
            System.err.println("no " + gs.getSizeText() + gc.getClassText() + " sprite lol");
        }
    }

    @Override
    public void update(Player p) {
        if (hp <= 0) {
            EntityHandler.getInstance(gamePanel).spawnBlood(worldX, worldY, gamePanel);
            markForRemoval();
            return;
        }
        detectPlayer(p);
        spriteCounter += 7.5;
        if (spriteCounter == 60) {
            spriteNum++;
            if (spriteNum > 3) {
                spriteNum = 0;
            }
            spriteCounter = 0;
        }
        setInMeleeRange(false);
        collide = false;
        Collision.getInstance(gamePanel).checkTile(this);

        collide = false;
        Collision.getInstance(gamePanel).checkTile(this);
        if (!collide) {
            if (gc.getClassText().equals("Archer")){
                strafeOnPlayer(p, gc.getAxis(), new ProjType.arrow());
            } else {
                moveTowardsPlayer(p);
            }
        }
        if (gc.getClassText().equals("Fighter")) {
            attackArea.setBounds(worldX, worldY, 16, 16);
            p.attackArea.setBounds(p.worldX, p.worldY, 16, 16);
            if (attackArea.intersects(p.attackArea)) {
                setInMeleeRange(true);
                p.hit();
            }
        }
    }

    @Override
    public void draw(Graphics2D graphics) {
        BufferedImage image;
        if (isInMeleeRange()) {
            image = switch (direction) {
                case "right", "up" -> enemyAttackRight[spriteNum];
                default -> enemyAttackLeft[spriteNum];
            };
        } else {
            image = switch (direction) {
                case "right", "up" -> enemyRight[spriteNum];
                default -> enemyLeft[spriteNum];
            };
        }
        int ascreenX = worldX - EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX;
        int ascreenY = worldY - EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY;
        if (worldX + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldX - EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
                worldX - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldX + EntityHandler.getInstance(gamePanel).getPlayer().screenX &&
                worldY + gamePanel.tileSize > EntityHandler.getInstance(gamePanel).getPlayer().worldY - EntityHandler.getInstance(gamePanel).getPlayer().screenY &&
                worldY - gamePanel.tileSize < EntityHandler.getInstance(gamePanel).getPlayer().worldY + EntityHandler.getInstance(gamePanel).getPlayer().screenY) {
            if (image != null) {
                graphics.drawImage(image, ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize, null);
            } else {
                graphics.setColor(Color.GREEN);
                graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
            }
        }
    }
}
