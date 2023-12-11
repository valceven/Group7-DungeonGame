package entity;

import main.GamePanel;
import main.KeyHandler;

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
        player = new Player(gamePanel, keyH);
//        enemyList.add(new Gob(15, 15, "down", new gobClass.Fighter(), new gobSize.Normal(), gamePanel));
//        enemyList.add(new Gob(15, 20, "down", new gobClass.Fighter(), new gobSize.Mini(), gamePanel));
//        enemyList.add(new Gob(20, 15, "down", new gobClass.Archer(), new gobSize.Normal(), gamePanel));
//        enemyList.add(new Gob(20, 20, "down", new gobClass.Archer(), new gobSize.Mini(), gamePanel));
//        enemyList.add(new SpGob.Imp(10, 20, "down", gamePanel));
//        enemyList.add(new SpGob.Mystic(10, 20, "down", gamePanel));
        enemyList.add(new Slime(15, 15, "down", gamePanel));

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
        player.draw(graphics);
        for (Enemy e: enemyList) {
            e.draw(graphics);
        }

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
