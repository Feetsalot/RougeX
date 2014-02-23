package thejavahub;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;
	
public class Item{
	
	public Image BASIC_SWORD = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/Sword.png").getImage().getScaledInstance(32, 32, 0);
	public Image BASIC_SPEAR = new ImageIcon("C:/Users/Owner/Desktop/eclipse/Intermediate Game/src/thejavahub/images/Spear.png").getImage().getScaledInstance(32, 32, 0);
	
	//public Rectangle itemRect;
	protected boolean[][] isSolid;
	public int blockX, blockY;
	
	public Image itemImage;
	
	//Item properties
	int itemID;
	int range;
	int damage, durability, resistance;
	
	int equipArea;
	
	
	public Item(int itemID, Material material, int range, boolean consumable, boolean se){
		

		this.damage = material.getDamage();
		this.durability = material.getDurability();
		this.resistance = material.getResistance();
		
		if (itemID == 1) { // Checking the ItemID
			this.range = range;
			this.itemImage = BASIC_SWORD; // Setting the item Image
			equipArea = 2; // Which part of the body this weapon is equipped to
		}
		else if (itemID == 2){
			this.range = range;
			this.itemImage = BASIC_SPEAR;
			equipArea = 2;
		}
	}
	
	//Status effects
	public void burn(){
		
	}
	public void freeze(){
		
	}
	public void posion(){
		
	}
	public void heal(){
		
	}
	

}