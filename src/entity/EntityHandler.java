package entity;

import main.GamePanel;
import main.KeyHandler;
import main.TitleScreen;
import objects.OBJ_mage;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

public class EntityHandler {
    GamePanel gamePanel;
    private static EntityHandler instance;
    private ArrayList<Enemy> enemyList = new ArrayList<>();
    private ArrayList<Projectile> projectiles = new ArrayList<>();
    KeyHandler keyH = new KeyHandler();
    Player player;
    public static BufferedImage error_image;
    private EntityHandler(GamePanel gp) {
        this.gamePanel = gp;
        try {
            error_image = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/proj/error.png")));
        } catch (IOException e) {
            System.err.println("Missing error image");
        }
        if (TitleScreen.characterType.equals("sword")) {
            player = new Player(keyH, new PlayerClass.Knight(), gamePanel);
        } else if (TitleScreen.characterType.equals("staff")) {
            player = new Player(keyH, new PlayerClass.Mage(), gamePanel);
        } else if (TitleScreen.characterType.equals("bow")) {
            player = new Player(keyH, new PlayerClass.Archer(), gamePanel);
        }
        spawn();
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
        for (Projectile p: projectiles) {
            p.update(player);
        }
        player.update();
        removeMarked();
    }

    public void draw(Graphics2D graphics) {
        for (Enemy e: enemyList) {
            e.draw(graphics);
        }
//        for (Projectile p: projectiles) {
//            p.draw(graphics);
//        }
        player.draw(graphics);
    }

    public Player getPlayer() {
        return player;
    }

    public KeyHandler getKeyH() {
        return keyH;
    }

    public void spawnProjectile(int x, int y, ProjType pt, String dir, String side, GamePanel gp) {
        projectiles.add(new Projectile(x, y, pt, dir, side, gp));
    }
    public void spawnBlood(int x, int y, GamePanel gp) {
        projectiles.add(new Projectile(x, y, new ProjType.blood(), "none", "neutral", gp));
    }

    public void removeMarked() {
        enemyList.removeIf(Enemy::isMarkedForRemoval);
        projectiles.removeIf(Projectile::isMarkedForRemoval);
        spawnBlood(48, 80, gamePanel);
    }

    public void killAll() {
        for (Enemy e: enemyList) {
            e.markForRemoval();
        }
//        for (Projectile p: projectiles) {
//            p.markForRemoval();
//        }
    }

    public ArrayList<Enemy> getEnemyList() {
        return enemyList;
    }

    public void spawn() {

        player = new Player(keyH, new PlayerClass.Knight(), gamePanel);
        spawner(48, 80, "mf");
        spawner(59,52, "may");
        spawner(62,54, "nay");
        spawner(76,56, "nf");
        spawner(83,51, "nax");
        spawner(88,48, "nay");
        spawner(88,47, "max");
        spawner(89,47, "mf");
        spawner(85,53, "nf");
        spawner(88,38, "may");
        spawner(77, 33, "nf");
        spawner(83, 30, "nf");
        spawner(67, 37, "myx");
        spawner(67, 19, "myx");
        spawner(55, 35, "myx");
        spawner(55, 19, "myx");
        spawner(57, 39, "im");
        spawner(20, 27, "boss");
    }

    void spawner(int x, int y, String type) {
        switch (type) {
            case "max":
                enemyList.add(new Gob(x, y, new gobClass.Archer('x'), new gobSize.Mini(), gamePanel));
                return;
            case "may":
                enemyList.add(new Gob(x, y, new gobClass.Archer('y'), new gobSize.Mini(), gamePanel));
                return;
            case "nax":
                enemyList.add(new Gob(x, y, new gobClass.Archer('x'), new gobSize.Normal(), gamePanel));
                return;
            case "nay":
                enemyList.add(new Gob(x, y, new gobClass.Archer('y'), new gobSize.Normal(), gamePanel));
                return;
            case "mf":
                enemyList.add(new Gob(x, y, new gobClass.Fighter(), new gobSize.Normal(), gamePanel));
                return;
            case "nf":
                enemyList.add(new Gob(x, y, new gobClass.Fighter(), new gobSize.Mini(), gamePanel));
                return;
            case "myx":
                enemyList.add(new SpGob.Mystic(x, y,  'x', gamePanel));
                return;
            case "myy":
                enemyList.add(new SpGob.Mystic(x, y,  'y', gamePanel));
                return;
            case "im":
                enemyList.add(new SpGob.Imp(x, y, gamePanel));
                return;
            case "boss":
                enemyList.add(new Slime(x, y, gamePanel));
                return;
        }
    }
}
