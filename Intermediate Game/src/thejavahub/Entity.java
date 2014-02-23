package thejavahub;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Entity {
	
	public int blockX, blockY;
	public int health, damage, armor;
	public Image image;
	
	public static boolean[][] isSolid;
	
	public Entity(int health, int damage, int armor, String imagePath){
		this.health = health;
		this.damage = damage;
		this.armor = armor;
		this.image = new ImageIcon(imagePath).getImage().getScaledInstance(32, 32, 0);
	}
	
	public void setEntityBlockXY() {
		
		Random rand = new Random();
		int x = rand.nextInt(22)+1;
		int y = rand.nextInt(18)+1;
		
		while(isSolid[x][y]) {
			x = rand.nextInt(22)+1;
			y = rand.nextInt(18)+1;
		}

		blockX = x;
		blockY = y;
	}

}
