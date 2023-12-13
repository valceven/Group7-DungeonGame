package main;

import javax.swing.*;

public class Main{
    public static void main(String[] args){

        JFrame app = new JFrame();
        app.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        app.setResizable(false);
        app.setTitle("DUNGEON GAME");
        TitleScreen titleScreen = new TitleScreen(app);
        app.add(titleScreen);
        app.pack();
        app.setSize(titleScreen.screenWidth, titleScreen.screenHeight);
        app.setLocationRelativeTo(null);
        app.setVisible(true);
    }
}