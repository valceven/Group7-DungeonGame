package main;
import entity.EntityHandler;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    public Collision collision = new Collision(this);
    public objectParent obj[] = new objectParent[10];
    Thread gameThread;
    int FPS = 60;
    TileManager tileM = new TileManager(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public EntityHandler entityHandler = EntityHandler.getInstance(this);
    public UI ui = new UI(this);
    public static BufferedImage error_image;
    public GamePanel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth,screenHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(entityHandler.getKeyH());
        this.setFocusable(true);
        try {
            error_image = ImageIO.read(getClass().getResourceAsStream("/proj/error.png"));
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("missing error.png");
        }
    }
    public void setUpGame(){
        aSetter.setObject();
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
        entityHandler.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D)g;
//        if(gameState == titleState){
//            ui.draw(graphics);
//        }else{
            tileM.draw(graphics);
            for (objects.objectParent objectParent : obj) {
                if (objectParent != null) {
                    objectParent.draw(graphics, this);
                }
            }
            entityHandler.draw(graphics);
            ui.draw(graphics);
            graphics.dispose();
//        }
    }
}
