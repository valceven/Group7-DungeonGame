package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
public class TitleScreen extends JPanel {
    private Image backgroundImage;
    private Image helpGuide;

    private boolean isCharacter = false;

    Font minecraft;
    private String sword = "/MainMenuScreen/buttons/sword1.png";
    private String bow = "/MainMenuScreen/buttons/bow1.png";
    private String staff = "/MainMenuScreen/buttons/staff1.png";
    private String play = "/MainMenuScreen/buttons/play1.png";
    private String option = "/MainMenuScreen/buttons/options1.png";
    private String help = "/MainMenuScreen/buttons/help1.png";
    final int originalTileSize = 16; // 16x16 tile
    final int scaleValue = 3;
    public final int tileSize = originalTileSize * scaleValue; // 48x48 tile
    public final int maxScreenColumn = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenColumn; // 768 px
    public final int screenHeight = tileSize * maxScreenRow; //576 px
    public static String characterType = "sword";
    static Sound sound = new Sound();


    public TitleScreen(JFrame window){
        try{
            InputStream is = getClass().getResourceAsStream("/MainMenuScreen/final_game_title5.png");
            if (is != null) {
                backgroundImage = ImageIO.read(is);
            } else {
                throw new IOException("Failed to load the background image");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream is = getClass().getResourceAsStream("/MainMenuScreen/Minecraftia-Regular.ttf");
            minecraft = Font.createFont(Font.TRUETYPE_FONT, is);
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(minecraft);
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        //Music
        sound.setFile(1);
        sound.play();
        sound.loop();

        JLabel swordLabel = new JLabel(new ImageIcon(getClass().getResource(sword)));
        JLabel bowLabel = new JLabel(new ImageIcon(getClass().getResource(bow)));
        JLabel staffLabel = new JLabel(new ImageIcon(getClass().getResource(staff)));
        JLabel playLabel = new JLabel(new ImageIcon(getClass().getResource(play)));
        JLabel optionLabel = new JLabel(new ImageIcon(getClass().getResource(option)));
        JLabel helpLabel = new JLabel(new ImageIcon(getClass().getResource(help)));

        JLabel weaponLabel = new JLabel(new ImageIcon(getClass().getResource("/MainMenuScreen/Weapon/default1.png")));

        swordLabel.setOpaque(false);
        bowLabel.setOpaque(false);
        staffLabel.setOpaque(false);
        playLabel.setOpaque(false);
        optionLabel.setOpaque(false);
        helpLabel.setOpaque(false);
        weaponLabel.setOpaque(false);

        swordLabel.addMouseListener(new DarkeningMouseListener(swordLabel, sword));
        bowLabel.addMouseListener(new DarkeningMouseListener(bowLabel, bow));
        staffLabel.addMouseListener(new DarkeningMouseListener(staffLabel, staff));
        playLabel.addMouseListener(new DarkeningMouseListener(playLabel, play));
        optionLabel.addMouseListener(new DarkeningMouseListener(optionLabel, option));
        helpLabel.addMouseListener(new DarkeningMouseListener(helpLabel, help));

        swordLabel.setPreferredSize(new Dimension(135, 42));
        bowLabel.setPreferredSize(new Dimension(135, 42));
        staffLabel.setPreferredSize(new Dimension(135, 42));
        playLabel.setPreferredSize(new Dimension(135, 42));
        optionLabel.setPreferredSize(new Dimension(135, 42));
        helpLabel.setPreferredSize(new Dimension(135, 42));

        setLayout(null);

        swordLabel.setBounds(177, 275, 135, 42);
        bowLabel.setBounds(315, 275, 135, 42);
        staffLabel.setBounds(454, 275, 135, 42);

        playLabel.setBounds(316,349, 135, 42);
        optionLabel.setBounds(316, 444, 135, 42);
        helpLabel.setBounds( 316, 398, 135, 42);

        weaponLabel.setBounds(336, 168, 96, 96);

        playLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                sound.setFile(0);
                sound.play();

                if(isCharacter ){
                    startGame(window);
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a weapon of your choice.", "Warning",JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        helpLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sound.setFile(0);
                sound.play();
                showHelpScreen(window);
            }
        });

        optionLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sound.setFile(0);
                sound.play();
                showOptionScreen(window);
            }
        });

        swordLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sound.setFile(0);
                sound.play();
                weaponLabel.setIcon(new ImageIcon(getClass().getResource("/MainMenuScreen/Weapon/swordWeapon.png")));
                characterType = "sword";
                isCharacter = true;
            }
        });

        bowLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                weaponLabel.setIcon(new ImageIcon(getClass().getResource("/MainMenuScreen/Weapon/bowweapon (2).png")));
                characterType = "bow";
                isCharacter = true;
                sound.setFile(0);
                sound.play();
            }
        });

        staffLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sound.setFile(0);
                sound.play();
                weaponLabel.setIcon(new ImageIcon(getClass().getResource("/MainMenuScreen/Weapon/staffweapon.png")));
                characterType = "staff";
                isCharacter = true;
            }
        });


        add(swordLabel);
        add(bowLabel);
        add(staffLabel);
        add(playLabel);
        add(optionLabel);
        add(helpLabel);
        add(weaponLabel);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, 768, 576, this);
        }
    }

    public void startGame(JFrame window){
        window.getContentPane().removeAll();
        GamePanel gp = new GamePanel();
        window.add(gp);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gp.startGameThread();

        gp.requestFocusInWindow();
    }

    public void showHelpScreen(JFrame parent){

        JFrame helpFrame = new JFrame();
        helpFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        helpFrame.setSize(300, 400);
        helpFrame.setLocationRelativeTo(parent);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setOpaque(false);

        JLabel titleLabel = new JLabel("Dungeon Crawl Guide");
        titleLabel.setFont(new Font(minecraft.getName(), Font.BOLD, 20));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setOpaque(false);

        JTextArea helpText = new JTextArea("Directions:\n\n" +
                "1. Player Controls:\n" +
                "Move Left: Press 'A' key.\n" +
                "Move Right: Press 'D' key.\n" +
                "Move Up: Press 'W' key.\n" +
                "Move Down: Press 'S' key.\n\n" +
                "Attack: Press 'Space' key.\n\n" +
                "2. Exploring the Dungeon:\n" +
                "The dungeon is filled with rooms and corridors.\n" +
                "Use the arrow keys to navigate through the dungeon.\n\n" +
                "3. Objective:\n" +
                "The main goal is to find the boss room hidden\n" +
                "somewhere in the dungeon.\n\n" +
                "TIP: Avoid the enemy's attack.\\n\\n\" +"
        );
        helpText.setEditable(false);
        helpText.setOpaque(false);
        helpText.setFont(new Font(minecraft.getName(), Font.PLAIN, 14));

        JScrollPane scrollPane = new JScrollPane(helpText);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());

        JLabel closeButton = new JLabel(new ImageIcon(getClass().getResource("/MainMenuScreen/help/Pause_1.png")));
        closeButton.setPreferredSize(new Dimension(60,18));

        closeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(closeButton);
                frame.dispose();
            }
        });

        closeButton.addMouseListener(new DarkeningMouseListener(closeButton, "/MainMenuScreen/help/Pause_1.png"));

        contentPanel.add(titleLabel);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(scrollPane);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(closeButton);

        helpFrame.add(contentPanel);
        helpFrame.setVisible(true);
    }

    public void showOptionScreen(JFrame window){

    }

    public void changeWeapon(JLabel label, String imageSource){

    }

    private static class DarkeningMouseListener extends MouseAdapter{

        private final JLabel label;
        private final ImageIcon pressedIcon;

        private final ImageIcon hoverIcon;
        private final ImageIcon originalIcon;
        public DarkeningMouseListener (JLabel label, String imagePath){
            this.label = label;
            this.originalIcon = new ImageIcon(getClass().getResource(imagePath));
            this.pressedIcon = createPressedIcon(imagePath);
            this.hoverIcon = createHoverIcon(imagePath);

        }

        @Override
        public void mousePressed(MouseEvent e) {
            label.setIcon(pressedIcon);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            label.setIcon(originalIcon);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            label.setIcon(hoverIcon);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            label.setIcon(originalIcon);
        }

        private ImageIcon createHoverIcon(String imagePath){
            switch (imagePath){
                case "/MainMenuScreen/buttons/sword1.png":
                    ImageIcon sword = new ImageIcon(getClass().getResource("/MainMenuScreen/hoverButtons/sword2.png"));
                    return sword;
                case "/MainMenuScreen/buttons/bow1.png":
                    ImageIcon bow = new ImageIcon(getClass().getResource("/MainMenuScreen/hoverButtons/bow2.png"));
                    return bow;
                case "/MainMenuScreen/buttons/staff1.png":
                    ImageIcon staff = new ImageIcon(getClass().getResource("/MainMenuScreen/hoverButtons/staff2.png"));
                    return staff;
                case "/MainMenuScreen/buttons/play1.png":
                    ImageIcon play = new ImageIcon(getClass().getResource("/MainMenuScreen/hoverButtons/play2.png"));
                    return play;
                case "/MainMenuScreen/buttons/options1.png":
                    ImageIcon option = new ImageIcon(getClass().getResource("/MainMenuScreen/hoverButtons/options2.png"));
                    return option;
                case "/MainMenuScreen/buttons/help1.png":
                    ImageIcon help = new ImageIcon(getClass().getResource("/MainMenuScreen/hoverButtons/help2.png"));
                    return help;
                case  "/MainMenuScreen/help/Pause_1.png":
                    ImageIcon close = new ImageIcon(getClass().getResource("/MainMenuScreen/help/Pause_2.png"));
                    return close;
                default:
                    return null;

            }
        }

        private ImageIcon createPressedIcon(String imagePath){

            switch (imagePath){
                case "/MainMenuScreen/buttons/sword1.png":
                    ImageIcon sword = new ImageIcon(getClass().getResource("/MainMenuScreen/pressedButtons/sword3.png"));
                    return sword;
                case "/MainMenuScreen/buttons/bow1.png":
                    ImageIcon bow = new ImageIcon(getClass().getResource("/MainMenuScreen/pressedButtons/bow3.png"));
                    return bow;
                case "/MainMenuScreen/buttons/staff1.png":
                    ImageIcon staff = new ImageIcon(getClass().getResource("/MainMenuScreen/pressedButtons/staff3.png"));
                    return staff;
                case "/MainMenuScreen/buttons/play1.png":
                    ImageIcon play = new ImageIcon(getClass().getResource("/MainMenuScreen/pressedButtons/play3.png"));
                    return play;
                case "/MainMenuScreen/buttons/options1.png":
                    ImageIcon option = new ImageIcon(getClass().getResource("/MainMenuScreen/pressedButtons/options3.png"));
                    return option;
                case "/MainMenuScreen/buttons/help1.png":
                    ImageIcon help = new ImageIcon(getClass().getResource("/MainMenuScreen/pressedButtons/help3.png"));
                    return help;
                case  "/MainMenuScreen/help/Pause_1.png":
                    ImageIcon close = new ImageIcon(getClass().getResource("/MainMenuScreen/help/Pause_3.png"));
                    return close;
                default:
                    return null;
            }
        }
    }
}
