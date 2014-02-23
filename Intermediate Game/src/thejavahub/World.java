package thejavahub;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class World {
	
    public Player player;
    public Mob mob;
    public mobCube cmob;
    public Rectangle[][] blocks;
    private Image[][] blockImg;
    public boolean[][] isSolid;
    //Block images
    private Image BG, BD, DB, BS, BW, BWC, SB, DOOR;
    public Image BASIC_SWORD, INVENTORY;
    
    private int xTiles, yTiles, tileSize;
    

    private List<Item> items;
    
    public World(int xTiles, int yTiles, int tileSize){
    	this.xTiles = xTiles;
    	this.yTiles = yTiles;
    	this.tileSize = tileSize;

    	
    	items = new ArrayList<Item>();
    	
        BG = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/tile_grass.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        DB = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/tile_sand.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        SB = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/tile_mudwall.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        BW = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/tile_water1.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        BWC = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/water_corner.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        SB = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/stonebrick_s.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        DB = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/floortile.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        DOOR = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/door.png").getImage().getScaledInstance(tileSize, tileSize, 0);
        INVENTORY = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/InventoryBox.gif").getImage();
        
        //Make an array that can contain 500 (invisible) rectangles, for collision detection, which is the area of the screen 25 width by 20 height
        blocks = new Rectangle[xTiles][yTiles];
        //Make an array that can contain 500 images(the pictures of tiles) which is the area of the screen 25 width by 20 height
        blockImg = new Image[xTiles][yTiles];
        isSolid = new boolean[xTiles][yTiles];
        player = new Player(items);
        cmob = new mobCube();
        player.inventory = new Item[7][7];
        player.inventory[0][0] = new Sword();
        loadArrays();
        
        Entity.isSolid = isSolid;
    	player.setEntityBlockXY();
    	cmob.setEntityBlockXY();
    }

    
    
    private void loadArrays(){
    	
    	//Basic Room Template
    	
    	Image[][] basicRoomImgs =  {{SB, SB, SB, DB, DB, SB, SB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{DB, DB, DB, DB, DB, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB}};

    	boolean[][] basicRoomSolid= {{true, true,true,false,false, true, true},
				   			 {true, false, false, false, false, false, false},
				   			 {true, false, false, false, false, false, false},
				   			 {false, false, false,false,false, false, false},
				   			 {true, false, false, false, false, false, false},
				   			 {true, false, false, false, false, false, false},
				   			{true, false, false, false, false, false, false}};
    	
    	//Test Room

    	Image[][] testRoomImgs =  {{SB, SB, SB, DB, DB, SB, SB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{DOOR, DB, DB, BW, BW, DB, DB},
    								{SB, DB, DB, BW, BW, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB},
    								{SB, DB, DB, DB, DB, DB, DB}};

    	boolean[][] testRoomSolid= {{true, true,true,false,false, true, true},
				   			 {true, false, false, false, false, false, false},
				   			 {true, false, false, false, false, false, false},
				   			 {false, false, false,true, true, false, false},
				   			 {true, false, false, true, true, false, false},
				   			 {true, false, false, false, false, false, false},
				   			{true, false, false, false, false, false, false}};
    	
    	//Diffrent size test room
    	Image[][] smallRoomImgs =  {{SB, SB, SB},
    								{SB, BW, SB},
    								{SB, SB, SB}};
    	

boolean[][] smallRoomSolid= {{true, true, true},
							 {true, true, true},
							 {true, true, true}};
    	
    	// Room generator 

		int roomSizeY = basicRoomImgs.length;
		int roomSizeX = basicRoomImgs[0].length;
		

		int smallroomSizeY = smallRoomImgs.length;
		int smallroomSizeX = smallRoomImgs[0].length;

        for(int y = 0; y < yTiles/roomSizeY; y++){
          for(int x = 0; x < xTiles/roomSizeX; x++){
        	  
        	  int refTileX = x * roomSizeX;
        	  int refTileY = y * roomSizeY;
        	  
        	  int smallrefTileX = x * smallroomSizeX;
        	  int smallrefTileY = y * smallroomSizeY;
        	 
        	  for (int tileX = 0; tileX < roomSizeX; tileX++) {
        		  for (int tileY = 0; tileY < roomSizeY; tileY++) {
        			  blockImg[refTileX + tileX][refTileY + tileY] = basicRoomImgs[tileY][tileX];
        			  isSolid[refTileX + tileX][refTileY + tileY] = basicRoomSolid[tileY][tileX];
        		  }
        	  }
        	  
        	  double randval = Math.random();
        	  
        	  
        	  // Test Room Generator
        	  if(randval < 0.5){
            	  
            	  for (int tileX = 0; tileX < roomSizeX; tileX++) {
            		  for (int tileY = 0; tileY < roomSizeY; tileY++) {
            			  blockImg[refTileX + tileX][refTileY + tileY] = testRoomImgs[tileY][tileX];
            			  isSolid[refTileX + tileX][refTileY + tileY] = testRoomSolid[tileY][tileX];
            		  }
            	  }
        	  }
          }
             
        }
        
    	  //Small Room Generator
        for(int y = 0; y < yTiles/smallroomSizeY; y++){
            for(int x = 0; x < xTiles/smallroomSizeX; x++){
          	  
          	  int smallrefTileX = x * smallroomSizeX;
          	  int smallrefTileY = y * smallroomSizeY;
        	  
        	  double randval = Math.random();
if(randval < 0.05){
      	  
      	  for (int tileX = 0; tileX < smallroomSizeX; tileX++) {
      		  for (int tileY = 0; tileY < smallroomSizeY; tileY++) {
      			  blockImg[smallrefTileX + tileX][smallrefTileY + tileY] = smallRoomImgs[tileY][tileX];
      			  isSolid[smallrefTileX + tileX][smallrefTileY + tileY] = smallRoomSolid[tileY][tileX];
      		  }
      	  }
  	  }
            }
        }
        for(int y = 0; y < yTiles; y++){
        	for(int x = 0; x < xTiles; x++){
        	if(y == yTiles - 1 || x == xTiles - 1 || y == 0 || x == 0){
  	  blockImg[x][y] = SB;
  	  isSolid[x][y] = true;
        	}
        	}
        }

        itemDrop(5);
        
        
    }
    

    

	private void itemDrop(int amount){
		
		Random rand = new Random();
		for (int i = 0; i < amount; i++) {
			int type = rand.nextInt(2);
			Item item = new Sword();
			switch(type){
			case 0: item = new Sword();
			break;
			case 1: item = new Spear();
			break;
			}
	
			int x = rand.nextInt(22)+1;
			int y = rand.nextInt(18)+1;
			
			while(isSolid[x][y] == true) {
				x = rand.nextInt(22)+1;
				y = rand.nextInt(18)+1;
			}
			
			item.blockX = x;
			item.blockY = y;
			
			items.add(item);
		}
	}
	
	public void moveEnemies() {
		cmob.think(player);
	}
    

	public void draw(Graphics g, boolean inState){

    	 for(int y = 0; y < yTiles; y++){
             for(int x = 0; x < xTiles; x++){

            	 g.drawImage(blockImg[x][y], x*tileSize, y*tileSize, null);
             }
    	 }

    	g.drawImage(player.image, player.blockX * tileSize, player.blockY * tileSize, null);
    	g.drawImage(cmob.image, cmob.blockX * tileSize, cmob.blockY * tileSize, null);

    	 
    	 for (Item item: items) {
    		 g.drawImage(item.itemImage, item.blockX * tileSize, item.blockY * tileSize, null);
    	 }

    	 if(player.melee && player.aState){
        	 g.setColor(new Color(1.0f, 0.0f, 0.0f, 0.5f));
        	 g.fillRect((player.blockX - 1) * tileSize, (player.blockY - 1) * tileSize, 3 * tileSize, 3 * tileSize);
    	 }
    	 
    	 if(player.lrange && player.aState){
        	 g.setColor(new Color(1.0f, 0.0f, 0.0f, 0.5f));
        	 g.fillRect(player.blockX * tileSize, (player.blockY - 2) * tileSize, 1 * tileSize, 5 * tileSize);
        	 g.fillRect((player.blockX - 2) * tileSize, player.blockY * tileSize, 5 * tileSize, 1 * tileSize);
    	 }
    	 
    	 if(inState == true){
    		 for (int x = 0; x < player.inventory.length; x++) {
    			 for (int y = 0; y < player.inventory.length; y++) {
    	             g.drawImage(INVENTORY, (1 + x)*32, (1 + y)*32, null);
    	             if (player.inventory[x][y] != null) {
    	            	 g.drawImage(player.inventory[x][y].itemImage, (1 + x)*32, (1 + y)*32, null);
    	             }
    	             if (x == player.invX && y == player.invY) {
    	            	 g.setColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
    	            	 g.fillRect((1 + x)*32, (1 + y)*32, 32, 32);
    	             }
    			 }
    		 }

    		 g.drawImage(INVENTORY, 9*32, 1*32, null);
    		 g.drawImage(INVENTORY, 9*32, 2*32, null);
    		 g.drawImage(INVENTORY, 9*32, 3*32, null);
    		 g.drawImage(INVENTORY, 9*32, 4*32, null);
    		 
    		 g.setColor(new Color(0.0f, 1.0f, 0.0f, 0.5f));
        	 g.fillRect(9*32, (1 + player.equipY)*32, 32, 32);

    		 if (player.head != null) {
    			 g.drawImage(player.head.itemImage, 9*32, 1*32, null);
    		 }
    		 if (player.larm != null) {
    			 g.drawImage(player.larm.itemImage, 9*32, 2*32, null);
    		 }
    		 if (player.rarm != null) {
    			 g.drawImage(player.rarm.itemImage, 9*32, 3*32, null);
    		 }
    		 if (player.torso != null) {
    			 g.drawImage(player.torso.itemImage, 9*32, 4*32, null);
    		 }
    		 

         	g.setColor(Color.WHITE);
         	g.setFont(new Font("Courier", Font.PLAIN, 32));
         	g.drawString("Head", 10*32, 2*32 - 5);
         	g.drawString("Left Arm", 10*32, 3*32- 5);
         	g.drawString("Right Arm", 10*32, 4*32 - 5);
         	g.drawString("Torso", 10*32, 5*32 - 5);
         }
    }
}
