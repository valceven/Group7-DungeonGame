package entity;

import main.GamePanel;
import main.KeyHandler;
import main.TitleScreen;
import objects.OBJ_mage;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class EntityHandler {
    GamePanel gamePanel;
    private static EntityHandler instance;
    ArrayList<Enemy> enemyList = new ArrayList<>();
    ArrayList<Projectile> projectiles = new ArrayList<>();
    KeyHandler keyH = new KeyHandler();
    Player player;
    private EntityHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        if (TitleScreen.characterType == "sword") {
            player = new Player(gamePanel, keyH, new PlayerClass.Knight());
        } else if (TitleScreen.characterType == "staff") {
            player = new Player(gamePanel, keyH, new PlayerClass.Mage());
        } else if (TitleScreen.characterType == "bow") {
            player = new Player(gamePanel, keyH, new PlayerClass.Archer());
        }
        player = new Player(gamePanel, keyH, new PlayerClass.Knight());
        enemyList.add(new Gob(15, 15, "down", new gobClass.Fighter(), new gobSize.Normal(), gamePanel));
        enemyList.add(new Gob(25, 20, "down", new gobClass.Fighter(), new gobSize.Mini(), gamePanel));
        enemyList.add(new Gob(20, 15, "left", new gobClass.Archer(), new gobSize.Normal(), gamePanel));
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
        for (Projectile p: projectiles) {
            p.update(player);
        }
        for (Enemy e: enemyList) {
            e.update(player);
        }
        player.update();
        removeMarkedEnemies();
    }

    public void draw(Graphics2D graphics) {
        for (Projectile p: projectiles) {
            p.draw(graphics);
        }
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

    public void spawnProjectile(int x, int y, ProjType pt, String dir, GamePanel gamePanel, String side) {
        projectiles.add(new Projectile(x, y, pt, dir, gamePanel, side));
    }
    public void spawnBlood(int x, int y, ProjType pt, GamePanel gamePanel) {
        projectiles.add(new Projectile(x, y, pt, "down", gamePanel, "neutral"));
    }

    public void removeMarkedEnemies() {
        Iterator<Enemy> iterator = enemyList.iterator();
        while (iterator.hasNext()) {
            Enemy enemy = iterator.next();
            if (enemy.isMarkedForRemoval()) {
                iterator.remove();
            }
        }
    }


}
