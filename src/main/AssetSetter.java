package main;

import objects.*;

public class AssetSetter {

    GamePanel gamePanel;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
    }

    public void setObject(){
        gamePanel.obj[0] = new OBJ_mage();
        gamePanel.obj[0].worldX = 15 * gamePanel.tileSize;
        gamePanel.obj[0].worldY = 21 * gamePanel.tileSize;
    }
}
