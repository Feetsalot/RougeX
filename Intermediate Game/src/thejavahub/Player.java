package thejavahub;

import java.awt.*;
import java.util.List;
import java.util.Random;

import javax.swing.ImageIcon;

public class Player extends Entity{
	
	public Rectangle playerRect;
	public Image playerImg;
	private int blockX, blockY;
	
	public int invX, invY, equipY;
	
	private boolean[][] isSolid;
	public Item[][] inventory;
	
	public Item head, torso, larm, rarm, item;
	
	public boolean equipped;
	
	public boolean melee, lrange;
	
	public boolean aState = true;
	
	public static int plevel = 1;
	
	private List<Item> items;
	
	public Player(boolean[][]isSolid, List<Item> items){
		super(10*plevel, 1+plevel, 2+plevel); //TODO: Change so that items effect player's stats
		playerImg = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/player.png").getImage().getScaledInstance(32, 32, 0);
        this.isSolid = isSolid;
        this.items = items;
		invX = 0;
		invY = 0;
		equipY = 0;
		rarm = new Sword();
	}
	public void pickPosition(){



		Random rand = new Random();
		int x = rand.nextInt(22)+1;
		int y = rand.nextInt(18)+1;
		
		while(isSolid[x][y] == true) {
			x = rand.nextInt(22)+1;
			y = rand.nextInt(18)+1;
		}
		playerRect = new Rectangle(x*32, y*32, 16, 16);
		blockX = x;
		blockY = y;
	}
	
	public void moveEquip(int dir) {

    	System.out.println("hi");
		switch (dir) {
		case 0:
			if (equipY > 0) {
				equipY--;

	        	System.out.println("hello" + equipY);
			}
			break;
		case 1:
			if (equipY < 3) {
				equipY++;
			}
			break;
		}
	}
	
    public void moveInv(int direction){
    	switch(direction){
	    	case 0:
	    		if(invY > 0)
	    		invY--;
	    	break;
	    	case 1:
	    		if(invY < 6)
	    		invY++;
	    	break;
	    	case 2:
	    		if(invX < 6)
	    		invX++;
	    	break;
	    	case 3:
	    		if(invX > 0)
	    		invX--;
	    	break;
    	}
    	
    }

    public void dropItem() {
    	if (inventory[invX][invY] != null) {
    		Item item = inventory[invX][invY];
    		inventory[invX][invY] = null;
    		items.add(item);
    		item.blockX = blockX;
    		item.blockY = blockY;
    	}
    }

    public void equipItem() {
    	if (inventory[invX][invY] != null) {
    		Item item = inventory[invX][invY];
    		inventory[invX][invY] = null;
    		
    		switch(item.equipArea) {
    		case 0:
    			if (head != null) {
    				inventory[invX][invY] = head;
        			head = null;
    			}
    			head = item;
    			break;

    		case 1:
    			if (larm != null) {
    				inventory[invX][invY] = larm;
        			larm = null;
    			}
    			larm = item;
    			break;

    		case 2:
    			if (rarm != null) {
    				inventory[invX][invY] = rarm;
        			rarm = null;
    			}
    			rarm = item;
    			break;

    		case 3:
    			if (torso != null) {
    				inventory[invX][invY] = torso;
        			torso = null;
    			}
    			torso = item;
    			break;
    		
    		}
    	}
    }
    
    public void unequipItem() {
    	switch(equipY) {
    		case 0:
    			if (head != null) {
    				addItemToInv(head);
        			head = null;
    			}
    			break;

    		case 1:
    			if (larm != null) {
    				addItemToInv(larm);
    				larm = null;
    			}
    			break;

    		case 2:
    			if (rarm != null) {
    				addItemToInv(rarm);
    				rarm = null;
    			}
    			break;

    		case 3:
    			if (torso != null) {
    				addItemToInv(torso);
    				torso = null;
    			}
    			break;
    		
    		
    	}
    }
	
	public void move(int direction){
		switch(direction){
		case 0:
			if (isSolid[blockX+1][blockY] == false)
			{
			playerRect.x += 32; //Right
			blockX += 1;
			}
		break;
		case 1:
			if (isSolid[blockX-1][blockY] == false)
			{
			playerRect.x -= 32; //Left
			blockX -= 1;
			}
		break;
		case 2: 
			if (isSolid[blockX][blockY+1] == false)
			{
			playerRect.y += 32; //Down
			blockY += 1;
			}
		break;
		case 3: 
			if (isSolid[blockX][blockY-1] == false)
			{
			playerRect.y -= 32; //Up
			blockY -= 1;
			}
			
		}
		
		// Check if there is an item to pick up.
		for (Item item: items) {
			if (item.blockX == blockX && item.blockY == blockY) {
				items.remove(item);
				
				addItemToInv(item);
				
			}
		}
	}
	
	public void addItemToInv(Item item) {
		inventorySearch:
			for (int invX = 0; invX < inventory.length; invX++) {
				for (int invY = 0; invY < inventory[0].length; invY++) {
					if (inventory[invX][invY] == null) {
						inventory[invX][invY] = item;
						break inventorySearch;
					}
				}
			}
	}
	
	public void attack(Item weapon){
		if(aState == true){
		if(weapon.range == 1){
			melee = true;
			lrange = false;
		}
		else if (weapon.range > 1){
			melee = false;
			lrange = true;
		}
		else{
			melee = false;
			lrange = false;
		}
	}
  }

}