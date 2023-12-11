package objects;

import javax.imageio.ImageIO;
import java.io.IOException;

public class OBJ_mage extends objectParent{

    public OBJ_mage(){

        name = "Mage";

        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objectz/mage-left.png"));
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
