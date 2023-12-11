//import java.awt.*;
//import java.awt.event.KeyEvent;
//import java.awt.event.MouseEvent;
//import java.awt.image.BufferedImage;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Objects;
//public class Ui {
//    main.GamePanel gp;
//    Graphics g2;
//    private MainMenu[] buttons = new MainMenu[3];
//    public Ui(main.GamePanel gp){
//        this.gp = gp;
//        buttons[0] = new MainMenu(gp.screenWidth/2, 150* gp.scaleValue,0, gp);
//        buttons[1] = new MainMenu(gp.screenWidth/2, (int)(220* gp.scaleValue),1, gp);
//        buttons[2] = new MainMenu(gp.screenWidth/2, (int)(290* gp.scaleValue),2, gp);
//    }
//    public void draw(Graphics g2){
//        this.g2 = g2;
//
//        g2.setColor(Color.white);
//
//        if(gp.gameState == gp.titleState){
//            drawMenuScreen();
//        }
//    }
//    private void drawMenuScreen() {
//        buttons[0].draw(g2);
//        buttons[1].draw(g2);
//        buttons[2].draw(g2);
//    }
//    public void update(){
//        for(MainMenu mb: buttons){
//            mb.update();
//        }
//    }
//    public void paintComponent(Graphics g){
//        for(MainMenu mb : buttons){
//            mb.draw(g);
//        }
//    }
//    public boolean isIn(MouseEvent e, MainMenu mb){
//        return mb.getBounds().contains(e.getX(), e.getY());
//    }
//    public void mousePressed(MouseEvent e){
//        for (MainMenu mb : buttons){
//            if(isIn(e, mb)){
//                mb.setMousePressed(true);
//                break;
//            }
//        }
//    }
//    public void mouseReleased(MouseEvent e){
//        for (MainMenu mb : buttons){
//            if(isIn(e, mb)){
//                if(mb.isMousePressed());
//                mb.applyGamestate();
//                break;
//            }
//        }
//        resetButtons();
//
//    }
//    private void resetButtons() {
//        for(MainMenu mb: buttons){
//            mb.resetBools();
//        }
//    }
//    public void mouseMoved(MouseEvent e){
//        for(MainMenu mb : buttons){
//            mb.setMouseOver(false);
//        }
//        for(MainMenu mb: buttons){
//            if(isIn(e, mb)){
//                mb.setMouseOver(true);
//            }
//        }
//    }
//}