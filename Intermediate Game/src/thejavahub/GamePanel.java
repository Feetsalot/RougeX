package thejavahub;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable {
    //Double buffering
    private Image dbImage;
    private Graphics dbg;
    //JPanel variable
    int GWIDTH, GHEIGHT;
    Dimension gameDim;
    //Game variables
    private Thread game;
    private volatile boolean running = false;
    private long period = 6*1000000; //ms -> nano
    private static final int DELAYS_BEFORE_YEILD = 10;
    //Game Objects
    World world;
    Player p1;
    public static boolean gameState = true;
    public boolean inState = false;
    private int x, y;

    
    public GamePanel(int xTiles, int yTiles, int tileSize){
    	
        world = new World(xTiles, yTiles, tileSize);
        p1 = world.player;
        
        GWIDTH = xTiles * tileSize;
        GHEIGHT = yTiles * tileSize;
        gameDim = new Dimension(GWIDTH, GHEIGHT);
        
        setPreferredSize(gameDim);
        setBackground(Color.WHITE);
        
        setFocusable(true);
        requestFocusInWindow();
        
        if(gameState == true){
      //Handle all key inputs from user
        addKeyListener(new KeyAdapter(){
        	
			@Override
            public void keyPressed(KeyEvent e){
            	System.out.println("Key Released: " + e);
            }
            @Override
            public void keyReleased(KeyEvent e){
                System.out.println("Key Released: " + e);
                	
                if (e.getKeyCode() == e.VK_W && gameState == true){

                	if(inState == true){
                		p1.moveInv(0);
                	}
                	else{
                		p1.move(3);
                		world.moveEnemies();
                		
                	}
                }
                else if (e.getKeyCode() == e.VK_S && gameState == true){
                	
                	if(inState == true){
                		p1.moveInv(1);
                		
                	}
                	else{
                		p1.move(2);
                		world.moveEnemies();
                		
                	}
                }
                else if (e.getKeyCode() == e.VK_A && gameState == true){
                	if(inState == true){
                		p1.moveInv(3);
                		
                	}
                	else{
                		p1.move(1);
                		world.moveEnemies();
                	
                	}
                }
                else if (e.getKeyCode() == e.VK_D && gameState == true){
                	if(inState == true){
                		p1.moveInv(2);
                		
                	}
                	else{
                		p1.move(0);
                		world.moveEnemies();
                		
                	}
                }
                else if (e.getKeyCode() == e.VK_U && gameState && inState){
                	p1.unequipItem();
                }
                else if (e.getKeyCode() == e.VK_R && gameState && inState && p1.aState == false) {
                	p1.dropItem();
                }
                else if (e.getKeyCode() == e.VK_E && gameState && inState) {
                	p1.equipItem();
                }
                else if (e.getKeyCode() == e.VK_UP && gameState && inState) {
                	p1.moveEquip(0);
                }
                else if (e.getKeyCode() == e.VK_DOWN && gameState && inState) {
                	p1.moveEquip(1);
                }
                else if (e.getKeyCode() == e.VK_X && gameState){
                	p1.aState = !p1.aState;
                	p1.attack(p1.rarm);

                }
                else if (e.getKeyCode() == e.VK_I){
                	inState = !inState; // Toggles the inventory
                	}
                
                else if (e.getKeyCode() == e.VK_P){
                	gameState = !gameState; // Toggles the pause Screen
                }
                else if (e.getKeyCode() == e.VK_ESCAPE){
                	System.exit(0);
                }
            }
            
            @Override
            public void keyTyped(KeyEvent e){
            	System.out.println("Key Released: " + e);
            }
        });
        }
        
    }


    
    public void run(){
    	long beforeTime, afterTime, diff, sleepTime, overSleepTime = 0;
        int delays = 0;
    	while(running){
        	beforeTime = System.nanoTime();
        	
            gameUpdate();
            gameRender();
            paintScreen();
            
            afterTime = System.nanoTime();
            diff = afterTime - beforeTime;
        	sleepTime = (period - diff) - overSleepTime;
           // If the sleep time is between 0 and the period sleep
        	if(sleepTime < period && sleepTime > 0){
            	try {
					game.sleep(sleepTime / 1000000L);
					overSleepTime = 0;
				} catch (InterruptedException e) {
					Logger.getLogger(GamePanel.class.getName()).log(Level.SEVERE, null);
				}
            }
        	// The diff was greater than the period
        	else if(diff > period){
        		overSleepTime = diff - period;
        	}
        	// Accumulate the amount of delays and evantuall yeild
        	else if(++delays >= DELAYS_BEFORE_YEILD){
        		game.yield();
        		delays = 0;
        		overSleepTime = 0;
        	}
        	// The loop took less time then expectted but we need to make up for the over sleep time
        	else{
        		overSleepTime = 0;
        	}
        	//Print out game stats
        	/*log(
        		"beforeTime:	" + beforeTime + "\n" +
        		"afterTime:		" + afterTime + "\n" +
        		"diff:			" + diff + "\n" +
        		"SleepTime:		" + sleepTime / 1000000L + "\n" +
        		"delays:		" + delays +"\n"
        		);*/
        }
    }
    
    private void gameUpdate(){
        if(running && game != null){
            
        }
    }
    
    private void gameRender(){
        if(dbImage == null){ // Create the buffer
            dbImage = createImage(GWIDTH, GHEIGHT);
            if(dbImage == null){
                System.err.println("dbImage is still null!");
                return;
            }else{
                dbg = dbImage.getGraphics();
            }
        }
        //Clear the screen
        dbg.setColor(Color.WHITE);
        dbg.fillRect(0, 0, GWIDTH, GHEIGHT);
        //Draw Game elements
        draw(dbg);
    }
    
    /* Draw all game content in this method */
    public void draw(Graphics g){
        world.draw(g, inState);
        
        
        if (gameState != true){
        	g.setColor(Color.BLUE);
        	g.setFont(new Font("Courier", Font.PLAIN, 50));
        	g.drawString("Paused", 5*32, 5*32);
        	g.setColor(new Color(0.0f, 0.0f, 0.0f, 0.5f));
        	g.fillRect(0, 0, GWIDTH, GHEIGHT);
        }
    }
    
    private void paintScreen(){
        Graphics g;
        try{
            g = this.getGraphics();
            if(dbImage != null && g != null){
                g.drawImage(dbImage, 0, 0, null);
            }
            Toolkit.getDefaultToolkit().sync(); //For some operating systems
            g.dispose();
        }catch(Exception e){
            System.err.println(e);
        }
    }
    
    public void addNotify(){
        super.addNotify();
        startGame();
    }
    
    private void startGame(){
        if(game == null || !running){
            game = new Thread(this);
            game.start();
            running = true;
        }
    }
    
    public void stopGame(){
        if(running){
            running = false;
        }
    }
    
    private void log(String s){
        System.out.println(s);
    }
}
