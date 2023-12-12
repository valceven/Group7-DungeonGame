package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

abstract class SpGob extends Enemy {
    public SpGob(int x, int y, String dir, GamePanel gamePanel) {
        super(x, y, dir, gamePanel);
    }

    public static class Imp extends SpGob {
        private final BufferedImage[] enemyUp = new BufferedImage[8];
        private final BufferedImage[] enemyDown = new BufferedImage[8];
        private final BufferedImage[] enemyLeft = new BufferedImage[8];
        private final BufferedImage[] enemyRight = new BufferedImage[8];

        public Imp(int x, int y, String dir, GamePanel gamePanel) {
            super(x, y, dir, gamePanel);
            try {
                for (int i = 0; i < 8; i++) {
                    enemyLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_left" + i + ".png"));
                    enemyRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_right" + i + ".png"));
                    enemyUp[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_up" + i + ".png"));
                    enemyDown[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/imp_down" + i + ".png"));
                }
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                System.err.println("no imp sprite lol");
            }
        }

        @Override
        public void update(Player p) {
            detectPlayer(p);

            moveTowardsPlayer(p);
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
                    graphics.setColor(Color.RED);
                    graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                }
            }
        }
    }

    public static class Mystic extends SpGob {
        private final BufferedImage[] enemyUp = new BufferedImage[8];
        private final BufferedImage[] enemyDown = new BufferedImage[8];
        private final BufferedImage[] enemyLeft = new BufferedImage[8];
        private final BufferedImage[] enemyRight = new BufferedImage[8];

        public Mystic(int x, int y, String dir, GamePanel gamePanel) {
            super(x, y, dir, gamePanel);
            try {
                for (int i = 0; i < 8; i++) {
                    enemyLeft[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_left" + i + ".png"));
                    enemyRight[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_right" + i + ".png"));
                    enemyUp[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_up" + i + ".png"));
                    enemyDown[i] = ImageIO.read(getClass().getResourceAsStream("/enemy/mystic_down" + i + ".png"));
                }
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                System.err.println("no mystic sprite lol");
            }
        }

        @Override
        public void update(Player p) {
            detectPlayer(p);
            moveTowardsPlayer(p);
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
                    graphics.setColor(Color.BLUE);
                    graphics.fillRect(ascreenX, ascreenY, gamePanel.tileSize, gamePanel.tileSize);
                }
            }
        }
    }
}
