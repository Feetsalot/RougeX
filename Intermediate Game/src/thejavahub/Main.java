package thejavahub;

import javax.swing.JFrame;

public class Main extends JFrame {
    
    GamePanel gp;
    
    public Main(){
    	int xTiles = 36;
    	int yTiles = 22;
    	int tileSize = 32;
        gp = new GamePanel(xTiles, yTiles, tileSize);
        setSize(xTiles*32+6, yTiles*32+25);
        setTitle("Rogue X");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setResizable(false);
        add(gp);
    }
    
    public static void main(String[] args) {
        Main m = new Main();
    }
    
}
