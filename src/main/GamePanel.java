package main;
import entity.EntityHandler;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

import objects.*;
import tile.*;
public class GamePanel extends JPanel implements Runnable{
    final int originalTileSize = 16; // 16x16 tile
    final int scaleValue = 3;

    public int tileSize = originalTileSize * scaleValue; // 48x48 tile
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; //576 px
    public final int maxWorldColumn = 100;
    public final int maxWorldRow = 100;
    public objectParent[] obj = new objectParent[10];
    Thread gameThread;
    int FPS = 60;
    int lvlState = 0;
    TileManager tileM = new TileManager(this);
//    public AssetSetter aSetter = new AssetSetter(this);
    BufferedImage win;
    BufferedImage lose;
    public UI ui = new UI(this);
    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(EntityHandler.getInstance(this).getKeyH());
        this.setFocusable(true);
        try {
            win = ImageIO.read(getClass().getResourceAsStream("/objectz/winners_only.png"));
        } catch (IOException e) {
            System.err.println("win screen not found");
        }
        try {
            lose = ImageIO.read(getClass().getResourceAsStream("/objectz/gameover.png"));
        } catch (IOException e) {
            System.err.println("game over not found");
        }
    }
    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }
    public void run(){
        double drawInterval = (double)1000000000/FPS;
        double drawTimeInterval = System.nanoTime() + drawInterval;

        while(gameThread != null){
            update();
            repaint();
            try {
                double timeRem = drawTimeInterval - System.nanoTime();
                timeRem /= 1000000;
                if(timeRem < 0){
                    timeRem = 0;
                }
                Thread.sleep((long)timeRem);
                drawTimeInterval += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void update(){
         EntityHandler.getInstance(this).update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
//        if(gameState == titleState){
//            ui.draw(graphics);
//        }else{
        if (lvlState == 0) {
            tileM.draw(graphics);
            for (objects.objectParent objectParent : obj) {
                if (objectParent != null) {
                    objectParent.draw(graphics, this);
                }
            }
            EntityHandler.getInstance(this).draw(graphics);
            ui.draw(graphics);
            graphics.dispose();
        } else if (lvlState == 1) {
            graphics.drawImage(win, 0, 0, 768, 576, null);
        } else if (lvlState == -1) {
            graphics.drawImage(lose, 0, 0, 768, 576, null);
        }
//        }
    }
    public void win() {System.out.println("You Win! :)");
        lvlState = 1;
        EntityHandler.getInstance(this).killAll();
    }
    public void lose() {
        System.out.println("Game Over!");
        lvlState = -1;
        EntityHandler.getInstance(this).killAll();
    }

}
