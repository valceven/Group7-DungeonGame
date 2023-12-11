package main;
import objects.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {

    Font monospaced_40;
    GamePanel gamePanel;
    Graphics2D graphics;
    BufferedImage hpImage[] = new BufferedImage[12];

    public UI(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        monospaced_40 = new Font("Monospaced", Font.PLAIN, 50);

        objectParent heart = new OBJ_hpBar(gamePanel);

        for(int i = 1; i < hpImage.length; i++) {
            hpImage[i] = heart.imageArr[i];

        }
    }
    public void drawPlayerHp(){

        int x = gamePanel.tileSize/2;
        int y = gamePanel.tileSize/2;
        int i = 0;

        graphics.drawImage(hpImage[11],x,y,gamePanel.tileSize*9,(int)(gamePanel.tileSize*1.75),null);

        i = gamePanel.entityHandler.getPlayer().hp;

        graphics.drawImage(hpImage[i],x,y,gamePanel.tileSize*9,(int)(gamePanel.tileSize*1.75),null);
    }
    public void draw(Graphics2D graphics){

        this.graphics = graphics;

        graphics.setFont(monospaced_40);
        graphics.setColor(Color.white);
        drawPlayerHp();
    }

}
