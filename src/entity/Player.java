package entity;

import main.GamePanel;
import main.KeyHandler;
import main.TitleScreen;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    private BufferedImage[] attackUp = new  BufferedImage[9];
    private BufferedImage[] attackDown = new  BufferedImage[9];
    private BufferedImage[] attackLeft = new  BufferedImage[9];
    private BufferedImage[] attackRight = new  BufferedImage[9];
    private BufferedImage[] playerLeft = new BufferedImage[9];
    private BufferedImage[] playerRight = new BufferedImage[9];
    private BufferedImage[] playerUp = new BufferedImage[9];
    private BufferedImage[] playerDown = new BufferedImage[9];
    boolean isAttacking = false;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    private final PlayerClass playerClass; // You can use this to represent the player's class


    public Player(GamePanel gamePanel, KeyHandler keyH, PlayerClass playerClass) {
        this.gamePanel = gamePanel;
        this.keyH = keyH;
        this.playerClass = playerClass;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
    }
    public void setDefaultValues(){
        worldX = gamePanel.tileSize * 16;
        worldY = gamePanel.tileSize * 18;
        speed = 4;
        direction = "down";
        maxHp = 11;
        hp = 10;
    }
    public void getPlayerImage() {
        try {
            for (int i = 1; i <= 8; i++) {
                playerLeft[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_left" + i + ".png"));
                playerRight[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_right" + i + ".png"));
                playerUp[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_up" + i + ".png"));
                playerDown[i - 1] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_down" + i + ".png"));
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void getPlayerAttackImage() {
        try {
            int scaledWidth = gamePanel.tileSize * 3;
            int scaledHeight = gamePanel.tileSize * 3;

            for (int i = 1; i <= 8; i++) {
                String imagePathLeft = "/attack/attack_" + playerClass.attack() + "_left" + i + ".png";
                attackLeft[i] = ImageIO.read(getClass().getResourceAsStream(imagePathLeft));
                attackLeft[i] = scaleImage(attackLeft[i], scaledWidth, scaledHeight, imagePathLeft);

                String imagePathRight = "/attack/attack_" + playerClass.attack() + "_right" + i + ".png";
                attackRight[i] = ImageIO.read(getClass().getResourceAsStream(imagePathRight));
                attackRight[i] = scaleImage(attackRight[i], scaledWidth, scaledHeight, imagePathRight);

                String imagePathUp = "/attack/attack_" + playerClass.attack() + "_up" + i + ".png";
                attackUp[i] = ImageIO.read(getClass().getResourceAsStream(imagePathUp));
                attackUp[i] = scaleImage(attackUp[i], scaledWidth, scaledHeight, imagePathUp);

                String imagePathDown = "/attack/attack_" + playerClass.attack() + "_down" + i + ".png";
                attackDown[i] = ImageIO.read(getClass().getResourceAsStream(imagePathDown));
                attackDown[i] = scaleImage(attackDown[i], scaledWidth, scaledHeight, imagePathDown);
            }
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    private BufferedImage scaleImage(BufferedImage originalImage, int scaledWidth, int scaledHeight, String imagePath) {
        try {
            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
            Graphics2D graphics = scaledImage.createGraphics();
            graphics.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
            return scaledImage;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error scaling image: " + imagePath);
            return null;
        }
    }

    public void update() {
        if(keyH.space){
            isAttacking = true;
            System.out.println("Attacking");
            attack();
        }else{
            isAttacking = false;
        }

        if(keyH.up || keyH.down || keyH.left || keyH.right) {
            if (keyH.up) {
                direction = "up";
            } else if (keyH.down) {
                direction = "down";
            } else if (keyH.left) {
                direction = "left";
            } else {
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
         //   gamePanel.collision.checkEnemy(this, gamePanel.entityHandler.enemyList);
     //       int monsterIndex = gamePanel.collision.checkEntity(this, gamePanel.monster);
            if (!collide && !isAttacking) {
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
    public void attack() {

        spriteCounter += 7.5;

        if (spriteCounter >= 35) {
            attackSpriteNum++;
            spriteCounter = 0;

            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction){
                case "up" : worldY -= attackArea.height; break;
                case "down" : worldY += attackArea.height; break;
                case "left" : worldX -= attackArea.width; break;
                case "right" : worldX += attackArea.width; break;
            }
            solidAreaWidth = attackArea.width;
            solidAreaHeight = attackArea.height;

            int monsterIndex = gamePanel.collision.checkEntity(this,gamePanel.entityHandler.enemyList);

        }
        if(attackSpriteNum > 8){
            isAttacking = false;
            attackSpriteNum = 1;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (isAttacking) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    image = attackUp[attackSpriteNum];
                } else {
                    image = playerUp[spriteNum];
                }
                break;
            case "left":
                if (isAttacking) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    image = attackLeft[attackSpriteNum];
                } else {
                    image = playerLeft[spriteNum];
                }
                break;
            case "right":
                if (isAttacking) {
                    image = attackRight[attackSpriteNum];
                } else {
                    image = playerRight[spriteNum];
                }
                break;
            case "down":
                if (isAttacking) {
                    image = attackDown[attackSpriteNum];
                } else {
                    image = playerDown[spriteNum];
                }
                break;
        }

        if (image != null) {
            int drawWidth = gamePanel.tileSize;
            int drawHeight = gamePanel.tileSize;

            if (isAttacking && (direction.equals("up") || direction.equals("down"))) {
                drawHeight *= 2;
            } else if (isAttacking && (direction.equals("left") || direction.equals("right"))) {
                drawWidth *= 2;
            }
            graphics.drawImage(image, tempScreenX, tempScreenY, drawWidth, drawHeight, null);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }
}