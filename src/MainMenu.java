//import java.awt.*;
//import java.awt.image.BufferedImage;
//    public class MainMenu{
//        private int xPos, yPos, rowIndex, index;
//        private boolean mouseOver, mousePressed;
//        private Rectangle bounds;
//        private BufferedImage[] imgs;
//        private static main.GamePanel gp;
//        public static final float SCALE = 2f;
//        public final int btnWidth_Default = 140;
//        public final int btnheight_Default = 56;
//        public final int btnWidth = (int)(btnWidth_Default * SCALE);
//        public final int btnHeight = (int)(btnheight_Default * SCALE);
//        private int xOffsetCenter = btnWidth/2;
//        public MainMenu(int xPos, int yPos, int rowIndex, main.GamePanel gameState){
//            this.xPos = xPos;
//            this.yPos = yPos;
//            this.rowIndex = rowIndex;
//            gp = gameState;
//            loadImgs();
//            initBounds();
//        }
//        private void initBounds() {
//            bounds = new Rectangle(xPos-xOffsetCenter, yPos, btnWidth, btnHeight);
//        }
//        private void loadImgs(){
//            imgs = new BufferedImage[3];
//            BufferedImage temp = LoadSave.GetSpriteAtlas(MAIN_MENU);
//            for(int i = 0; i < imgs.length; i++){
//                imgs[i] = temp.getSubimage(i * btnWidth_Default, rowIndex * btnheight_Default,btnWidth_Default,btnheight_Default );
//            }
//        }
//        public void draw(Graphics g){
//            g.drawImage(imgs[index], xPos - xOffsetCenter, yPos, btnWidth, btnHeight, null);
//        }
//        public void update(){
//            index = 0;
//            if(mouseOver){
//                index = 1;
//            }
//            if(mousePressed){
//                index = 2;
//            }
//        }
//        public boolean isMouseOver() {
//            return mouseOver;
//        }
//        public void setMouseOver(boolean mouseOver) {
//            this.mouseOver = mouseOver;
//        }
//        public boolean isMousePressed() {
//            return mousePressed;
//        }
//        public void setMousePressed(boolean mousePressed) {
//            this.mousePressed = mousePressed;
//        }
//        public Rectangle getBounds(){
//            return bounds;
//        }
//        public void applyGamestate(){
//            gp.gameState = 0;
//        }
//        public void resetBools(){
//            mousePressed = false;
//            mouseOver = false;
//        }
//}
