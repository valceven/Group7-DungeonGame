package entity;

import main.Collision;
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
    private BufferedImage[] attackUp = new  BufferedImage[8];
    private BufferedImage[] attackDown = new  BufferedImage[8];
    private BufferedImage[] attackLeft = new  BufferedImage[8];
    private BufferedImage[] attackRight = new  BufferedImage[8];
    private BufferedImage[] playerLeft = new BufferedImage[8];
    private BufferedImage[] playerRight = new BufferedImage[8];
    private BufferedImage[] playerUp = new BufferedImage[8];
    private BufferedImage[] playerDown = new BufferedImage[8];
    int isAttacking = 0;
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    private final PlayerClass playerClass; // You can use this to represent the player's class
    private int hitInvulnTime = 0;
    private int cooldown = 0;


    public Player(KeyHandler keyH, PlayerClass playerClass, GamePanel gp) {
        this.keyH = keyH;
        this.gamePanel = gp;
        this.playerClass = playerClass;
        screenX = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);
        screenY = gamePanel.screenWidth / 2 - (gamePanel.tileSize / 2);

        attackArea.width = 36;
        attackArea.height = 36;

        //setDefaultValues();
        worldX = gamePanel.tileSize * 48;
        worldY = gamePanel.tileSize * 90;
//        worldX = gamePanel.tileSize * 20;
//        worldY = gamePanel.tileSize * 27;
        speed = 4;
        direction = "down";
        maxHp = 11;
        hp = 10;
        //getPlayerImage();

        try {
            for (int i = 0; i < 8; i++) {
                playerLeft[i ] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_left" + i + ".png"));
                playerRight[i ] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_right" + i + ".png"));
                playerUp[i] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_up" + i + ".png"));
                playerDown[i] = ImageIO.read(getClass().getResourceAsStream("/player/" + playerClass.getName() + "_down" + i + ".png"));
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("incomplete player sprites");
        }
        ///getPlayerAttackImage();
        try {
            int scaledWidth = gamePanel.tileSize * 3;
            int scaledHeight = gamePanel.tileSize * 3;

            for (int i = 0; i < 8; i++) {
                String imagePathLeft = "/attack/attack_" + playerClass.getName() + "_left" + i + ".png";
                attackLeft[i] = ImageIO.read(getClass().getResourceAsStream(imagePathLeft));
                attackLeft[i] = scaleImage(attackLeft[i], scaledWidth, scaledHeight, imagePathLeft);

                String imagePathRight = "/attack/attack_" + playerClass.getName() + "_right" + i + ".png";
                attackRight[i] = ImageIO.read(getClass().getResourceAsStream(imagePathRight));
                attackRight[i] = scaleImage(attackRight[i], scaledWidth, scaledHeight, imagePathRight);

                String imagePathUp = "/attack/attack_" + playerClass.getName() + "_up" + i + ".png";
                attackUp[i] = ImageIO.read(getClass().getResourceAsStream(imagePathUp));
                attackUp[i] = scaleImage(attackUp[i], scaledWidth, scaledHeight, imagePathUp);

                String imagePathDown = "/attack/attack_" + playerClass.getName() + "_down" + i + ".png";
                attackDown[i] = ImageIO.read(getClass().getResourceAsStream(imagePathDown));
                attackDown[i] = scaleImage(attackDown[i], scaledWidth, scaledHeight, imagePathDown);
            }
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("Incomplete player attack sprites");
        }
    }
//    public void setDefaultValues(){
//    }
//    public void getPlayerImage() {
//    }
//    public void getPlayerAttackImage() {
//    }

    private BufferedImage scaleImage(BufferedImage originalImage, int scaledWidth, int scaledHeight, String imagePath) {
        try {
            BufferedImage scaledImage = new BufferedImage(scaledWidth, scaledHeight, originalImage.getType());
            Graphics2D graphics = scaledImage.createGraphics();
            graphics.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
            return scaledImage;
        } catch (Exception e) {
            System.err.println("Error scaling image: " + imagePath);
            return null;
        }
    }

    public void update() {
        if (hp <= 0) {
            gamePanel.lose();
        }
        if(keyH.space && cooldown <= 0) {
            isAttacking = 5;
            cooldown = 10;
            //EntityHandler.getInstance().killAll();
        }
        if (isAttacking > 0) {
            attack();
        }
        isAttacking--;
        cooldown--;
        hitInvulnTime--;

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
                if (spriteNum > 5) {
                    spriteNum = 0;
                }
                spriteCounter = 0;
            }
            collide = false;
            Collision.getInstance(gamePanel).checkTile(this);
//            Collision.getInstance.checkEntity(this, EntityHandler.getInstance().getEnemyList());
//            int monsterIndex = Collision.getInstance().checkEntity(this, GamePanel.getInstance().monster);
            if (!collide && isAttacking <= 0) {
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

//            int currentWorldX = worldX;
//            int currentWorldY = worldY;
//            int solidAreaWidth = solidArea.width;
//            int solidAreaHeight = solidArea.height;


            collide = false;
            Collision.getInstance(gamePanel).checkTile(this);
            if (!collide) {
                switch (direction) {
                    case "up":
                        worldY -= attackArea.height;
                        break;
                    case "down":
                        worldY += attackArea.height;
                        break;
                    case "left":
                        worldX -= attackArea.width;
                        break;
                    case "right":
                        worldX += attackArea.width;
                        break;
                }
            }
//            solidAreaWidth = attackArea.width;
//            solidAreaHeight = attackArea.height;

            attackArea.setBounds(worldX, worldY, 32, 32);
            for (Enemy e: EntityHandler.getInstance(gamePanel).getEnemyList()) {
                e.attackArea.setBounds(e.worldX,e.worldY, 20, 20);
                if (attackArea.intersects(e.attackArea)) {
                    e.hp-=1;
                }
            }

            //int monsterIndex = Collision.getInstance().checkEntity(this, EntityHandler.getInstance(gamePanel).getEnemyList());

        }
        if(attackSpriteNum > 5){
            attackSpriteNum = 0;
            spriteCounter = 0;
        }
    }
    public void draw(Graphics2D graphics) {
        BufferedImage image = null;
        int tempScreenX = screenX;
        int tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (isAttacking > 0) {
                    tempScreenY = screenY - gamePanel.tileSize;
                    image = attackUp[attackSpriteNum];
                } else {
                    image = playerUp[spriteNum];
                }
                break;
            case "left":
                if (isAttacking > 0) {
                    tempScreenX = screenX - gamePanel.tileSize;
                    image = attackLeft[attackSpriteNum];
                } else {
                    image = playerLeft[spriteNum];
                }
                break;
            case "right":
                if (isAttacking > 0) {
                    image = attackRight[attackSpriteNum];
                } else {
                    image = playerRight[spriteNum];
                }
                break;
            case "down":
                if (isAttacking > 0) {
                    image = attackDown[attackSpriteNum];
                } else {
                    image = playerDown[spriteNum];
                }
                break;
        }

        if (image != null) {
            int drawWidth = gamePanel.tileSize;
            int drawHeight = gamePanel.tileSize;

            if (isAttacking > 0 && (direction.equals("up") || direction.equals("down"))) {
                drawHeight *= 2;
            } else if (isAttacking > 0 && (direction.equals("left") || direction.equals("right"))) {
                drawWidth *= 2;
            }
            graphics.drawImage(image, tempScreenX, tempScreenY, drawWidth, drawHeight, null);
        } else {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }
    public void hit() {
        if (hitInvulnTime <= 0) {
            hitInvulnTime = 50;
            System.out.println("OW");
            if (hp != 0) {
                hp--;
            }
        }

    }
}