package thejavahub;


	public class Material {

		private int damage, durability, resistance;
		
		public Material(int dmg, int dur, int res) {
			damage = dmg;
			durability = dur;
			resistance = res;
		}

		public static Material newWood() {
			return new Material(1, 1, 1);
		}	

		public static Material newStone() {
			return new Material(2, 1, 1);
		}

		public static Material newSteel() {
			return new Material(3, 1, 1);
		}

		public static Material newMithril() {
			return new Material(4, 1, 1);
		}

		public int getDamage() {
			return damage;
		}

		public void setDamage(int damage) {
			this.damage = damage;
		}

		public int getDurability() {
			return durability;
		}

		public void setDurability(int durability) {
			this.durability = durability;
		}

		public int getResistance() {
			return resistance;
		}

		public void setResistance(int resistance) {
			this.resistance = resistance;
		}
		
	}



