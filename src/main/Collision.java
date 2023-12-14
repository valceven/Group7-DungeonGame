package main;
import entity.Entity;

public class Collision {
    GamePanel gamePanel;
    private static Collision instance;

    private Collision(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
    public static synchronized Collision getInstance(GamePanel gamePanel) {
        if (instance == null) {
            instance = new Collision(gamePanel);
        }
        return instance;
    }

    public void checkTile(Entity entity) {
        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height + 1;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int t1, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13, t14, t15, t16, t17, t18, t19, t20, t21, t22, t23, t42, t46, t47, t50, t51, t53, t54;

        switch (entity.direction) {
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed) / gamePanel.tileSize;
                t1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t4 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t5 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t6 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t7 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t8 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t9 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t10 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t11 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t12 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t13 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t14 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t15 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t16 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t17 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t18 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t19 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t20 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t21 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t22 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t23 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t42 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t46 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t47 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t50 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t51 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t53 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t54 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                if (gamePanel.tileM.tile[t1].collision || gamePanel.tileM.tile[t4].collision ||
                        gamePanel.tileM.tile[t5].collision || gamePanel.tileM.tile[t6].collision ||
                        gamePanel.tileM.tile[t7].collision || gamePanel.tileM.tile[t8].collision ||
                        gamePanel.tileM.tile[t9].collision || gamePanel.tileM.tile[t10].collision ||
                        gamePanel.tileM.tile[t11].collision || gamePanel.tileM.tile[t12].collision ||
                        gamePanel.tileM.tile[t13].collision || gamePanel.tileM.tile[t14].collision ||
                        gamePanel.tileM.tile[t15].collision || gamePanel.tileM.tile[t16].collision ||
                        gamePanel.tileM.tile[t17].collision || gamePanel.tileM.tile[t18].collision ||
                        gamePanel.tileM.tile[t19].collision || gamePanel.tileM.tile[t20].collision ||
                        gamePanel.tileM.tile[t21].collision || gamePanel.tileM.tile[t22].collision ||
                        gamePanel.tileM.tile[t23].collision || gamePanel.tileM.tile[t42].collision ||
                        gamePanel.tileM.tile[t46].collision || gamePanel.tileM.tile[t47].collision ||
                        gamePanel.tileM.tile[t50].collision || gamePanel.tileM.tile[t51].collision ||
                        gamePanel.tileM.tile[t53].collision || gamePanel.tileM.tile[t54].collision) {
                    entity.collide = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY - entity.speed) / gamePanel.tileSize;
                t1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t4 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t5 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t6 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t7 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t8 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t9 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t10 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t11 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t12 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t13 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t14 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t15 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t16 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t17 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t18 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t19 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t20 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t21 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t22 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t23 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t42 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t46 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t47 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t50 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t51 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t53 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t54 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileM.tile[t1].collision || gamePanel.tileM.tile[t4].collision ||
                        gamePanel.tileM.tile[t5].collision || gamePanel.tileM.tile[t6].collision ||
                        gamePanel.tileM.tile[t7].collision || gamePanel.tileM.tile[t8].collision ||
                        gamePanel.tileM.tile[t9].collision || gamePanel.tileM.tile[t10].collision ||
                        gamePanel.tileM.tile[t11].collision || gamePanel.tileM.tile[t12].collision ||
                        gamePanel.tileM.tile[t13].collision || gamePanel.tileM.tile[t14].collision ||
                        gamePanel.tileM.tile[t15].collision || gamePanel.tileM.tile[t16].collision ||
                        gamePanel.tileM.tile[t17].collision || gamePanel.tileM.tile[t18].collision ||
                        gamePanel.tileM.tile[t19].collision || gamePanel.tileM.tile[t20].collision ||
                        gamePanel.tileM.tile[t21].collision || gamePanel.tileM.tile[t22].collision ||
                        gamePanel.tileM.tile[t23].collision || gamePanel.tileM.tile[t42].collision ||
                        gamePanel.tileM.tile[t46].collision || gamePanel.tileM.tile[t47].collision ||
                        gamePanel.tileM.tile[t50].collision || gamePanel.tileM.tile[t51].collision ||
                        gamePanel.tileM.tile[t53].collision || gamePanel.tileM.tile[t54].collision) {
                    entity.collide = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed) / gamePanel.tileSize;
                t1 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t4 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t5 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t6 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t7 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t8 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t9 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t10 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t11 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t12 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t13 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t14 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t15 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t16 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t17 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t18 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t19 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t20 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t21 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t22 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t23 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t42 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t46 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t47 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t50 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                t51 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t53 = gamePanel.tileM.mapTileNum[entityLeftCol][entityTopRow];
                t54 = gamePanel.tileM.mapTileNum[entityLeftCol][entityBottomRow];
                if (gamePanel.tileM.tile[t1].collision || gamePanel.tileM.tile[t4].collision ||
                        gamePanel.tileM.tile[t5].collision || gamePanel.tileM.tile[t6].collision ||
                        gamePanel.tileM.tile[t7].collision || gamePanel.tileM.tile[t8].collision ||
                        gamePanel.tileM.tile[t9].collision || gamePanel.tileM.tile[t10].collision ||
                        gamePanel.tileM.tile[t11].collision || gamePanel.tileM.tile[t12].collision ||
                        gamePanel.tileM.tile[t13].collision || gamePanel.tileM.tile[t14].collision ||
                        gamePanel.tileM.tile[t15].collision || gamePanel.tileM.tile[t16].collision ||
                        gamePanel.tileM.tile[t17].collision || gamePanel.tileM.tile[t18].collision ||
                        gamePanel.tileM.tile[t19].collision || gamePanel.tileM.tile[t20].collision ||
                        gamePanel.tileM.tile[t21].collision || gamePanel.tileM.tile[t22].collision ||
                        gamePanel.tileM.tile[t23].collision || gamePanel.tileM.tile[t42].collision ||
                        gamePanel.tileM.tile[t46].collision || gamePanel.tileM.tile[t47].collision ||
                        gamePanel.tileM.tile[t50].collision || gamePanel.tileM.tile[t51].collision ||
                        gamePanel.tileM.tile[t53].collision || gamePanel.tileM.tile[t54].collision) {
                    entity.collide = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed) / gamePanel.tileSize;
                t1 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t4 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t5 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t6 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t7 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t8 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t9 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t10 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t11 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t12 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t13 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t14 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t15 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t16 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t17 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t18 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t19 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t20 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t21 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t22 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t23 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t42 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t46 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t47 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t50 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                t51 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t53 = gamePanel.tileM.mapTileNum[entityRightCol][entityTopRow];
                t54 = gamePanel.tileM.mapTileNum[entityRightCol][entityBottomRow];
                if (gamePanel.tileM.tile[t1].collision || gamePanel.tileM.tile[t4].collision ||
                        gamePanel.tileM.tile[t5].collision || gamePanel.tileM.tile[t6].collision ||
                        gamePanel.tileM.tile[t7].collision || gamePanel.tileM.tile[t8].collision ||
                        gamePanel.tileM.tile[t9].collision || gamePanel.tileM.tile[t10].collision ||
                        gamePanel.tileM.tile[t11].collision || gamePanel.tileM.tile[t12].collision ||
                        gamePanel.tileM.tile[t13].collision || gamePanel.tileM.tile[t14].collision ||
                        gamePanel.tileM.tile[t15].collision || gamePanel.tileM.tile[t16].collision ||
                        gamePanel.tileM.tile[t17].collision || gamePanel.tileM.tile[t18].collision ||
                        gamePanel.tileM.tile[t19].collision || gamePanel.tileM.tile[t20].collision ||
                        gamePanel.tileM.tile[t21].collision || gamePanel.tileM.tile[t22].collision ||
                        gamePanel.tileM.tile[t23].collision || gamePanel.tileM.tile[t42].collision ||
                        gamePanel.tileM.tile[t46].collision || gamePanel.tileM.tile[t47].collision ||
                        gamePanel.tileM.tile[t50].collision || gamePanel.tileM.tile[t51].collision ||
                        gamePanel.tileM.tile[t53].collision || gamePanel.tileM.tile[t54].collision) {
                    entity.collide = true;
                }
                break;
        }
    }

//    public int checkEntity(Player p, ArrayList<Enemy> target) {
//        int index = 999;
//
//        p.solidArea.x = p.worldX + p.solidArea.x;
//        p.solidArea.y = p.worldY + p.solidArea.y;
//
//        for (int i = 0; i < target.size(); i++) {
//            Enemy enemy = target.get(i);
//            if (enemy != null) {
//                enemy.solidArea.x = enemy.worldX + enemy.solidArea.x;
//                enemy.solidArea.y = enemy.worldY + enemy.solidArea.y;
//
//                switch (p.direction) {
//                    case "up":
//                        p.solidArea.y -= p.speed;
//                        break;
//                    case "down":
//                        p.solidArea.y += p.speed;
//                        break;
//                    case "left":
//                        p.solidArea.x -= p.speed;
//                        break;
//                    case "right":
//                        p.solidArea.x += p.speed;
//                        break;
//                }
//
//                if (p.solidArea.intersects(enemy.solidArea)) {
//                    p.collide = true;
//                    index = i;
//                }
//            }
//        }
//
//        return index;
//    }
}

