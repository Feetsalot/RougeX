package thejavahub;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Mob extends Entity {
	
	private boolean targeted;
	
	public Mob(int health, int damage, int armor, String imgPath){
		super(health, damage, armor, imgPath);
		
		targeted = false;
	}
	
	public void think(Player player){
		if(player.blockX <= blockX + 2 && player.blockX >= blockX - 2 && player.blockY <= blockY + 2 && player.blockY >= blockY - 2){
			targeted =  true;
			if(targeted){
				int newBlockX = blockX + Integer.signum(player.blockX - blockX);
				int newBlockY = blockY + Integer.signum(player.blockY - blockY);
				
				if (!isSolid[newBlockX][newBlockY] && (newBlockX != player.blockX || newBlockY != player.blockY)) {
					blockX = newBlockX;
					blockY = newBlockY;
				}
			}
		}
	}
}
