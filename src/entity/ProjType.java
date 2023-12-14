package entity;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface ProjType{

    String getProjName();
    int frames();
    int timer();
    class arrow implements ProjType {
        @Override
        public String getProjName() {
            return "blood";
        }

        @Override
        public int frames() {
            return 1;
        }


        @Override
        public int timer() {
            return 50;
        }
    }
    class orb implements ProjType {
        @Override
        public String getProjName() {
            return "orb";
        }

        @Override
        public int frames() {
            return 1;
        }


        @Override
        public int timer() {
            return 50;
        }
    }
    class blood implements ProjType {
        @Override
        public String getProjName() {
            return "blood";
        }

        @Override
        public int frames() {
            return 4;
        }

        @Override
        public int timer() {
            return 16;
        }
    }
}
