package entity;

import main.Collision;
import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract class SpGob extends Enemy {
    public SpGob(int x, int y, GamePanel gamePanel) {
        super(x, y, gamePanel);
    }

    public static class Imp extends SpGob {
        private final BufferedImage[] enemyLeft = new BufferedImage[4];
        private final BufferedImage[] enemyRight = new BufferedImage[4];
        private final BufferedImage[] enemyALeft = new BufferedImage[4];
        private final BufferedImage[] enemyARight = new BufferedImage[4];

        public Imp(int x, int y, GamePanel gamePanel) {
            super(x, y, gamePanel);
            setAttackSpeed(30);
            speed = 4;
            try {
                for (int i = 0; i < 4; i++) {
                    enemyLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_left" + i + ".png"));
                    enemyRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_right" + i + ".png"));
                    enemyALeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_attack_left" + i + ".png"));
                    enemyARight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_attack_right" + i + ".png"));
                }
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                System.err.println("no imp sprite lol");
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
            if (!collide) {
                moveTowardsPlayer(p);
            }
            attackArea.setBounds(worldX, worldY, 16, 16);
            p.attackArea.setBounds(p.worldX, p.worldY, 16, 16);
            if (attackArea.intersects(p.attackArea)) {
                setInMeleeRange(true);
                p.hit();
            }
        }

        @Override
        public void draw(Graphics2D graphics) {
            BufferedImage image;
            if (isInMeleeRange()) {
                image = switch (direction) {
                    case "right", "up" -> enemyARight[spriteNum];
                    default -> enemyALeft[spriteNum];
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
                    graphics.setColor(Color.RED);
                    graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                }
            }
        }
    }

    public static class Mystic extends SpGob {
        private final BufferedImage[] enemyLeft = new BufferedImage[4];
        private final BufferedImage[] enemyRight = new BufferedImage[4];
        private final BufferedImage[] enemyALeft = new BufferedImage[4];
        private final BufferedImage[] enemyARight = new BufferedImage[4];
        private char axis;
        public Mystic(int x, int y, char ax, GamePanel gamePanel) {
            super(x, y, gamePanel);
            setAttackSpeed(30);
            speed = 2;
            axis = ax;
            try {
                for (int i = 0; i < 4; i++) {
                    enemyLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_left" + i + ".png"));
                    enemyRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_right" + i + ".png"));
                    enemyALeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_attack_left" + i + ".png"));
                    enemyARight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_attack_right" + i + ".png"));
                }
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                System.err.println("no mystic sprite lol");
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
            collide = false;
            Collision.getInstance(gamePanel).checkTile(this);
            if (!collide) {
                strafeOnPlayer(p, axis, new ProjType.orb());
            }
        }

        @Override
        public void draw(Graphics2D graphics) {
            BufferedImage image;
            if (isInMeleeRange()) {
                image = switch (direction) {
                    case "right", "up" -> enemyALeft[spriteNum];
                    default -> enemyALeft[spriteNum];
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
                    graphics.setColor(Color.BLUE);
                    graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                }
            }
        }
    }
}
