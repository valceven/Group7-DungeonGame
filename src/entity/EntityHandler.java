package entity;

import main.GamePanel;
import main.KeyHandler;
import objects.OBJ_mage;

import java.awt.*;
import java.util.ArrayList;

public class EntityHandler {
    GamePanel gamePanel;
    private static EntityHandler instance;
    ArrayList<Enemy> enemyList = new ArrayList<>();
    ArrayList<Projectile> projectiles = new ArrayList<>();
    KeyHandler keyH = new KeyHandler();
    Player player;
    private EntityHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        player = new Player(gamePanel, keyH, new PlayerClass.Knight());
  //      enemyList.add(new Gob(15, 15, "down", new gobClass.Fighter(), new gobSize.Normal(), gamePanel));
       enemyList.add(new Gob(25, 20, "down", new gobClass.Fighter(), new gobSize.Mini(), gamePanel));
        enemyList.add(new Gob(20, 15, "down", new gobClass.Archer(), new gobSize.Normal(), gamePanel));
        enemyList.add(new Gob(20, 20, "down", new gobClass.Archer(), new gobSize.Mini(), gamePanel));
        enemyList.add(new SpGob.Imp(16, 20, "down", gamePanel));
        enemyList.add(new SpGob.Mystic(17, 20, "down", gamePanel));
        enemyList.add(new Slime(15, 18, "down", gamePanel));


//        enemyList = new OBJ_mage();
//        gamePanel.obj[0].worldX = mage.worldx * gamePanel.tileSize;
//        gamePanel.obj[0].worldY = 21 * gamePanel.tileSize;
        // add new object to list
        // set worldX of new object to enemy.worldX*tilesize
        // set the same to y

    }
    public static synchronized EntityHandler getInstance(GamePanel gamePanel){
        if (instance == null) {
            instance = new EntityHandler(gamePanel);
        }
        return instance;
    }

    public void update() {
        for (Enemy e: enemyList) {
            e.update(player);
        }
        player.update();

    }

    public void draw(Graphics2D graphics) {
        for (Enemy e: enemyList) {
            e.draw(graphics);
        }
        player.draw(graphics);
    }

    public Player getPlayer() {
        return player;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void spawnProjectile(int x, int y, String dir, GamePanel gamePanel, String side) {
        projectiles.add(new Projectile(x, y, dir, gamePanel, side));
    }

}
