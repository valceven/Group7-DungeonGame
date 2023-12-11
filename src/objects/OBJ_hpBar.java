package objects;
import main.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
public class OBJ_hpBar extends objectParent{
    GamePanel gamePanel;
    Graphics2D graphics;
    public OBJ_hpBar(GamePanel gamePanel){

        this.gamePanel = gamePanel;

        name = "hitpoint";

        try{
            for(int i = 1; i < imageArr.length; i++){
                imageArr[i] = ImageIO.read(getClass().getResourceAsStream("/objectz/hpbars/hp_lvl_"+i+".png"));
            }
        }catch(IOException x){
            x.printStackTrace();
        }

    }


}
