package main;

import entity.Enemy;
import entity.EntityHandler;
import objects.*;

public class AssetSetter {

    GamePanel gamePanel;
    EntityHandler entities;

    public AssetSetter(GamePanel gamePanel){
        this.gamePanel = gamePanel;
        entities = EntityHandler.getInstance(gamePanel);
    }

    public void setObject(){

    }


}
