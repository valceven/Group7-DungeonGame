package entity;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Projectile extends Entity {
    GamePanel gamePanel;
    public final int screenX;
    public final int screenY;
    String side;

    private BufferedImage[] projUp = new BufferedImage[8];
    private BufferedImage[] projDown = new BufferedImage[8];
    private BufferedImage[] projLeft = new BufferedImage[8];
    private BufferedImage[] projRight = new BufferedImage[8];
    public Projectile(int x, int y, String dir, GamePanel gamePanel, String side) {
        this.gamePanel = gamePanel;
        screenX = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        screenY = gamePanel.screenWidth/2 - (gamePanel.tileSize/2);
        this.side = side;

        worldX = gamePanel.tileSize * x;
        worldY = gamePanel.tileSize * y;
        direction = dir;
        speed = 4;
        try {
            for (int i = 0; i < 8; i++) {
                projLeft[i] = ImageIO.read(getClass().getResourceAsStream("/proj/arrow_left" + i + ".png"));
                projRight[i] = ImageIO.read(getClass().getResourceAsStream("/proj/arrow_right" + i + ".png"));
                projUp[i] = ImageIO.read(getClass().getResourceAsStream("/proj/arrow_up" + i + ".png"));
                projDown[i] = ImageIO.read(getClass().getResourceAsStream("/proj/arrow_down" + i + ".png"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void update(Player p) {

    }
    public void draw(Graphics2D graphics) {
        BufferedImage image = switch (direction) {
            case "left" -> projLeft[spriteNum];
            case "right" -> projRight[spriteNum];
            case "up" -> projUp[spriteNum];
            case "down" -> projDown[spriteNum];
            default -> null;
        };
        try {
            graphics.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        } catch (NullPointerException e) {
            graphics.setColor(Color.BLUE);
            graphics.fillRect(screenX, screenY, gamePanel.tileSize, gamePanel.tileSize);
        }
    }


}
