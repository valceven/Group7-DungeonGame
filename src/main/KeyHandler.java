package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;

    @Override
    public void keyTyped(KeyEvent e) {

    }
    @Override
    public void keyPressed(KeyEvent e) {

        int action = e.getKeyCode();

        if(action == KeyEvent.VK_W){
            up = true;
        }
        if(action == KeyEvent.VK_S){
            down = true;
        }
        if(action == KeyEvent.VK_A){
            left = true;
        }
        if(action == KeyEvent.VK_D){
            right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int action = e.getKeyCode();

        if(action == KeyEvent.VK_W){
            up = false;
        }
        if(action == KeyEvent.VK_S){
            down = false;
        }
        if(action == KeyEvent.VK_A){
            left = false;
        }
        if(action == KeyEvent.VK_D){
            right = false;
        }
    }
}
